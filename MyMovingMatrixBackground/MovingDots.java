import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class MovingDots extends JFrame{
	public static void main(String[] args) {
		new MovingDots();
	}
	
	MyPanel canvasPanel = new MyPanel();
	MovingDots(){
		
		this.add(canvasPanel);
		
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
}


class MyPanel extends JPanel implements ActionListener{
	static int panelWidth = 750;
	static int panelHeight = 750;
	Dot[] dots = new Dot[32];
	ArrayList<Line> lines = new ArrayList<>();
	MyPanel(){
		this.setPreferredSize(new Dimension(panelWidth, panelHeight));
		this.setBackground(Color.black);
		for(int i=0; i<dots.length; i++){
			dots[i] = new Dot();
		}
		Timer timer = new Timer(10,this);
		timer.start();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		lines.clear();
		for(int i=0; i<dots.length; i++){
			dots[i].move();
			detectLine(i);
		}
		repaint();
	}
	
	public void detectLine(int i){
		Rectangle searchArea = new Rectangle((int) dots[i].currentX-dots[i].maxLineLength/2, (int) dots[i].currentY-dots[i].maxLineLength/2, dots[i].maxLineLength, dots[i].maxLineLength);
		for(int j=0; j<dots.length; j++){
			if(j==i) continue;
			if(searchArea.contains((int)dots[j].currentX, (int)dots[j].currentY)){
				lines.add(new Line((int)dots[i].currentX+dots[i].radius/2, (int)dots[i].currentY+dots[i].radius/2, (int)dots[j].currentX+dots[j].radius/2, (int)dots[j].currentY+dots[j].radius/2));
			}
		}
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(Color.white);
		for(int i=0; i<dots.length; i++){
			g2.fillOval((int) dots[i].currentX, (int) dots[i].currentY, dots[i].radius, dots[i].radius);
		}
		for(int i=0; i<lines.size(); i++){
			g2.drawLine(lines.get(i).x1, lines.get(i).y1, lines.get(i).x2, lines.get(i).y2);
		}
	}
}


class Dot {
	int minRad = 10;
	int maxRad = 20;
	double minSpeed = 0.2;
	double maxSpeed = 1.1;
	int startX;
	int startY;
	double currentX;
	double currentY;
	double movementAngle;
	double movementSpeed;
	int radius;
	int maxLineLength = 250;
	Dot(){
		Random rng = new Random();
		this.radius = rng.nextInt(this.maxRad-this.minRad)+this.minRad;
		this.startX = rng.nextInt(MyPanel.panelWidth-this.radius);
		this.startY = rng.nextInt(MyPanel.panelHeight-this.radius);
		this.currentX = this.startX;
		this.currentY = this.startY;
		this.movementAngle = rng.nextInt(360);
		this.movementSpeed = rng.nextDouble()*this.maxSpeed+this.minSpeed;
	}
	public void move(){
		if(this.currentX>0 && this.currentX<MyPanel.panelWidth){
			if(this.currentY>0 && this.currentY<MyPanel.panelHeight){
				this.currentX += this.movementSpeed*Math.cos(Math.toRadians(this.movementAngle));
				this.currentY += this.movementSpeed*Math.sin(Math.toRadians(this.movementAngle));
			} else {
				this.movementAngle = 360-this.movementAngle;
				if(this.currentY<0) this.currentY=1;
				else if(this.currentY>MyPanel.panelHeight) this.currentY = MyPanel.panelHeight-1;
			}
			
		} else {
			this.movementAngle = 180-this.movementAngle;
			if(this.currentX<0) this.currentX=1;
			else if(this.currentX>MyPanel.panelWidth) this.currentX = MyPanel.panelWidth-1;
		}
	}
}

class Line {
	int x1;
	int x2;
	int y1;
	int y2;
	
	Line(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
}














