package TicTacToe;

import javafx.scene.text.Text;

public class TileText extends Text{
    
    public void draw_X(){
        setText("X");
    }
    public void draw_O(){
        setText("O");
    }

    public String getValue(){
        return this.getText();
    }
}