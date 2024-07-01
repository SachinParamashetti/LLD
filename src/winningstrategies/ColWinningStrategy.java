package winningstrategies;

import java.util.HashMap;
import java.util.Map;

import models.Board;
import models.Move;

public class ColWinningStrategy implements WinningStrategy{
	Map<Integer, Map<Character, Integer>> map = new HashMap<>();
	
	
	
	@Override
	public boolean checkWinner(Board board, Move move) {
		int col = move.getCell().getCol();
		char symbol = move.getPlayer().getSymbol();
		
		//check if current col present in hashmap
		if(!map.containsKey(col)) {
			map.put(col, new HashMap<>());
		}
		
		Map<Character, Integer> colMap = map.get(col);
		//first time entry for symbol
		
		if(!colMap.containsKey(symbol)) {
			colMap.put(symbol, 0);
		}
		
		//update the symbol count for colmap
		colMap.put(symbol, colMap.get(symbol)+1);
		
		//check if the symbol count has reached size of the board
		if(board.getBoard().size()==colMap.get(symbol)) {
			System.out.println("Winning via "+col);
			return true;
		}
		
		return false;
	} 



}
