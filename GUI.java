import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUI extends JPanel implements MouseListener {
    gameLogig othello;
    public int BlackPoints = 0;
    public int WhitePoints = 0;
    gameLogig.celleStatus[][] board = new gameLogig.celleStatus[8][8];
    public boolean spilSlut = false;
    public GUI(){
        othello = new gameLogig();
        othello.setBoard();

        JFrame vindue = new JFrame("Othello");
        vindue.setSize(525,550);
        vindue.setLocation(100,100);
        vindue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vindue.add(this);
        vindue.setVisible(true);
        vindue.setResizable(true);
        vindue.addMouseListener(this);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        board = gameLogig.board;
        BlackPoints = gameLogig.sort;
        WhitePoints = gameLogig.hvid;


        g.drawString("Black Points: " + BlackPoints,60,25);
        g.drawString("White Points: " + WhitePoints,375,25);

        if (!spilSlut) {
            if (!gameLogig.erHvid) {
                g.drawString("Black turn ", 217, 25);
            } else {
                g.drawString("White turn ", 217, 25);
            }
        } else {
            if (BlackPoints > WhitePoints) {
                g.drawString("Black wins ", 217, 25);
            }  if ((BlackPoints < WhitePoints)) {
                g.drawString("White wins ", 217, 25);
            }  if (BlackPoints == WhitePoints) {
                g.drawString("Draw ", 217, 25);
            }
        }


        g.setColor(new Color(0,153,0));
        g.fillRect(50, 50, 400, 400);

        g.setColor(Color.BLACK);
        for (int i = 0; i < 8; i++) {
            g.drawString(String.valueOf(i),50 * i + 70,45);
            g.drawString(String.valueOf(i),30,50 * i + 75);
            for (int j = 0; j < 8; j++) {
                g.drawRect(50 + 50 * j, 50 + 50 * i, 50, 50);
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == gameLogig.celleStatus.kanP) {
                    g.setColor(Color.BLACK);
                    g.drawOval(50 + 50 * j, 50 + 50 * i, 50, 50);
                } else if (board[i][j] == gameLogig.celleStatus.Sort) {
                    g.setColor(Color.BLACK);
                    g.fillOval(50 + 50 * j, 50 + 50 * i, 50, 50);
                } else if (board[i][j] == gameLogig.celleStatus.Hvid) {
                    g.setColor(Color.WHITE);
                    g.fillOval(50 + 50 * j, 50 + 50 * i, 50, 50);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int Y = e.getY();
        int y = (Y - 79) / 50;
        System.out.println(y);

        int X = e.getX();
        int x = (X - 55) / 50;
        System.out.println(x);

        spilSlut = othello.spil(y,x);
        System.out.println(spilSlut);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}