package winningstrategies;

import java.util.HashMap;
import java.util.Map;

import models.Board;
import models.Move;

public class RowWinningStrategy implements WinningStrategy{
	Map<Integer, Map<Character, Integer>> map = new HashMap<>();
	
	
	
	@Override
	public boolean checkWinner(Board board, Move move) {
		int row = move.getCell().getRow();
		char symbol = move.getPlayer().getSymbol();
		
		//check if current row present in hashmap
		if(!map.containsKey(row)) {
			map.put(row, new HashMap<>());
		}
		
		Map<Character, Integer> rowMap = map.get(row);
		//first time entry for symbol
		
		if(!rowMap.containsKey(symbol)) {
			rowMap.put(symbol, 0);
		}
		
		//update the symbol count for rowmap
		rowMap.put(symbol, rowMap.get(symbol)+1);
		
		//check if the symbol count has reached size of the board
		if(board.getBoard().size()==rowMap.get(symbol)) {
			System.out.println("Winning via "+row);
			return true;
		}
		
		return false;
	} 

}
