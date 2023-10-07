package com.noi.mario.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.noi.mario.MarioGame;
import com.noi.mario.Sprite.Enemies.Enemies;
import com.noi.mario.Sprite.InteractiveTileObject;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        if (fixA.getUserData() == "head" || fixB.getUserData() == "head"){
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject) object.getUserData()).onHeadHit();
            }
        }

        switch (cDef){
            case MarioGame.ENEMY_HEAD_BIT | MarioGame.MARIO_BIT:
                if (fixA.getFilterData().categoryBits == MarioGame.ENEMY_HEAD_BIT)
                    ((Enemies)fixA.getUserData()).hitOnHead();

                else
                    ((Enemies)fixB.getUserData()).hitOnHead();
                break;

            case MarioGame.ENEMY_BIT | MarioGame.OBJECT_BIT:
                if (fixA.getFilterData().categoryBits == MarioGame.ENEMY_BIT)
                    ((Enemies)fixA.getUserData()).reverseVelocity(true, false);

                else
                    ((Enemies)fixB.getUserData()).reverseVelocity(true, false);
                break;

            case MarioGame.MARIO_BIT | MarioGame.ENEMY_BIT:
                Gdx.app.log("MARIO", "DIED");
                break;

            case MarioGame.ENEMY_BIT | MarioGame.ENEMY_BIT:
                ((Enemies)fixA.getUserData()).reverseVelocity(true, false);
                ((Enemies)fixB.getUserData()).reverseVelocity(true, false);
                break;

        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
