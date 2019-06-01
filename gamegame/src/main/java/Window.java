import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


class Window extends JFrame implements Runnable{

    private JButton VIPButton = new JButton("VIPигрок!");
    private JButton ConnectButton = new JButton("Законнектиться");
    private JButton PlayerButton = new JButton("Игрок!");
    private JTextField ipField = new JTextField("                                  ");
    private ServerSocket serverSocket;
    private Socket remoteClientSocket1 = null;
    private Socket remoteClientSocket = null;
    private PrintWriter fromClientToServer = null;
    private BufferedReader toClientFromServer = null;
    private boolean clientConnection = false;
    static boolean isServer = false;
    private static boolean Ready = false;
    static int verticalStep;
    static int horizontalStep;
    private static boolean verticalWin, horizontalWin, diagonalWin;
    static boolean clientDataReady = false;
    static boolean serverDataReady = false;
    static boolean serverCanGo = false;
    static boolean clientCanGo = true;
    private GameFrame gameFrame = null;
    static int N;
    static int[][] board;

    Window(){
        super("Крестики Vs Нолики: Битва");
        N = 3;

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 80));
        JPanel panelIP = new JPanel();
        panelIP.setLayout(new FlowLayout(FlowLayout.CENTER, 55, 60));
        JPanel panelSlider = new JPanel();
        panelSlider.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 40));

        this.getContentPane().add(panel);

        final JSlider slider = new JSlider(JSlider.HORIZONTAL, 3, 15, 3);
        slider.setMajorTickSpacing(12);
        slider.setMinorTickSpacing(0);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setEnabled(false);

        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                N = slider.getValue();
                try {
                    remoteClientSocket1 = new Socket(InetAddress.getByName(ipField.getText()), 755);
                    fromClientToServer = new PrintWriter(remoteClientSocket1.getOutputStream(), true);
                    toClientFromServer = new BufferedReader(new InputStreamReader(remoteClientSocket1.getInputStream()));
                    clientConnection = true;
                } catch (IOException ioe) {
                    System.err.println("Slider error");
                    System.exit(1);
                }
            }
        });

        ipField.setEnabled(false);
        ConnectButton.setEnabled(false);

        ConnectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    remoteClientSocket = new Socket(InetAddress.getByName(ipField.getText()), 756);
                    fromClientToServer = new PrintWriter(remoteClientSocket.getOutputStream(), true);
                    toClientFromServer = new BufferedReader(new InputStreamReader(remoteClientSocket.getInputStream()));
                    clientConnection = true;
                    gameFrame = new GameFrame("Крестики Vs Нолики: Битва1");
                }
                catch (UnknownHostException uhe) {System.exit(1);
                } catch (IOException ioe) { System.exit(1); } }});


        VIPButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VIPButton.setEnabled(false);
                PlayerButton.setEnabled(false);
                InetAddress ia3 = null;
                try {
                    ia3 = InetAddress.getLocalHost();
                } catch (UnknownHostException ex) {
                    JOptionPane.showMessageDialog(null, "Can't get IP!", "Fatal error!", 1);
                    System.exit(1);
                }
                setTitle("Your IP: " + ia3.getHostAddress());
                isServer = true;
                Ready = true;
                try {
                    serverSocket = new ServerSocket(755);
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null, "Error 755!");
                    System.exit(1);
                }
                try {
                    serverSocket = new ServerSocket(756);
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null, "Error 1234!");
                    System.exit(1);
                }
            }
        });


        ipField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (ipField.getText().equals("                                  ")) {
                    ipField.setText("");
                }
            }
        });


        PlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayerButton.setEnabled(false);
                VIPButton.setEnabled(false);
                ipField.setEnabled(true);
                ConnectButton.setEnabled(true);
                slider.setEnabled(true);
                isServer = false;
                Ready = true;
            }
        });

        panel.add(VIPButton);
        panel.add(PlayerButton);

        panelIP.add(createPanel(new TitledBorder("Введите IP")));
        panelIP.add(createPanel1(new LineBorder(Color.ORANGE, 4)));

        JLabel label = new JLabel("Выберите размерность поля", new ImageIcon("поле.png"), SwingConstants.LEFT);

        panelSlider.add(label);
        panelSlider.add(slider);
        panel.add(panelIP);
        panel.add(panelSlider);
        setSize(700, 1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {board[i][j] = 0; } }
    }

    private JPanel createPanel(TitledBorder border) {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(ipField);
        panel1.setBorder(new CompoundBorder(new EmptyBorder(12,12,12,12), border));
        return panel1;
    }

    private JPanel createPanel1(LineBorder border) {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(ConnectButton);
        panel1.setBorder(new CompoundBorder(new EmptyBorder(12,12,12,12), border));
        return panel1;
    }

    public static boolean Draw(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if( board[i][j] == 0 ){
                    return false; } } }
        return true;
    }
    public static boolean checkKrestikWin() {
        //switch (N){
        //    case 3:
        for (int i = 0; i < N; i++) {
            horizontalWin = true;
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 1) {
                    horizontalWin = false;
                    break; } }
            if (horizontalWin) return true;}
        for (int i = 0; i < N; i++) {
            verticalWin = true;
            for (int j = 0; j < N; j++) {
                if (board[j][i] != 1) {
                    verticalWin = false;
                    break; } }
            if (verticalWin) return true;}
        diagonalWin = true;
        for (int i = 0; i < N; i++) {
            if (board[i][i] != 1) {
                diagonalWin = false;
                break; } }
        if (diagonalWin) return true;
        diagonalWin = true;
        for (int i = 0; i < N; i++) {
            int j = N - 1 - i;
            if (board[i][j] != 1) {
                diagonalWin = false;
                break; } }
        if (diagonalWin) return true;
        //   break;

           /* case 15:
diagonalWin = true;
        horizontalWin = true;
        verticalWin = true;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] != 1) {
                    horizontalWin = false;
                    break; } } }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[j][i] != 1) {
                    verticalWin = false;
                    break; } } }
        for (int i = 0; i < 5; i++) {
            int j = i;
            if (board[i][j] != 1) {
                diagonalWin = false;
                break; } }
        for (int i = 0; i < 5; i++) {
            int j = 5 - 1 - i;
            if (board[i][j] != 1) {
                diagonalWin = false;
                break; } }
        if(diagonalWin || verticalWin || horizontalWin) {return true;}
            break;

            default:
                System.err.println("Error!");
                System.exit(1);
                break;
        }*/
        return false;
    }

    public static boolean checkNolikWin() {
        //   switch (N) {
        //       case 3:
        for (int i = 0; i < N; i++) {
            horizontalWin = true;
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 2) {
                    horizontalWin = false;
                    break; } }
            if(horizontalWin)return true;}
        for (int i = 0; i < N; i++) {
            verticalWin = true;
            for (int j = 0; j < N; j++) {
                if (board[j][i] != 2) {
                    verticalWin = false;
                    break; } }
            if(verticalWin)return true;}
        diagonalWin = true;
        for (int i = 0; i < N; i++) {
            if (board[i][i] != 2) {
                diagonalWin = false;
                break;} }
        if(diagonalWin)return true;
        diagonalWin = true;
        for (int i = 0; i < N; i++) {
            int j = N - 1 - i;
            if (board[i][j] != 2) {
                diagonalWin = false;
                break; } }
        if(diagonalWin)return true;
        //if (diagonalWin || verticalWin || horizontalWin) { return true; }

         /*   case 15:

                for (int i = 0; i < 5; i++) {
            horizontalWin = true;
            verticalWin = true;
            diagonalWin = true;
            for (int j = 0; j < 5; j++) {
                if (board[i][j] != 2) {
                    horizontalWin = false;
                    break; } } }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[j][i] != 2) {
                    verticalWin = false;
                    break; } } }
        for (int i = 0; i < 5; i++) {
            int j = i;
            if (board[i][j] != 2) {
                diagonalWin = false;
                break;} }
        for (int i = 0; i < 5; i++) {
            int j = 5 - 1 - i;
            if (board[i][j] != 2) {
                diagonalWin = false;
                break; } }
        if (diagonalWin || verticalWin || horizontalWin) { return true; }

            default:
                System.err.println("Error!");
                System.exit(1);
                break;
        }*/
        return false;
    }

    public void run() {
        while (!Ready) {
            try { Thread.sleep(100);
            } catch (InterruptedException e) { e.printStackTrace(); } }
        if (!isServer) {
            while (!clientConnection) {
                try { Thread.sleep(100);
                } catch (InterruptedException e) {e.printStackTrace(); } }
            while (true) {
                try { while (true) {
                    if (clientDataReady) {
                        String s = "";
                        s = s.concat(String.valueOf(verticalStep));
                        s = s.concat(" ");
                        s = s.concat(String.valueOf(horizontalStep));
                        fromClientToServer.println(s);
                        clientDataReady = false;
                        break; } else {
                        try { Thread.sleep(5);
                        } catch (InterruptedException e) {e.printStackTrace(); } } }
                    String str;
                    while ((str = toClientFromServer.readLine()) != null) {
                        String[] words = str.split(" ");
                        int verStep = Integer.parseInt(words[0]);
                        int horStep = Integer.parseInt(words[1]);
                        board[verStep][horStep] = 2;
                        gameFrame.repaint();
                        clientCanGo = true;
                        break; } }
                catch (IOException ex) { Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex); } }
        } else {
            try {
                Socket clientSocket = serverSocket.accept();
                PrintWriter fromServerToClient = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader toServerFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                Thread.sleep(1000);
                String inputLine;
                String outputLine;
                gameFrame = new GameFrame("Крестики Vs Нолики: Битва");
                while (true) {
                    while ((inputLine = toServerFromClient.readLine()) != null) {
                        String[] coords = inputLine.split(" ");
                        int vStep = Integer.parseInt(coords[0]);
                        int hStep = Integer.parseInt(coords[1]);
                        if (board[vStep][hStep] != 0) {
                            java.awt.Toolkit.getDefaultToolkit().beep();
                        } else { board[vStep][hStep] = 1; }
                        gameFrame.repaint();
                        serverCanGo = true;
                        break; }
                    while( true ){
                        if( serverDataReady ){
                            outputLine = String.valueOf(verticalStep);
                            outputLine = outputLine.concat(" ");
                            outputLine = outputLine.concat(String.valueOf(horizontalStep));
                            serverDataReady = false;
                            fromServerToClient.println(outputLine);
                            break; }
                        else{Thread.sleep(5); } }}
            } catch (InterruptedException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, "Client disconnected...");
                System.exit(1); } } }
}
