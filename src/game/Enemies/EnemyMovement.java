package game.Enemies;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import game.Enemies.NinjaEnemy;
import game.MainCharacter.NinjaPlayer;
import org.jbox2d.common.Vec2;
/*
This Class produce the movement for the base enemy implements StepListener
 */
public class EnemyMovement implements StepListener {
    private NinjaEnemy enemy;
    private NinjaPlayer body;
    public EnemyMovement(NinjaEnemy enemy, NinjaPlayer body){
        this.enemy = enemy;
        this.body = body;
    }

    //check the position of the player to either walk left or right
    @Override
    public void preStep(StepEvent stepEvent) {

        Vec2 playerPos = body.getPosition();
        Vec2 enemyPos = enemy.getPosition();
        if (playerPos.x < enemyPos.x){
            enemy.startWalking(-2);

        }
        if (playerPos.x > enemyPos.x){
            enemy.startWalking(2);
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
