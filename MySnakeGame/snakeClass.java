public class snakeClass {
    boolean alive = true;
    int startSize=5;
    int snakeY = windowClass.gameSize/2+(startSize/2);
    int snakeX = windowClass.gameSize/2;
    snakeClass(){
        for(int i=0; i<startSize; i++){
            windowClass.fieldArray[snakeY+i][snakeX] = startSize-i;
        }
    }
    void doMovement(int direction){
        for(int i=0; i<windowClass.gameSize; i++){
            for(int j=0; j<windowClass.gameSize; j++){
                if(windowClass.fieldArray[i][j]>0){
                    windowClass.fieldArray[i][j]--;
                }
            }
        }
        switch(direction){
            case 0: snakeY--; break;
            case 1: snakeX++; break;
            case 2: snakeY++; break;
            case 3: snakeX--; break;
            default: snakeY++; break;
        }
        checkOutOfBound();
        if(!alive) return;
        checkSelfColide();
        checkAteApple();

        if(!alive) return;
        windowClass.fieldArray[snakeY][snakeX] = startSize;
    }
    void checkOutOfBound(){
        if(snakeY<0 || snakeX<0 || snakeY>=windowClass.gameSize || snakeX>=windowClass.gameSize){
            alive=false;
        }
    }
    void checkSelfColide(){
        if(windowClass.fieldArray[snakeY][snakeX]>0) alive=false;
    }
    void checkAteApple(){
        if(snakeY == appleClass.appleY && snakeX == appleClass.appleX){
            startSize++;
            appleClass apple = new appleClass();
        }
    }
    int checkDirection(){
        //TO DO
        return 0;
    }
}