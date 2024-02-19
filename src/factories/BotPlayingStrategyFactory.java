package factories;

import Strategy.BotPlayingStrategy;
import Strategy.EasyBotPlayingStrategy;
import models.DifficultyLevel;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategy(DifficultyLevel difficultylevel) {
        return  new EasyBotPlayingStrategy();
    }
}
