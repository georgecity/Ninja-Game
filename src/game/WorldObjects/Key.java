package game.WorldObjects;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.Color;

/**
 *Simple Key production
 * @author george.L
 */

/**
 * a Key.
 */
public class Key extends DynamicBody {
    private static final Shape keyIcon = new BoxShape(0.5f,0.5f);
    
    public Key(World world) {
        super(world, keyIcon);
        setFillColor(Color.orange);
        addImage(new BodyImage("data/Miscellaneous/Key.png", 1.05f));
        setGravityScale(500);
    }
}
