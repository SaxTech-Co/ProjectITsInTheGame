public class Boss {
    String name;
    int combatHp;
    String image;
    int xCoord;
    int yCoord;
    int width;
    int height;

    public Boss(String name, int combatHp, String image, int xCoord, int yCoord, int width, int height) {
        this.name = name;
        this.combatHp = combatHp;
        this.image = image;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.width = width;
        this.height = height;
    }
}