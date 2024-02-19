package controllers;

import Strategy.WinningStrategy;
import exception.DuplicateSymbol;
import exception.MoreThanOneBotException;
import exception.PlayersandBoardCountMismatch;
import models.Game;
import models.Player;

import java.util.List;

public class GameController {
    public Game createGame(int dimension , List<Player>playerList, List<WinningStrategy>winningStrategiesList) throws DuplicateSymbol, PlayersandBoardCountMismatch, MoreThanOneBotException {
        return Game.getBuilder()
                .setPlayer(playerList)
                .setDimensions(dimension)
                .setWinningStrategies(winningStrategiesList)
                .build();
    }
    public void makeMove(Game game){
        game.makeMove();
    }
    public void printBoard(Game game){
        game.printBoard();
    }

    public void undo(Game game) {
        game.undo();
    }
}
