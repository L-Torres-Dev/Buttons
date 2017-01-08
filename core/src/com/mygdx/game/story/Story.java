package com.mygdx.game.story;

import com.mygdx.game.Screens.ScreenManager;
import com.mygdx.game.utils.TextDisplay;

/**
 * Created by louie on 9/11/2016.
 */
public class Story {

    TextDisplay display;
    ScreenManager manager;

    public int progress;
    public int innerProgress;

    Story(TextDisplay display, ScreenManager manager){
        this.manager = manager;
        progress = 1;
        innerProgress = 0;
        this.display = display;
    }

    public void progress(){

    }

    public int getInnerProgress() {
        return innerProgress;
    }

    public ScreenManager getManager() {
        return manager;
    }

    public int getProgress() {
        return progress;
    }
}
