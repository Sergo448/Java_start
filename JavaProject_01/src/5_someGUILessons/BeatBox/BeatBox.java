/* PACKAGE */
//package someGUILessons.BeatBox;

/* IMPORTS */
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;
import java.util.*;
import javax.sound.midi.*;
import javax.swing.text.html.HTMLDocument;


public class BeatBox {
    
    /* VARIABLES */
    JPanel maiPanel;
    ArrayList<JCheckBox> checkBoxsList; // Массив для хранения флажков
    Sequencer sequencer;
    Sequence sequence;
    Track track;
    JFrame theFrame;

    /* Названия инструментов в виде строкового массива, предназначенные для 
     * создания меток в пользовательском интерфейсе (на каждый ряд)
     */
    String[] instrumentsNames = {
        "Bass Drum", 
        "Closet Hi-Hat",
        "Open Hi-Hat",
        "Acoustic Snare",
        "Crash Cymbal",
        "Hand Clap",
        "High Tom",
        "Hi Bongo",
        "Maracas",
        "Whistle",
        "Low Conga",
        "Cowbell", 
        "Vibraslap",
        "Low-mid Tom",
        "High Agogo",
        "Open Hi Conga"
    };

    /* Барабанные клавиши */
    int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public static void main(String[] args) {
        new BeatBox().buildGUI();
    }

    public void buildGUI() {
        theFrame = new JFrame("Cyber BeatBox");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout layout = new BorderLayout(); // BorderLayout - наклажывает компоненты друг на друга
        JPanel background = new JPanel(layout);

        /* Пустая граница позволяет создать поля между краями панели и местом размещения компонентов */
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        
        checkBoxsList = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton buttonStart = new JButton("Start");
        buttonStart.addActionListener(new MyButtonStartListener());
        buttonBox.add(buttonStart);

        JButton buttonStop = new JButton("Stop");
        buttonStop.addActionListener(new MyButtonStopListener());
        buttonBox.add(buttonStop);

        JButton buttonUpTempo = new JButton("Tempo Up");
        buttonUpTempo.addActionListener(new MyButtonUpTempoListener());
        buttonBox.add(buttonUpTempo);

        JButton buttonDownTempo = new JButton("Tempo Down");
        buttonDownTempo.addActionListener(new MyButtonDownTempoListener());
        buttonBox.add(buttonDownTempo);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (int i = 0; i < 16; i++) {
            nameBox.add(new Label(instrumentsNames[i]));
        }

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        theFrame.getContentPane().add(background);

        GridLayout grid = new GridLayout(16, 16); // Матрица флажков
        grid.setVgap(1);
        grid.setHgap(2);
        maiPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, maiPanel);

        for (int i = 0; i < 256; i++) {
            /* Хаполняем матрицу флажков JCheckBox объектами */
            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected(false);
            checkBoxsList.add(checkBox);
            maiPanel.add(checkBox);
        }

        setUpMidi();

        theFrame.setBounds(50, 50, 300, 300);
        theFrame.pack();
        theFrame.setVisible(true);
    }

    public void setUpMidi() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();

            sequence = new Sequence(Sequence.PPQ, 4);

            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (Exception err) {err.printStackTrace();}
    }

    /* MAIN 
     * Преобразуем состояние флажков в MIDI - события
     * и добавляем на дорожку
     */
    public void buildTrackAndStart() {
        int[] trackList = null; // Для хранения значеня всех 16 тактов инструментов

        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for (int i = 0; i < 16; i++) {
            trackList = new int[16];
            int key = instruments[i];
            
            for (int j = 0; j < 16; j++) {
                JCheckBox jCheckBox = (JCheckBox) checkBoxsList.get(j + (16 * i));

                if (jCheckBox.isSelected()) {
                    trackList[j] = key;
                } else {
                    trackList[j] = 0;
                }
            }

            makeTracks(trackList);
            track.add(makeEvent(176, 1, 127, 0, 16));            
        }

        track.add(makeEvent(192, 9, 1, 0, 15));

        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (Exception err) {err.printStackTrace();}
    }

    /* INNER CLASS 1 */
    public class MyButtonStartListener implements ActionListener {
        public void actionPerformed (ActionEvent actionEvent) {
            buildTrackAndStart();
        }
    }

    /* INNER CLASS 2 */
    public class MyButtonStopListener implements  ActionListener {
        public void actionPerformed (ActionEvent actionEvent) {
            sequencer.stop();
        }
    }

    /* INNER CLASS 3 */
    public class MyButtonUpTempoListener implements ActionListener {
        public void actionPerformed (ActionEvent actionEvent) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor( (float) (tempoFactor * 1.03));
        }
    }

    /* INNER CLASS 4 */
    public class MyButtonDownTempoListener implements ActionListener {
        public void actionPerformed (ActionEvent actionEvent) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor( (float) (tempoFactor * 0.97));
        }
    }

    public void makeTracks(int[] list) {
        for (int i = 0; i < 16; i++) {
            int key = list[i];

            if (key != 0) {
                track.add(makeEvent(144, 9, key, 100, i));
                track.add(makeEvent(128, 9, key, 100, i + 1));
            }
        }
    }

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {

            ShortMessage shortMessage = new ShortMessage();
            shortMessage.setMessage(comd, chan, one, two);
            event = new MidiEvent(shortMessage, tick);

        } catch (Exception err) {err.printStackTrace();}
        return event;
    }


    // SERIALIZING AND SAVING
    public class MySendListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            boolean[] checkBoxState = new boolean[256];

            for (int i = 0; i < 256; i++) {
                JCheckBox checkBox = (JCheckBox) checkBoxsList.get(i);
                if (checkBox.isSelected()) {
                    checkBoxState[i] = true;
                }
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File("CheckBox.ser"));
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(checkBoxState);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public class MyReadInListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            boolean[] checkBoxState = new boolean[256];

            try {
                FileInputStream fileInputStream = new FileInputStream(new File("CheckBox.ser"));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                checkBoxState = (boolean[]) objectInputStream.readObject();

            } catch (Exception exception) {
                exception.printStackTrace();
            }

            for (int i = 0; i < 256; i++) {
                JCheckBox checkBox = (JCheckBox) checkBoxsList.get(i);
                if (checkBoxState[i]) {
                    checkBox.setSelected(true);
                } else { 
                    checkBox.setSelected(false);
                }
            }

            sequencer.stop();
            buildTrackAndStart();
        }
    }
}
