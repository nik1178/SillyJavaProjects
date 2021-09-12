import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class MyPaint extends JFrame implements KeyListener{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyPaint();
	}
	DrawCanvas drawCanvas = new DrawCanvas();
	MyPaint(){
		
		this.add(drawCanvas);

		this.addKeyListener(this);
		
		this.setSize(new Dimension(500,600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
	}

	//Keyboard
	boolean ctrlKey = false;
	boolean zKey = false;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==17){
			ctrlKey = true;
		}
		if(e.getKeyCode()==90){
			//zKey = true;
			if(ctrlKey/*  && zKey */){
				drawCanvas.lineCoordsX1.remove(drawCanvas.lineNameList.get(drawCanvas.lineNameList.size()-1));
				drawCanvas.lineCoordsX2.remove(drawCanvas.lineNameList.get(drawCanvas.lineNameList.size()-1));
				drawCanvas.lineCoordsY1.remove(drawCanvas.lineNameList.get(drawCanvas.lineNameList.size()-1));
				drawCanvas.lineCoordsY2.remove(drawCanvas.lineNameList.get(drawCanvas.lineNameList.size()-1));
				drawCanvas.lineCoordsColor.remove(drawCanvas.lineNameList.get(drawCanvas.lineNameList.size()-1));
				drawCanvas.lineNameList.remove(drawCanvas.lineNameList.size()-1);

				drawCanvas.coordsX.remove(drawCanvas.dotNameList.get(drawCanvas.dotNameList.size()-1));
				drawCanvas.coordsY.remove(drawCanvas.dotNameList.get(drawCanvas.dotNameList.size()-1));
				drawCanvas.coordsColor.remove(drawCanvas.dotNameList.get(drawCanvas.dotNameList.size()-1));
				drawCanvas.dotNameList.remove(drawCanvas.dotNameList.size()-1);
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==17){
			ctrlKey = false;
		}
		if(e.getKeyCode()==90){
			zKey = false;
		}
	}
}

class DrawCanvas extends JPanel implements MouseListener, MouseMotionListener{
	DrawCanvas(){
		this.setPreferredSize(new Dimension(500,500));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setBackground(Color.black);
	}
	
	HashMap<String, Integer> coordsX = new HashMap<>();
	HashMap<String, Integer> coordsY = new HashMap<>();
	HashMap<String, Color> coordsColor = new HashMap<>();
	ArrayList<String> dotNameList = new ArrayList<>();
	
	Color paintColor = Color.white;
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		for(String i : coordsX.keySet()){
			g2.setPaint(coordsColor.get(i));
			g2.fillOval(coordsX.get(i)-2, coordsY.get(i)-2, 5, 5);
		}
		g2.setStroke(new BasicStroke(5));
		for(String i : lineCoordsX1.keySet()){
			g2.setPaint(lineCoordsColor.get(i));
			g2.drawLine(lineCoordsX1.get(i), lineCoordsY1.get(i), lineCoordsX2.get(i), lineCoordsY2.get(i));
		}
	}
	HashMap<String,Integer> lineCoordsX1 = new HashMap<>();
	HashMap<String,Integer> lineCoordsY1 = new HashMap<>();
	HashMap<String,Integer> lineCoordsX2 = new HashMap<>();
	HashMap<String,Integer> lineCoordsY2 = new HashMap<>();
	HashMap<String,Color> lineCoordsColor = new HashMap<>();
	ArrayList<String> lineNameList = new ArrayList<>();
	int tempX;
	int tempY;
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		String lineName = tempX+""+tempY;
		lineNameList.add(lineName);
		lineCoordsX1.put(lineName, tempX);
		lineCoordsY1.put(lineName, tempY);
		tempX = e.getX();
		tempY = e.getY();
		lineCoordsX2.put(lineName, tempX);
		lineCoordsY2.put(lineName, tempY);
		lineCoordsColor.put(lineName, paintColor);
		if(!coordsX.containsKey(tempX+""+tempY+""+paintColor.getBlue()+""+paintColor.getRed()+""+paintColor.getGreen())){
			dotNameList.add(tempX+""+tempY+""+paintColor.getBlue()+""+paintColor.getRed()+""+paintColor.getGreen());
			coordsX.put(dotNameList.get(dotNameList.size()-1), tempX);
			coordsY.put(dotNameList.get(dotNameList.size()-1), tempY);
			coordsColor.put(dotNameList.get(dotNameList.size()-1), paintColor);
		}
		repaint();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		tempX = e.getX();
		tempY = e.getY();
		if(!coordsX.containsKey(tempX+""+tempY+""+paintColor.getBlue()+""+paintColor.getRed()+""+paintColor.getGreen())){
			dotNameList.add(tempX+""+tempY+""+paintColor.getBlue()+""+paintColor.getRed()+""+paintColor.getGreen());
			coordsX.put(dotNameList.get(dotNameList.size()-1), tempX);
			coordsY.put(dotNameList.get(dotNameList.size()-1), tempY);
			coordsColor.put(dotNameList.get(dotNameList.size()-1), paintColor);
		}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}