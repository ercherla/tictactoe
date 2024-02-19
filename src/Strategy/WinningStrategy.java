package Strategy;
import models.Board;
import models.Move;
public interface WinningStrategy {
    boolean checkWinner(Board board , Move move);

    void handleBoard(Board board, Move lastMove);
}
