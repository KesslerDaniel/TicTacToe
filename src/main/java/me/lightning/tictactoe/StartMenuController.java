package me.lightning.tictactoe;

import javafx.fxml.FXML;

import java.io.IOException;

public class StartMenuController {

    @FXML
    public void startNewGame() throws IOException {
        TicTacToe.setRoot("gameView");       
    }

}
