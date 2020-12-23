package ru.antongrutsin.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import ru.antongrutsin.base.BaseScreen;
import ru.antongrutsin.base.ScreenMusic;
import ru.antongrutsin.math.Rect;
import ru.antongrutsin.pool.BulletPool;
import ru.antongrutsin.pool.EnemyPool;
import ru.antongrutsin.pool.ExplosionPool;
import ru.antongrutsin.sprite.Background;
import ru.antongrutsin.sprite.Bullet;
import ru.antongrutsin.sprite.Enemy;
import ru.antongrutsin.sprite.GameOver;
import ru.antongrutsin.sprite.Star;
import ru.antongrutsin.sprite.XWing;
import ru.antongrutsin.utils.EnemyEmitter;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 256;

    private enum State {PLAYING, GAME_OVER}

    private Texture bg;
    private Background background;

    private TextureAtlas atlas;

    private BulletPool bulletPool;
    private EnemyPool enemyPool;
    private ExplosionPool explosionPool;

    private EnemyEmitter enemyEmitter;

    private Star[] stars;
    private ScreenMusic music;
    private XWing xWing;

    private Sound bulletSound;
    private Sound explosionSound;

    private State state;

    private GameOver gameOver;

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
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.mp3"));
        explosionPool = new ExplosionPool(atlas, explosionSound);
        enemyPool = new EnemyPool(bulletPool, explosionPool, worldBounds);
        xWing = new XWing(atlas, bulletPool, explosionPool);
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/shooting.mp3"));
        enemyEmitter = new EnemyEmitter(atlas, worldBounds, bulletSound, enemyPool);
        gameOver = new GameOver(atlas);
        music = new ScreenMusic("sounds/gamescreen.mp3", 0.1f);
        state = State.PLAYING;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollision();
        freeAllDestroyed();
        draw();
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        explosionPool.dispose();
        explosionSound.dispose();
        enemyPool.dispose();
        xWing.dispose();
        music.dispose();
        super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        xWing.resize(worldBounds);
        gameOver.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (state == State.PLAYING) {
            xWing.touchDown(touch, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (state == State.PLAYING) {
            xWing.touchUp(touch, pointer, button);
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state == State.PLAYING) {
            xWing.keyDown(keycode);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (state == State.PLAYING) {
            xWing.keyUp(keycode);
        }
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        explosionPool.updateActiveObjects(delta);
        if (state == State.PLAYING) {
            bulletPool.updateActiveObjects(delta);
            enemyPool.updateActiveObjects(delta);
            xWing.update(delta);
            enemyEmitter.generate(delta);
        }
    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyedActiveObjects();
        enemyPool.freeAllDestroyedActiveObjects();
        explosionPool.freeAllDestroyedActiveObjects();
    }

    private void draw() {
        Gdx.gl.glClearColor(0.55f, 0.23f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        if (state == State.PLAYING) {
            bulletPool.drawActiveObjects(batch);
            enemyPool.drawActiveObjects(batch);
            xWing.draw(batch);
        } else if (state == State.GAME_OVER) {
            gameOver.draw(batch);
        }
        explosionPool.drawActiveObjects(batch);
        batch.end();
    }

    private void checkCollision() {
        if (state == State.GAME_OVER) {
            return;
        }
        List<Enemy> enemyList = enemyPool.getActiveObjects();
        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (Enemy enemy : enemyList) {
            float minDist = enemy.getHalfWidth() + xWing.getHalfWidth();
            if (xWing.pos.dst(enemy.pos) < minDist) {
                enemy.destroy();
                xWing.damage(enemy.getDamage());
            }
            for (Bullet bullet : bulletList) {
                if (bullet.getOwner() == xWing && enemy.isBulletCollision(bullet)) {
                    enemy.damage(bullet.getDamage());
                    bullet.destroy();
                }
            }
        }
        for (Bullet bullet : bulletList) {
            if (bullet.getOwner() != xWing && xWing.isBulletCollision(bullet)) {
                xWing.damage(bullet.getDamage());
                bullet.destroy();
            }
        }
        if (xWing.isDestroyed()) {
            state = State.GAME_OVER;
        }
    }
}
