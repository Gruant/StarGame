package ru.antongrutsin.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.antongrutsin.base.BaseScreen;
import ru.antongrutsin.math.Rect;
import ru.antongrutsin.sprite.Background;
import ru.antongrutsin.sprite.Star;
import ru.antongrutsin.sprite.XWing;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 256;

    private Texture bg;
    private Background background;

    private Texture img;
    private XWing xWing;

    private TextureAtlas atlas;
    private Star[] stars;

    @Override
    public void show() {
        super.show();
        bg = new Texture("background.jpg");
        img = new Texture("X-W.png");
        atlas = new TextureAtlas("menuAtlas.tpack");
        background = new Background(bg);
        xWing = new XWing(img);
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas);
        }
        xWing = new XWing(img);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    private void update(float delta) {
        xWing.update(delta);
        for (Star star : stars) {
            star.update(delta);
        }
    }

    private void draw() {
        Gdx.gl.glClearColor(0.55f, 0.23f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        xWing.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        img.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        xWing.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        xWing.touchDown(touch, pointer, button);
        return super.touchDown(touch, pointer, button);
    }
}
