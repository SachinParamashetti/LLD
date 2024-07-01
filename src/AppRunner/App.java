package AppRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.GameController;
import exception.DuplicateSymbolException;
import exception.MoreThanOneBotException;
import exception.PlayersCountMismatchException;
import models.Bot;
import models.BotDifficultyLevel;
import models.Game;
import models.GameState;
import models.Player;
import models.PlayerType;
import winningstrategies.ColWinningStrategy;
import winningstrategies.DiagonalWinningStartegy;
import winningstrategies.RowWinningStrategy;
import winningstrategies.WinningStrategy;

public class App {

	public static void main(String[] args) throws MoreThanOneBotException, DuplicateSymbolException, PlayersCountMismatchException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		GameController gameController = new GameController();
		
		int dimension = 3;
		List<Player>  playerList = new ArrayList<>();
		List<WinningStrategy> winningStrategies = new ArrayList<WinningStrategy>();
		
		playerList.add(new Player('X',"abc",1,PlayerType.HUMAN));
		playerList.add(new Bot('O',"GPT",2,PlayerType.BOT,BotDifficultyLevel.EASY));
		
		winningStrategies.add(new RowWinningStrategy());
		winningStrategies.add(new ColWinningStrategy());
		winningStrategies.add(new DiagonalWinningStartegy());
		
		Game game = gameController.startGame(dimension, playerList, winningStrategies);
		
		while(game.getGameState().equals(GameState.IN_PROGRESS)) {
			
			
			game.printBoard();
			
			System.out.println("Does anyone want to undo? (y/n)");
			String undo=scanner.next();
			if(undo.equalsIgnoreCase("y")) {
				gameController.undo(game);
				continue;
			}
			
			gameController.makeMove(game);
			
		}
		
		if(GameState.SUCCESS.equals(game.getGameState())) {
			System.out.println(game.getWinner().getName()+", Congrats!!! You won the Game :)");
		}
		if(GameState.DRAW.equals(game.getGameState())) {
			System.out.println("Match tied :|");
		}
	}

}
