public class Tile{
    public enum TileType {WALL, FLOOR, DOOR_OPEN, DOOR_CLOSED};
    private TileType tileType;
    private Item item = null;
    private Player player = null;
    public char symbol;
    public Tile(TileType tileType){
        this.tileType = tileType;
        symbol = assignSymbol(tileType);
    }

    public Tile(TileType tileType, Item item){
        this.tileType = tileType;
        this.item = item;
        symbol = assignSymbol(tileType);
    }

    public TileType getTileType(){
        return tileType;
    }

    public void setTileType(TileType tileType){
        this.tileType = tileType;
        symbol = assignSymbol(tileType);
    }

    public boolean hasPlayer(){
        return (player != null);
    }

    public Player getPlayer(){
        return player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Item getItem(){
        return item;
    }

    public void setItem(Item item){
        this.item = item;
    }

    public boolean hasItem(){
        return (item != null);
    }

    public static TileType assignType(char c){
        TileType retType;
        switch(c) {
            case '#':
                retType = TileType.WALL;
                break;
            case '.':
                retType = TileType.FLOOR;
                break;
            case 'X':
                retType = TileType.DOOR_CLOSED;
                break;
            case 'O':
                retType = TileType.DOOR_OPEN;
                break;
            default:
                retType = TileType.FLOOR;
        }
        return retType;
    }
    private char assignSymbol(TileType tileType){
        char symbol;
        switch(tileType) {
            case WALL:
                symbol = '#';
                break;
            case FLOOR:
                symbol = '.';
                break;
            case DOOR_CLOSED:
                symbol = 'X';
                break;
            case DOOR_OPEN:
                symbol = 'O';
                break;
            default:
                symbol = '?';
        }
        return symbol;
    }
}