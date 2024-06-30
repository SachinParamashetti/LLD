package botplayingstrategy;

import models.BotDifficultyLevel;

public class BotPlayingStartegyFactory {

	public static BotPlayingStrategy getBotPlayingStartegyForDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
		// TODO Auto-generated method stub
//		System.out.println(botDifficultyLevel);
//		if(botDifficultyLevel.equals("EASY")) {
			return new EasyBotPlayingStrategy();
//		}
////		return new MediumBotPlayingStrategy();
	}

	
}
