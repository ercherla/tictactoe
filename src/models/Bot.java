package models;

import Strategy.BotPlayingStrategy;
import factories.BotPlayingStrategyFactory;

public class Bot extends Player{
    private DifficultyLevel difficultylevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(Character symbol, String name, int id, PlayerType playerType, DifficultyLevel difficultylevel) {
        super(symbol, name, id, playerType);
        this.difficultylevel = difficultylevel;
        this.botPlayingStrategy  = BotPlayingStrategyFactory.getBotPlayingStrategy(difficultylevel);
    }

    public DifficultyLevel getDifficultylevel() {
        return difficultylevel;
    }

    public void setDifficultylevel(DifficultyLevel difficultylevel) {
        this.difficultylevel = difficultylevel;
    }

    @Override
    public Cell makeMove(Board board) {
        System.out.println("Bot is making the move");
        Cell cell = botPlayingStrategy.makeMove(board);
        cell.setCellstate(CellState.FILLED);
        cell.setPlayer(this);
        return cell;
    }
}
