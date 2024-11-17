package PR7_8_9;

import java.awt.*;
import java.util.Random;
class TFish {
    private int x, y;
    private int dx, dy;
    private Color color;
    public TFish(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.dx = new Random().nextInt(3) + 1;
        this.dy = new Random().nextInt(3) - 1;
        this.color = color;
    }
    public void swim(int width, int height) {
        x += dx;
        y += dy;
        if (x > width || x < 0) dx = -dx;
        if (y > height || y < 0) dy = -dy;
    }
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, 30, 15);
        int[] xPoints = {x - 10, x, x};
        int[] yPoints = {y +7, y, y + 15};
        g.fillPolygon(xPoints, yPoints, 3);
    }
}