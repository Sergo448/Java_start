import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleChatClientA2 {
    private JTextField outgoing;
    private PrintWriter writer;
    private Socket socket;
    private JLabel backLabel;

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
        }
    }


    public void go() {
        
        // создаем главное окно
        JFrame frame = new JFrame("Ludicrously Simple Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        outgoing = new JTextField(40);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());

        // Вставляем логотип в верхний угол окна
        try {
            ImageIcon icon = new ImageIcon(SimpleChatClientA2.class.getResource("logo.png"));
            frame.setIconImage(icon.getImage());
            } catch (Exception exception) {
                System.out.println("Ошибка при загрузке изображения");
                exception.printStackTrace();
        }

        try {
            // Загружаем изображение
            URL url = SimpleChatClientA2.class.getResource("background.jpg");
            
            // Устанавливаем изображение в качестве фона
            JLabel label = new JLabel(new ImageIcon(url));
            frame.setContentPane(label);
            } catch (Exception exception) {
                System.out.println("Файл background.jpg не найден");
                exception.printStackTrace();
        }


        // Располагаем все на frame
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        frame.getContentPane().add(mainPanel);

        
        setUpNetWorking();
        frame.setSize(800, 500);
        frame.setVisible(true); 
    }
    

    public static void main(String[] args) {
        SimpleChatClientA2 scca = new SimpleChatClientA2();
        scca.go();
    }
}
