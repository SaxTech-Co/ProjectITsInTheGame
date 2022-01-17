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

    //BOSSES
    Boss spaghettiMonster = new Boss("Spaghetti Monster", 50, "media/graphics/characters/SpaghettiMonsterEndBossV2Size4_Left.png", 400, 200, 250, 250);

    //SYSTEM
    int health = 4;
    int currentRoom = 1;
    int lastRoom = 1;
    boolean roomClear = false;
    String fullHeart = "media/graphics/Tomato_Full_Heart.png";
    String halfHeart = "media/graphics/Tomato_Half_Heart.png";
    int heartWidth = 50;
    int heartHeight = 50;
    int xHeartOne = 625;
    int yHeartOne = 25;
    int xHeartTwo = 700;
    int yHeartTwo = 25;

    //LEVEL
    String roomOne = "media/graphics/levels/room1.png";
    String roomTwo = "media/graphics/levels/room2.png";
    String roomThree = "media/graphics/levels/room3.png";
    boolean levelActive = true;


    public void run() {

        //Test method to show example of sizes
        //testRun();
        SaxionApp.print("Press any button to start!");
        SaxionApp.pause();
        while(levelActive){
            if(currentRoom == 1){
                levelOne();
            }
            if(currentRoom == 2){
                levelTwo();
            }
            if(currentRoom == 3){
                levelThree();
            }
            if(currentRoom == 4){
                //level4
            }
            if(currentRoom == 5){
                //level5
            }
            if(currentRoom == 6){
                //level6
            }
            if(currentRoom == 7){
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
        SaxionApp.drawImage("media/graphics/characters/SpaghettiMonsterEndBossV2Size4_Left.png", 400, 200, 250,250);
        SaxionApp.drawImage("media/graphics/characters/TacoV4Default.png", 200, 300, 88,64);
        SaxionApp.sleep(2);
        loadScreen();
        unloadScreen();

        SaxionApp.readChar();

    }

    private void loadScreen() {
        SaxionApp.drawImage("media/graphics/loading/1.png",0,100,750,500);
        SaxionApp.sleep(0.05);
        SaxionApp.drawImage("media/graphics/loading/1.png",0,100,750,500);
        SaxionApp.sleep(0.05);
        SaxionApp.drawImage("media/graphics/loading/2.png",0,100,750,500);
        SaxionApp.sleep(0.05);
        SaxionApp.drawImage("media/graphics/loading/2.png",0,100,750,500);
        SaxionApp.sleep(0.1);
        SaxionApp.drawImage("media/graphics/loading/2.png",0,100,750,500);
        SaxionApp.sleep(0.1);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,100,750,500);
        SaxionApp.sleep(0.1);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,100,750,500);
        SaxionApp.sleep(0.1);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,100,750,500);
        SaxionApp.sleep(0.1);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,100,750,500);
        SaxionApp.pause();

    }

    private void unloadScreen() {
        SaxionApp.removeLastDraw();
        SaxionApp.sleep(0.05);
        SaxionApp.removeLastDraw();
        SaxionApp.sleep(0.05);
        SaxionApp.removeLastDraw();
        SaxionApp.sleep(0.05);
        SaxionApp.removeLastDraw();
        SaxionApp.sleep(0.05);
        SaxionApp.removeLastDraw();
        SaxionApp.sleep(0.05);
        SaxionApp.removeLastDraw();
        SaxionApp.sleep(0.05);
        SaxionApp.removeLastDraw();
        SaxionApp.sleep(0.05);
        SaxionApp.removeLastDraw();
        SaxionApp.sleep(0.05);
        SaxionApp.removeLastDraw();
        SaxionApp.sleep(0.05);
        SaxionApp.pause();

    }

    public void damage(int damageAmount){
        health = health - damageAmount;
    }

    //Draw & move
    public void dummyDraw(){
        SaxionApp.drawImage(taco,xCoordinateTaco, yCoordinateTaco, tacoWidth, tacoHeight);
    }

    public void drawTaco(){
        SaxionApp.removeLastDraw();
        SaxionApp.drawImage(taco, xCoordinateTaco, yCoordinateTaco, tacoWidth, tacoHeight);
        //checkHostileOverlap();
        if (xCoordinateTaco >= spaghettiMonster.xCoord-50 && xCoordinateTaco <= spaghettiMonster.xCoord-50 + spaghettiMonster.width
                && yCoordinateTaco >= spaghettiMonster.yCoord && yCoordinateTaco <= spaghettiMonster.yCoord + spaghettiMonster.height-50) {
            //SaxionApp.drawRectangle(50,50,300,300);
            drawFight();
        }
    }

    public void drawFight(){
        loadScreen();
        SaxionApp.clear();
        SaxionApp.drawImage(roomOne,0,100,750,500);
        SaxionApp.drawImage(spaghettiMonster.image, spaghettiMonster.xCoord, spaghettiMonster.yCoord, spaghettiMonster.width,spaghettiMonster.height);
        SaxionApp.drawImage(taco, 225, 300, tacoWidth, tacoHeight);
        SaxionApp.drawImage("media/graphics/loading/1.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/loading/1.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/loading/2.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/loading/2.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/loading/2.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,100,750,500);
        unloadScreen();
        SaxionApp.pause();

        SaxionApp.setFill(Color.black);
        SaxionApp.setBorderColor(Color.black);
        SaxionApp.drawRectangle(0,450, SaxionApp.getWidth(),SaxionApp.getHeight());
        SaxionApp.setBorderColor(Color.white);
        SaxionApp.drawText("J - Attack [5dmg]", 75, 475, 20);
        SaxionApp.drawText("K - SpecialAttack [10dmg]", 75, 505, 20);
        SaxionApp.drawText("L - Escape(DEV)", 75, 535, 20);
        SaxionApp.drawText(spaghettiMonster.name + " HP: " + String.valueOf(spaghettiMonster.combatHp), 375, 505, 20);

        boolean validInput = false;
        int monsterHP = spaghettiMonster.combatHp;
        while (validInput = true) {
            char userInput = SaxionApp.readChar();
            SaxionApp.removeLastDraw();
            if (userInput == 'j') {
                spaghettiMonster.combatHp -= 5;
                System.out.println(spaghettiMonster.combatHp);
            } else if (userInput == 'k') {
                spaghettiMonster.combatHp -= 10;
                System.out.println(spaghettiMonster.combatHp);
            } else if (userInput == 'l') {
                System.out.println(spaghettiMonster.combatHp);
            } else {
                System.out.println("INVALID INPUT");
            }
            SaxionApp.drawText(spaghettiMonster.name + " HP: " + spaghettiMonster.combatHp, 375, 505, 20);
        }
        SaxionApp.pause();
    }

     public void moveUp(){
        yCoordinateTaco = yCoordinateTaco - 32;
         drawTaco();
     }

    public void moveDown(){
        yCoordinateTaco = yCoordinateTaco + 32;
        drawTaco();
    }

    public void moveLeft(){
        xCoordinateTaco = xCoordinateTaco - 44;
        drawTaco();
    }

    public void moveRight(){
        xCoordinateTaco = xCoordinateTaco + 44;
        drawTaco();
    }

    public void moveCharacter(){
        char input = SaxionApp.readChar();
        SaxionApp.printLine(input);
        if(input == 's' && yCoordinateTaco < (SaxionApp.getHeight() - 100)){
            moveDown();
        } else if(input == 'w' && yCoordinateTaco > (164)){
            moveUp();
        } else if(input == 'a' && xCoordinateTaco > (22)){
            moveLeft();
        } else if(input == 'd' && xCoordinateTaco < (644)){
            moveRight();
        }
    }

    //Levels
    public void levelOne(){
        if(lastRoom == 2){
           xCoordinateTaco = 668;
           yCoordinateTaco = 300;
        } else if(lastRoom == 3){
            xCoordinateTaco = 50;
            yCoordinateTaco = 300;
        }
        lastRoom = 1;
        drawRoomOne();

        while(levelActive){
            moveCharacter();
            //to room 2
            if(xCoordinateTaco == 536 && yCoordinateTaco < 364 && yCoordinateTaco > 236){
                currentRoom = 2;
            }
            //to room 3

        }
    }

    public void levelTwo(){
        if(lastRoom == 1){
            xCoordinateTaco = 50;
            yCoordinateTaco = 300;
        } else if(lastRoom == 5){
            xCoordinateTaco = 325;
            yCoordinateTaco = 164;
        }
        lastRoom = 2;
        drawRoomTwo();
        while(levelActive){
            moveCharacter();
        }
    }

    public void levelThree(){
        if(lastRoom == 1){
            xCoordinateTaco = 686;
            yCoordinateTaco = 300;
        }
        lastRoom = 3;
        drawRoomThree();
        while(levelActive){
            moveCharacter();
        }
    }

    public void drawRoomOne(){
        SaxionApp.clear();

        SaxionApp.drawImage(fullHeart, 300, 20, 50, 50);
        SaxionApp.drawImage(fullHeart, 360, 20, 50, 50);
        SaxionApp.drawImage(halfHeart, 420, 20, 50, 50);

        SaxionApp.drawImage(roomOne,0,100,750,500);

        if (roomClear == false) {
            SaxionApp.drawImage(spaghettiMonster.image, spaghettiMonster.xCoord, spaghettiMonster.yCoord, spaghettiMonster.width,spaghettiMonster.height);
        }
        dummyDraw();
        drawTaco();

    }

    public void drawRoomTwo(){
        SaxionApp.clear();
        SaxionApp.drawImage(roomTwo,0,100,750,500);
        dummyDraw();
        drawTaco();
    }

    public void drawRoomThree(){
        SaxionApp.clear();
        SaxionApp.drawImage(roomThree,0,100,750,500);
        dummyDraw();
        drawTaco();
    }
}
