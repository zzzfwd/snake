package pkg;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    private List<Node> body;                    // 蛇的身体
    private Node head;                            // 蛇头
    private Node tail;                              // 蛇尾
    private Direction direction;              // 蛇的移动方向

    public List<Node> getBody() {
        return body;
    }

    public void setBody(List<Node> body) {
        this.body = body;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Snake() {
        this(new Node(0, 0), Direction.DOWN);
    }

    public Snake(Node head, Direction direction) {
        this.body = new LinkedList<>();
        this.body.add(head);
        this.head = head;
        this.tail = body.get(body.size() - 1);
        this.direction = direction;
    }

    public void move() {
        switch (this.direction) {
            case UP:
                body.add(0, new Node(head.getX(), head.getY() - Node.HEIGHT));
                break;
            case LEFT:
                body.add(0, new Node(head.getX() - Node.WIDTH, head.getY()));
                break;
            case DOWN:
                body.add(0, new Node(head.getX(), head.getY() + Node.HEIGHT));
                break;
            case RIGHT:
                body.add(0, new Node(head.getX() + Node.WIDTH, head.getY()));
                break;
            default:
                break;
        }
        head = body.get(0);
        body.remove(tail);
        tail = body.get(body.size() - 1);
    }

    // 检测蛇是否移出了游戏边界
    public boolean moveOutOfBounds() {
        if (head.getX() < 0 || head.getY() < 0 || head.getX() >= Game.GAME_WIDTH || head.getY() >= Game.GAME_HEIGHT) {
            System.out.println("You move out of bounds!");
            return true;
        }
        return false;
    }

    // 检测蛇是否撞到了自己
    public boolean hitItself(Snake snake) {
        for (int i = 1; i < body.size(); ++i) {
            if (head.getX() == snake.body.get(i).getX() && snake.head.getY() == body.get(i).getY()) {
                System.out.println("You hit yourself!");
                return true;
            }
        }
        return false;
    }

    // 检测蛇是否吃到了食物
    public boolean eatFood(Food food) {
        if (head.getX() == food.getX() && head.getY() == food.getY()) {
            System.out.println("You eat food!");
            food.setEaten(true);
            body.add(0, new Node(food.getX(), food.getY()));
            head = body.get(0);
            return true;
        }
        return false;
    }
}
