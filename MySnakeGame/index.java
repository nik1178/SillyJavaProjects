import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
public class index implements ActionListener, KeyListener, ChangeListener{
    static int speed = 100;
    int direction = 0;
    index() throws InterruptedException{
        windowClass window = new windowClass();
        window.createStartingField();
        snakeClass snake = new snakeClass();
        appleClass apple = new appleClass();
        long lastUpdate=System.currentTimeMillis();
        window.textArea.addKeyListener(this);
        window.slider.addChangeListener(this);
        for(;;){
            if(!snake.alive) break;
            window.print();
            Thread.sleep((long) (1000/(Math.pow(speed/100.0,3))));
            snake.doMovement(direction);
        }
        window.textArea.setText("LOSER LOST YOU ARE DIE");
    }

    public void stateChanged(ChangeEvent e)
    {
        speed=windowClass.slider.getValue();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if(e.getKeyCode()==KeyEvent.VK_W){
            direction=0;
        } else if(e.getKeyCode()==KeyEvent.VK_D){
            direction=1;
        } else if(e.getKeyCode()==KeyEvent.VK_S){
            direction=2;
        } else if(e.getKeyCode()==KeyEvent.VK_A){
            direction=3;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    public static void main(String[] args) throws InterruptedException {
        var run = new index();
    }
}