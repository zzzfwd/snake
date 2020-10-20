package pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener {
    private Image offScreenImage = null;
    private boolean isOver = false;
    private Snake snake;
    private Food food;


    public GamePanel(int width, int height) {
        snake = new Snake(new Node(20, 20), Direction.DOWN);
        food = new Food(100, 100);
//        this.setPreferredSize(new Dimension(width, height));
//        this.setSize(width, height);
        this.setBounds(0, 0, width, height);
        this.setBackground(Color.BLACK);
        new Thread(new PaintThread()).start();
    }

    public void startGame() {
        if (isOver) {
            isOver = false;
            new Thread(new PaintThread()).start();
        }
        snake.getBody().clear();
        snake.getBody().add(new Node(20, 20));
        snake.setHead(snake.getBody().get(0));
        snake.setTail(snake.getBody().get(snake.getBody().size() - 1));
        snake.setDirection(Direction.DOWN);
        food.setX(100);
        food.setY(100);
    }

    public void gameOver() {
        isOver = true;
        System.out.println("Game Over!");
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
        if (this.offScreenImage == null) {
            this.offScreenImage = this.createImage(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        }
        Graphics gOffScreen = this.offScreenImage.getGraphics();
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.drawString("snake length: " + snake.getBody().size(), 0, 30);
        this.drawSnake(g, snake);
        this.drawFood(g, food);
        snake.move();
        if (snake.moveOutOfBounds() || snake.hitItself(snake)) {
            this.gameOver();
        }
        snake.eatFood(food);
        food.generateFood(snake);
    }

    public void drawSnake(Graphics g, Snake snake) {
        List<Node> body = snake.getBody();
//        g.setColor(Color.YELLOW);
//        g.fillOval(body.get(0).getX(), body.get(0).getY(), Node.WIDTH, Node.HEIGHT);
        g.setColor(Color.CYAN);
        for (int i = 1; i < body.size(); ++i) {
            g.fillOval(body.get(i).getX(), body.get(i).getY(), Node.WIDTH, Node.HEIGHT);
        }
        g.setColor(Color.YELLOW);
        g.fillOval(body.get(0).getX(), body.get(0).getY(), Node.WIDTH, Node.HEIGHT);
    }

    public void drawFood(Graphics g, Food food) {
        g.setColor(Color.GREEN);
        g.fillOval(food.getX(), food.getY(), Node.WIDTH, Node.HEIGHT);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (snake.getDirection() != Direction.DOWN)
                    snake.setDirection(Direction.UP);
                break;
            case KeyEvent.VK_LEFT:
                if (snake.getDirection() != Direction.RIGHT)
                    snake.setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_DOWN:
                if (snake.getDirection() != Direction.UP)
                    snake.setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_RIGHT:
                if (snake.getDirection() != Direction.LEFT)
                    snake.setDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_R:
                this.startGame();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private class PaintThread implements Runnable {
        @Override
        public void run() {
            while (!isOver) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
    }
}
