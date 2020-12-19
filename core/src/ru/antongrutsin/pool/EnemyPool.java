package ru.antongrutsin.pool;

import ru.antongrutsin.base.SpritesPool;
import ru.antongrutsin.math.Rect;
import ru.antongrutsin.sprite.Enemy;

public class EnemyPool extends SpritesPool<Enemy> {

    private final BulletPool bulletPool;
    private final Rect worldBounds;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    public Enemy newObject() {
        return new Enemy(bulletPool, worldBounds);
    }
}
