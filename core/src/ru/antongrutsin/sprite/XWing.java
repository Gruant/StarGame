package ru.antongrutsin.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.antongrutsin.base.Sprite;
import ru.antongrutsin.math.Rect;

public class XWing extends Sprite {
    private Vector2 direction;
    private Vector2 tmp;
    private Vector2 destination;
    private Vector2 keyV;
    private static float V_LEN = 0.005f;
    private Rect worldBounds;
    private int keycode;

    public XWing(TextureAtlas atlas) {
        super(splitStarShip(atlas.findRegion("main_ship"),2));
        tmp = new Vector2();
        destination = new Vector2();
        direction = new Vector2();
        setScale(0.12f);
        keyV = new Vector2(0.005f,0);
        this.pos.set(0, -0.4f);
    }


    @Override
    public void update(float delta) {
        tmp.set(destination);
        if(tmp.sub(pos).len() > V_LEN){
            pos.add(direction);
        } else {
            pos.set(destination);
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(worldBounds.getHeight()); }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        destination.set(touch);
        direction.set(destination.cpy().sub(pos)).setLength(V_LEN);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                pos.add(keyV.rotateDeg(180));
                keyV.rotateDeg(180);
                break;
            case Input.Keys.RIGHT:
                pos.add(keyV);
                break;
        }
        return false;
    }

    public static TextureRegion[] splitStarShip (TextureRegion textureRegion, int cols){
        TextureRegion[] regions = new TextureRegion[2];
        int tileWidth = textureRegion.getRegionWidth()/cols;
        int tileHeight = textureRegion.getRegionHeight();
        regions[0] = new TextureRegion(textureRegion, 0, tileHeight, tileWidth, tileHeight);
        regions[1] = new TextureRegion(textureRegion, tileWidth, tileHeight, tileWidth, tileHeight);
        return regions;
    }

}
