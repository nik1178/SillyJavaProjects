//import javax.swing.*;

import java.awt.*;
//import java.awt.event.*;
import java.util.*;
public class MyRunnable extends GridLabel {


    int x1=0;
    int x2=0;
    int y1=0;
    int y2=0;

    volatile static int DELAY = 100;

    volatile int stopButtonCounter = 0;
    volatile boolean fakeBoolean = true;
    volatile boolean shouldReturn = false;
    @Override
    public void run() {
        while(fakeBoolean){
            
            algo(0, 0);
            shouldReturn=true;
            if(stopButtonCounter==1) MyFrame.startButtonStop();
            stopButtonCounter=0;
            x1=0;
            x2=0;
            y1=0;
            y2=0;
        }
    }



    double LINE_SIZE = GridLabel.LINE_SIZE;
    public void algo(int vertical, int horizontal)
    {
        //System.out.println(shouldReturn);
        if(shouldReturn) return;
        stopButtonCounter=1;
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        if(horizontal<MyFrame.gridSize)
        {
            x1=horizontal;
            y1=y2;
            x2=horizontal+1;
            
            repaint();
            algo(vertical, horizontal+1);

            x1=horizontal;
            y1=y2;
            x2=horizontal+1;
            
            repaint();
        }
        else{
            MyFrame.howManyPaths++;
        }
        if(vertical<MyFrame.gridSize && horizontal<MyFrame.gridSize)
        {
            x2=x1;
            y2=vertical+1;
            y1=vertical;

            repaint();
            algo(vertical+1, horizontal);
        }
    }
    static HashMap<String,Integer> hashx1 = new HashMap<>();
    static HashMap<String,Integer> hashx2 = new HashMap<>();
    static HashMap<String,Integer> hashy1 = new HashMap<>();
    static HashMap<String,Integer> hashy2 = new HashMap<>();
    @Override
    public void paint(Graphics g){
        LINE_SIZE = GridLabel.LINE_SIZE;
        super.paint(g);
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        int tempx1 = (int)(this.x1*LINE_SIZE);
        int tempy1 = (int)(this.y1*LINE_SIZE);
        int tempx2 = (int)(this.x2*LINE_SIZE);
        int tempy2 = (int)(this.y2*LINE_SIZE);
        if(!(hashx1.containsKey(tempx1+""+tempx2+""+tempy1+""+tempy2))){
            hashx1.put(tempx1+""+tempx2+""+tempy1+""+tempy2,tempx1);
            hashx2.put(tempx1+""+tempx2+""+tempy1+""+tempy2,tempx2);
            hashy1.put(tempx1+""+tempx2+""+tempy1+""+tempy2,tempy1);
            hashy2.put(tempx1+""+tempx2+""+tempy1+""+tempy2,tempy2);
        }

        g2D.setPaint(Color.red);
        g2D.setStroke(new BasicStroke(3));
        for(String i : hashx1.keySet()){
            tempx1 = hashx1.get(i);
            tempy1 = hashy1.get(i);
            tempx2 = hashx2.get(i);
            tempy2 = hashy2.get(i);
            g2D.drawLine(tempx1, tempy1, tempx2, tempy2);
        }

        tempx1 = (int)(this.x1*LINE_SIZE);
        tempy1 = (int)(this.y1*LINE_SIZE);
        tempx2 = (int)(this.x2*LINE_SIZE);
        tempy2 = (int)(this.y2*LINE_SIZE);
        g2D.setPaint(Color.cyan);
        g2D.setStroke(new BasicStroke(5));
        g2D.drawLine(tempx1, tempy1, tempx2, tempy2);
        
        //System.out.println("hm " +x1 + " " + x2);
    }
}