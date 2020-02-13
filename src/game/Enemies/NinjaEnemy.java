package game.Enemies;

import city.cs.engine.*;
import game.MainCharacter.NinjaPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/*
This Class generate the standard enemy and implements CollisionListener
 */

public class NinjaEnemy extends Walker implements CollisionListener {
    private static final Shape BallShape1 = new BoxShape(1, 1);
    private static final BodyImage trapImage = new BodyImage("data/Miscellaneous/mine.png", 2);
    private NinjaPlayer body;
    private int health= 100;
    private int damage= 50;
    private static SoundClip hitmarker;
    private static SoundClip explosion;
    //load the sound to be played
    static {
        try{
            hitmarker = new SoundClip("data/Soundtrack/SoundFX/playerHurt.wav");
            explosion = new SoundClip("data/Soundtrack/SoundFX/bombDestroyed.wav");
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }


    public NinjaEnemy(World world, NinjaPlayer body){
        super(world,BallShape1);
        addImage(trapImage);
        this.body = body;
        setGravityScale(0);
    }
    //override method for the collision of the shuriken with the enemy
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == body) {
            body.healthDecrease(damage);
            System.out.println("ninja Enemy hit ");
            hitmarker.play();
        }
    }
    // getters and setters for  health nd health decrease
    public int getHealth(){return  health;}
    public void setHealth(int health){this.health =  health;}
    public void healthDecrease(int damage){ health = health - damage; }

    //override destroy method to play sound before destroying body
    @Override
    public void destroy() {
        explosion.play();
        super.destroy();
    }
}
