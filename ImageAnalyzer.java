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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Alexova
 */
public class ImageAnalyzer extends Thread{
    Database db = new Database();
    private int allBalls = 0;
    private int redFrom = 150;
    private int redTo = 235;
    private int greenTo = 255;
    private int blueTo = 255;
    private int greenFrom = 0;
    private int blueFrom = 150;
    private int left = 160;
    private int right = 730;
    private int top = 180;
    private int bottom = 400;
    private int width = right - left;
    private int height = bottom - top;
    private int minWeight = 350;
     private int WEIGHT = 750;
    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    private ArrayList<BallObject> list;
    public void setRed(int from, int to){ redFrom = from; redTo = to;}
    public void setGreen(int from, int to){ greenFrom = from; greenTo = to;}
    public void setBlue(int from, int to){ blueFrom = from; blueTo = to;}
    public void setSize(int left, int right, int top, int bottom){this.left = left; this.right = right; this.top = top; this.bottom = bottom; }
    private BufferedImage base;
    public void setWeight(int WEIGHT){this.WEIGHT = WEIGHT;}
    
    public ImageAnalyzer(){
        frame.setSize(860, 250);
        frame.setLocation(460,460);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(label);
        
        
    }
    
    public  boolean  check(Image pic, int num){
        list = new ArrayList<BallObject>();
        base = convertToBufferedImage(pic);
        base = base.getSubimage(left, top, right-left, bottom-top);
        width = right - left;
        height = bottom - top;
        int sum = 0;
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                           
               Color col = new Color(base.getRGB(i, j));
               if (col.getRed() >= redFrom && col.getRed() <= redTo && col.getBlue() >= blueFrom && col.getBlue()<= blueTo && col.getGreen() <= greenTo && col.getGreen() >= greenFrom){
                   Color col2 = generateColor();
                   list.add(new BallObject(i, j, col2));
                   fillObject(i, j, col2);
               }
            }
        }
        
        for (int i = list.size() - 1; i >= 0 ; i--){
            
            if(list.get(i).getSize() < this.minWeight)list.remove(i);
        }
        
        for (int i = list.size() - 1; i >= 0 ; i--){
           list.get(i).findBorders();
           for(int j = 0; j < list.get(i).getTopBorderLength(); j++){
               base.setRGB(j + list.get(i).getLeft(), list.get(i).getTopBorder(j), Color.RED.getRGB());
               base.setRGB(j + list.get(i).getLeft(), list.get(i).getLowBorder(j), Color.RED.getRGB());
            }
           for(int j = 0; j < list.get(i).getLeftBorderLength(); j++ ){
                base.setRGB(list.get(i).getLeftBorder(j),  j + list.get(i).getBottom(), Color.RED.getRGB());
                base.setRGB(list.get(i).getRightBorder(j), j + list.get(i).getBottom(), Color.RED.getRGB());
            }
           
           list.get(i).getExtremum();
           for(int j = 0; j < list.get(i).getExtremumList().size(); j++){
               base.setRGB(list.get(i).getExtremumList().get(j).y, list.get(i).getExtremumList().get(j).x, Color.ORANGE.getRGB());
           
           }
           sum = sum + list.get(i).getBalls(WEIGHT);
        }
      
        
        Graphics g = base.getGraphics();
        g.drawString("Number of balls:"+ String.valueOf(sum) + " Current time:" + getDateTime(), 10, 10);
        allBalls += sum;
        
        if (list.size() > 0){
        pic = base;
        label.setIcon(new ImageIcon(pic));
        
        String add = new String("resultingPics\\" + num + ".png");                     
        try {
            ImageIO.write(base, "png", new File(add));
            
        } catch (IOException ex) {
            Logger.getLogger(ImageAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.insert(sum);
        return false;
        }  else return true;
    }
    
    
    private Color generateColor(){
       Random random = new Random();
           
        int red = random.nextInt(100);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);
        return new Color(red, green, blue); 
    }
    
    
    private void fillObject (int i, int j, Color col2){
        Color col = new Color(base.getRGB(i, j));
        if(col.getRed() >= redFrom && col.getRed() <= redTo && col.getBlue() >= blueFrom && col.getBlue()<= blueTo && col.getGreen() <= greenTo && col.getGreen() >= greenFrom){
            base.setRGB(i, j, col2.getRGB());
            list.get(list.size()-1).add(i, j);
            if (i > 1 && i < width-1 && j > 0 && j < height-1){
                fillObject(i+1, j, col2);
                fillObject(i-1, j, col2);
                fillObject(i, j+1, col2);
                fillObject(i, j-1, col2);
            }
        }    
    }
    
    
    public BufferedImage convertToBufferedImage(Image image){
    BufferedImage newImage = new BufferedImage(
        image.getWidth(null), image.getHeight(null),
        BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = newImage.createGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();
    return newImage;
}
    public int getRedFrom(){return redFrom;}
    public int getBlueFrom(){return blueFrom;}
    public int getGreenFrom(){return greenFrom;}
    public int getRedTo(){return redTo;}
    public int getBlueTo(){return blueTo;}
    public int getGreenTo(){return greenTo;}
    public void setMinWeight(int k){this.minWeight = k;}
    public int getAllBalls(){return allBalls;}
    public int getSize(){return list.size();}
    private String getDateTime() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Date date = new Date();

        return dateFormat.format(date);

    }
    
}
