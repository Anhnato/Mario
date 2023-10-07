package com.noi.mario.Sprite.Enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.noi.mario.Screens.PlayScreen;

public abstract class Enemies extends Sprite {
    protected World world;
    protected PlayScreen screen;
    public Body b2body;

    public Vector2 velocity;

    public Enemies(PlayScreen screen, float x, float y) {
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineEnemies();
        velocity = new Vector2(-1, -2);
        b2body.setActive(false);
    }

    protected abstract void defineEnemies();
    public abstract void update(float dt);
    public abstract void hitOnHead();
    public abstract void hitByEnemy(Enemies enemy);

    public void reverseVelocity(boolean x, boolean y){
        if (x)
            velocity.x = -velocity.x;
        if (y)
            velocity.y = -velocity.y;
    }
}