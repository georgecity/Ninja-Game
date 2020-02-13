package game.WorldObjects;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
/*
moving platform World object production, implements Step Listener
 */
public class MovingPlatform extends StaticBody implements StepListener {
    private static final Shape hitBox = new BoxShape(1, 1);
    private static final BodyImage mplatform = new BodyImage("data/Miscellaneous/movingPlatform.png", 1f);
    private Vec2 orininalPos ;
//    private World world;
    private float speed = 0.1f;

    public MovingPlatform(World world, Vec2 orininalPos){
        super(world);
        this.orininalPos = orininalPos;
        Fixture platform = new SolidFixture(this, new BoxShape(2.5f, 0.5f));
        addImage(mplatform);

    }

    /*
    override method set the distance of travel in the y position (up /down).
     */
    @Override
    public void preStep(StepEvent stepEvent){
        setPosition(new Vec2(getPosition().x, getPosition().y + speed));
        if( getPosition().y> (orininalPos.y + 22)) {
            speed = -speed;

        }else if( getPosition().y< (orininalPos.y - 22)) {
            speed = - speed;
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
