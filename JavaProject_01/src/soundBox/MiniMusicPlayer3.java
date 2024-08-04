package soundBox;

import java.awt.*;
import javax.sound.midi.*;
import javax.swing.*;


public class MiniMusicPlayer3 {
    static JFrame frame = new JFrame("My first music klip");
    static  MyDrawPanel mdp;

    public void setUpGui() {
        mdp = new MyDrawPanel();
        frame.setContentPane(mdp);
        frame.setBounds(30, 30, 300, 300);
        frame.setVisible(true);
    }
    
    public void go() {
        setUpGui();

        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addControllerEventListener(mdp, new int[] {127});
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            int r = 0;
            for (int i = 0; i < 60; i += 4) {
                r = (int) (Math.random() * 50 + 1);
                track.add(makeEvent(144, 1, r, 100, i));
                track.add(makeEvent(176, 1, 127, 0, i));
                track.add(makeEvent(128, 1, r, 100, i + 2));
            }

            sequencer.setSequence(seq);
            sequencer.start();
            sequencer.setTempoInBPM(120);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
            MidiEvent event = null;

            try {
                ShortMessage smessage = new ShortMessage();
                smessage.setMessage(comd, chan, one, two);
                event = new MidiEvent(smessage, tick);

            } catch (Exception ex) { }
            return event;
        }

    class MyDrawPanel extends JPanel implements  ControllerEventListener {
        boolean msg = false;

        public void controlChange(ShortMessage event) {
            msg = true;
            repaint();
        }

        public void paintComponent(Graphics g) {

            if (msg) {
                Graphics2D g2d = (Graphics2D) g;

                int red = (int) (Math.random() * 255);
                int green = (int) (Math.random() * 255);
                int blue = (int) (Math.random() * 255);

                g.setColor(new Color(red, green, blue));

                int height = (int) (Math.random() * 120 + 10);
                int width = (int) (Math.random() * 120 + 10);

                int x_position = (int) (Math.random() * 40 + 10);
                int y_position = (int) (Math.random() * 40 + 10);

                g.fillRect(x_position, y_position, height, width);
                msg = true;
            }
        }
    }

    public static void main(String[] args) {
        
        MiniMusicPlayer3 mini = new MiniMusicPlayer3();
        mini.go();
    }
}
