package winningstrategies;

import java.util.HashMap;
import java.util.Map;

import models.Board;
import models.Move;

public class DiagonalWinningStartegy implements WinningStrategy{
	
	Map<Character, Integer> leftDiagonalMap = new HashMap<>();
	Map<Character, Integer> rightDiagonalMap = new HashMap<>();
	@Override
	public boolean checkWinner(Board board, Move move) {
		int row = move.getCell().getRow();
		int col = move.getCell().getCol();
		char symbol = move.getPlayer().getSymbol();
		
		//check if cell is part of left diagonal
		if(row==col) {
			//check if the symbol is coming for the first time in left diagonal
			if(!leftDiagonalMap.containsKey(symbol)) {
				leftDiagonalMap.put(symbol, 0);
			}
			leftDiagonalMap.put(symbol, 1+leftDiagonalMap.get(symbol));
			
			//check if the count of current symbol is same as size of board
			if(board.getDimension()==leftDiagonalMap.get(symbol)) {
				System.out.println("Winning via left diagonal");
				return true;
			}
		}
		
		//check if the cell is part of the right diagonal
		if(row+col == board.getDimension()-1) {
			if(!rightDiagonalMap.containsKey(symbol)) {
				rightDiagonalMap.put(symbol, 0);
			}
			
			rightDiagonalMap.put(symbol, 1+rightDiagonalMap.get(symbol));
			
			//check if the count of current symbol is same as size of board
			if(board.getDimension()==rightDiagonalMap.get(symbol)) {
				System.out.println("Winning via right diagonal");
				return true;
			}
		}
		
		return false;
	}

}
