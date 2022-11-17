package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final int PROPORTION = 4;
    private final JFrame frame = new JFrame();

    public SimpleGUIWithFileChooser(final Controller controller){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout( ));
        final JTextArea text = new JTextArea();
        final JButton button = new JButton ("Save");
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    controller.addStringToFile(text.getText());
                }
                catch(IOException e){
                    JOptionPane.showMessageDialog(frame,e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(text,BorderLayout.CENTER);
        panel.add(button,BorderLayout.SOUTH);
        final JTextField text2 = new JTextField();
        text2.setText(controller.getPath(controller.getFile()));
        text2.setEditable(false);
        final JButton button2 = new JButton("Browse...");
        button2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final JFileChooser fileChosen = new JFileChooser();
                final int result = fileChosen.showSaveDialog(frame);
                switch(result){
                    case JFileChooser.APPROVE_OPTION: {
                        controller.setFile(fileChosen.getSelectedFile());
                        text2.setText(controller.getPath(controller.getFile()));
                        break;
                    }
                    case JFileChooser.CANCEL_OPTION: {
                        break;
                    }
                    default:{
                        JOptionPane.showMessageDialog(frame, result, "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }  
            }

        });
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(text2,BorderLayout.CENTER);
        panel2.add(button2,BorderLayout.LINE_END);
        panel.add(panel2,BorderLayout.NORTH);
        frame.setContentPane(panel);
        
    }

    private void display(){
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int width = (int)screen.getWidth();
        final int height = (int)screen.getHeight();
        frame.setSize(width/PROPORTION, height/PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    
    public static void main(String[] args){
        final SimpleGUI newSGWithFileChooser = new SimpleGUI(new Controller());
        newSGWithFileChooser.display();
    }

}
