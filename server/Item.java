public class Item {
    enum ItemType {SWORD, KEY};
    ItemType itemType;
    public final char symbol;

    public Item(ItemType itemType){
        this.itemType = itemType;
        symbol = assignSymbol(itemType);
    }
    
    private char assignSymbol(ItemType itemType){
        char symbol;
        switch(itemType) {
            case SWORD:
                symbol = 'S';
                break;
            case KEY:
                symbol = 'K';
                break;
            default:
                symbol = '?';
        }
        return symbol;
    }
}
