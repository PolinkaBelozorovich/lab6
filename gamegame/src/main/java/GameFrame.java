import javax.swing.*;

class GameFrame extends JFrame {
    private GamePanel gp;
    GameFrame(String n) {
        super(n);
        gp = new GamePanel();
        getContentPane().add(gp);
        setSize(800, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
