import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class BreakoutPanel extends JPanel {
    public static final int BRICK_WIDTH = 20,
                            BRICK_HEIGHT = 20,
                            PANEL_WIDTH = 800,
                            PANEL_HEIGHT = 600,
                            PADDLE_WIDTH = 100,
                            BALL_WIDTH = 15,
                            BALL_HEIGHT = 15;
    private ArrayList<Brick> wall = new ArrayList<>();
    private int wallWidth = 40, wallHeight = 6;
    private Paddle paddle = new Paddle(PANEL_WIDTH/2 - PADDLE_WIDTH/2, PANEL_HEIGHT - 50, PADDLE_WIDTH, BRICK_HEIGHT);
    private double ballAngle = Math.toRadians(Math.random()*180), ballSpeed = 3;
    private double ballDeltaX = ballSpeed * Math.cos(ballAngle);
    private double ballDeltaY = ballSpeed * Math.sin(ballAngle);
    private Ball ball = new Ball(PANEL_WIDTH/2 - BALL_WIDTH/2, PANEL_HEIGHT/2 + 50, BALL_WIDTH, BALL_HEIGHT);
    private Timer timer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            for(Brick b : wall){
                if(ball.intersects(b)){
                    ballAngle = -ballAngle;
                    wall.remove(b);
                    break;
                }
                else if(ball.intersects(paddle)){
                    ballAngle = -ballAngle;
                    ballSpeed += Math.signum(ballSpeed) * 0.1;
                    break;
                }
            }

            if(ball.x < BALL_WIDTH)
                ballAngle = Math.PI-ballAngle;
            else if(ball.x > PANEL_WIDTH - BALL_WIDTH)
                ballAngle = Math.PI-ballAngle;

            ballDeltaX = ballSpeed * Math.cos(ballAngle);
            ballDeltaY = ballSpeed * Math.sin(ballAngle);
            ball.x += ballDeltaX;
            ball.y += ballDeltaY;
            repaint();
        }
    });

    public BreakoutPanel(){
        super();
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        timer.start();

        for(int r = 0; r < wallHeight; r++)
        {
            for(int c = 0; c < wallWidth; c++){
                Brick b = new Brick(c * BRICK_WIDTH,r * BRICK_HEIGHT,BRICK_WIDTH,BRICK_HEIGHT);
                wall.add(b);
            }
        }
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                paddle.x = e.getX()-50;
                if(paddle.x < 0)
                    paddle.x = 0;
                else if(paddle.x > PANEL_WIDTH - PADDLE_WIDTH)
                    paddle.x = PANEL_WIDTH - PADDLE_WIDTH;
                repaint();
            }
        });
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g.fillRect(0,0,800,600);
        for(Brick b: wall){
            g2d.setColor(Color.BLACK);
            g2d.drawRect((int) b.x, (int) b.y, (int) b.width, (int) b.height);
            b.draw(g2d);
        }
        paddle.draw(g2d);
        ball.draw(g2d);

    }

}
