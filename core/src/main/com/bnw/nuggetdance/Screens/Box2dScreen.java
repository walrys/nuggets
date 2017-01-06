package com.bnw.nuggetdance.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJoint;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.bnw.nuggetdance.Debug.MouseJointAdapter;
import com.bnw.nuggetdance.Nuggets;

/**
 * Created by Walrus on 1/5/2017.
 */

public class Box2dScreen implements Screen{
    private Nuggets game;
    private World world;
    private Box2DDebugRenderer renderer;

    public Box2dScreen(Nuggets game){
        this.game = game;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -9.81f), true);
        renderer = new Box2DDebugRenderer();

        { // setup
            EdgeShape groundShape = new EdgeShape();
            groundShape.set(-50, -6, 50, -6);

            Body ground = world.createBody(new BodyDef());
            ground.createFixture(groundShape, 0);

            groundShape.dispose();

            MouseJointDef mouseJointDef = new MouseJointDef();
            mouseJointDef.bodyA = ground;
            mouseJointDef.maxForce = 50000;
            mouseJointDef.collideConnected = true;

            Gdx.input.setInputProcessor(new MouseJointAdapter(mouseJointDef, true, game.gameCam));
        }

        createRope(3);
    }

    private Body[] createRope(int length) {
        Body[] segments = new Body[length];
        RevoluteJoint[] joints = new RevoluteJoint[length - 1];
        RopeJoint[] ropeJoints = new RopeJoint[length - 1];

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        BodyDef staticBody = new BodyDef();
        staticBody.type = BodyDef.BodyType.StaticBody;

        float width = .2f, height = 2;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        for(int i = 0; i < segments.length; i++) {
            if (i == 0) {
                segments[0] = world.createBody(staticBody);
                //segments[0].createFixture(shape, 2);
            }
            else {
                segments[i] = world.createBody(bodyDef);
                segments[i].createFixture(shape, 2);
            }
        }
        shape.dispose();

        RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.localAnchorA.y = -height / 2;
        jointDef.localAnchorB.y = height / 2;

        for(int i = 0; i < joints.length; i++) {
            jointDef.bodyA = segments[i];
            jointDef.bodyB = segments[i + 1];
            joints[i] = (RevoluteJoint) world.createJoint(jointDef);
        }

        RopeJointDef ropeJointDef = new RopeJointDef();
        ropeJointDef.localAnchorA.set(0, -height / 2);
        ropeJointDef.localAnchorB.set(0, height / 2);
        ropeJointDef.maxLength = height;

        for(int i = 0; i < ropeJoints.length; i++) {
            ropeJointDef.bodyA = segments[i];
            ropeJointDef.bodyB = segments[i + 1];
            ropeJoints[i] = (RopeJoint) world.createJoint(ropeJointDef);
        }

        return segments;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(1 / 60f, 8, 3);
        renderer.render(world, game.gameCam.combined);
    }

    @Override
    public void resize(int width, int height) {
        game.gameCam.viewportWidth = width / 50;
        game.gameCam.viewportHeight = height / 50;
        game.gameCam.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        world.dispose();
        renderer.dispose();
    }
}
