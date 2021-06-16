import java.util.Random;
public class appleClass {
    static int appleY;
    static int appleX;
    appleClass(){
        boolean createdApple = false;
        Random RNG = new Random();
        while(!createdApple){
            appleY = RNG.nextInt(windowClass.gameSize);
            appleX = RNG.nextInt(windowClass.gameSize);
            for(int i=0; i<windowClass.gameSize; i++){
                for(int j=0; j<windowClass.gameSize; j++){
                    if(windowClass.fieldArray[appleY][appleX]==0){
                        createdApple=true;
                        windowClass.fieldArray[appleY][appleX] = -1;
                    }
                }
            }
        }
    }
}
