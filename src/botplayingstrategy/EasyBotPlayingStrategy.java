package botplayingstrategy;

import java.util.List;

import models.Board;
import models.Cell;
import models.CellState;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{

	@Override
	public Cell makeMove(Board board) {
		// TODO Auto-generated method stub
		for(List<Cell> row : board.getBoard()) {
			for(Cell cell:row) {
				if(CellState.EMPTY.equals(cell.getCellState())) {
					System.out.println(cell);
					return cell;
				}
			}
		}
		return null;
		
	}

}
