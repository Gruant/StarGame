package ru.antongrutsin.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.antongrutsin.base.BaseScreen;
import ru.antongrutsin.math.Rect;
import ru.antongrutsin.sprite.Background;
import ru.antongrutsin.sprite.XWing;

public class GameScreen extends BaseScreen {

    private Texture bg;
    private Background background;

//    private Texture img;
//    private XWing xWing;

    @Override
    public void show() {
        super.show();
        bg = new Texture("background.jpg");
        background = new Background(bg);

//        img = new Texture("X-W.png");
//        xWing = new XWing(img);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.55f, 0.23f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
//        xWing.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
//        img.dispose();
        super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
//        xWing.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
//        xWing.move(touch);
        return super.touchDown(touch, pointer, button);
    }




}
