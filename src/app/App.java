package app;

import Strategy.ColumnWinningStrategy;
import Strategy.DiagnolWinningStrategy;
import Strategy.RowWinningStrategy;
import Strategy.WinningStrategy;
import controllers.GameController;
import exception.DuplicateSymbol;
import exception.MoreThanOneBotException;
import exception.PlayersandBoardCountMismatch;
import models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws DuplicateSymbol, PlayersandBoardCountMismatch, MoreThanOneBotException {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);
        int dimension = 3;
        ArrayList<Player>players =new ArrayList<>();

        players.add(new HumanPlayer('X',"Aditya",1, PlayerType.HUMAN,scanner));
        players.add(new HumanPlayer('0',"Ladda",2, PlayerType.HUMAN,scanner));


        ArrayList<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColumnWinningStrategy());
        winningStrategies.add(new DiagnolWinningStrategy());

        Game game = gameController.createGame(dimension,players,winningStrategies);

        while(GameState.IN_PROG.equals(game.getGameState())){
            gameController.printBoard(game);
            gameController.makeMove(game);
        }

        if(GameState.CONCLUDED.equals(game.getGameState())){
            System.out.println(game.getWinner().getName()+" Has won the game");
        }

        if(GameState.DRAW.equals(game.getGameState())){
            System.out.println("the game is a TIE");
        }

    }
}
