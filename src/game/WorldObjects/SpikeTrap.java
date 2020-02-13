package game.WorldObjects;

import city.cs.engine.*;
import game.MainCharacter.NinjaPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class SpikeTrap extends StaticBody implements CollisionListener {
    private static final Shape spike = new PolygonShape(
            -0.562f, -0.437f, -0.1f, 0.402f, 0.367f, -0.464f, -0.544f, -0.469f);
    private static final BodyImage spikeImage =
            new BodyImage("data/Miscellaneous/spike.png", 2.5f);
    private NinjaPlayer body;
    private static SoundClip hitmarker;
    static {
        try{
            hitmarker = new SoundClip("data/Soundtrack/SoundFX/playerHurt.wav");
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }

    public SpikeTrap(World world, NinjaPlayer body) {
        super(world, spike);
        addImage(spikeImage);
        this.body = body;

    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == body) {
            body.healthDecrease(500);
            hitmarker.play();
            System.out.println("ninja hit spike");
        }

    }
}
