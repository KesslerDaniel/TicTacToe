package me.lightning.tictactoe;

public class GameBoard {
    
    private char[][] fields;
    private int usedFields;
    private char[] players;
    private int currentPlayer;
    private WinningLinePos winningLinePos;
    
    public GameBoard() {
        this.players = new char[]{'X', 'O'};
        this.fields = new char[3][3];
        this.currentPlayer = 0;
        this.usedFields = 0;
        this.winningLinePos = null;
    }

    public void setField(int x, int y, char value) {
        if (fields[x][y] == Character.UNASSIGNED) {
            usedFields++;
        }

        fields[x][y] = value;
    }

    public WinningLinePos getWinningLinePos() {
        return winningLinePos;
    }

    public char getCurrentPlayer() {
        return players[currentPlayer];
    }

    public void switchPlayer() {
        currentPlayer++;

        if (currentPlayer >= players.length) {
            currentPlayer = 0;
        }
    }
    
    public boolean isFull() {
        return usedFields >= 9;
    }
    
    public boolean isWinner(char player) {
        
        for (int i = 0; i < fields.length; i++) {
            if (fields[i][0] == player && fields[i][1] == player && fields[i][2] == player) {
                winningLinePos = new WinningLinePos(0, i, 2, i);
                return true;
            }
        }
        
        for (int i = 0; i < fields[0].length; i++) {
            if (fields[0][i] == player && fields[1][i] == player && fields[2][i] == player) {
                winningLinePos = new WinningLinePos(i, 0, i, 2);
                return true;
            }
        }
        
        if (fields[0][0] == player && fields[1][1] == player && fields[2][2] == player) {
            winningLinePos = new WinningLinePos(0, 0, 2, 2);
            return true;
        }
        
        if (fields[0][2] == player && fields[1][1] == player && fields[2][0] == player) {
            winningLinePos = new WinningLinePos(0, 2, 2, 0);
            return true;
        }
        
        return false;
    }

    public void clearBoard() {

        usedFields = 0;
        winningLinePos = null;

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j] = Character.UNASSIGNED;
            }
        }
    }

}
