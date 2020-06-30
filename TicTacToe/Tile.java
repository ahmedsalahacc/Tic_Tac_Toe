package TicTacToe;

import javafx.geometry.Pos;
//import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Tile extends StackPane{
    private Rectangle border;
    private TileText text;
    //private boolean turn;
    

    public Tile(){
        //Setting text
        text = new TileText();
        text.setFont(Font.font(72));
        ////event listener
        setOnMouseClicked(event ->{
            if (!Tictactoe.playable){
                return;
            }
            if (event.getButton()== MouseButton.PRIMARY){
                if (!Tictactoe.x_turn) 
                    return;
                else{
                    System.out.println(Tictactoe.x_turn);
                    text.draw_X();
                    Tictactoe.switch_flag();
                    Tictactoe.checkState();
                }
            }
            else if (event.getButton()== MouseButton.SECONDARY)
            {
                if(Tictactoe.x_turn){
                    return;    
                } else{
                    text.draw_O();
                    System.out.println(Tictactoe.x_turn);
                    Tictactoe.switch_flag();
                    Tictactoe.checkState();

                }
                
            } 
                
        });
        //setting tile btn
        setPrefSize(200, 200);
        this.border = new Rectangle(200,200);
        border.setFill(Color.YELLOW);
        border.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);
        getChildren().addAll(this.border,text);
    }

    public TileText getText(){
        return this.text;
    }

    public double getCenterX(){
        return getTranslateX()+100;
    }
    public double getCenterY(){
        return getTranslateY()+100;
    }
}