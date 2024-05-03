import java.util.Arrays;

public class gameLogig {
    public static int sort;
    public static int hvid;
    public int antalKanPlaseres;
    public static boolean spilSlut = false;
    celleStatus opp;
    celleStatus Spiller;
    public static Boolean erHvid = false;
    private Boolean pladsere = false;
    public static celleStatus[][] board = new celleStatus[8][8];
    public enum celleStatus{
        Hvid,
        Sort,
        Tom_,
        kanP
    }



    public void setBoard(){
        //Set all cells to empty
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board[x][y] = celleStatus.Tom_;
            }
        }

        board[4][3] = celleStatus.Sort;
        board[3][4] = celleStatus.Sort;
        board[3][3] = celleStatus.Hvid;
        board[4][4] = celleStatus.Hvid;
        erHvid = true;
        erHvid = tur();
        kanPlaseres();

    }
    public boolean tur(){
        if (erHvid) {
            Spiller = celleStatus.Sort;
            opp = celleStatus.Hvid;
            return false;
        }
        if (!erHvid) {
            Spiller = celleStatus.Hvid;
            opp = celleStatus.Sort;
            return true;
        }
        return false;
    }
    public boolean erValid(int row, int col){
        if (checkDirection(row, col, 0, 1, opp) ||
                checkDirection(row, col, 1, 0, opp) ||
                checkDirection(row, col, 1, 1, opp) ||
                checkDirection(row, col, 1, -1, opp) ||
                checkDirection(row, col, 0, -1, opp) ||
                checkDirection(row, col, -1, 0, opp) ||
                checkDirection(row, col, -1, -1, opp) ||
                checkDirection(row, col, -1, 1, opp)) {
            return true;
        }
        return false;
    }
    public void doMove(int row, int col){
        if (checkDirection(row, col, 0, 1, opp)) {
            gørMove(row, col, row, col + 1, 0, 1, opp);
        }
        if (checkDirection(row, col, 1, 0, opp)) {
            gørMove(row, col, row + 1, col, 1, 0, opp);
        }
        if (checkDirection(row, col, 1, 1, opp)) {
            gørMove(row, col, row + 1, col + 1, 1, 1, opp);
        }
        if (checkDirection(row, col, 1, -1, opp)) {
            gørMove(row, col, row + 1, col - 1, 1, -1, opp);
        }
        if (checkDirection(row, col, 0, -1, opp)) {
            gørMove(row, col, row, col - 1, 0, -1, opp);
        }
        if (checkDirection(row, col, -1, 0, opp)) {
            gørMove(row, col, row - 1, col, -1, 0, opp);
        }
        if (checkDirection(row, col, -1, -1, opp)) {
            gørMove(row, col, row - 1, col - 1, -1, -1, opp);
        }
        if (checkDirection(row, col, -1, 1, opp)) {
            gørMove(row, col, row - 1, col + 1, -1, 1, opp);
        }
    }
    public void kanPlaseres(){
        antalKanPlaseres =0;
        pladsere = false;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == celleStatus.kanP) {
                    board[row][col] = celleStatus.Tom_;
                }
                if (erValid(row, col) && erTom(row, col)) {
                    board[row][col] = celleStatus.kanP;
                    antalKanPlaseres += 1;
                }
            }
        }
        printBoard();
    }
    private boolean checkDirection(int row, int col, int rowChange, int colChange, celleStatus opp) {
        // Tjekker en bestemt retning for modstanderens brikker og kontrollerer om der er en linje af brikker, der kan vendes
        int currentRow = row + rowChange;
        int currentCol = col + colChange;
        boolean foundOpponent = false;

        while (currentRow >= 0 && currentRow < 8 && currentCol >= 0 && currentCol < 8) {
            if (board[currentRow][currentCol] == opp) {
                foundOpponent = true;
            } else if (board[currentRow][currentCol] == Spiller && foundOpponent) {
                return true;
            } else {
                break;
            }
            currentRow += rowChange;
            currentCol += colChange;
        }
        return false;
    }
    public void gørMove(int rowStart, int colStart, int rowEnd, int colEnd, int rowChange, int colChange, celleStatus opp) {
        int currentRow = rowStart + rowChange;
        int currentCol = colStart + colChange;

        while (currentRow != rowEnd || currentCol != colEnd) {
            if (board[currentRow][currentCol] == opp) {
                board[currentRow][currentCol] = Spiller;
            }
            currentRow += rowChange;
            currentCol += colChange;
        }
        board[rowEnd][colEnd] = Spiller;
    }
    public boolean erTom(int x, int y) {
        return board[x][y] == celleStatus.Tom_;
    }

    public boolean erkanPlaseres(int x, int y) {return board[x][y] == celleStatus.kanP;}

    public boolean spil(int x, int y) {

        if (erkanPlaseres(x,y)) {
            board[x][y] = celleStatus.Tom_;
            pladsere = true;
            if (erValid(x,y))
            {
                doMove(x,y);
                board[x][y] = Spiller;
                erHvid = tur();

            }

            kanPlaseres();
        }
        return antalKanPlaseres == 0;
    }
    public void printBoard(){

        Score();
        System.out.println("       0,    1,    2,    3,    4,    5,    6,    7");
        for (int row = 0; 8 > row; row++) {
            System.out.println(row+": "+ Arrays.toString(board[row]));
        }
    }

    public boolean Score(){
        sort = 0;
        hvid = 0;
        for (int row = 0; row< board.length;row++){
            for (int col = 0; col < board[row].length;col++){
                if(board[row][col] == celleStatus.Hvid){
                    hvid++;
                }
                if(board[row][col] == celleStatus.Sort){
                    sort++;
                }
            }
        }
        if (antalKanPlaseres > 0) {
            System.out.println("Spiller: " + Spiller + "\nSort: " + sort + "\nHvid: " + hvid);
        } else {
            if (sort > hvid) {
                System.out.println("Sort vandt");
                System.out.println("Spiller: " + Spiller + "\nSort: " + sort + "\nHvid: " + hvid);
                return true;
            } else {
                System.out.println("Hvid vandt");
                System.out.println("Spiller: " + Spiller + "\nSort: " + sort + "\nHvid: " + hvid);
                return true;
            }
        }
        return false;
    }
}
