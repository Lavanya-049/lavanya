/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lavan
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.*;

public class SimpleFifteenPuzzleGame extends JPanel {

    private int gridSize;
    private int numTiles;
    private int[] tiles;
    private int tileSize;
    private int blankPos;
    private int margin;
    private boolean gameOver;
    private int moves;
    private Timer timer;
    private int elapsedTime;
    
    private static final Random random = new Random();

    public SimpleFifteenPuzzleGame(int gridSize, int dim, int margin) {
        this.gridSize = gridSize;
        this.margin = margin;

        this.numTiles = gridSize * gridSize - 1;
        this.tiles = new int[gridSize * gridSize];
        this.tileSize =(dim - 2 * margin) / gridSize;

        setPreferredSize(new Dimension(dim, dim + margin));
        setBackground(Color.WHITE);
        setFont(new Font("SansSerif", Font.BOLD, 30));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!gameOver) {
                    int ex = e.getX() - margin;
                    int ey = e.getY() - margin;
                    if (ex < 0 || ex > tileSize * gridSize || ey < 0 || ey > tileSize * gridSize) {
                        return;
                    }
                    int row = ey / tileSize;
                    int col = ex / tileSize;
                    moveTile(row, col);
                    repaint();
                }
            }
        });
        startNewGame();
    }

    private void startNewGame() {
        resetTiles();
        shuffleTiles();
        gameOver = false;
        moves = 0;
        elapsedTime = 0;

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime++;
                repaint();
            }
        });
        timer.start();
    }

    private void resetTiles() {
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = (i + 1) % tiles.length;
        }
        blankPos = tiles.length - 1;
    }

    private void shuffleTiles() {
        for (int i = 0; i < numTiles; i++) {
            int randomPos = random.nextInt(i + 1);
            int temp = tiles[i];
            tiles[i] = tiles[randomPos];
            tiles[randomPos] = temp;
        }
    }

    private void moveTile(int row, int col) {
        int pos = row * gridSize + col;
        int blankRow = blankPos / gridSize;
        int blankCol = blankPos % gridSize;

        if ((row == blankRow && Math.abs(col - blankCol) == 1) ||
            (col == blankCol && Math.abs(row - blankRow) == 1)) {
            tiles[blankPos] = tiles[pos];
            tiles[pos] = 0;
            blankPos = pos;
            moves++;
            gameOver = checkWin();
            if (gameOver) {
                timer.stop();
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < numTiles; i++) {
            if (tiles[i] != i + 1) {
                return false;
            }
        }
        return true;
    }

    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    for (int i = 0; i < tiles.length; i++) {
        int row = i / gridSize;
        int col = i % gridSize;
        int x = margin + col * tileSize;
        int y = margin + row * tileSize;

        if (tiles[i] == 0) continue;

        g2d.setColor(new Color(70, 130, 180));  // Smoother, richer blue
        g2d.fillRoundRect(x, y, tileSize, tileSize, 15, 15);  // Rounded corners

        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));  // Thicker tile borders
        g2d.drawRoundRect(x, y, tileSize, tileSize, 15, 15);  // Rounded borders

        g2d.setColor(Color.WHITE);
        g2d.drawString(String.valueOf(tiles[i]), x + tileSize / 2 - 10, y + tileSize / 2 + 10);
    }

    // Reduce font size for moves and timer
    g2d.setFont(new Font("SansSerif", Font.PLAIN, 18));
    g2d.setColor(Color.BLACK);
    g2d.drawString("Moves: " + moves, 10, getHeight() - 30);  // Adjusted y-position
    g2d.drawString("Time: " + elapsedTime + "s", 150, getHeight() - 30);  // Adjusted y-position to align properly

    if (gameOver) {
        g2d.setColor(Color.GREEN);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 24));  // Slightly larger for "You Win!"
        g2d.drawString("You Win!", getWidth() / 2 - 50, getHeight() - 60);  // Placed above moves and timer text
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Simple 15 Puzzle Game");
            frame.setResizable(false);
            frame.add(new SimpleFifteenPuzzleGame(4, 400, 30), BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
