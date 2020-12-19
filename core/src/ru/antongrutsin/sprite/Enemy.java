package ru.antongrutsin.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.antongrutsin.base.Ship;
import ru.antongrutsin.math.Rect;
import ru.antongrutsin.pool.BulletPool;

public class Enemy extends Ship {

    Vector2 speedV;

    public Enemy(BulletPool bulletPool, Rect worldBounds) {
        super(bulletPool);
        this.worldBounds = worldBounds;
        this.v = new Vector2();
        this.v0 = new Vector2();
        this.bulletPos = new Vector2();
        speedV = new Vector2(0,-0.4f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        // в if реализован режим быстрого появления, дальше стандартный режим боя.
        if (getTop() < worldBounds.getTop()){
            v.set(v0);
        }
        bulletPos.set(pos.x, pos.y - getHalfHeight());
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
    }

    public void set(
            TextureRegion[] regions,
            TextureRegion bulletRegion,
            Sound bulletSound,
            float bulletHeight,
            Vector2 bulletV,
            int damage,
            int hp,
            float reloadInterval,
            Vector2 v0,
            float height
    ) {
        this.regions = regions;
        this.bulletRegion = bulletRegion;
        this.bulletSound = bulletSound;
        this.bulletHeight = bulletHeight;
        this.bulletV = bulletV;
        this.damage = damage;
        this.hp = hp;
        this.reloadInterval = reloadInterval;
        this.v0 = v0;
        this.v.set(speedV);
        setHeightProportion(height);
    }
}
