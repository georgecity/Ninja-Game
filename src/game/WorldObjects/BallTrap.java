package game.WorldObjects;

import city.cs.engine.*;
import game.MainCharacter.NinjaPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/*
Shooting Balltrap object CollisionListener

 */
public class BallTrap extends DynamicBody implements CollisionListener {
    private static final float radius = 1f;
    private static final BodyImage trapImage = new BodyImage("data/Miscellaneous/ball.png", 2*radius);
    private NinjaPlayer player;
    private SolidFixture cannon;
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

    public BallTrap(World world, NinjaPlayer player){
        super(world);
        this.player = player;
        cannon = new SolidFixture(this, new CircleShape(radius));
        addImage(trapImage);
        setGravityScale(0);
        cannon.setDensity(2500);

}

//override method check collision of the ball with player and Static Bodies
@Override
public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof NinjaPlayer) {
            ((NinjaPlayer)e.getOtherBody()).healthDecrease(100);
            hitmarker.play();
            System.out.println("ninja hit ballTrap");
        }
        if (e.getOtherBody() instanceof StaticBody) {
            e.getReportingBody().destroy();
            System.out.println(" ballTrap destroyed");
        }
    }
}
