package game.GameControllers;

import city.cs.engine.*;
import game.MainCharacter.NinjaPlayer;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Key handler to control the player
 */

public class Controller extends KeyAdapter {
    //Sting facingTo used to identify which side the player is facing to
    private String facingTo = "right";
    private boolean crouching = false;
    private NinjaPlayer body;
    private static BodyImage rest = new BodyImage("data/Sprite/sprite.png", 2.4f);
    private static BodyImage run = new BodyImage("data/Sprite/runningSprite.gif", 2.3f);
    private static BodyImage jump = new BodyImage("data/Sprite/jump.gif", 2.2f);
    private static BodyImage crouch = new BodyImage("data/Sprite/crouch.png", 2.25f);


    public Controller(NinjaPlayer body) { this.body = body; }

    /**
     * Handle key press events for walking, crouching and jumping.
     *
     * @param e description of the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE) { // Esc = quit

        } else if (code == KeyEvent.VK_SPACE) { // Space = jump
            Vec2 v = body.getLinearVelocity();
            if (facingTo == "right") {
                body.removeAllImages();
                body.addImage(jump).reset();
            }
            if (facingTo == "left") {
                body.removeAllImages();
                body.addImage(jump).flipHorizontal();
            }
            if (Math.abs(v.y) < 0.01f) {
                body.jump(body.getJumpingSpeed());

            }

        } else if (code == KeyEvent.VK_A) {

            body.startWalking(-body.getWalkingSpeed()); // A = walk left
            body.removeAllImages();
            body.addImage(run).flipHorizontal();
            facingTo = "left";

        } else if (code == KeyEvent.VK_D) {
            body.startWalking(body.getWalkingSpeed()); // D = walk right
            body.removeAllImages();
            body.addImage(run);
            facingTo = "right";

        } else if (code == KeyEvent.VK_S) {
            if (!crouching) {
                body.getFix1().destroy();
                crouching = true;                    // S = crouching
            }
            body.removeAllImages();
            body.addImage(crouch);

            if (facingTo == "right") {
                body.removeAllImages();
                body.addImage(crouch);
            }
            if (facingTo == "left") {
                body.removeAllImages();
                body.addImage(crouch).flipHorizontal();
            }
        }
    }

    /**
     * Handle key release events (stop walking).d
     *
     * @param e description of the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            body.stopWalking();
            body.removeAllImages();
            body.addImage(rest).flipHorizontal();

        } else if (code == KeyEvent.VK_D) {
            body.stopWalking();
            body.removeAllImages();
            body.addImage(rest);

        } else if (code == KeyEvent.VK_S) {
            crouching = false;
            body.stopWalking();
            body.setFix();
            if (facingTo == "right") {
                body.removeAllImages();
                body.addImage(rest);
            }
            if (facingTo == "left") {
                body.removeAllImages();
                body.addImage(rest).flipHorizontal();
            }

        }

    }
    public void setBody(NinjaPlayer body) { this.body = body; }
}
