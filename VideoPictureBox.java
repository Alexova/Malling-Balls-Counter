/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Icon;

/**
 *
 * @author Alexova
 */
public class VideoPictureBox extends JFrame {
    String adress = new String("VideoSequence\\");
    String defaultImg = new String(adress + "default.jpg");
    private int width = 860;
    private int height = 460;
    private Timer timer;
    private Image image = null;
    int countForImage;
    Image img;
    
    ImageAnalyzer analyzer = new ImageAnalyzer();
    
    public ImageAnalyzer getAnalyzer(){return analyzer;}
    

public VideoPictureBox() {
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setVisible(true);
        image =  new ImageIcon(defaultImg).getImage();
        count = 1000000;
        timer = new Timer();
        this.setLayout(null);
        this.setLocation(460,0);
        
      
        
    }

    private int count;
    TimerTask animate = new TimerTask() {
        boolean allowToCheck = true;
        @Override
        public synchronized void run() {
            if (count < 1002612) image = new ImageIcon(generateName(count)).getImage();
            repaint();
            
            if (checkLine(allowToCheck)) {
               boolean picRes  = analyzer.check(image, count);
                
                if (picRes == false) {
                    countForImage = count + 35;
                    allowToCheck = false;
                }
                
                
                
                
            } 
            if (count == countForImage){
                            
                allowToCheck = true;
            }
            count++;
            if (count == 1002612) System.out.println(analyzer.getAllBalls());
        }
    };

     public void paint(Graphics canvas) {
         
         
        canvas.drawImage(image, 0, 0,  null);
        
    }
     
    
     public String generateName(int count){
         String fileName = new String(count + ".jpeg");
         fileName = fileName.substring(1);
         fileName = adress+ "img_" + fileName;
         return fileName;
     }
 
    public void go(){
        timer.schedule(animate, 1,33);
        
    }
    
    public boolean checkLine(boolean allowedToCheck){
        boolean result = false;
        if (allowedToCheck == true){
            BufferedImage img = convertToBufferedImage(image);
            
             for (int i = 150; i < 780;i++){
                 Color col = new Color(img.getRGB(i, 300));
                 if (col.getRed() >= 150 && col.getBlue() > 150){
                     result = true;
                 }
                 
             }
        }
        return result;
    }
    
    public BufferedImage convertToBufferedImage(Image image)
{
    BufferedImage newImage = new BufferedImage(
        image.getWidth(null), image.getHeight(null),
        BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = newImage.createGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();
    return newImage;
}
    
    
}


    

