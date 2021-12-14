import nl.saxion.app.SaxionApp;
import java.awt.*;

public class Application implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new Application(), 750, 600);
    }

    //TACO
    String taco = "media/graphics/characters/TacoV4Default.png";
    int tacoHeight = 64;
    int tacoWidth = 88;
    int xCoordinateTaco = 325;
    int yCoordinateTaco = 300;

    //SYSTEM
    double health = 3.0;
    int currentLevel = 1;
    String fullHeart = "media/graphics/loading/Tomato_Full_Heart.png";
    String halfHeart = "media/graphics/loading/Tomato_Half_Heart.png";
    int heartWidth = 50;
    int heartHeight = 50;
    int xHeartOne = 550;
    int yHeartOne = 25;
    int xHeartTwo = 625;
    int yHeartTwo = 25;
    int xHeartThree = 700;
    int yHeartThree = 25;

    //LEVEL
    String roomOne = "media/graphics/levels/Start_veld.png";
    boolean levelActive = true;


    public void run() {
        //Test method to show example of sizes
        //testRun();
        SaxionApp.print("Press any button to start!");
        SaxionApp.pause();
        while(levelActive){
            if(currentLevel == 1){
                levelOne();
            } else if(currentLevel == 2){
                //level2
            } else if(currentLevel == 3){
                //level3
            } else if(currentLevel == 4){
                //level4
            } else if(currentLevel == 5){
                //level5
            } else if(currentLevel == 6){
                //level6
            }
        }

    }

    private void testRun() {
        SaxionApp.turnBorderOff();
        SaxionApp.drawRectangle(0,100,750,500);
        SaxionApp.drawLine(0,150,750,150); //top
        SaxionApp.drawLine(0,550,750,550); //bottom
        SaxionApp.drawLine(50, 0, 50, 600); // left
        SaxionApp.drawLine(700, 0, 700, 600); // right
        SaxionApp.setFill(Color.black);
        SaxionApp.drawRectangle(0,0,750,100);
//        SaxionApp.drawImage("media/graphics/levels/start_veld.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/characters/SpaghettiMonsterEndBossV2Size4.png", 100, 200, 250,250);
        SaxionApp.drawImage("media/graphics/characters/TacoV4Default.png", 500, 300, 88,64);
        SaxionApp.sleep(2);
        loadScreen();
        unloadScreen();

        SaxionApp.readChar();

    }

    private void loadScreen() {
        SaxionApp.drawImage("media/graphics/loading/1.png",0,100,750,500);
        SaxionApp.sleep(0.25);
        SaxionApp.drawImage("media/graphics/loading/2.png",0,100,750,500);
        SaxionApp.sleep(0.25);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,100,750,500);
        SaxionApp.sleep(0.25);
        SaxionApp.drawImage("media/graphics/loading/4.png",0,100,750,500);
        SaxionApp.sleep(0.25);
        SaxionApp.pause();

    }
    private void unloadScreen() {
        SaxionApp.removeLastDraw();
        SaxionApp.sleep(0.25);
        SaxionApp.removeLastDraw();
        SaxionApp.sleep(0.25);
        SaxionApp.removeLastDraw();
        SaxionApp.sleep(0.25);
        SaxionApp.removeLastDraw();
        SaxionApp.sleep(0.25);
        SaxionApp.pause();

    }

    public void damage(double damageAmount){
        health = health - damageAmount;
    }

    //Draw & move
    public void dummyDraw(){
        SaxionApp.drawImage(taco,xCoordinateTaco, yCoordinateTaco, tacoWidth, tacoHeight);
    }

    public void drawTaco(){
        SaxionApp.removeLastDraw();
        SaxionApp.drawImage(taco,xCoordinateTaco, yCoordinateTaco, tacoWidth, tacoHeight);
    }
     public void moveUp(){
        yCoordinateTaco = yCoordinateTaco - 64;
         drawTaco();
     }

    public void moveDown(){
        yCoordinateTaco = yCoordinateTaco + 64;
        drawTaco();
    }

    public void moveLeft(){
        xCoordinateTaco = xCoordinateTaco - 88;
        drawTaco();
    }

    public void moveRight(){
        xCoordinateTaco = xCoordinateTaco + 88;
        drawTaco();
    }

    public void moveCharacter(){
        char input = SaxionApp.readChar();
        SaxionApp.printLine(input);
        if(input == 's' && yCoordinateTaco < (SaxionApp.getHeight() - 114)){
            moveDown();
        } else if(input == 'w' && yCoordinateTaco > (214)){
            moveUp();
        } else if(input == 'a' && xCoordinateTaco > (88)){
            moveLeft();
        } else if(input == 'd' && xCoordinateTaco < (SaxionApp.getWidth() - 138)){
            moveRight();
        }
    }

    //menu's
    public void escapeMenu(){
        //menu
    }


    //NOT WORKING YET!!!
    /*public void drawHealth(){
        if(health == 3.0){
            SaxionApp.removeLastDraw();
            SaxionApp.removeLastDraw();
            SaxionApp.removeLastDraw();
            SaxionApp.drawImage(fullHeart, xHeartOne, yHeartOne, heartWidth, heartHeight);
            SaxionApp.drawImage(fullHeart, xHeartTwo, yHeartTwo, heartWidth, heartHeight);
            SaxionApp.drawImage(fullHeart, xHeartThree, yHeartThree, heartWidth, heartHeight);
        } else if(health == 2.5){
            SaxionApp.removeLastDraw();
            SaxionApp.removeLastDraw();
            SaxionApp.removeLastDraw();
            SaxionApp.drawImage(fullHeart, xHeartOne, yHeartOne, heartWidth, heartHeight);
            SaxionApp.drawImage(fullHeart, xHeartTwo, yHeartTwo, heartWidth, heartHeight);
            SaxionApp.drawImage(halfHeart, xHeartThree, yHeartThree, heartWidth, heartHeight);
        } else if(health == 2.0){
            SaxionApp.removeLastDraw();
            SaxionApp.removeLastDraw();
            SaxionApp.drawImage(fullHeart, xHeartOne, yHeartOne, heartWidth, heartHeight);
            SaxionApp.drawImage(fullHeart, xHeartTwo, yHeartTwo, heartWidth, heartHeight);
        } else if(health == 1.5){
            SaxionApp.removeLastDraw();
            SaxionApp.removeLastDraw();
            SaxionApp.drawImage(fullHeart, xHeartOne, yHeartOne, heartWidth, heartHeight);
            SaxionApp.drawImage(halfHeart, xHeartTwo, yHeartTwo, heartWidth, heartHeight);
        } else if(health == 1.0){
            SaxionApp.removeLastDraw();
            SaxionApp.drawImage(fullHeart, xHeartOne, yHeartOne, heartWidth, heartHeight);
        } else if(health == 0.5){
            SaxionApp.removeLastDraw();
            SaxionApp.drawImage(halfHeart, xHeartOne, yHeartOne, heartWidth, heartHeight);
        }
    }*/


    //Levels
    public void levelOne(){
        drawRoomOne();
        while(levelActive){
            moveCharacter();
            //drawHealth();
        }
    }

    public void drawRoomOne(){
        SaxionApp.clear();
        SaxionApp.drawImage(roomOne,0,100,750,500);
        dummyDraw();
        drawTaco();
    }
}