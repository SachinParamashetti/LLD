package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exception.DuplicateSymbolException;
import exception.MoreThanOneBotException;
import exception.PlayersCountMismatchException;
import winningstrategies.WinningStrategy;

public class Game {
	private List<Player> players;
	private Board board;
	private List<Move> moves;
	private Player winner;
	private GameState gameState;
	private int nextPlayerIndex;
	private List<WinningStrategy> winningstratergies;
	
	
	private Game(int dimension, List<Player> players, List<WinningStrategy> winningstratergies) {
		this.board= new Board(dimension);
		this.players = players;
		this.winningstratergies = winningstratergies;
		this.gameState = GameState.IN_PROGRESS;
		this.nextPlayerIndex = 0;
		this.moves = new ArrayList<>();
	}
	public static Builder getBuilder() {
		return new Builder();
	}
	
	public static class Builder {
		private List<Player> players;
		private List<WinningStrategy> winningStrategies;
		private int dimension;
		 
		private Builder() {
			
			this.players = new ArrayList<>();
			this.winningStrategies = new ArrayList<>();
			this.dimension = 0;
		}
		
		public Game build() throws MoreThanOneBotException, DuplicateSymbolException, PlayersCountMismatchException {
			/*
			 * 1. Validate Bot count <=1
			 * 2. ValidateUniqueSymbolForPlayers
			 * 3. Validate Dimension and player count
			 */
			
			validateBotCount();
			validateUniqueSymbolForPlayers();
			validateDimensionsAndPlayerCount();
			
			return new Game(dimension, players, winningStrategies);
		}
		
		private void validateDimensionsAndPlayerCount() throws PlayersCountMismatchException {
			// TODO Auto-generated method stub
			if(players.size()!=dimension-1) {
				throw new PlayersCountMismatchException();
			}
		}

		private void validateUniqueSymbolForPlayers() throws DuplicateSymbolException {
			// TODO Auto-generated method stub
			Set<Character> symbols = new HashSet();
			for(Player player:players) {
				if(symbols.contains(player.getSymbol())) {
					throw new DuplicateSymbolException();
				}
				else {
					symbols.add(player.getSymbol());
				}
			}
			
		}

		private void validateBotCount() throws  MoreThanOneBotException {
			// TODO Auto-generated method stub
			int botCount=0;
			
			for(Player player:players) {
				if(player.getPlayerType().equals(PlayerType.BOT)) {
					botCount++;
				}
			}
			if (botCount>1) {
				throw new MoreThanOneBotException();
			}
		}

		public Builder setPlayers(List<Player> players) {
			this.players = players;
			return this;
		}

		public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
			this.winningStrategies = winningStrategies;
			return this;
		}

		public Builder setDimension(int dimension) {
			this.dimension = dimension;
			return this;
		}
		 
	}
	
	 
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public List<Move> getMoves() {
		return moves;
	}
	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}
	public Player getWinner() {
		return winner;
	}
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	public GameState getGameState() {
		return gameState;
	}
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	public int getNextPlayerIndex() {
		return nextPlayerIndex;
	}
	public void setNextPlayerIndex(int nextPlayerIndex) {
		this.nextPlayerIndex = nextPlayerIndex;
	}
	public void printBoard() {
		// TODO Auto-generated method stub
		board.printBoard();
	}
	public void makeMove() {
		// TODO Auto-generated method stub
		Player player = players.get(nextPlayerIndex);
		Cell cell = player.makeMove(board);
		
		Move move = new Move(cell, player);
		moves.add(move);
		
		if(checkWinner(move,board)) {
			gameState = gameState.SUCCESS;
			winner = player;
			return;
		}
		
		/* 
		 	checking for draw
		*/
		if(moves.size()==board.getDimension()*board.getDimension()) {
			gameState=gameState.DRAW;
		}
		
		nextPlayerIndex++;
		nextPlayerIndex = nextPlayerIndex % players.size();
	}
	private boolean checkWinner(Move move, Board board) {
		// TODO Auto-generated method stub
		for(WinningStrategy winningStrategy : winningstratergies) {
			if(winningStrategy.checkWinner(board,move)) {
				return true;
			}	
		}
		return false;
	}
	
}
