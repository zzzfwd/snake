package pkg;

public class Node {
    public static final int WIDTH = 20;         // 结点的宽度
    public static final int HEIGHT = 20;        // 结点的高度
    private int x, y;               // 结点的x, y坐标

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

    public Node() {
        this(0, 0);
    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
