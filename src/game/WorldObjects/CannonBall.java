package game.WorldObjects;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.GAMEPANEL.GameLevel;
import game.MainCharacter.NinjaPlayer;
import org.jbox2d.common.Vec2;
/*
CannonBall Object production
 */
public class CannonBall extends Walker implements StepListener {
    private NinjaPlayer player;
    private int index=0;
    private GameLevel world;
    private static final BodyImage arrowTrap = new BodyImage("data/Miscellaneous/ArrowTrap.png", 2.5f);
    private static final Shape arrowBarrel =  new BoxShape(1, 1);

    public CannonBall(GameLevel world, NinjaPlayer player) {
        super(world, arrowBarrel );
        addImage(arrowTrap);
        this.player = player;
        this.world = world;
    }

    /*
    shoot a cannon ball to the left direction every certain time, collisionListener implemented through Balltrap Class
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        index++;

        if (index%120==0) {
            BallTrap ballTrap = new BallTrap(getWorld(),player);
            ballTrap.setPosition(new Vec2(getPosition().x -0.5f, getPosition().y));
            ballTrap.setLinearVelocity(new Vec2(-25, 0));
            ballTrap.addCollisionListener(ballTrap);
            System.out.println(getPosition());
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
//world.newBallTrap(new Vec2(getPosition().x -0.5f, getPosition().y));
//        System.out.println(getPosition());


