/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Alexova
 */
public class MainFrame extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
    FromTo red = new FromTo("Red", 180, 255, 0, 255);
    FromTo green = new FromTo("Green", 0, 255, 0, 255);
    FromTo blue = new FromTo("Blue", 150, 255, 0, 255);
    FromTo size = new FromTo("Size", 350, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
    FromTo leftRight = new FromTo("Get piece LR", 160, 730, 0, 854);
    FromTo topBottom = new FromTo("Get piece BT", 200, 380, 0, 480);
    GridLayout g = new GridLayout(1, 2);
    JPanel menu = new JPanel();
    JPanel pictures = new JPanel();
    JPanel baseImage = new JPanel();
    JPanel analyzedImage = new JPanel();
    JTextField weight = new JTextField();
    JButton start = new JButton("Start");
    JButton reset = new JButton("Reset");
    JPanel buttonPanel = new JPanel();
    
    Listener l = new Listener();
    VideoPictureBox box = new VideoPictureBox();
    
    public MainFrame(){
        
        this.setName("Mill Balls Counter. Diploma project by Tarasenko Mariya");
        this.setSize(450, screenSize.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(menu);
        menu.add(red);
        menu.add(green);
        menu.add(blue);
        menu.add(size);
        menu.add(leftRight);
        menu.add(topBottom);
         menu.add(weight);
        weight.setSize(20, 10);
        weight.setText("750");
        pictures.setLayout(null);
        menu.setLayout(new GridLayout(8,1));
        menu.add(buttonPanel);
        buttonPanel.add(start);
        buttonPanel.setLayout(new GridLayout(3,1));
        buttonPanel.add(reset);
        
        start.addActionListener(l);
        reset.addActionListener(l) ;   
        
        
    }
    public class Listener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
       Object sourse = e.getSource();
       if (sourse == start)  box.go();
       
        if (sourse == reset){
        box.getAnalyzer().setRed(red.fromValue(), red.toValue());
        box.getAnalyzer().setGreen(green.fromValue(), green.toValue());
        box.getAnalyzer().setBlue(blue.fromValue(), blue.toValue());
        box.getAnalyzer().setSize(leftRight.fromValue(), leftRight.toValue(), topBottom.fromValue(), topBottom.toValue());
        box.getAnalyzer().setMinWeight(size.fromValue());
        box.getAnalyzer().setWeight(Integer.parseInt(weight.getText()));
    }
    
    
    
    }
      
    
}
    
    
}

