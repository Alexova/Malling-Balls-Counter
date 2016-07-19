package frame;


import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;



/**
 * last changed on 05/06/2016 14:27
 * Class desribes a number of points, which form an object which contains balls
 * @author Alexova
 */
public class BallObject {
    
    private ArrayList<Point> list = new ArrayList<Point>(); //contains all points of an object
    private ArrayList<Point> extremum = new ArrayList<Point>(); //contains points between balls
    
   
    private Color col; //visualization color of an object
    private int left = 1000000; //left border coord
    private int right = 0; //right border coord
    private int top = 0; //top border coord
    private int bottom = 1000000; //low border coord
    private int width; //width of an object
    private int height; // height of an object
    private int balls; //number of balls counted
    private int [] topBorder;//an array with data of top border of object
    private int[] lowBorder;//an array with data of low border of object
    private int [] rightBorder; //an array with data of right border of object
    private int[] leftBorder;//an array with data of left border of object
    
    
    
    /**
     * BallObject Constructor
     * @param i x of a point
     * @param j y of a point
     * @param col color of an object
     */
    public BallObject (int i, int j, Color col){
        list.add(new Point(i, j));
        if (i > right) right = i;
        if (i < left) left = i;
        if (j > top) top = j;
        if (j < bottom) bottom = j;
        this.col = col;
    }
    /**
     * adds a point to list
     * @param i x of a point added to a list
     * @param j y of a point added to a list
     * also checks if it is left or riight border
     */
    public void add(int i, int j){
        list.add(new Point(i, j));
        if (i > right) right = i;
        if (i < left) left = i;
        if (j > top) top = j;
        if (j < bottom) bottom = j;
    }
    
 
    
    /**
     * calculates the width and heght of an object
     */
    public void countWidthHeight(){
        width = right - left;
        height = top - bottom;
        
        //print();
    }
    /*Developers method for analyzing the object
    public void print(){
        System.out.println("Size " + list.size() + " width " + width + " height " + height);
    }
    */
     
    /**
     * method which finds borders from the points already found;
     */    
        
    public void findBorders(){
        lowBorder = new int[right - left + 1];
        topBorder = new int [right - left + 1];
        rightBorder = new int [- bottom + top + 1];
        leftBorder = new int[- bottom + top + 1];
        for(int i = 0; i < lowBorder.length; i++){
            lowBorder[i] = 5;
            topBorder[i] = 144;
        }
        for (int i = 0; i < leftBorder.length; i++){
            leftBorder[i] = 0;
            rightBorder [i] = right+1;
        }
        
        for (int i = 0; i < list.size(); i++){
            int k = list.get(i).y;
            int m = list.get(i).x;
            
            
            if (k  > lowBorder[m - left]){
                lowBorder[m - left] = k;
            }
            if (k < topBorder[m - left]){
                topBorder[m - left] = k;
            }
            if (m < rightBorder[k - bottom]){
                rightBorder[k-bottom] = m;
            }
            if (m > leftBorder[k - bottom]){
                leftBorder[k - bottom ] = m;
            }
            
        }
    }
    
    
    public void getExtremum(){
        int k = 10;
                
        for(int i = 0; i < lowBorder.length-k; i++){
            
            int max = lowBorder[i];
            int minKff = i;
            
            for (int j = i + 1; j < i + 11; j++){
                if(lowBorder[j] < max) {
                    max = lowBorder[j];
                    minKff = j;
                }
            }
            if(max != lowBorder[i] && max != lowBorder[i+k]){
                extremum.add(new Point(max, minKff+left));
            }            
            
            
        }
        
        for (int i = 0; i < topBorder.length - k; i++){
            int max = topBorder[i];
            int minKff = i;
            for (int j = i + 1; j < i + 11; j++){
                if(topBorder[j]> max){
                    max = topBorder[j];
                    minKff = j;
                }
            }    
            if(max != topBorder[i] && max!= topBorder[i + k]){
                extremum.add(new Point (max, minKff+left));
            }
        }
    }
    
    /**
     * Mothod to find out how many balls are there in the ball object
     */
    public void countBalls(int k){
        if (list.size() <= 1170) balls = balls + 1;
        else if (list.size() <= 1.6 * k) balls = 1;
        else if (list.size() <= 2.5 * k) balls = 2;
        else if (list.size() <= 3.6 * k) balls =  3;
        else if (list.size() <= 4.7 * k) balls = 4;
        else if (list.size() <= 5.8 * k) balls = 5;
        else if (list.size() <= 7 * k) balls = 6;
        else if (list.size() <= 8.4 * k) balls = 7;
        else if (list.size() <= 9.6 * k) balls = 8;
        else if (list.size() <= 11.4 * k) balls = 9;
        else if (list.size() <= 12.4 * k) balls = 10;
        else if (list.size() <= 13.7 * k)balls = 11;
        else if (list.size() <= 14.3 * k) balls = 12;
        else if (list.size() <= 15.5 * k) balls = 13;
        else balls = balls + list.size() / (k + 100);
    }
    
    public int getBalls(int k) {countBalls(k);return balls;}
    
       /**
     * 
     * @return number of points in an object
     */
    public int getSize(){return list.size();}
    /**
     * 
     * @return color of an object
     */
    public Color getColor() {return col;}
    public int getLeft(){return left;}
    public int getRight(){return right;}
    public int getTop(){return top;}
    public int getBottom(){return bottom;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public ArrayList<Point> getExtremumList(){return extremum;}
    public int getTopBorderLength(){return topBorder.length;}
    public int getLowBorderLength(){return lowBorder.length;}
    public int getLeftBorderLength(){return leftBorder.length;}
    public int getRightBorderLength(){return rightBorder.length;}
    public int getTopBorder(int i){return topBorder[i];}
    public int getLowBorder(int i){return lowBorder[i];}
    public int getLeftBorder(int i){return leftBorder[i];}
    public int getRightBorder(int i){return rightBorder[i];}
    
}
