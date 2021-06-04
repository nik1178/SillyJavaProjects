import java.util.*;
public class myGraph {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.println("Input formula (numbers smaller than 100) (format: nx^exp +- nx^exp +- ... +- nx + n):"); String formula=scan.nextLine();
        System.out.println("Input graph size (5-59):"); int radius=scan.nextInt();
        System.out.println("Input step size"); double step=scan.nextDouble();
        scan.close();
        String[][] coordinates = new String[radius*2][radius*2];
        setCoordinateSystem(coordinates, radius, step);
        
        for(double x=-radius; x<radius; x+=0.01)
        {
            /*if(x<10 && x>=0) coordinates[radius*2-1][(int)x+radius]="  " + (int)x;
            else if(x>-10 && x<0) coordinates[radius*2-1][(int)x+radius]=" " + (int)x;
            else if(x<0) coordinates[radius*2-1][(int)x+radius]="" + (int)x;
            else coordinates[radius*2-1][(int)x+radius]=" " + (int)x;*/
            //int y = -(int)((Math.pow(x*step, 3) + Math.pow(x*step, 2)*4 + x*step - 3)*(1/step));
            //int y=-(int)(Math.pow(x*step, 3)*(1/step));
            //if(!(y>=radius || y<-radius)) coordinates[y+radius][(int)x+radius]=" @";

            int y = -getFormula(formula, x, step);
            if(!(y>=radius || y<-radius)) coordinates[y+radius][(int)x+radius]=" @";
        }
        print(radius, coordinates);
    }

    private static String[][] setCoordinateSystem(String[][] coordinates, int radius, double step)
    {
        for(int x=-radius; x<radius; ++x)
        {
            for(int y = -radius; y<radius; ++y)
            {
                if((x%(1/step)==0 && y%(1/step)==0) || step>1) coordinates[y+radius][radius + x]=" .";
                //if(x%5==0 && y%5==0) coordinates[radius+x][(int)y+radius]=" -";
                coordinates[y+radius][radius]=" |";
                coordinates[radius][y+radius]="--";
            }
        }
        return coordinates;
    }

    private static int getFormula(String formula, double x, double step)
    {
        double y1=1;
        double y=0;
        for(int i=formula.length()-1; i>=0; --i)
        {
            y1=ifStatements(i, y1, x, step, formula);
            if(formula.charAt(i)=='+' || formula.charAt(i)=='-')
            {
                if(formula.charAt(i)=='-') y-=y1;
                else y+=y1;
                if(i!=0)y1=1;
                else y1=0;
            }
        }
        y+=y1;
        return (int)(y*(1/step));
    }
    private static double ifStatements(int i, double y1, double x, double step, String formula)
    {
        if(Character.isDigit(formula.charAt(i)) && i!=0 && formula.charAt(i-1)=='^')
        {
            y1*=Math.pow(x*step, Character.getNumericValue(formula.charAt(i)));
        }
        else if(Character.isDigit(formula.charAt(i)))
        {
            if(i!=0 && Character.isDigit(formula.charAt(i-1)))
            {
                y1*= Character.getNumericValue(formula.charAt(i))+Character.getNumericValue(formula.charAt(i-1))*10;
            }
            else if(i!=formula.length()-1 && !Character.isDigit(formula.charAt(i+1))) y1*=Character.getNumericValue(formula.charAt(i));
        }
        else if(formula.charAt(i)=='x' && (i==formula.length()-1 || formula.charAt(i+1)!='^'))
        {
            y1*=x*step;
        }
        return y1;
    }

    private static void print(int radius, String[][] coordinates)
    {
        for(int i=0; i<radius*2; i++)
        {
            for(int j=0; j<radius*2; j++)
            {
                if(coordinates[i][j]==null) coordinates[i][j]="  ";
                System.out.print(coordinates[i][j]);
            }
            System.out.println();
        }
    }
}
