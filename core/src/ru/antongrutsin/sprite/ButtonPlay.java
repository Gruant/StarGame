package ru.antongrutsin.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.antongrutsin.base.BaseButton;
import ru.antongrutsin.math.Rect;
import ru.antongrutsin.screen.GameScreen;

public class ButtonPlay extends BaseButton {

    private static final float HEIGHT = 0.24f;
    private static final float MARGIN = 0.03f;

    private final Game game;

    public ButtonPlay(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btPlay"));
        this.game = game;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + MARGIN);
        setLeft(worldBounds.getLeft() + MARGIN);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen());
    }
}
