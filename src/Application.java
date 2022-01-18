import nl.saxion.app.SaxionApp;
import java.awt.*;

public class Application implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new Application(), 750, 600);
    }
    public void resetGame() {
        xCoordinateTaco = 225;
        yCoordinateTaco = 300;
        spaghettiMonster.combatHp = 50;
        health = 6;
    }
    //TACO
    String taco = "media/graphics/characters/TacoV4Default.png";
    int tacoHeight = 64;
    int tacoWidth = 88;
    int xCoordinateTaco = 225;
    int yCoordinateTaco = 300;

    //BOSSES
    Boss spaghettiMonster = new Boss("Spaghetti Monster", 50, "media/graphics/characters/SpaghettiMonsterEndBossV2Size4_Left.png", 400, 200, 250, 250);

    //SYSTEM
    int health = 6;
    int currentRoom = 1;
    int lastRoom = 1;
    boolean roomClear = false;
    String fullHeart = "media/graphics/Tomato_Full_Heart.png";
    String halfHeart = "media/graphics/Tomato_Half_Heart.png";
    int heartWidth = 50;
    int heartHeight = 50;
//    int xHeartOne = 625;
//    int yHeartOne = 25;
//    int xHeartTwo = 700;
//    int yHeartTwo = 25;

    //LEVEL
    String roomOne = "media/graphics/levels/room1.png";
    String roomTwo = "media/graphics/levels/room2.png";
    String roomThree = "media/graphics/levels/room3.png";
    boolean levelActive = true;

    int tries = 0;

    public void run() {
        System.out.println(tries + " test");
        //Test method to show example of sizes
        //testRun();
        resetGame();
        drawMainMenu();
        fromMenuLoading();
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

    private void drawMainMenu() {
        SaxionApp.drawImage(roomOne, 0, 50, 750,500);
        SaxionApp.drawImage("media/graphics/loading/1.png",0,50, 750,500);
        SaxionApp.drawImage("media/graphics/loading/1.png",0,50, 750,500);
        SaxionApp.setFill(Color.black);
        SaxionApp.setBorderColor(Color.black);
        SaxionApp.drawRectangle(0,0,750,50);
        SaxionApp.drawRectangle(0,550,750,50);
        Color tacoAdventure = SaxionApp.createColor(219, 144, 0);
        SaxionApp.setBorderColor(tacoAdventure);
        SaxionApp.drawText("Taco's Adventure", 180, 230, 50);
        SaxionApp.setBorderColor(Color.white);
        SaxionApp.drawText("Press any button to start!", 265, 290, 20);
        SaxionApp.drawText("Retries: " + tries, 350, 330, 15);
        SaxionApp.readChar();
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

    public void damage(int damageAmount){
        health = health - damageAmount;
    }

    //Draw & move (SPECIAL: To make certain "removeLastDraw"s work properly)
    public void emptyDraw(){
        SaxionApp.drawText("",0,0,0);
    }

    //-------------------------------------------Draw Taco-----------------------------------------

    public void drawTaco(){
        SaxionApp.removeLastDraw();
        boundsCheck();
        SaxionApp.drawImage(taco, xCoordinateTaco, yCoordinateTaco, tacoWidth, tacoHeight);
        checkHostileOverlap();
    }
    // Check for out of bounds and reset to last location
    private void boundsCheck() {
        if (xCoordinateTaco < 49) {
            xCoordinateTaco+=44;
        } else if (xCoordinateTaco > 621) {
            xCoordinateTaco-=44;
        } else if (yCoordinateTaco < 140) {
            yCoordinateTaco+=32;
        } else if (yCoordinateTaco > 460) {
            yCoordinateTaco-=32;
        }
    }
    // Check if on hostile tile
    private void checkHostileOverlap() {
        if (xCoordinateTaco >= spaghettiMonster.xCoord-50 && xCoordinateTaco <= spaghettiMonster.xCoord-50 + spaghettiMonster.width
                && yCoordinateTaco >= spaghettiMonster.yCoord && yCoordinateTaco <= spaghettiMonster.yCoord + spaghettiMonster.height-50) {
            //SaxionApp.drawRectangle(50,50,300,300);
            fightScreen(); // Start Fight
        }
    }

    //---------------------------------------------COMBAT-------------------------------------------

    public void fightScreen(){
        drawFight();

        drawTacoHP(); // Initial Health Draw before the fight as "removeLastDraw" comes next in rotation beneath this
        boolean activeFight = true;
        int monsterHP = spaghettiMonster.combatHp; // QoL addition for ease of use
        while (activeFight) {
            // Redraw HP after damage
            redrawTacoHP();
            // HP check for Taco (player)
            if (health <= 0) {
                System.out.println("TACO DIED");
                loadScreen();
                SaxionApp.drawText("You have been killed by:", 170, 300, 40);
                SaxionApp.setBorderColor(Color.red);
                SaxionApp.drawText(spaghettiMonster.name, 220, 350, 40);
                SaxionApp.drawText("Want to try again and win?", 168, 400, 40);
                char continueAnswer = SaxionApp.readChar();
                if (continueAnswer == 'y') {
                    SaxionApp.clear();
                    tries++;
                    run();
                } else {
                    levelActive = false;
                    break;
                }
            }
            SaxionApp.drawText(spaghettiMonster.name + " HP: " + monsterHP, 375, 505, 20);
            monsterHP = userMoveChoice(monsterHP);
            SaxionApp.removeLastDraw();
            SaxionApp.drawText(spaghettiMonster.name + " HP: " + monsterHP, 375, 505, 20);
            SaxionApp.sleep(2);
            if (monsterHP <= 0) {
                activeFight = false;
                System.out.println("MONSTER DEFEATED");
                //DEMO ENDING
                loadScreen();
                demoEnding();
            } else {
                damage(1);
            }
            //DEBUG
//            System.out.println(health);

        }
        //DEBUG
//        System.out.println("FINISHED FIGHT");

    }

    private void drawFight() {
        loadScreen();
        SaxionApp.clear();
        SaxionApp.drawImage(roomOne,0,100,750,500);
        SaxionApp.drawImage(spaghettiMonster.image, spaghettiMonster.xCoord, spaghettiMonster.yCoord, spaghettiMonster.width,spaghettiMonster.height);
        SaxionApp.drawImage(taco, 225, 300, tacoWidth, tacoHeight);
        whileLoading();
        unloadScreen();

        Color bgColor = SaxionApp.createColor(35,35,35);
        SaxionApp.setFill(bgColor);
        SaxionApp.setBorderColor(bgColor);
        SaxionApp.drawRectangle(0,450, SaxionApp.getWidth(),SaxionApp.getHeight());
        SaxionApp.setBorderColor(Color.white);
        SaxionApp.drawText("J - Attack [5dmg]", 75, 475, 20);
        SaxionApp.drawText("K - Special Attack [10dmg]", 75, 505, 20);
        SaxionApp.drawText("L - Heal(+1) [xINF](Dev)", 75, 535, 20);
        SaxionApp.drawRectangle(0,0,0,0);
        SaxionApp.drawRectangle(0,0,0,0);
    }

    private int userMoveChoice(int monsterHP) {
        boolean correctUserInput = true;
        while (correctUserInput) {
            char userInput = SaxionApp.readChar();
            if (userInput == 'j') {
                monsterHP -= 5;
                correctUserInput = false;
                System.out.println(monsterHP);
            } else if (userInput == 'k') {
                monsterHP -= 10;
                correctUserInput = false;
                System.out.println(monsterHP);
            } else if (userInput == 'l') {
                health+=2;
                correctUserInput = false;
            } else {
                System.out.println("INVALID INPUT");
            }
        }
        return monsterHP;
    }

    //------------------------------------------DEMO ENDING-----------------------------------------

    private void demoEnding() {
        SaxionApp.clear();
        SaxionApp.drawImage(roomOne, 0, 50, 750,500);
        SaxionApp.drawImage("media/graphics/loading/1.png",0,50, 750,500);
        SaxionApp.drawImage("media/graphics/loading/1.png",0,50, 750,500);
        SaxionApp.setFill(Color.black);
        SaxionApp.setBorderColor(Color.black);
        SaxionApp.drawRectangle(0,0,750,50);
        SaxionApp.drawRectangle(0,550,750,50);
        SaxionApp.setBorderColor(Color.red);
        SaxionApp.drawText("The Spaghetti Monster has been slain", 130, 200, 30);
        SaxionApp.setBorderColor(Color.white);
        SaxionApp.drawText("and you have finished this demo game.", 125, 300, 30);
        SaxionApp.drawText("Thank you for playing", 190, 400, 40);
        toMenuLoading();
        unloadScreen();
        SaxionApp.sleep(999);
    }

    //-------------------------------------------MOVEMENT-------------------------------------------

    public void moveUp(){
        yCoordinateTaco = yCoordinateTaco - 32;
        drawTaco();
        System.out.println("X: " + xCoordinateTaco + " | Y: " + yCoordinateTaco);
     }

    public void moveDown(){
        yCoordinateTaco = yCoordinateTaco + 32;
        drawTaco();
        System.out.println("X: " + xCoordinateTaco + " | Y: " + yCoordinateTaco);
    }

    public void moveLeft(){
        xCoordinateTaco = xCoordinateTaco - 44;
        drawTaco();
        System.out.println("X: " + xCoordinateTaco + " | Y: " + yCoordinateTaco);
    }

    public void moveRight(){
        xCoordinateTaco = xCoordinateTaco + 44;
        drawTaco();
        System.out.println("X: " + xCoordinateTaco + " | Y: " + yCoordinateTaco);
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

    //--------------------------------------------LEVELS--------------------------------------------
    
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

    //------------------------------------------ROOM DRAWS------------------------------------------
    
    public void drawRoomOne(){
        SaxionApp.clear();

        drawTacoHP();

        SaxionApp.drawImage(roomOne,0,100,750,500);

        //Load Screen
        whileLoading();
        SaxionApp.sleep(0.1);
        unloadScreen();

        if (roomClear == false) {
            SaxionApp.drawImage(spaghettiMonster.image, spaghettiMonster.xCoord, spaghettiMonster.yCoord, spaghettiMonster.width,spaghettiMonster.height);
        }
        emptyDraw();
        drawTaco();
    }

    public void drawRoomTwo(){
        SaxionApp.clear();
        SaxionApp.drawImage(roomTwo,0,100,750,500);
        emptyDraw();
        drawTaco();
    }

    public void drawRoomThree(){
        SaxionApp.clear();
        SaxionApp.drawImage(roomThree,0,100,750,500);
        emptyDraw();
        drawTaco();
    }

    //---------------------------------------MONSTER HP DRAW----------------------------------------

    /*private void drawSpaghettiMonsterHP() {
        String halfMeatball = "media/graphics/Meatball_Half_Heart.png";
        String fullMeatball = "media/graphics/Meatball_Full_Heart.png";
        int monsterHP = spaghettiMonster.combatHp/10*2;
        //375, 505,
        int healthCounter = 0;
        int healthXCoord = 425;
        SaxionApp.setBorderColor(Color.white);
        //SaxionApp.drawText("Taco's Health: ", 250, 40, 25);
        boolean drawHalfHeart = true;
        while (healthCounter < monsterHP) {
            if (drawHalfHeart) {
                SaxionApp.drawImage(halfMeatball, healthXCoord, 505, heartWidth, heartHeight);
                drawHalfHeart = false;
            } else {
                SaxionApp.drawImage(fullMeatball, healthXCoord, 505, heartWidth, heartHeight);
                drawHalfHeart = true;
                healthXCoord+=60;
            }
            healthCounter++;
        }
    }

    private void redrawSpaghettiMonsterHP() {

        int monsterHP = spaghettiMonster.combatHp;
        int healthCounter = -1;
        boolean drawHalfHeart = true;
        while (healthCounter < monsterHP+1) {
            SaxionApp.removeLastDraw();
            healthCounter++;
        }
        if (monsterHP > 0) {
            drawSpaghettiMonsterHP();
        }
    }*/
    //-----------------------------------------TACO HP DRAW-----------------------------------------

    private void drawTacoHP() {
        SaxionApp.drawRectangle(50,10,150,75);
        SaxionApp.drawText("Map Placeholder", 70, 40, 15);

        int healthCounter = 0;
        int healthXCoord = 425;
        SaxionApp.setBorderColor(Color.white);
        SaxionApp.drawText("Taco's Health: ", 250, 40, 25);
        boolean drawHalfHeart = true;
        while (healthCounter < health) {
            if (drawHalfHeart) {
                SaxionApp.drawImage(halfHeart, healthXCoord, 25, heartWidth, heartHeight);
                drawHalfHeart = false;
            } else {
                SaxionApp.drawImage(fullHeart, healthXCoord, 25, heartWidth, heartHeight);
                drawHalfHeart = true;
                healthXCoord+=60;
            }
            healthCounter++;
        }
    }

    private void redrawTacoHP() {
        int healthCounter = -1;
        boolean drawHalfHeart = true;
        while (healthCounter < health+1) {
            SaxionApp.removeLastDraw();
            healthCounter++;
        }
        if (health > 0) {
            drawTacoHP();
        }
    }

    //-----------------------------------------LOAD SCREENS-----------------------------------------

    // Only when loading in FROM the 'main menu' / 'a menu'
    private void fromMenuLoading() {
        SaxionApp.drawImage("media/graphics/loading/1.png",0,50,750,500);
        SaxionApp.sleep(0.05);
        SaxionApp.drawImage("media/graphics/loading/1.png",0,50,750,500);
        SaxionApp.sleep(0.05);
        SaxionApp.drawImage("media/graphics/loading/2.png",0,50,750,500);
        SaxionApp.sleep(0.05);
        SaxionApp.drawImage("media/graphics/loading/2.png",0,50,750,500);
        SaxionApp.sleep(0.1);
        SaxionApp.drawImage("media/graphics/loading/2.png",0,50,750,500);
        SaxionApp.sleep(0.1);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,50,750,500);
        SaxionApp.sleep(0.1);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,50,750,500);
        SaxionApp.sleep(0.1);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,50,750,500);
        SaxionApp.sleep(0.1);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,50,750,500);
//        SaxionApp.pause();
    }
    // Only when loading in TO the 'main menu' / 'a menu'
    private void toMenuLoading() {
        SaxionApp.drawImage("media/graphics/loading/1.png",0,50,750,500);
        SaxionApp.drawImage("media/graphics/loading/1.png",0,50,750,500);
        SaxionApp.drawImage("media/graphics/loading/2.png",0,50,750,500);
        SaxionApp.drawImage("media/graphics/loading/2.png",0,50,750,500);
        SaxionApp.drawImage("media/graphics/loading/2.png",0,50,750,500);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,50,750,500);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,50,750,500);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,50,750,500);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,50,750,500);
    }

    // General Load Screen Initialization
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
//        SaxionApp.pause();
    }

    // Mid Load Screen [Always between "loadScreen();" and "unloadScreen();"]
    private void whileLoading() {
        SaxionApp.drawImage("media/graphics/loading/1.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/loading/1.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/loading/2.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/loading/2.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/loading/2.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,100,750,500);
        SaxionApp.drawImage("media/graphics/loading/3.png",0,100,750,500);
    }

    // Unload Load Screen
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
//        SaxionApp.pause();

    }

}

