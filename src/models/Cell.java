package models;

public class Cell {
    int row;
    int col;
    private CellState cellstate;
    private Player player;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellstate = CellState.EMPTY;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public CellState getCellstate() {
        return cellstate;
    }

    public void setCellstate(CellState cellstate) {
        this.cellstate = cellstate;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void printCell() {
       if( cellstate.equals(CellState.FILLED)){
           System.out.print("| "+player.getSymbol()+" |");
       }
       else{
           System.out.print("|-|");
       }
    }
}
