package game.Enemies;

import city.cs.engine.*;

import game.MainCharacter.NinjaPlayer;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

/*
This Class generate a Boss enemy that extends minion Class
 */
public class Boss extends Minion implements StepListener, CollisionListener {
    private Timer t;
    private static final BodyImage boss = new BodyImage("data/Sprite/enemySprite/boss/bossSprite.gif", 7.5f);
    private static final BodyImage death = new BodyImage("data/Sprite/enemySprite/boss/bossDeath.gif", 7.5f);
    private static SoundClip dead;
    //load the sound to be played
    static {
        try {
            dead = new SoundClip("data/Soundtrack/SoundFX/enemyDead.WAV");

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public Boss(World world, NinjaPlayer ninjaPlayer) {

        super(world, ninjaPlayer);
        //set the boss health removes minion image and adds Boss sprite
        setHealth(200);
        removeAllImages();
        addImage(boss);
        setGravityScale(2.5f);

        //set the timer for the boss shooting
        t = new Timer(2000, new BossShooting(world, this));
        t.setInitialDelay(0);
        t.start();

    }

    public Timer getT() {
        return t;
    }

    public void setT(Timer t) {
        this.t = t;
    }

   //override destroy method to play sound before destroying body
    @Override
    public void destroy() {
        removeAllImages();
        addImage(death);
        dead.play();
        super.destroy();
    }
}
