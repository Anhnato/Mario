package com.noi.mario.Sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.noi.mario.MarioGame;
import com.noi.mario.Scene.Hud;
import com.noi.mario.Screens.PlayScreen;

public class Brick extends InteractiveTileObject{
    public Brick(PlayScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MarioGame.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick", "Collision");
        setCategoryFilter(MarioGame.DESTROY_BIT);
        getCell().setTile(null);
        Hud.addScore(200);

        MarioGame.manager.get("audio/sound/breakblock.wav", Sound.class).play();
    }
}
