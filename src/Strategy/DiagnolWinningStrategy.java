package Strategy;

import models.Board;
import models.Move;

import java.util.HashMap;
import java.util.Map;

public class DiagnolWinningStrategy implements WinningStrategy{
    Map<Character, Integer> leftDiaMpa = new HashMap<>();
    Map<Character, Integer> rightDiaMpa = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Character symbol = move.getPlayer().getSymbol();

        if(row==col){
            if(!leftDiaMpa.containsKey(symbol)){
                leftDiaMpa.put(symbol, 0);
            }
            leftDiaMpa.put(symbol, leftDiaMpa.get(symbol)+1);

            if(leftDiaMpa.get(symbol).equals(board.getDimension())){
                return true;
            }
        }


        if(row+col==(board.getDimension()-1)){

            if(!rightDiaMpa.containsKey(symbol)){
                rightDiaMpa.put(symbol, 0);
            }
            rightDiaMpa.put(symbol, rightDiaMpa.get(symbol)+1);

            if(rightDiaMpa.get(symbol).equals(board.getDimension())){
                return true;
            }
        }

        return false;

    }

}
