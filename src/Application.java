import nl.saxion.app.SaxionApp;

public class Application implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new Application(), 500, 500);
    }

    public void run() {
        SaxionApp.printLine("Test");

    }

}