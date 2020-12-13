package ru.antongrutsin.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.antongrutsin.base.Sprite;
import ru.antongrutsin.math.Rect;

public class XWing extends Sprite {
    private Vector2 direction;
    private Vector2 tmp;
    private Vector2 destination;
    private static float V_LEN = 0.005f;

    public XWing(Texture region) {
        super(new TextureRegion(region));
        tmp = new Vector2();
        destination = new Vector2();
        direction = new Vector2();
        setScale(0.12f);
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
    public void resize(Rect worldBounds) { setHeightProportion(worldBounds.getHeight()); }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        destination.set(touch);
        direction.set(destination.cpy().sub(pos)).setLength(V_LEN);
        return false;
    }
}
