import javax.swing.*;
import java.awt.*;
public class windowClass {
    final static int gameSize = 30;
    int width = (int)(gameSize*16.7);
    int height = (int) (gameSize*19.3) + 20;
    final JFrame window = new JFrame();
    final JTextArea textArea = new JTextArea();
    final static JSlider slider = new JSlider();
    windowClass(){
        window.setBounds(600,200,width,height);
        window.setLayout(null);
        window.setResizable(false);
        textArea.setBounds(0,20,width,height);
        Font f = new Font("MONOSPACED", Font.PLAIN, 13);
        textArea.setFont(f);
        textArea.setEditable(false);
        slider.setBounds(0,0,width-15,20);
        slider.setMinimum(1);
        slider.setMaximum(200);
        slider.setValue(index.speed);
        window.add(slider);
        window.add(textArea);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    static int[][] fieldArray = new int[gameSize][gameSize];
    static void createStartingField(){
        for(int i=0; i<gameSize; i++){
            for(int j=0; j<gameSize; j++){
                fieldArray[i][j]=0;
            }
        }
    }
    void print(){
        StringBuilder image = new StringBuilder("");
        for(int i=0; i<gameSize; i++){
            for(int j=0; j<gameSize; j++){
                if(fieldArray[i][j]==0) image.append("  ");
                else if(fieldArray[i][j]==-1) image.append("[]");
                else if(fieldArray[i][j]<10) image.append("" + fieldArray[i][j] + fieldArray[i][j]);
                else image.append("@@");
            }
            image.append("\n");
        }
        textArea.setText(image.toString());
        
    }
}
