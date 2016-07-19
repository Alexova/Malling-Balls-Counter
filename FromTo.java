/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Alexova
 */
public class FromTo extends JPanel{
    //JPanel board = new JPanel();
    JLabel name;
    PluMin f = new PluMin();
    PluMin t = new PluMin();
    FlowLayout layout = new FlowLayout();
    private JTextField from;
    private JTextField to;
    Listener l = new Listener();
    int top;
    int floor;
    public FromTo(String boardName, int fromNumber, int toNumber, int floor, int top){
        this.name = new JLabel(boardName);
        from = new JTextField(fromNumber);
        to = new JTextField(toNumber);
        this.top = top;
        this.floor = floor;
        
        from.setColumns(5);
        to.setColumns(5);
        
        from.setText(String.valueOf(fromNumber));
        to.setText(String.valueOf(toNumber));
        this.setLayout(layout);
        this.add(name);
        this.add(new JLabel(" from "));
        this.add(f);
        this.add(from);
        
        this.add(new JLabel(" to "));
        this.add(t);
        this.add(to);
        
        f.getPlus().addActionListener(l);
        f.getMinus().addActionListener(l);
        t.getMinus().addActionListener(l);
        t.getPlus().addActionListener(l);
        
        
        
    }
    public JTextField getTo(){return to;}
    public JTextField getFrom(){return from;}
    public int toValue(){return Integer.valueOf(to.getText());}
    public int fromValue(){return Integer.valueOf(from.getText());}
    public class Listener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
          Object sourse = e.getSource();
          
          /* При увеличении нижней границы проверяем значение текстового поля
             Если поле+1 равно верхней границе, увеличиваем верхнюю границу на 1 тоже.
             Если верхняя граница максимальна, то оставляем нижнюю без изменений.
             Минимальное значение равно floor. Максимальное top -1
          */
            if (sourse == f.getPlus()){
              int topVal = Integer.parseInt(getTo().getText());
              int lowVal = Integer.parseInt(getFrom().getText());
              
              if (lowVal + 1 < topVal){
                  getFrom().setText(String.valueOf(lowVal + 1));
              } else if (lowVal + 1 == topVal && lowVal != top - 1){
                  getFrom().setText(String.valueOf(lowVal + 1));
                  getTo().setText(String.valueOf(topVal + 1));
              }
            }
             /* При увеличении верхней границы проверяем чтобы она не была больше top.
            Если текущее значение равно top, оставляем неизменным
            */
            if (sourse == t.getPlus()){
              int num = Integer.parseInt(getTo().getText());
              if(num < top) getTo().setText(String.valueOf(num + 1));
            }
             /* При уменьшении нижней границы проверяем чтобы она не была меньше floor.
            Если текущее значение равно floor, оставляем неизменным
            */
            if (sourse == f.getMinus()){
               int num = Integer.parseInt(getFrom().getText());
               if(num > floor) getFrom().setText(String.valueOf(num - 1));
                
              
            }
            /* При уменьшении нижней границы проверяем значение текстового поля
             Если поле-1 равно нижней границе,уменьшаем нижнюю границу на 1 тоже.
             Если нижняя граница минимальна, то оставляем верхнюю без изменений.
             Минимальное значение равно floor. Максимальное top -1
          */
            if (sourse == t.getMinus()){
              int topVal = Integer.parseInt(getTo().getText());
              int lowVal = Integer.parseInt(getFrom().getText());
              
              if (topVal - 1 > lowVal){
                  getTo().setText(String.valueOf(topVal - 1));
              } else if (topVal - 1 == lowVal && topVal != floor + 1){
                  getTo().setText(String.valueOf(topVal - 1));
                  getFrom().setText(String.valueOf(lowVal - 1));
              }
            
            }
        }
    }
}


