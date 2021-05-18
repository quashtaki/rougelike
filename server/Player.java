

public class Player{
    private Item wieldedItem = null;
    public char symbol;
    private int xPos;
    private int yPos;
    public Player(int xPos, int yPos, char symbol){
        this.xPos = xPos;
        this.yPos = yPos;
        this.symbol = symbol;
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
}