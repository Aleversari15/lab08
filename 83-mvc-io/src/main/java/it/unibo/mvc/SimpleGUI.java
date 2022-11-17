package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();

    public SimpleGUI(final SimpleController controller){
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JTextField textField = new JTextField();
        final JTextArea textArea = new JTextArea();
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        final JButton button1 = new JButton("Print");
        final JButton button2 = new JButton("Show history");
        panel.add(panel2,BorderLayout.SOUTH);
        panel.add(textField,BorderLayout.NORTH);
        panel2.add(button1,BorderLayout.WEST);
        panel2.add(button2);
        panel.add(textArea);
        frame.getContentPane().add(panel);

        button1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                controller.setNextString(textField.getText());
                controller.printString();
            }

        });

        button2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                textArea.setText((controller.getHistory()).toString());
            }

        });

    }

    private void display(){
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int)screen.getWidth();
        final int height = (int)screen.getHeight();
        frame.setSize(width/PROPORTION, height/PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main (String[] args){
        SimpleGUI gui = new SimpleGUI(new SimpleController());
        gui.display();
    }

}
