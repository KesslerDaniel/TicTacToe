package me.lightning.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {

    @FXML
    private Label gameStatus;

    @FXML
    private GridPane gameGrid;

    @FXML
    private Pane winningLineContainer;

    @FXML
    private Line winningLine;

    @FXML
    private Button restartButton;

    private GameBoard gameBoard;

    @FXML
    public void initialize(URL url, ResourceBundle resource) {
        gameBoard = new GameBoard();
        gameStatus.setText("Player " + gameBoard.getCurrentPlayer() + " begins");
    }

    @FXML
    public void onCellClick(ActionEvent event) {

        Button button = (Button) event.getSource();
        int row = GridPane.getRowIndex(button);
        int col = GridPane.getColumnIndex(button);

        char currentPlayer = gameBoard.getCurrentPlayer();

        gameBoard.setField(row, col, currentPlayer);

        button.setText(String.valueOf(currentPlayer));
        button.setDisable(true);

        if (gameBoard.isWinner(currentPlayer)) {
            gameStatus.setText("Player " + currentPlayer + " wins!");
            endGame();
            drawWinningLine(gameBoard.getWinningLinePos());
            return;

        } else if (gameBoard.isFull()) {
            gameStatus.setText("It's a draw!");
            endGame();
            return;
        }

        gameBoard.switchPlayer();
        gameStatus.setText("Player " + gameBoard.getCurrentPlayer() + "'s turn");
    }

    private void endGame() {
        gameGrid.setDisable(true);
        restartButton.setVisible(true);
    }

    @FXML
    public void restartGame() throws IOException {
        gameBoard.clearBoard();
        gameBoard.switchPlayer();

        resetGameGrid();
        resetWinningLine();

        restartButton.setVisible(false);
        gameStatus.setText("Player " + gameBoard.getCurrentPlayer() + " begins");
    }

    private void resetGameGrid() {

        for (Node node : gameGrid.getChildren()) {
            if (node instanceof Button button) {
                button.setText("");
                button.setDisable(false);
            }
        }

        gameGrid.setDisable(false);

    }

    private void drawWinningLine(WinningLinePos pos) {
        winningLine.setStartX(pos.x1() * 90);
        winningLine.setStartY(pos.y1() * 90);
        winningLine.setEndX(pos.x2() * 90);
        winningLine.setEndY(pos.y2() * 90);

        winningLineContainer.setVisible(true);
    }

    private void resetWinningLine() {
        winningLine.setStartX(0);
        winningLine.setStartY(0);
        winningLine.setEndX(0);
        winningLine.setEndY(0);

        winningLineContainer.setVisible(false);
    }

}