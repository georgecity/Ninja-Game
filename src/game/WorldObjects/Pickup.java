package game.WorldObjects;

import city.cs.engine.*;
import game.GAMEPANEL.Game;
import game.MainCharacter.NinjaPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Collision listener that allows the player to collect things.
 */
public class Pickup implements CollisionListener {
    private NinjaPlayer body;
    private Game game;
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

    public Pickup(NinjaPlayer body) {
        this.body = body;
        this.game = game;
    }

    //override method check player collision with the key
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == body) {
            body.incrementKeyCount();
            e.getReportingBody().destroy();
        }
    }

}
