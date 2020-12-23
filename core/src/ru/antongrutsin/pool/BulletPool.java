package ru.antongrutsin.pool;

import ru.antongrutsin.base.SpritesPool;
import ru.antongrutsin.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    public Bullet newObject() {
        return new Bullet();
    }
}
