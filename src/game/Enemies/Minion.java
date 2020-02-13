package game.Enemies;

import city.cs.engine.*;
import game.MainCharacter.NinjaPlayer;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/*
This class produces the Minion FSM that is also called to produce the boss
 */
public class Minion extends DynamicBody implements StepListener, CollisionListener {
    private static final BodyImage sprite = new BodyImage("data/Sprite/enemySprite/boss/bossSprite.gif", 7f);
    private static final BodyImage run = new BodyImage("data/Sprite/enemySprite/boss/bossRun.gif", 7f);
    private static final BodyImage death = new BodyImage("data/Sprite/enemySprite/boss/bossDeath.gif", 7f);
    private SolidFixture minion;
    private static SoundClip dead;
    //load the sound to be played
    static {
        try{
            dead = new SoundClip("data/Soundtrack/SoundFX/enemyDead.WAV");

        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }

    //field for each state of the FSM body
    public enum State {ROLL_LEFT, ROLL_RIGHT, STAND_STILL}
    //Distance needed to change state
    public static final float RANGE = 5;
    private World world;
    private State state;
    private NinjaPlayer ninjaPlayer;
    //health and damage set
    private int health= 100;
    private int damage= 25;

    public Minion(World world, NinjaPlayer ninjaPlayer) {
        super(world);
        this.ninjaPlayer = ninjaPlayer;
        //rest state implemented
        state = State.STAND_STILL;
        getWorld().addStepListener(this);
        minion = new SolidFixture(this, new BoxShape(1.5f, 2.25f));
        addImage(sprite);
        minion.setDensity(150);
        setGravityScale(2.5f);
    }

    //check if body is near range in minion right side
    public boolean inRangeLeft() {
        float gap = getPosition().x - ninjaPlayer.getPosition().x;
        return gap < RANGE && gap > 0;
    }

    //check if body is near range in minion left side
    public boolean inRangeRight() {
        float gap = getPosition().x - ninjaPlayer.getPosition().x;
        return gap > -RANGE && gap < 0;
    }

    // update minion state
    public void preStep(StepEvent e) {
        if (inRangeRight()) {
            if (state != State.ROLL_RIGHT) {
                state = State.ROLL_RIGHT;
                setLinearVelocity(new Vec2(5, 0));
                removeAllImages();
                addImage(run);
            }

        } else if (inRangeLeft()) {
            if (state != State.ROLL_LEFT) {
                state = State.ROLL_LEFT;
                setLinearVelocity(new Vec2(-5, 0));
                removeAllImages();
                addImage(run).flipHorizontal();
            }

        } else {
            if (state != State.STAND_STILL) {
                state = State.STAND_STILL;
                setLinearVelocity(new Vec2(0, 0));
                removeAllImages();
                addImage(sprite);
            }
        }
    }

    //override method handles minion and Boss Collisions
    @Override
    public void addCollisionListener(CollisionListener listener) {
        super.addCollisionListener(listener);
        this.ninjaPlayer = ninjaPlayer;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == ninjaPlayer) {
            ninjaPlayer.healthDecrease(damage);
            System.out.println("ninja Enemy hit ");
        }
    }

    //override destroy method to play sound before destroying body
    @Override
    public void destroy() {
        removeAllImages();
        addImage(death);
        dead.play();
        super.destroy();
    }

    public void postStep(StepEvent e) {
    }

    // getters and setters for health, state and health decrease
    public int getHealth(){return  health;}
    public void setHealth(int health){this.health =  health;}
    public State getState() { return state; }
    public void healthDecrease(int damage){ health = health - damage; }

}
