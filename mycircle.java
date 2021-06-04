import java.util.*;

public class mycircle {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.println("Input radius (4-50):"); int radius=scan.nextInt();
        scan.close();
        String[][] coordinates = new String[radius*2+2][radius*2+2];
        for(int i=0; (Math.PI*i)/1000<2*Math.PI; i++)
        {
            coordinates[(int)(Math.cos((Math.PI*i)/1000)*(radius-1)+radius+1)][(int)(Math.sin((Math.PI*i)/1000)*(radius-1)+radius+1)]="**";
        }

        print(radius, coordinates);
        printfill(radius, coordinates);
    }

    private static void print(int radius, String[][] coordinates)
    {
        for(int i=0; i<radius*2+1; i++)
        {
            for(int j=0; j<radius*2+1; j++)
            {
                if(coordinates[i][j]==null) coordinates[i][j]="  ";
                System.out.print(coordinates[i][j]);
            }
            System.out.println();
        }
    }
    
    private static void printfill(int radius, String[][] coordinates)
    {
        for(int i=0; i<radius*2+1; i++)
        {
            int left = 0;
            int right = 0;
            boolean first=true;
            for(int j=0; j<radius*2+1; j++)
            {
                if(first && coordinates[i][j].contains("**")) 
                {
                    left=j;
                    j+=radius/4;
                    first=false;
                }
                else if(!first && coordinates[i][j].contains("**"))
                {
                    right=j;
                }
            }
            for(int j=left; j<right; j++)
            {
                coordinates[i][j]="**";
            }
            //System.out.println(left + " " + right);
        }
        print(radius, coordinates);
    }
}