import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

class MyPaint2 extends JFrame implements KeyListener{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyPaint2();
	}
	DrawCanvas2 drawCanvas = new DrawCanvas2();
	MyPaint2(){
		
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
				/* drawCanvas.lineCoordsX1.remove(drawCanvas.lineNameList.get(drawCanvas.lineNameList.size()-1));
				drawCanvas.lineCoordsX2.remove(drawCanvas.lineNameList.get(drawCanvas.lineNameList.size()-1));
				drawCanvas.lineCoordsY1.remove(drawCanvas.lineNameList.get(drawCanvas.lineNameList.size()-1));
				drawCanvas.lineCoordsY2.remove(drawCanvas.lineNameList.get(drawCanvas.lineNameList.size()-1));
				drawCanvas.lineCoordsColor.remove(drawCanvas.lineNameList.get(drawCanvas.lineNameList.size()-1));
				drawCanvas.lineNameList.remove(drawCanvas.lineNameList.size()-1);

				drawCanvas.coordsX.remove(drawCanvas.dotNameList.get(drawCanvas.dotNameList.size()-1));
				drawCanvas.coordsY.remove(drawCanvas.dotNameList.get(drawCanvas.dotNameList.size()-1));
				drawCanvas.coordsColor.remove(drawCanvas.dotNameList.get(drawCanvas.dotNameList.size()-1));
				drawCanvas.dotNameList.remove(drawCanvas.dotNameList.size()-1); */
				//drawCanvas.paths.remove(--drawCanvas.currentPath);
				if(drawCanvas.currentPath>0)
					drawCanvas.paths.remove(--drawCanvas.currentPath);
				//--drawCanvas.currentPath;
				repaint();
			}
		}
		if(e.getKeyCode()==89){
			//zKey = true;
			if(ctrlKey/*  && zKey */){
				/* drawCanvas.lineCoordsX1.remove(drawCanvas.lineNameList.get(drawCanvas.lineNameList.size()-1));
				drawCanvas.lineCoordsX2.remove(drawCanvas.lineNameList.get(drawCanvas.lineNameList.size()-1));
				drawCanvas.lineCoordsY1.remove(drawCanvas.lineNameList.get(drawCanvas.lineNameList.size()-1));
				drawCanvas.lineCoordsY2.remove(drawCanvas.lineNameList.get(drawCanvas.lineNameList.size()-1));
				drawCanvas.lineCoordsColor.remove(drawCanvas.lineNameList.get(drawCanvas.lineNameList.size()-1));
				drawCanvas.lineNameList.remove(drawCanvas.lineNameList.size()-1);

				drawCanvas.coordsX.remove(drawCanvas.dotNameList.get(drawCanvas.dotNameList.size()-1));
				drawCanvas.coordsY.remove(drawCanvas.dotNameList.get(drawCanvas.dotNameList.size()-1));
				drawCanvas.coordsColor.remove(drawCanvas.dotNameList.get(drawCanvas.dotNameList.size()-1));
				drawCanvas.dotNameList.remove(drawCanvas.dotNameList.size()-1); */
				//drawCanvas.paths.remove(--drawCanvas.currentPath);
				//drawCanvas.currentPath+=1;
				repaint();
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

class DrawCanvas2 extends JPanel implements MouseListener, MouseMotionListener{
	DrawCanvas2(){
		this.setPreferredSize(new Dimension(500,500));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setBackground(Color.black);
	}
	
	/* HashMap<String, Integer> coordsX = new HashMap<>();
	HashMap<String, Integer> coordsY = new HashMap<>();
	HashMap<String, Color> coordsColor = new HashMap<>();
	ArrayList<String> dotNameList = new ArrayList<>(); */
	
	Color paintColor = Color.white;
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));
		if(started){
			for(int i=0; i<paths.size(); i++){
				g2.draw(paths.get(i));
			}
		}
	}
	boolean started = false;
	/* HashMap<String,Integer> lineCoordsX1 = new HashMap<>();
	HashMap<String,Integer> lineCoordsY1 = new HashMap<>();
	HashMap<String,Integer> lineCoordsX2 = new HashMap<>();
	HashMap<String,Integer> lineCoordsY2 = new HashMap<>();
	HashMap<String,Color> lineCoordsColor = new HashMap<>(); */
	ArrayList<String> lineNameList = new ArrayList<>();
	int tempX;
	int tempY;
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		paths.get(currentPath).lineTo(e.getX(), e.getY());
		repaint();
	}
	ArrayList<Path2D> paths = new ArrayList<>();
	int currentPath = 0;
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		paths.add(new Path2D.Double());
		paths.get(currentPath).moveTo(e.getX(), e.getY());
		started = true;
		repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		paths.get(currentPath).moveTo(e.getX(), e.getY());
		paths.get(currentPath).closePath();
		currentPath++;
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



	
}