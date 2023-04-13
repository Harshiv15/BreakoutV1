import java.awt.*;

public class Paddle extends Brick {
    public Paddle(double x, double y, double w, double h) {
        super(x, y, w, h);

    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fill(this);
    }
}
