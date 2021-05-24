import java.util.ArrayList;

public class Game {
    
    final int ySize = 30;
    final int xSize = 50;
    private Player player1;
    private Player player2;
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
        player1 = new Player(18,10,'1');
        player2 = new Player(20,20,'2');
        board[10][18].setPlayer(player1);
        board[20][20].setPlayer(player2);
    }

    public void movePlayer(Player player, int endX, int endY){
        if (!board[endY][endX].hasPlayer() && (board[endY][endX].getTileType() == Tile.TileType.FLOOR || board[endY][endX].getTileType() == Tile.TileType.DOOR_OPEN || (board[endY][endX].getTileType() == Tile.TileType.DOOR_CLOSED && player.getItem() != null && player.getItem().itemType == Item.ItemType.KEY))){
            if(board[endY][endX].getTileType() == Tile.TileType.DOOR_CLOSED){
              player.setItem(null);
              board[endY][endX].setTileType(Tile.TileType.DOOR_OPEN);
            }
            board[endY][endX].setPlayer(player);
            board[player.getY()][player.getX()].setPlayer(null);
            player.setX(endX);
            player.setY(endY);
            System.out.println("moved " + player.symbol + " to " + endX + ":" + endY);
            printBoard();
            if(player.getItem()==null && board[endY][endX].getItem()!=null){
                player.setItem(board[endY][endX].getItem());
                board[endY][endX].setItem(null);
            }
        }else if(board[endY][endX].hasPlayer() && (player.getItem().itemType == Item.ItemType.SWORD)){
            board[endY][endX].getPlayer().kill();
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

    public Player getPlayer1(){
        return player1;
    }
    
    public Player getPlayer2(){
        return player2;
    }

    public void printBoard(){
        for (int y = 0; y<ySize; y++){
            for (int x = 0; x<xSize; x++){
                if(board[y][x].hasPlayer()){
                    System.out.print(board[y][x].getPlayer().symbol);
                }else if(board[y][x].hasItem()){
                    System.out.print(board[y][x].getItem().symbol);
                }else{
                    System.out.print(board[y][x].symbol);
                }
                
            }
            System.out.println();
        }
    }
}
