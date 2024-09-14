import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class SimpleChatClientA {
    
    private JFrame mainFrame;
    private ImageIcon backgroundIcon;
    private JLabel backgroundLabel;
    private JButton sendButton;
    private JTextField outgoing;
    private PrintWriter writer;
    private Socket socket;

    private int bgwidth = 650;
    private int bgheight = 650;

    private void setUpNetWorking() {
        try {
            socket = new Socket("127.0.0.1", 5000);
            writer = new PrintWriter(socket.getOutputStream());
            System.err.println("networking established");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public class SendButtonListener implements ActionListener {
        public void actionPerformed (ActionEvent actionEvent) {
            try {
                writer.println(outgoing.getText());
                writer.flush();

            } catch (Exception exception) {
                exception.printStackTrace();
            }

            outgoing.setText("");
            outgoing.requestFocus();


            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    disposeOfDialog();
                }
            });
            timer.setRepeats(false);
            timer.start();
            JOptionPane.showMessageDialog(null, "Massage recieved", "Info massage", JOptionPane.INFORMATION_MESSAGE);
            }
    }

    private static void disposeOfDialog() {
        Window dialogOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
        if (dialogOwner instanceof Dialog) {
            ((Dialog) dialogOwner).dispose();
        } else if (dialogOwner instanceof Frame) {
            ((Frame) dialogOwner).dispose();
        }
    }

    public void go() {

        // background
        backgroundIcon = new ImageIcon(this.getClass().getResource("imgs/background.jpg"));
        backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setSize(bgwidth, bgheight);

        // sendbutton
        sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener()); 
        sendButton.setBounds(bgwidth - 150, bgheight - 600, 100, 50);
        backgroundLabel.add(sendButton);

        // outgoing
        outgoing = new JTextField();   
        outgoing.setBounds(50, 50, 400, 50);
        backgroundLabel.add(outgoing);

        // mainFrame
        mainFrame = new JFrame("Ludicrously Simple Chat Client");
        mainFrame.getContentPane().add(BorderLayout.CENTER, backgroundLabel);

        try {
            ImageIcon icon = new ImageIcon(SimpleChatClientA2.class.getResource("imgs/logo.png"));
            mainFrame.setIconImage(icon.getImage());
            } catch (Exception exception) {
                System.out.println("Ошибка при загрузке изображения");
                exception.printStackTrace();
        }

        setUpNetWorking();
        
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setSize(bgwidth, bgheight);
        mainFrame.setVisible(true);
    }
    

    public static void main(String[] args) {
        SimpleChatClientA scca = new SimpleChatClientA();
        scca.go();
    }
}
