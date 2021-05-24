

public class Player{
    private Item wieldedItem = null;
    public char symbol;
    private int xPos;
    private int yPos;
    private boolean alive = true;
    public Player(int xPos, int yPos, char symbol){
        this.xPos = xPos;
        this.yPos = yPos;
        this.symbol = symbol;
    }

    public String getStats(){
        StringBuilder sb = new StringBuilder("Player no.: " + symbol + "\n");
        sb.append("Status: " + ((alive) ? "Alive\n" : "Dead\n"));
        sb.append("Wielding: " + ((wieldedItem != null) ? wieldedItem.itemType.toString() + "\n" : "Nothing\n"));
        return sb.toString();
    }

    public int getX(){
        return xPos;
    }

    public int getY(){
        return yPos;
    }

    public void setX(int xPos){
        this.xPos = xPos;
    }

    public void setY(int yPos){
        this.yPos = yPos;
    }

    public Item getItem(){
        return wieldedItem;
    }

    public void setItem(Item wieldedItem){
        this.wieldedItem = wieldedItem;
    }

    public void kill() {
        if (alive) alive = false;
    }
}