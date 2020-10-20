package pkg;

import java.util.List;
import java.util.Random;

public class Food {
    private int x, y;           // 食物的x, y坐标
    private boolean isEaten = false;        // 判断食物是否被吃掉(false表示没被吃掉, true表示食物被吃掉)
    private Random random = new Random();      // 随机生成食物的坐标

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isEaten() {
        return isEaten;
    }

    public void setEaten(boolean eaten) {
        isEaten = eaten;
    }

    public Food() {
        this(50, 50);
    }

    public Food(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // 随机生成食物(食物不能在蛇的身体上生成)
    public Food generateFood(Snake snake) {
        if (isEaten) {
            do {
                this.x = random.nextInt(Game.GAME_WIDTH / Node.WIDTH) * Node.WIDTH;
                this.y = random.nextInt(Game.GAME_HEIGHT / Node.HEIGHT) * Node.HEIGHT;
            } while (onSnackBody(snake));
            isEaten = false;
        }
        return this;
    }

    // 检测食物是否在蛇身上生成
    public boolean onSnackBody(Snake snake) {
        List<Node> body = snake.getBody();
        for (int i = 0; i < body.size(); ++i) {
            if (this.x == body.get(i).getX() && this.y == body.get(i).getY()) {
                return true;
            }
        }
        return false;
    }
}
