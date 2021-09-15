import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Main extends JFrame{
    public static void main(String args[]){
        new Main();
    }

    DrawPanel drawPanel = new DrawPanel();
    Main(){
        
        this.add(drawPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
class DrawPanel extends JPanel implements ActionListener{
    Timer timer;
    double[][] dots = new double[32][7]; // 0=startX, 1=startY, 2=Radius, 3=x to reach, 4=y to reach, 5=currentX, 6=currentY
    int width = 500;
    int height = 500;
    DrawPanel(){
        super(true);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(width,height));

        Random rng = new Random();
        for(int i=0; i<dots.length; i++){
            dots[i][2] = rng.nextInt(10)+5;

            dots[i][0] = rng.nextInt((int)(this.width-dots[i][2]));
            dots[i][3] = rng.nextInt((int)(this.width-dots[i][2]));
            dots[i][5] = dots[i][0];

            dots[i][1] = rng.nextInt((int)(this.height-dots[i][2]));
            dots[i][4] = rng.nextInt((int)(this.height-dots[i][2]));
            dots[i][6] = dots[i][1];
        }

        timer = new Timer(10,this);
        timer.start();
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.white);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        //System.out.println(g2.getRenderingHints());

        for(int i=0; i<dots.length; i++){
            int x = (int) dots[i][5];
            int y = (int) dots[i][6];
            int radius = (int) dots[i][2];
            g2.fillOval(x, y, radius, radius);
        }
        for(int i=0; i<allLines.size(); i++){
            int x1 = (int)allLines.get(i)[0];
            int y1 = (int)allLines.get(i)[1];
            int x2 = (int)allLines.get(i)[2];
            int y2 = (int)allLines.get(i)[3];
            int currentLineLength = (int)Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
            if(currentLineLength<1) currentLineLength=1;
            int strokeSize = this.lineLength/currentLineLength-1;
            if(strokeSize<1) strokeSize=1;
            if(strokeSize>maxStrokeSize) strokeSize=maxStrokeSize;
            g2.setStroke(new BasicStroke(strokeSize));
            g2.drawLine(x1, y1, x2, y2);
        }
    }
    ArrayList<double[]> allLines = new ArrayList<>();
    int lineLength = 150;
    int maxStrokeSize = 5;
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        allLines.clear();
        for(int i=0; i<dots.length; i++){
            int x = (int) dots[i][3]; /* System.out.println(x); */
            int y = (int) dots[i][4];
            int recWidth = 100;
            Rectangle rec = new Rectangle(x-recWidth/2,y-recWidth/2,recWidth,recWidth);
            if(rec.contains(dots[i][5], dots[i][6])){
                Random rng = new Random();
                dots[i][3] = rng.nextInt((int)(this.width-dots[i][2]));
                dots[i][0] = dots[i][5];
                
                dots[i][4] = rng.nextInt((int)(this.height-dots[i][2]));
                dots[i][1] = dots[i][6];
            } else {
                int sign = 0;
                /* if(dots[i][3]-dots[i][0]<0) sign=-1;
                else sign=1;
                dots[i][5]+=sign;

                if(dots[i][4]-dots[i][1]<0) sign=-1;
                else sign=1;
                dots[i][6]+=sign; */
                dots[i][5] += (dots[i][3]-dots[i][5])/this.width;
                dots[i][6] += (dots[i][4]-dots[i][6])/this.height;
            }

            x=(int) dots[i][5];
            y=(int) dots[i][6];
            int counter = 0;
            int areaWidth = lineLength; 
            Rectangle lineRectangle = new Rectangle(x-areaWidth/2,y-areaWidth/2,areaWidth,areaWidth);
            for(int j=0; j<dots.length; j++){
                if(j==i) continue;
                if(lineRectangle.contains(dots[j][5],dots[j][6])){
                    double[] currentLine = {dots[i][5]+dots[i][2]/2, dots[i][6]+dots[i][2]/2, dots[j][5]+dots[j][2]/2, dots[j][6]+dots[j][2]/2};
                    allLines.add(currentLine);
                    counter++;
                }
                //if(counter>=2) break;
            }
        }
        repaint();
    }
}