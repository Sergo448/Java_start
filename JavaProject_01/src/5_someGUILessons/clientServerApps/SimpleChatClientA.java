import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleChatClientA {
    JTextField outgoing;
    PrintWriter writer;
    Socket socket;

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
        JFrame frame = new JFrame("Ludicrously Simple Chat Client");
        JPanel mainPanel = new JPanel();
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());      
        
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        setUpNetWorking();
        frame.setSize(1000, 700);
        frame.setVisible(true);
    }
    

    public static void main(String[] args) {
        SimpleChatClientA scca = new SimpleChatClientA();
        scca.go();
    }
}
