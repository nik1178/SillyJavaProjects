public class gameOfLifeCheckEveryCell {
    public static void main(String[] args) throws InterruptedException {
        fieldString table = new fieldString(1000,1000);
        table.setDots(0,0);
        table.setDots(49,49);
        table.setDots(25,25);
        table.setDots(26,26);
        table.setDots(26,24);
        table.setDots(27,27);
        table.setDots(27,23);
        table.setDots(28,24);
        table.setDots(28,26);
        table.setDots(29,25);
        int counter=0;
        long fullTime=0;
        for(int i=0; i<800; i++){
            for(int j=0; j<800; j++){
                if(i%2==0 && j%2==0) 
                    table.setDots(i+100,j+100);
                    if(i%2!=0 && j%2!=0) 
                    table.setDots(i+100,j+100);
            }
        }
        while(true){
            /* long timer=System.nanoTime(); */
            table.print();
            table.evolutionSetup();
            //Thread.sleep(1000);
            /* fullTime+=System.nanoTime()-timer;
            counter++;
            if(counter>1000) break;
            System.out.println(counter); */
        }
        /* System.out.println(fullTime/counter); */
    }
}
