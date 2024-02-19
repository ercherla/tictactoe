package models;

import java.util.Scanner;

public class HumanPlayer extends Player{

    public Scanner scanner ;

    public HumanPlayer(Character symbol, String name, int id, PlayerType playerType, Scanner scanner) {
        super(symbol, name, id, playerType);
        this.scanner = scanner;
    }

    @Override
    public Cell makeMove(Board board) {
        System.out.println(this.getName()+", your turn,enter row and column");
        int row = scanner.nextInt();
        int column = scanner.nextInt();

        while (!validateRowandCol(row,column,board)){
            System.out.println(this.getName()+", Invalid move,Please enter row and column");
             row = scanner.nextInt();
             column = scanner.nextInt();
        }

        //Valid move
        Cell cell = board.getBoard().get(row).get(column);
        cell.setCellstate(CellState.FILLED);
        cell.setPlayer(this);
        return cell;
    }

    private boolean validateRowandCol(int row, int column, Board board) {
        if(row >= board.getDimension() || row < 0){
            return false;
        }
        if(column >= board.getDimension() || column < 0){
            return false;
        }
        if(CellState.FILLED.equals(board.getBoard().get(row).get(column).getCellstate())){
            return false;
        }
        return true;
    }

//    public HumanPlayer(Character symbol, String name, int id, PlayerType playerType) {
//        super(symbol, name, id, playerType);
//    }
}
