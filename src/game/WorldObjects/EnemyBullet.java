package game.WorldObjects;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import game.MainCharacter.NinjaPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/*
Collision listener that handles Boss bullets collisions
 */
public class EnemyBullet implements CollisionListener {
    private int damage;
    private static SoundClip hitmarker;
    //load the sound to be played
    static {
        try{
            hitmarker = new SoundClip("data/Soundtrack/SoundFX/playerHurt.wav");
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }

    public EnemyBullet(int damage){
        this.damage = damage;

    }

    //override method check collisions with the world and player
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof StaticBody){
            e.getReportingBody().destroy();
            System.out.println("shuriken destroyed");
        }
        if (e.getOtherBody() instanceof NinjaPlayer){
            e.getReportingBody().destroy();
            ((NinjaPlayer)e.getOtherBody()).healthDecrease(damage);
            hitmarker.play();
            System.out.println("enemy got hit");

        }
    }
}
