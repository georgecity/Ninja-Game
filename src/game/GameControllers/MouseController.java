package game.GameControllers;

import city.cs.engine.*;
import game.GAMEPANEL.GameLevel;
import game.MainCharacter.NinjaPlayer;
import game.MainCharacter.Shuriken;
import game.MainCharacter.ShurikenCollison;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
/*
Mouse Handler to control player shooting
 */
public class MouseController extends MouseAdapter {

private World world;
private UserView view;
private NinjaPlayer body;
private Shuriken shuriken;
private Controller controller;
private String  facingTo = "right";
private static final Shape attack = new BoxShape(0.5f, 1);
private static BodyImage shoot = new BodyImage("data/Sprite/shurikenShoot.gif", 2.25f);
private static final BodyImage wave = new BodyImage("data/Miscellaneous/shuriken.png", 1);

public MouseController(UserView view, GameLevel world){
    this.view= view;
    this.body= world.getNinjaPlayer();
    this.world= world;
}

    //Handle mouse press events fo shooting
     @Override
     public void mousePressed(MouseEvent e) {
    super.mousePressed(e);
    if (SwingUtilities.isLeftMouseButton(e)){
//        body.stopWalking();
        shuriken= new Shuriken(view.getWorld());
        Vec2 playerPos= new Vec2(body.getPosition());
        Vec2 distance= new Vec2(view.viewToWorld(e.getPoint()).sub(body.getPosition()));
        Vec2 mousePos= new Vec2(view.viewToWorld(e.getPoint()));
        //add image depending on the side player is facing to
        if (facingTo == "right") {
            body.removeAllImages();
            body.addImage(shoot);
        }
        if (facingTo == "left"){
            body.removeAllImages();
            body.addImage(shoot).flipHorizontal();
        }

        float offSetX= 0;
        float offSetY= 0;
        if (distance.x > 0.1f){
            offSetX = offSetX +0.1f;
        }
        if (distance.x < 0.1f){
            offSetX = offSetX -0.1f;
        }

        //set the shuriken initial position when shooting
        shuriken.setPosition(new Vec2(playerPos.x + offSetX , playerPos.y + offSetY));
        shuriken.setBullet(true);
        //check mouse position when pressed and set linear velocity to that side
        if (playerPos.x < mousePos.x){
            shuriken.setLinearVelocity(new Vec2(25, 0));
            shuriken.removeAllImages();
            shuriken.addImage(shuriken.waveImage());
            facingTo = "right";
        }
        if (playerPos.x > mousePos.x){
            shuriken.setLinearVelocity(new Vec2(-25, 0));
            shuriken.removeAllImages();
            shuriken.addImage(shuriken.waveImage()).flipHorizontal();
            facingTo = "left";
        }
        shuriken.addCollisionListener(new ShurikenCollison(10, body));
        shuriken.addCollisionListener(new ShurikenCollison(100, body));
        shuriken.addCollisionListener(new ShurikenCollison(100, body));
    }
}

//Handle mouse release events fo shooting
@Override
public void mouseReleased(MouseEvent e) {
    super.mouseReleased(e);
    if (facingTo == "right") {
        body.removeAllImages();
        body.addImage(new BodyImage("data/Sprite/sprite.png", 2.4f));
    }
    if (facingTo == "left"){
        body.removeAllImages();
        body.addImage(new BodyImage("data/Sprite/sprite.png", 2.4f)).flipHorizontal();
    }
}

public void setBody( GameLevel world){ body = world.getNinjaPlayer(); }
}


