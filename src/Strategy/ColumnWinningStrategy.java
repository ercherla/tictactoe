package Strategy;

import models.Board;
import models.Move;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy{
    Map<Integer, Map<Character, Integer>> columnMaps = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int column = move.getCell().getCol();
        Character symbol = move.getPlayer().getSymbol();

        if(!columnMaps.containsKey(column)){
            columnMaps.put(column, new HashMap<>());
        }

        Map<Character, Integer> columnMap = columnMaps.get(column);
        if(!columnMap.containsKey(symbol)){
            columnMap.put(symbol, 0);
        }
        columnMap.put(symbol, columnMap.get(symbol)+1);

        if(columnMap.get(symbol).equals(board.getDimension())){
            return true;
        }

        return false;
    }

    @Override
    public void handleBoard(Board board, Move lastMove) {
        Character symbol = lastMove.getPlayer().getSymbol();
        int col = lastMove.getCell().getCol();

        Map<Character, Integer> colMap = columnMaps.get(col);
        colMap.put(symbol,colMap.get(symbol)-1);

    }

}
