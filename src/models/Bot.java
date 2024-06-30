package models;

import botplayingstrategy.BotPlayingStartegyFactory;
import botplayingstrategy.BotPlayingStrategy;

public class Bot extends Player {
	private BotDifficultyLevel botDifficultyLevel;
	private BotPlayingStrategy botPlayingStrategy;
	
	public Bot(char symbol, String name, int id, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
		super(symbol, name, id, playerType);
		this.botDifficultyLevel = botDifficultyLevel;
		this.botPlayingStrategy = BotPlayingStartegyFactory.getBotPlayingStartegyForDifficultyLevel(botDifficultyLevel);
	}
	
	public Cell makeMove(Board board) {
		System.out.println("Get ready for the BOT's move");
		Cell cell = botPlayingStrategy.makeMove(board);
		cell.setCellState(CellState.FILLED);
		cell.setPlayer(this);
		return cell;
	}
}
