package ru.antongrutsin.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.antongrutsin.base.BaseScreen;
import ru.antongrutsin.base.ScreenMusic;
import ru.antongrutsin.math.Rect;
import ru.antongrutsin.pool.BulletPool;
import ru.antongrutsin.pool.EnemyPool;
import ru.antongrutsin.sprite.Background;
import ru.antongrutsin.sprite.Star;
import ru.antongrutsin.sprite.XWing;
import ru.antongrutsin.utils.EnemyEmitter;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 256;

    private Texture bg;
    private Background background;

    private TextureAtlas atlas;

    private BulletPool bulletPool;
    private EnemyPool enemyPool;

    private EnemyEmitter enemyEmitter;

    private Star[] stars;
    private ScreenMusic music;
    private XWing xWing;

    private Sound bulletSound;

    @Override
    public void show() {
        super.show();
        bg = new Texture("background.jpg");
        atlas = new TextureAtlas("mainAtlas.tpack");
        background = new Background(bg);
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        enemyPool = new EnemyPool(bulletPool, worldBounds);
        xWing = new XWing(atlas, bulletPool);
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/shooting.mp3"));
        enemyEmitter = new EnemyEmitter(atlas, worldBounds, bulletSound, enemyPool);
        music = new ScreenMusic("sounds/gamescreen.mp3", 0.1f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        freeAllDestroyed();
        draw();
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        enemyPool.dispose();
        xWing.dispose();
        music.dispose();
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
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        xWing.touchUp(touch, pointer, button);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        xWing.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        xWing.keyUp(keycode);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        bulletPool.updateActiveObjects(delta);
        enemyPool.updateActiveObjects(delta);
        enemyEmitter.generate(delta);
        xWing.update(delta);
    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyedActiveObjects();
        enemyPool.freeAllDestroyedActiveObjects();
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
        bulletPool.drawActiveObjects(batch);
        enemyPool.drawActiveObjects(batch);
        batch.end();
    }
}
