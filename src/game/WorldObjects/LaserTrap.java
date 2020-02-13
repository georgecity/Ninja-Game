package game.WorldObjects;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.GAMEPANEL.GameLevel;
import game.MainCharacter.NinjaPlayer;

import org.jbox2d.common.Vec2;
/*
Laser turret production, extends walker and implements StepListener
 */
public class LaserTrap extends Walker implements StepListener {
    private NinjaPlayer player;
    private int index=0;
    private static final BodyImage arrowTrap = new BodyImage("data/Miscellaneous/laserTrap.png", 1);
    private static final Shape arrowBarrel = new BoxShape(1, 0.5f);

    public LaserTrap(GameLevel world, NinjaPlayer player) {
        super(world, arrowBarrel );
        addImage(arrowTrap);
        this.player = player;
        setGravityScale(500);
    }

    /*
    override method check player position and shoots accordingly
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        index++;
        Vec2 direction= new Vec2(getPosition().sub(player.getPosition()));
        float x = 0;
        float y = 0;

        if (index%120==0) {
            Laser laser = new Laser(getWorld());
            laser.applyForce(direction.mul(-100));
            if (direction.y > 0.5f) {
                y =-0.5f;
            } else if (direction.y < 0.5f) {
                y = 0.5f;
            } else if (direction.x > 0.5f) {
                y = -0.5f;
            } else if (direction.x < 0.5f) {
                y = 0.5f;
            }

            //CollisionListener from Laser class implemented
            laser.addCollisionListener(laser);
            laser.setPosition(new Vec2(getPosition().x + x, getPosition().y + y));
            
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}


