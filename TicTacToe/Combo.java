package TicTacToe;

public class Combo {
    protected Tile []tiles;
    
    public Combo(Tile ...tiles){
        this.tiles = tiles;
    }

    public boolean isComplete(){
        if (tiles[0].getText().getValue().isEmpty())
            return false;
        return tiles[0].getText().getValue().equals(tiles[1].getText().getValue()) &&
                tiles[0].getText().getValue().equals(tiles[2].getText().getValue());

    }
}