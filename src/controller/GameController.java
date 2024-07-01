package controller;

import java.util.List;

import exception.DuplicateSymbolException;
import exception.MoreThanOneBotException;
import exception.PlayersCountMismatchException;
import models.Game;
import models.Player;
import winningstrategies.WinningStrategy;

public class GameController {
	public Game startGame(int dimension,List<Player> playerList,List<WinningStrategy> winningStartegies ) throws MoreThanOneBotException, DuplicateSymbolException, PlayersCountMismatchException {
		return Game.getBuilder()
				.setDimension(dimension)
				.setPlayers(playerList)
				.setWinningStrategies(winningStartegies)
				.build();
		
	}
	
	public void printBoard(Game game) {
		game.printBoard();
	}
	
	public void makeMove(Game game) {
		game.makeMove();
	}
	
	public void undo(Game game) {
		game.undo();
	}
	
}
