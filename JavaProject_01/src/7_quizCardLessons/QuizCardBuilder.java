import java.util.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
 * QuizCardBuilder
 */
public class QuizCardBuilder {

    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;
    private JFrame frame;

    private int jTextAreaRows = 6;
    private int jTextAreaColumns = 20;
    private boolean wrap = true;

    public static void main(String[] args) {
        QuizCardBuilder builder = new QuizCardBuilder();
        builder.go();

    }

    public void go() {
        // Forming GUI

        frame = new JFrame("Quiz Card Builder");
        JPanel maiPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);
        
        question = new JTextArea(jTextAreaRows, jTextAreaColumns);
        question.setLineWrap(wrap);
        question.setWrapStyleWord(wrap);
        question.setFont(bigFont);

        JScrollPane qScroller = new JScrollPane(question);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        answer = new JTextArea(jTextAreaRows, jTextAreaColumns);
        answer.setLineWrap(wrap);
        answer.setWrapStyleWord(wrap);
        answer.setFont(bigFont);

        JScrollPane aScroller = new JScrollPane(answer);
        aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton nexButton = new JButton("Nex card");

        cardList = new ArrayList<QuizCard>();

        JLabel qLabel = new JLabel("Question:");
        JLabel aLabel = new JLabel("Answer:");

        maiPanel.add(qLabel);
        maiPanel.add(aLabel);
        maiPanel.add(qScroller);
        maiPanel.add(aScroller);
        maiPanel.add(nexButton);

        nexButton.addActionListener(new NextCardListener());
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, maiPanel);
        frame.setSize(500, 600);
        frame.setVisible(true);
    }

    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

    private void saveFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (QuizCard card: cardList) {
                writer.write(card.getQuestion() + "/");
                writer.write(card.getAnswer() + "\n");
            }
            writer.close();
        } catch (IOException exception) {
            System.out.println("Couldn't write the cardList out");
            exception.printStackTrace();
        }
    }


    public class NextCardListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            QuizCard qCard = new QuizCard(question.getText(), answer.getText());
            cardList.add(qCard);
            clearCard();
        }
    }

    public class NewMenuListener implements ActionListener {
        public void actionPerformed (ActionEvent actionEvent) {
            cardList.clear();
            clearCard();
        }
    }

    public class SaveMenuListener implements ActionListener {
        public void actionPerformed (ActionEvent actionEvent) {
            QuizCard quizCard = new QuizCard(question.getText(), answer.getText());
            cardList.add(quizCard);

            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    
        
    }
}