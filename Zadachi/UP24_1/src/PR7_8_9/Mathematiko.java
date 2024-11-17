package PR7_8_9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
public class Mathematiko extends JPanel {
    static final int GRID_SIZE = 5;
    static final int DECK_SIZE = 52;
    static final int[] CARD_NUMBERS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    static int[][] grid = new int[GRID_SIZE][GRID_SIZE];
    static java.util.List<Integer> deck = new ArrayList<>();
    static Random random = new Random();
    private JButton drawCardButton;
    private JLabel scoreLabel;
    private boolean playerTurn = true;
    public Mathematiko() {
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        drawCardButton = new JButton("Взять карту");
        drawCardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playTurn();
            }
        });
        topPanel.add(drawCardButton);
        scoreLabel = new JLabel("Счет: 0");
        topPanel.add(scoreLabel);
        add(topPanel, BorderLayout.NORTH);
        initializeDeck();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = Math.min(getWidth(), getHeight()) / GRID_SIZE;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                int x = col * cellSize;
                int y = row * cellSize;
                g.drawRect(x, y, cellSize, cellSize);
                if (grid[row][col] != 0) {
                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(grid[row][col]), x + cellSize / 2, y + cellSize / 2);
                }
            }
        }
    }
    public static void initializeDeck() {
        deck.clear();
        for (int i = 0; i < 4; i++) {
            for (int num : CARD_NUMBERS) {
                deck.add(num);
            }
        }
        Collections.shuffle(deck);
    }
    public void playTurn() {
        if (deck.isEmpty()) {
            drawCardButton.setEnabled(false);
            return;
        }
        int card = drawCard();
        if (playerTurn) {
            placeNumberRandomly(card);
        } else {
            computerMove(card);
        }
        playerTurn = !playerTurn;
        repaint();
        calculateScore();
    }
    public static int drawCard() {
        return deck.remove(random.nextInt(deck.size()));
    }

    public static void placeNumberRandomly(int number) {
        while (true) {
            int row = random.nextInt(GRID_SIZE);
            int col = random.nextInt(GRID_SIZE);
            if (grid[row][col] == 0) {
                grid[row][col] = number;
                break;
            }
        }
    }
    public void computerMove(int number) {
        int bestRow = -1, bestCol = -1, bestScore = Integer.MIN_VALUE;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (grid[row][col] == 0) {
                    grid[row][col] = number;
                    int currentScore = evaluatePotentialScore();
                    grid[row][col] = 0;
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestRow = row;
                        bestCol = col;
                    }
                }
            }
        }
        if (bestRow != -1 && bestCol != -1) {
            grid[bestRow][bestCol] = number;
        }
    }
    public void calculateScore() {
        int totalScore = 0;
        for (int i = 0; i < GRID_SIZE; i++) {
            totalScore += evaluateLine(grid[i]);
        }
        for (int j = 0; j < GRID_SIZE; j++) {
            int[] column = new int[GRID_SIZE];
            for (int i = 0; i < GRID_SIZE; i++) {
                column[i] = grid[i][j];
            }
            totalScore += evaluateLine(column);
        }
        int[] diagonal1 = new int[GRID_SIZE];
        int[] diagonal2 = new int[GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            diagonal1[i] = grid[i][i];
            diagonal2[i] = grid[i][GRID_SIZE - i - 1];
        }
        totalScore += evaluateLine(diagonal1);
        totalScore += evaluateLine(diagonal2);
        scoreLabel.setText("Счет: " + totalScore);
    }
    public static int evaluateLine(int[] line) {
        int score = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : line) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        if (countMap.containsValue(4)) {
            score = 160;
        } else if (countMap.containsValue(3)) {
            score = 40;
        }
        return score;
    }
    public int evaluatePotentialScore() {
        int potentialScore = 0;
        for (int i = 0; i < GRID_SIZE; i++) {
            potentialScore += evaluateLine(grid[i]);
        }
        return potentialScore;
    }
}
