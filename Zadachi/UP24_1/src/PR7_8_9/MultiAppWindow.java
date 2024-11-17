package PR7_8_9;


import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
public class MultiAppWindow extends JFrame {
    public MultiAppWindow() {
        setTitle("Работа 7,8,9");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JTabbedPane tabbedPane = new JTabbedPane();
        drawKit whalePanel = new drawKit();
        tabbedPane.addTab("Кит", whalePanel);
        TAquarium aquariumPanel = new TAquarium();
        tabbedPane.addTab("Аквариум", aquariumPanel);
        Mathematiko mathGamePanel = new Mathematiko();
        tabbedPane.addTab("Математико", mathGamePanel);
        add(tabbedPane);
        new Timer(50, e -> aquariumPanel.Run()).start();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MultiAppWindow window = new MultiAppWindow();
            window.setVisible(true);
        });
    }
}
class drawKit extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawWhale(g);
    }
    private void drawWhale(Graphics g) {
        g.setColor(Color.BLACK);
        int[] xPoints = {350, 380, 380};
        int[] yPoints = {325, 280, 375};
        int[] xPoints1 = {350, 420, 380};
        int[] yPoints1 = {325, 375, 375};
        int[] xPoints2 = {350, 380, 350};
        int[] yPoints2 = {325, 375, 375};
        g.fillPolygon(xPoints2,yPoints2,3);
        g.fillPolygon(xPoints1,yPoints1,3);
        g.fillPolygon(xPoints,yPoints,3);
        g.fillRect(250,325,100,50);
        g.fillArc(50,250,250,250,0,180);
        g.setColor(Color.WHITE);
        int x = 120;
        int y = 300;
        int diameter = 25;
        g.fillOval(x, y, diameter, diameter);
        g.setColor(Color.BLACK);
        int x1= 130;
        int y1 = 315;
        int diameter1 = 10;
        g.drawLine(170,255,170,180);
        g.drawLine(175,255,175,180);
        g.drawLine(165,255,165,200);
        g.drawLine(180,255,180,200);
        g.drawLine(170,180,150,200);
        g.drawLine(175,180,190,200);
        g.drawLine(165,200,155,210);
        g.drawLine(180,200,190,210);
        g.fillOval(x1, y1, diameter1, diameter1);
    }
}
class TAquarium extends JPanel {
    private java.util.List<TFish> fishes = new ArrayList<>();
    public TAquarium() {
        Color[] colors = {Color.RED, Color.BLUE, Color.ORANGE, Color.PINK};
        int[][] positions = {{50, 50}, {100, 100}, {150, 150}, {300, 300}};
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < colors.length; j++) {
                fishes.add(new TFish(positions[j][0], positions[j][1], colors[j]));
            }
        }
    }
    public void Init() {
        JFrame frame = new JFrame("Аквариум");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);
        new Timer(50, e -> Run()).start();
    }
    public void Run() {
        for (TFish fish : fishes) {
            fish.swim(getWidth(), getHeight());
        }
        repaint();
    }
    public void Done() {
        System.exit(0);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawRocks(g);
        for (TFish fish : fishes) {
            fish.draw(g);
        }
    }
    private void drawRocks(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        int[] xPoints = {100, 175, 25};
        int[] yPoints = {100, 800, 800};
        g.fillPolygon(xPoints, yPoints, 3);
        int[] xPoints1 = {200, 350, 50};
        int[] yPoints1 = {25, 800, 800};
        g.fillPolygon(xPoints1, yPoints1, 3);
        int[] xPoints2 = {300, 525, 75};
        int[] yPoints2 = {300, 800, 800};
        g.fillPolygon(xPoints2, yPoints2, 3);
    }
}


