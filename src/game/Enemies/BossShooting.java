package game.Enemies;

import city.cs.engine.*;
import game.GAMEPANEL.GameLevel;
import game.MainCharacter.Shuriken;
import game.WorldObjects.EnemyBullet;
import org.jbox2d.common.Vec2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
this class produces the Bullets for the boss and implements Action listener
to shoot in the direction of the player
 */
public class BossShooting implements ActionListener{
private Shuriken shuriken;
private World world;
private DynamicBody body;
private CollisionListener collisionListener;
    private static final BodyImage wave = new BodyImage("data/Miscellaneous/shuriken.png", 1);

public BossShooting(World world, DynamicBody body){
    this.world = world;
    this.body = body;
    //collisionListener from EnemyBullet Class
    collisionListener = (new EnemyBullet(50));


}
//this override method also add the collisionListener and set the linear velocity of the shuriken along the action listener
    @Override
    public void actionPerformed(ActionEvent e) {

    if (((Boss)body).getState() == Boss.State.ROLL_LEFT){
        shuriken = new Shuriken(world);
        shuriken.addCollisionListener(collisionListener);
        shuriken.setPosition(new Vec2(body.getPosition().x - 1, body.getPosition().y -1));
        body.setLinearVelocity(new Vec2(-5,  0));
        shuriken.removeAllImages();
        shuriken.addImage(wave).flipHorizontal();

    }
    if (((Boss)body).getState() == Boss.State.ROLL_RIGHT){
        shuriken = new Shuriken(world);
        shuriken.setPosition(new Vec2(body.getPosition().x + 1, body.getPosition().y - 1));
        shuriken.addCollisionListener(collisionListener);
        body.setLinearVelocity(new Vec2(5,  0));
        shuriken.removeAllImages();
        shuriken.addImage(wave);
        }

    }
}






