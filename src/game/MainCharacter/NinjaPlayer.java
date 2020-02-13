package game.MainCharacter;

import city.cs.engine.*;
import game.GameControllers.Controller;
import game.GAMEPANEL.TileMap;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Simple Player production
 * and Crouching Fixture setting
 */

public class NinjaPlayer extends Walker implements StepListener {
    private int keyCount;
    private int killCount;
    private UserView view;
    private int damage = 25;
    private int health = 300;
    private Controller controller;
    private static float WALKING_SPEED = 7.5f;
    private static float JUMPING_SPEED = 22.5f;

    /**
     * creates to fixture in the same position for each time the player is crouching or standing
     */

    public void setFix(){ sf1 = new SolidFixture(this, new PolygonShape(-0.523f,0.371f, -0.111f,0.885f, 0.115f,0.885f, 0.399f,0.358f, 0.288f,-0.913f, -0.576f,-0.925f, -0.531f,0.317f));}
    private SolidFixture sf1;
    private SolidFixture sf2;
    public SolidFixture getFix1(){return  sf1;}
    public SolidFixture getFix2(){return  sf2;}

    /**
     * Load Sound of player when killed
     */
    private static SoundClip dead;
    static {
        try{
            dead = new SoundClip("data/Soundtrack/SoundFX/PlayerKilled.wav");
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }

    /**
     * Initialise the player body and set key/kill count
     * @param world draws player in the world
     * @param tilemap make player aware of the tilemap
     */
    public NinjaPlayer(World world, TileMap tilemap) {
        super(world);
        sf1 = new SolidFixture(this, new PolygonShape(-0.523f,0.371f, -0.111f,0.885f, 0.115f,0.885f, 0.399f,0.358f, 0.288f,-0.913f, -0.576f,-0.925f, -0.531f,0.317f));
        sf2 = new SolidFixture(this, new PolygonShape(-0.47f,-0.136f, -0.082f,0.34f, 0.146f,0.344f, 0.402f,-0.128f, 0.258f,-0.94f, -0.518f,-0.944f, -0.478f,-0.176f));
        addImage(new BodyImage("data/Sprite/sprite.png", 2.4f));
        sf1.setFriction(10f);
        sf2.setFriction(10f);
        keyCount = 0;
        killCount = 0;
    }

    /**
     * getter and setter for player values
     * @return player values
     */
    public int getKeyCount() { return keyCount; }
    public int getKillCount() { return killCount; }
    public void incrementKeyCount() { keyCount++; System.out.println("Key Obtained = " + keyCount); }
    public void incrementKillCount() { killCount++; System.out.println("Enemy Killed = " + killCount); }
    public void healthDecrease(int damage){ health = health - damage; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    @Override
    public void preStep(StepEvent stepEvent) {
    }

    /**
     * destroy player if health is less than 0
      * @param stepEvent
     */
    @Override
    public void postStep(StepEvent stepEvent) {
        if (health <=0) {
            destroy();
            getWorld().stop();
        }
    }

    /**
     * override destroy to play dead sound first
      */
    @Override
    public void destroy() {
        dead.play();
        super.destroy();
    }

    /**
     * geters and setter to obtain player values
      * @return walking/jumping speed
     */
    public int getDamage(){ return damage;}
    public float getJumpingSpeed(){return JUMPING_SPEED;}
    public void setJumpingSpeed(float jumpingSpeed) { JUMPING_SPEED = jumpingSpeed; }
    public float getWalkingSpeed(){return WALKING_SPEED;}
}




