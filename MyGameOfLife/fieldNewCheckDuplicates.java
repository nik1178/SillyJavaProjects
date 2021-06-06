import java.util.*;

public class fieldNewCheckDuplicates {
    int vsize;
    int hsize;
    int[][] table;
    //int evolutionChecks;
    public fieldNewCheckDuplicates(int vsize, int hsize){
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
        ArrayList<Integer> findAlivev = new ArrayList<>(200);
        ArrayList<Integer> findAliveh = new ArrayList<>(200);
        addAliveCellsToList(findAlivev, findAliveh);
        System.out.println("ADDED TO LIST");
        //removeDuplicate(findAlivev, findAliveh);
        System.out.println("REMOVED DUPLICATES");
        removeInvalid(findAlivev, findAliveh);
        System.out.println("REMOVED INVALIDS!");
        /* System.out.println(findAlivev);
        System.out.println(findAliveh); */
        evolve(findAlivev, findAliveh);
        //System.out.println(evolutionChecks);
    }
    public void evolve(List<Integer> findAlivev, List<Integer> findAliveh){
        //for all the elemnts in the list
        int[][] tempTable = new int[vsize][hsize];
        for(int i=0; i<findAlivev.size(); i++){
            int neighbourAmount=0;
            //look at all the sorrounding cells and the selected one
            for(int vertical=findAlivev.get(i)-1; vertical<=findAlivev.get(i)+1; vertical++){
                for(int horizontal=findAliveh.get(i)-1; horizontal<=findAliveh.get(i)+1; horizontal++){
                    //check that it's inside the table
                    if(vertical>=0 && vertical<vsize && horizontal>=0 && horizontal<hsize && table[vertical][horizontal]==1 && !(vertical==findAlivev.get(i) && horizontal==findAliveh.get(i))){
                        neighbourAmount++;
                    }
                    //evolutionChecks++;
                }
            }
            //System.out.println(neighbourAmount);
            if(neighbourAmount<2 || neighbourAmount>3) tempTable[findAlivev.get(i)][findAliveh.get(i)]=0;
            else if(neighbourAmount==3) tempTable[findAlivev.get(i)][findAliveh.get(i)]=1;
            else if(neighbourAmount==2 && table[findAlivev.get(i)][findAliveh.get(i)]==1) tempTable[findAlivev.get(i)][findAliveh.get(i)]=1;
        }
        table = tempTable.clone();
    }
    int firsti;
    public void addAliveCellsToList(List<Integer> findAlivev, List<Integer> findAliveh){
        int change=1;
        firsti=0;
        for(int i=0; i<vsize; i++){
            for(int j=0; j<hsize; j++){
                //if it's alive, add it to the list
                if(table[i][j]==1){
                    //add all the sorrounding cells too
                    for(int g=i-1; g<=i+1; g++){
                        for(int h=j-1; h<=j+1; h++){
                            findPosition(findAlivev, findAliveh, g, h, change);
                            change=0;
                            /* findAlivev.add(g);
                            findAliveh.add(h); */
                            //evolutionChecks++;
                        }
                    }
                }
                //evolutionChecks++;
            }
            change=1;
            //System.out.println("thonk" + i);
        }
        //System.out.println(findAlivev);
    }
    public void findPosition(List<Integer> findAlivev, List<Integer> findAliveh, int g, int h, int change){
        //delete any pair of numbers which has duplicates
        //int firsti = findFirstI(findAlivev, findAliveh, g, h);
        if(change==1) firsti=findAlivev.size();
        checkHorizontals(findAlivev, findAliveh, firsti, g, h);
        //removeDuplicate(findAlivev, findAliveh);
    }

/*     public void findFirstI(List<Integer> findAlivev, List<Integer> findAliveh, int g, int h){
        for(int i=firsti+1; i<findAlivev.size(); i++){
            if((findAlivev.get(i)==g && findAliveh.get(i)==h)){
                return;
            }
            if(findAlivev.get(i)>g || findAliveh.get(i)>h){
                findAlivev.add(i, g);
                findAliveh.add(i, h);
                return;
            }
        }
    } */
    public void checkHorizontals(List<Integer> findAlivev, List<Integer> findAliveh, int firsti, int g, int h){
        for(int j=firsti; j<findAlivev.size(); j++){
            if(findAlivev.get(j)==g && findAliveh.get(j)==h){
                return;
            }
        }
        findAlivev.add(g);
        findAliveh.add(h);
    }
    public void removeInvalid(List<Integer> findAlivev, List<Integer> findAliveh){
        for(int i=0; i<findAliveh.size(); i++){
            if(findAlivev.get(i)==-1 || findAliveh.get(i)==-1 || findAlivev.get(i)==table.length || findAliveh.get(i)==table.length){
                //System.out.println("hm");
                findAlivev.remove(i);
                findAliveh.remove(i);
                i--;
            }
            //evolutionChecks++;
        }
    }





    public void removeDuplicate(List<Integer> findAlivev, List<Integer> findAliveh){
        //sort the one with horizontal positions first, and have the one with vertical positions copy it
        sortHorizontal(findAlivev, findAliveh);
        //now do the opposite, so we basically did a Radix sort
        sortVertical(findAlivev, findAliveh);
        //delete any pair of numbers which has duplicates
        for(int i=1; i<findAliveh.size(); i++){
            if(findAlivev.get(i-1).equals(findAlivev.get(i)) && findAliveh.get(i-1).equals(findAliveh.get(i))){
                findAlivev.remove(i);
                findAliveh.remove(i);
                i--;
                System.out.println("there was a duplicate");
            }
            //evolutionChecks++;
        }
    }
    public void sortHorizontal(List<Integer> findAlivev, List<Integer> findAliveh){
        for(int i=0; i<findAlivev.size(); i++){
            for(int j=0; j<findAlivev.size()-1-i; j++){
                if(findAliveh.get(j)>findAliveh.get(j+1)){
                    int temp=findAlivev.get(j);
                    int temp1=findAliveh.get(j);
                    findAlivev.set(j, findAlivev.get(j+1));
                    findAliveh.set(j, findAliveh.get(j+1));
                    findAlivev.set(j+1, temp);
                    findAliveh.set(j+1, temp1);
                    //System.out.println("Had so horizontal sort");
                }
                //evolutionChecks++;
            }
        }
    }
    public void sortVertical(List<Integer> findAlivev, List<Integer> findAliveh){
        for(int i=0; i<findAlivev.size(); i++){
            for(int j=0; j<findAlivev.size()-1-i; j++){
                if(findAlivev.get(j)>findAlivev.get(j+1)){
                    int temp=findAlivev.get(j);
                    int temp1=findAliveh.get(j);
                    findAlivev.set(j, findAlivev.get(j+1));
                    findAliveh.set(j, findAliveh.get(j+1));
                    findAlivev.set(j+1, temp);
                    findAliveh.set(j+1, temp1);
                    System.out.println("Had so vertical sort");
                }
                //evolutionChecks++;
            }
        }
    }
}
