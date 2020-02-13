package game.MainCharacter;

import city.cs.engine.*;
import game.Enemies.Boss;
import game.Enemies.Minion;
import game.Enemies.NinjaEnemy;
import game.MainCharacter.NinjaPlayer;
import game.WorldObjects.BallTrap;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/**
Shooting shuriken CollisionListener
 */
public class ShurikenCollison implements CollisionListener {
    /**
     * Set values for shuriken damage
     */

    private int damage;
   private NinjaPlayer body;
   /**
   *Load sounds played when enemy is hurted
    */
   private static SoundClip soundFX;
    static {
        try{
            soundFX = new SoundClip("data/Soundtrack/SoundFX/minionHurt.wav");
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }

    /**
     * initialise Listener
     * @param damage set Damage value to each  enemy
     * @param body set which body is going to affect
     */
   public ShurikenCollison(int damage, NinjaPlayer body){
       this.damage = damage;
       this.body = body;
   }

    /**
     *     Handles shuriken collisions and acts accordingly depending on the collision
     *     and the object in which the shuriken collides
     */
    @Override
    public void collide(CollisionEvent e) {
        /**
         * Some collisions generate an increase in the player Kill Count
         */
        if (e.getOtherBody() instanceof StaticBody){
            e.getReportingBody().destroy();
            System.out.println("shuriken destroyed");
        }

        if (e.getOtherBody() instanceof BallTrap){
            e.getReportingBody().destroy();
            System.out.println("shuriken destroyed");
        }

        if (e.getOtherBody() instanceof NinjaEnemy){
            e.getReportingBody().destroy();
            ((NinjaEnemy)e.getOtherBody()).healthDecrease(body.getDamage());
            System.out.println("enemy got hit");
            if (((NinjaEnemy)e.getOtherBody()).getHealth()<= 0){
                ((NinjaEnemy)e.getOtherBody()).destroy();
                body.incrementKillCount();
            }
        }

        if (e.getOtherBody() instanceof Minion) {
            e.getReportingBody().destroy();
            ((Minion) e.getOtherBody()).healthDecrease(body.getDamage());
            soundFX.play();
            System.out.println("enemy got hit");
            if (((Minion)e.getOtherBody()).getHealth() <= 0){
            ((Minion) e.getOtherBody()).destroy();
            body.incrementKillCount();
            }
        }

        if (e.getOtherBody() instanceof Boss) {
            e.getReportingBody().destroy();
            ((Boss) e.getOtherBody()).healthDecrease(body.getDamage());
            soundFX.play();
            System.out.println("enemy got hit");
            if (((Boss)e.getOtherBody()).getHealth() <= 0){
                ((Boss)e.getOtherBody()).getT().stop();
                ((Boss) e.getOtherBody()).destroy();
                body.incrementKillCount();
            }
        }
    }
}
