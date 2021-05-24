import java.util.ArrayList;

public class Game {
    
    final int ySize = 30;
    final int xSize = 50;
    private Player[] players;
    String[] inputBoard = {"##################################################",
                           "#.........#......................................#",
                           "#.........#......................................#",
                           "#.........#......................................#",
                           "#.........#......................................#",
                           "#.........#......................................#",
                           "#.........#......................................#",
                           "#####X#####......#.#.............................#",
                           "#................#.#.............................#",
                           "#................###.............................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "#................................................#",
                           "##################################################"};

//                            y      x
    Tile[][] board = new Tile[ySize][xSize];
    public Game(){
        buildBoard();
        printBoard();
    }

    public void addPlayer(Player player, int x, int y){
      board[y][x].setPlayer(player);
    }

    private void buildBoard(){
        int x;
        for (int y = 0; y<ySize; y++){
            x = 0;
            for (char c: inputBoard[y].toCharArray() ){
                board[y][x++] = new Tile(Tile.assignType(c));
            }
        }
        board[5][5].setItem(new Item(Item.ItemType.SWORD));
        board[8][18].setItem(new Item(Item.ItemType.KEY));
    }

    public void movePlayer(Player player, int endX, int endY){
        if (!board[endY][endX].hasPlayer() && // move to the wanted tile if there is no player there and
        (board[endY][endX].getTileType() == Tile.TileType.FLOOR // the wanted tile is floor or
        || board[endY][endX].getTileType() == Tile.TileType.DOOR_OPEN // the wanted tile is an open door or
        || (board[endY][endX].getTileType() == Tile.TileType.DOOR_CLOSED && player.getItem() != null && player.getItem().itemType == Item.ItemType.KEY)) // the wanted tile is a closed door, and the player has a key
        ){
            if(board[endY][endX].getTileType() == Tile.TileType.DOOR_CLOSED){
              player.setItem(null); // open the door by using (and consuming) the key
              board[endY][endX].setTileType(Tile.TileType.DOOR_OPEN);
            }
            board[endY][endX].setPlayer(player);
            board[player.getY()][player.getX()].setPlayer(null);
            player.setX(endX);
            player.setY(endY);
            System.out.println("moved player" + player.symbol + " to " + endX + ":" + endY);
            if(player.getItem()==null && board[endY][endX].getItem()!=null){
                player.setItem(board[endY][endX].getItem());
                board[endY][endX].setItem(null);
                System.out.println("Player " + player.symbol + " picked up " + player.getItem());
            }
        }else if(board[endY][endX].hasPlayer() && player.getItem() != null && (player.getItem().itemType == Item.ItemType.SWORD)){
            board[endY][endX].getPlayer().kill();
            System.out.println("Killed player " + board[endY][endX].getPlayer().symbol);
        }
    }

    public String getBoard(){
        StringBuilder sBuilder = new StringBuilder();
        for (int y = 0; y<ySize; y++){
            for (int x = 0; x<xSize; x++){
                if(board[y][x].hasPlayer()){
                    sBuilder.append(board[y][x].getPlayer().symbol);
                }else if(board[y][x].hasItem()){
                    sBuilder.append(board[y][x].getItem().symbol);
                }else{
                    sBuilder.append(board[y][x].symbol);
                }
                
            }
            sBuilder.append("\n");
        }

        return sBuilder.toString();
    }

    public Player[] getPlayers(){
        return players;
    }

    public void printBoard(){
        System.out.println(getBoard());
    }
}
