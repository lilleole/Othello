import java.util.Scanner;

public class terminalSpil {
    int x;
    int y;
    Scanner spil = new Scanner(System.in);
    gameLogig othello = new gameLogig();
    public void Spil() {
        othello.setBoard();
        while(!gameLogig.spilSlut)
        {
            while (true) {
                System.out.println("Sæt ned værdi, imellem 0-7: ");
                y = spil.nextInt();
                if (y > 8 || y < 0) {
                    System.out.println("Invalid værdi");
                } else {
                    break;
                }
            }
            while (true) {
                System.out.println("Sæt højre værdi, imellem 0-7: ");
                x = spil.nextInt();
                if (x > 8 || x < 0) {
                    System.out.println("Invalid værdi");
                } else {
                    break;
                }
            }
            if (othello.erkanPlaseres(y,x)) {
                othello.spil(y, x);
            } else {
                System.out.println("ugyldig plasering");
            }
        }
    }
}




