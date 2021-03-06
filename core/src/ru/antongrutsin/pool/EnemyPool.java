package ru.antongrutsin.pool;

import ru.antongrutsin.base.SpritesPool;
import ru.antongrutsin.math.Rect;
import ru.antongrutsin.sprite.Enemy;

public class EnemyPool extends SpritesPool<Enemy> {

    private final BulletPool bulletPool;
    private final ExplosionPool explosionPool;
    private final Rect worldBounds;

    public EnemyPool(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.worldBounds = worldBounds;
    }

    @Override
    public Enemy newObject() {
        return new Enemy(bulletPool, explosionPool, worldBounds);
    }
}
