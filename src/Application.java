import nl.saxion.app.SaxionApp;

import java.awt.*;

public class Application implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new Application(), 750, 600);
    }

    public void run() {
        //Test method to show example of sizes
        testRun();

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

}