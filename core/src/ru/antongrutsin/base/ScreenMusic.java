package ru.antongrutsin.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class ScreenMusic {
    protected Music music;

    public ScreenMusic(String path, float volume) {
        music = Gdx.audio.newMusic(Gdx.files.internal(path));
        music.setVolume(volume);
        music.setLooping(true);
        music.play();
    }

    public void dispose(){
        music.dispose();
    }

}
