package ru.antongrutsin.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.antongrutsin.base.BaseScreen;

public class GameScreen extends BaseScreen {

    protected Texture img;
    protected TextureRegion region;
    protected Texture starShip;
    protected Vector2 position;
    protected Vector2 destination;
    protected Vector2 direction;


    @Override
    public void show() {
        super.show();
        img = new Texture("background.jpg");
        starShip = new Texture("X-W.png");
        position = new Vector2(Gdx.graphics.getWidth()/2f, 35);
        destination = new Vector2(0, 0);
        direction = new Vector2(0f, 0f);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        region = new TextureRegion(img, 800, 300, 500,800);
        batch.draw(region, 0, 0);
        batch.draw(starShip, position.x - 35, position.y - 35, 70, 70);
        batch.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
