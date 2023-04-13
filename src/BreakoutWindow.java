import javax.swing.*;
import java.awt.*;

public class BreakoutWindow extends JFrame {
    public BreakoutWindow(){
        super("My stupendous breakout clone");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new BreakoutPanel());
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }
}
