package pkg;

import javax.swing.*;

public class Game extends JFrame {
    public static final int FRAME_WIDTH = 1000;         // 整个游戏窗口的宽度
    public static final int FRAME_HEIGHT = 829;         // 整个游戏窗口的高度
    public static final int GAME_WIDTH = 800;            // 游戏面板的宽度
    public static final int GAME_HEIGHT = 800;          // 游戏面板的高度
    private GamePanel gamePanel;
    private ScorePanel scorePanel;

    public Game() {
        gamePanel = new GamePanel(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        scorePanel = new ScorePanel(
                Game.FRAME_WIDTH - Game.GAME_WIDTH,
                Game.GAME_HEIGHT);

        this.setLayout(null);
        this.add(gamePanel);
        this.add(scorePanel);

        this.addKeyListener(gamePanel);

        this.setTitle("Snake");
        this.setSize(Game.FRAME_WIDTH, Game.FRAME_HEIGHT);
        this.setLocation(500, 100);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Game();
    }
}
