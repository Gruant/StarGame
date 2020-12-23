package ru.antongrutsin.pool;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.antongrutsin.base.SpritesPool;
import ru.antongrutsin.sprite.Explosion;

public class ExplosionPool extends SpritesPool<Explosion> {

    private final TextureAtlas atlas;
    private final Sound sound;

    public ExplosionPool(TextureAtlas atlas, Sound sound) {
        this.atlas = atlas;
        this.sound = sound;
    }

    @Override
    public Explosion newObject() {
        return new Explosion(atlas.findRegion("explosion"), 9, 9, 74, sound);
    }
}
