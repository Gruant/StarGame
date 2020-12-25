package ru.antongrutsin.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.antongrutsin.base.BaseButton;
import ru.antongrutsin.math.Rect;
import ru.antongrutsin.screen.GameScreen;

public class NewGame extends BaseButton {
    private GameScreen gameScreen;

    public NewGame(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(0.08f);
    }

    @Override
    public void action() {
        gameScreen.newGame();
    }
}

