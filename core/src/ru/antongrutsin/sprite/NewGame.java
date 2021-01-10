package ru.antongrutsin.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.antongrutsin.base.BaseButton;
import ru.antongrutsin.math.Rect;
import ru.antongrutsin.screen.GameScreen;

public class NewGame extends BaseButton {

    private GameScreen screen;

    public NewGame(TextureAtlas atlas, GameScreen screen) {
        super(atlas.findRegion("button_new_game"));
        this.screen = screen;
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(-0.04f);
        setHeightProportion(0.06f);
    }

    @Override
    public void action() {
        screen.startNewGame();
    }
}
