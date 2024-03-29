package Strategy;

import models.Board;
import models.Cell;
import models.CellState;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{

    @Override
    public Cell makeMove(Board board) {
        for(List<Cell> row : board.getBoard()){
            for(Cell cell : row){
                if(CellState.EMPTY.equals(cell.getCellstate())){
                    return cell;
                }
            }
        }
        return null;
    }
}
