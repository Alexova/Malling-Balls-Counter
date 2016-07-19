/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PluMin extends JPanel{
    private JButton plu = new JButton("+");
    private JButton min = new JButton("-");
    GridLayout g = new GridLayout(2,1);
    
    public JButton getPlus(){return plu;}
    public JButton getMinus(){return min;}
    
   public PluMin(){
       this.setLayout(g);
       this.add(plu);
       this.add(min);
       plu.setSize(20, 10);
       min.setSize(20, 10);
       this.setSize(20, 20);
   }
}
