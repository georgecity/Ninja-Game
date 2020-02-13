package game.WorldObjects;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.Enemies.NinjaEnemy;
import game.MainCharacter.NinjaPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/*
Simple laser shooting object
 */
public class Laser extends DynamicBody implements CollisionListener{
    private static final float radius = 0.5f;
    private static final Shape arrow = new CircleShape(radius);
    private static final BodyImage laser = new BodyImage("data/Miscellaneous/laserProjectile.png", 1.75f*radius);
    private NinjaPlayer body;
    private static SoundClip hitmarker;
    static {
        //load the sound to be played
        try{
            hitmarker = new SoundClip("data/Soundtrack/SoundFX/playerHurt.wav");
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }

    public Laser(World world){
        super(world,arrow);
        addImage(laser);
        setGravityScale(0);
        setBullet(true);

    }

    //override method check collision of the laser with the world
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof StaticBody){
            e.getReportingBody().destroy();
            System.out.println("shuriken destroyed");
        }
        if (e.getOtherBody() instanceof NinjaPlayer) {
            e.getReportingBody().destroy();
            ((NinjaPlayer) e.getOtherBody()).healthDecrease(10);
            hitmarker.play();
            System.out.println("enemy got hit");
        }

    }
}
