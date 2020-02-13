package game.MainCharacter;

import city.cs.engine.*;
import city.cs.engine.Shape;
/*
Player shooting shuriken production
 */
public class Shuriken extends DynamicBody {
    private static final float radius = 0.5f;
    private static final Shape attack = new CircleShape(radius);
    private static final BodyImage wave = new BodyImage("data/Miscellaneous/shuriken.png", 2*radius);

    public Shuriken(World world){
        super(world,attack);
        addImage(wave);
        setGravityScale(0);
    }

    public  BodyImage waveImage() {
        return wave;
    }
}
