import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball extends Ellipse2D.Double {
    private Color ballColor;
    public Ball(double x, double y, double w, double h) {
        super(x, y, w, h);
        ballColor = Color.WHITE;
    }

    public Color getBallColor() {
        return ballColor;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(ballColor);
        g2d.fill(this);
    }
}
