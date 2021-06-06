import java.util.*;

public class fieldString {
    int vsize;
    int hsize;
    int[][] table;
    //int evolutionChecks;
    public fieldString(int vsize, int hsize){
        table = new int[vsize][hsize];
        this.vsize=vsize;
        this.hsize=hsize;
    }
    public void setDots(int v, int h){ //set a certain field to alive
        table[v][h]=1;
    }
    public void setDots(int v, int v1, int h, int h1){ //set a range to alive
        for(int i=v; i<=v1; i++){
            for(int j=h; j<=h1; j++){
                table[i][j]=1;
            }
        }
    }
    public void print(){
        StringBuilder image = new StringBuilder("");
        for(int i=0; i<vsize; i++){
            for(int j=0; j<hsize; j++){
               if(table[i][j]==1) image.append("@");
                else image.append(".");
            }
            image.append("\n");
        }
        System.out.println(image);
    }
    public void evolutionSetup(){
        //evolutionChecks=0;
        /* System.out.println(findAlivev);
        System.out.println(findAliveh); */
        evolve();
        //System.out.println(evolutionChecks);
    }
    public void evolve(){
        //for all the elemnts in the list
        int[][] tempTable = new int[vsize][hsize];
        for(int i=0; i<vsize; i++){
            for(int j=0; j<hsize; j++){
                int neighbourAmount=0;
                //look at all the sorrounding cells and the selected one
                for(int vertical=i-1; vertical<=i+1; vertical++){
                    for(int horizontal=j-1; horizontal<=j+1; horizontal++){
                        //check that it's inside the table
                        if(vertical>=0 && vertical<vsize && horizontal>=0 && horizontal<hsize && table[vertical][horizontal]==1 && !(vertical==i && horizontal==j)){
                            neighbourAmount++;
                        }
                        //evolutionChecks++;
                    }
                }
                //System.out.println(neighbourAmount);
                if(neighbourAmount<2 || neighbourAmount>3) tempTable[i][j]=0;
                else if(neighbourAmount==3) tempTable[i][j]=1;
                else if(neighbourAmount==2 && table[i][j]==1) tempTable[i][j]=1;
            }
        }
        table = tempTable.clone();
    }
}
