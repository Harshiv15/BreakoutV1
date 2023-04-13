import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Brick extends Rectangle2D.Double {
    private Color brickColor;
    public Brick(double x, double y, double w, double h) {
        super(x, y, w, h);
        brickColor = Math.random() < 0.5 ? Color.WHITE : Color.ORANGE;
    }

    public Color getBrickColor() {
        return brickColor;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(brickColor);
        g2d.fill(this);
    }
}
