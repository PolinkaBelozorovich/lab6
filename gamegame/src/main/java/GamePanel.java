import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private boolean gameOverHappened = false;
    GamePanel() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int click, x = me.getX(),y = me.getY(), xIndex = 0,yIndex = 0;
                if( (Window.isServer && !Window.serverCanGo) || (!Window.isServer && !Window.clientCanGo) ){ return; }
                if (!Window.isServer) { click = 1; } else { click = 2; }
                for (int i = 0; i < Window.N; i++) {
                    if (x < (i + 1) * getWidth() / Window.N) {
                        xIndex = i;
                        break; } }
                for (int i = 0; i < Window.N; i++) {
                    if (y < (i + 1) * getHeight() / Window.N) {
                        yIndex = i;
                        break; } }
                if (Window.board[yIndex][xIndex] == 0) {
                    Window.board[yIndex][xIndex] = click;
                    Window.verticalStep = yIndex;
                    Window.horizontalStep = xIndex;
                    if( !Window.isServer ) {
                        Window.clientDataReady = true;
                        Window.clientCanGo = false;
                    } else{
                        Window.serverDataReady = true;
                        Window.serverCanGo = false; }
                }
                repaint(); }}); }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(5.0f));
        for (int i = 0; i < Window.N - 1; i++) { g2d.drawLine((i + 1) * getWidth() / Window.N, 0, (i + 1) * getWidth() / Window.N, getHeight()); }
        for (int i = 0; i < Window.N - 1; i++) { g2d.drawLine(0, (i + 1) * getHeight() / Window.N, getWidth(), (i + 1) * getHeight() / Window.N); }
        for (int i = 0; i < Window.N; i++) {
            for (int j = 0; j < Window.N; j++) {
                if (Window.board[i][j] == 1) {
                    paintKrestik(g2d, j, i, getWidth(), getHeight(), Window.N);
                } else {
                    if (Window.board[i][j] == 2) { paintNolik(g2d, j, i, getWidth(), getHeight(), Window.N); } }} }
        if (Window.checkKrestikWin() && !gameOverHappened ) {
            gameOverHappened = true;
            SwingUtilities.invokeLater(new Runnable(){
                public void run() {
                    if( Window.isServer ){
                        JOptionPane.showMessageDialog(null, "Вы проиграли!");
                    } else{ JOptionPane.showMessageDialog(null, "Вы победили!"); }
                    System.exit(0); }});
            return; }
        if (Window.checkNolikWin() && !gameOverHappened ) {
            gameOverHappened = true;
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    if( Window.isServer ){ JOptionPane.showMessageDialog(null, "Вы победили!"); }
                    else{ JOptionPane.showMessageDialog(null, "Вы проиграли!"); }
                    System.exit(0); }});
            return; }
        if( Window.Draw() && !gameOverHappened ){
            gameOverHappened = true;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(null, "Ничья!!!");
                    System.exit(0); }}); } }



    private void paintKrestik(Graphics g, int xStep, int yStep, int width, int height, int N){
        Image image = new ImageIcon("крестик1.pnG").getImage();
        int xLeft = xStep * width / N + 25;
        int yUp = yStep * height / N + 20;
        g.drawImage(image, xLeft, yUp, null);
    }

    private void paintNolik(Graphics g, int xStep, int yStep, int width, int height, int N) {
        Image image = new ImageIcon("нолик.jpg").getImage();
        int xLeft = xStep * width / N + 25;
        int yUp = yStep * height / N + 20;
        g.drawImage(image, xLeft, yUp, null); }}