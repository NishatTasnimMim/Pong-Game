package javaponggame;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Model {
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;

    static Set<Direction> keys = new HashSet<>(25);
    Timer timer;
    boolean first = false;
    boolean direction = false, axis = false;
    double ballX = 0, ballY = 0;
    double p1X = 0, p1Y = 0;
    double p2X = 0, p2Y = 0;
    double incline = -0.5;
    List<Entity> entities = new ArrayList<>(20);
    View view;

    public Model(View view) {
        this.view = view;
        startTimer();
        view.setModel(this);
    }

    public final void startTimer() {
        timer = new Timer(2, (ActionEvent arg0) -> {
            update(view.getBounds());
            view.repaint();
        });
        timer.start();
    }

    public void update(Rectangle bounds) {
        if (paddle1 == null || paddle2 == null || ball == null) {
            paddle1 = new Paddle(1);
            paddle2 = new Paddle(2);
            ball = new Ball();
            ballX = 300;
            ballY = 300;
            p1X = 30;
            p2X = 650;
            p1Y = 350;
            p2Y = 350;
            paddle1.setView(view);
            paddle2.setView(view);
            paddle1.createBindings();
            paddle2.createBindings();
            entities.add(paddle1);
            entities.add(paddle2);
            entities.add(ball);
        }

        if (paddle1.getScore() > 7) {
            JOptionPane.showMessageDialog(view, "Player 1 has won!");
            paddle1.resetScore();
            paddle2.resetScore();
        } else if (paddle2.getScore() > 7) {
            JOptionPane.showMessageDialog(view, "Player 2 has won!");
            paddle1.resetScore();
            paddle2.resetScore();
        }

        setDirection(direction, incline);
        bounce();

        // TODO Add functionality for changing ball location...
        // TODO Don't forget to use collision detection!!!

        if (keys.contains(Direction.LEFT_UP)) {
            p1Y -= 2;
        } else if (keys.contains(Direction.LEFT_DOWN)) {
            p1Y += 2;
        }
        if (keys.contains(Direction.RIGHT_UP)) {
            p2Y -= 2;
        } else if (keys.contains(Direction.RIGHT_DOWN)) {
            p2Y += 2;
        }

        paddle1.setLocation(new Point((int) p1X, (int) p1Y));
        paddle2.setLocation(new Point((int) p2X, (int) p2Y));
        ball.setLocation(new Point((int) ballX, (int) ballY));

    }

    public Entity[] getEntities() {
        return entities.toArray(new Entity[0]);

    }

    public void bounce() {
        // TODO Paddle collision detection

        if (ballX < p1X + paddle1.getSize().width && ballY > p1Y && ballY < p1Y + paddle2.getSize().height) {
            direction = true;
        }

        if (ballX + ball.getSize().width > p2X && ballY > p2Y && ballY < p2Y + paddle1.getSize().height) {
            direction = false;
        }

        if (ballX < view.getBounds().x) {
            paddle2.increaseScore();

            direction = !direction;

            ballX = 300;
            ballY = 300;
            // direction = true;
        }
        if (ball.getLocation().x > view.getBounds().x + view.getBounds().width) {
            paddle1.increaseScore();

            direction = !direction;

            ballX = 300;
            ballY = 300;
            // direction = false;
        }
        if (ball.getLocation().y < view.getBounds().y) {
            ballY++;
            incline *= -1;

        }
        if (ball.getLocation().y > view.getBounds().height) {
            ballY--;
            incline *= -1;
        }

        /////////////
        if (paddle1.getLocation().y < view.getBounds().y) {
            p1Y = view.getBounds().x - 1;
        }
        if (paddle1.getLocation().y + paddle1.getSize().height > view.getBounds().height + 22) {
            p1Y = view.getBounds().height - paddle1.getSize().height + 22;
        }

        if (paddle2.getLocation().y < view.getBounds().y) {
            p2Y = view.getBounds().x - 1;
        }
        if (paddle2.getLocation().y + paddle2.getSize().height > view.getBounds().height + 22) {
            p2Y = view.getBounds().height - paddle2.getSize().height + 22;
        }

    }

    public void setDirection(boolean Xdir, double inc) {
        ballY += inc;
        if (Xdir) {
            ballX++;
        } else if (!Xdir) {
            ballX--;
        }
    }

    public int getPaddleScore(int paddleNum) {
        if (paddleNum == 1)
            return paddle1.getScore();
        else {
            return paddle2.getScore();
        }
    }
}