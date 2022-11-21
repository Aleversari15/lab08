package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();

    public SimpleGUI(final Controller controller){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        this.frame.getContentPane().add(panel);
        panel.setLayout(new BorderLayout( ));
        final JTextArea text = new JTextArea();
        final JButton button = new JButton ("Save");
        panel.add(button,BorderLayout.SOUTH);
        panel.add(text);

        button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                try{
                    controller.addStringToFile(text.getText());
                }
                catch(IOException e){
                    JOptionPane.showMessageDialog(frame,e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int)screen.getWidth();
        final int height = (int)screen.getHeight();
        frame.setSize(width/PROPORTION, height/PROPORTION);
        frame.setLocationByPlatform(true);
    }

    public void display(){
        frame.setVisible(true);
    }

    //starts the graphical application
    public static void main(String[] args){
        SimpleGUI newSG = new SimpleGUI(new Controller());
        newSG.display();
    }
}
