package models;

import Strategy.WinningStrategy;
import exception.DuplicateSymbol;
import exception.MoreThanOneBotException;
import exception.PlayersandBoardCountMismatch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private List<WinningStrategy> winningStrategies;
    private List<Player> playerList;
    private Board board;
    private Player winner;
    private GameState gameState;
    private int nextPlayerIndex;
    private List<Move> moves;

    private Game(List<Player> playerList,int dimension,List<WinningStrategy> winningStrategies) {
        this.nextPlayerIndex = 0;
        this.gameState = GameState.IN_PROG;
        this.moves = new ArrayList<>();
        this.playerList = playerList;
        this.board = new Board(dimension);
        this.winningStrategies = winningStrategies;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
    public static Builder getBuilder(){
        return new Builder();
    }

    public void printBoard() {
        board.printBoard();
    }

    public void makeMove() {
        Player player = playerList.get(nextPlayerIndex);
        Cell cell =  player.makeMove(board);

        Move move = new Move(cell,player);
        moves.add(move);

        if(checkWinner(move,board)){
            gameState = GameState.CONCLUDED;
            winner = player;
            return;
        }
        if(moves.size()==board.getDimension()*board.getDimension()){
            gameState = GameState.DRAW;
            return;
        }

        nextPlayerIndex++;
        nextPlayerIndex = nextPlayerIndex % playerList.size();
    }

    private boolean checkWinner(Move move, Board board) {
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(board,move)){
                return true;
            }
        }
        return false;
    }


    public static  class Builder {
        private List<Player> players;
        private int dimension;
        private List<WinningStrategy> winningStrategies;

        public Builder() {
            this.dimension = 0;
            this.players = new ArrayList<>();
        }

        public Builder setDimensions(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayer(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Game build() throws MoreThanOneBotException, PlayersandBoardCountMismatch, DuplicateSymbol {
            validateBotCount();
            validateDimensionAndPlayerCount();
            validateUniqueSymbolForEachPlayer();
            return new Game(players, dimension, winningStrategies);
        }

        private void validateUniqueSymbolForEachPlayer() throws DuplicateSymbol {
            HashSet<Character> symbols = new HashSet<>();
            for (Player player : players) {
                if (symbols.contains(player.getSymbol())) {
                    throw new DuplicateSymbol();
                }
                symbols.add(player.getSymbol());
            }
        }

        private void validateDimensionAndPlayerCount() throws PlayersandBoardCountMismatch {
            if (players.size() != dimension - 1) {
                throw new PlayersandBoardCountMismatch();
            }
        }

        private void validateBotCount() throws MoreThanOneBotException {
            int botCount = 0;
            for (Player player : players) {
                if (player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount++;
                }
                if (botCount > 1) {
                    throw new MoreThanOneBotException();
                }
            }
        }

    }

}
