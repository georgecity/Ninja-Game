package game.GAMEPANEL;

import city.cs.engine.World;
import game.Enemies.Boss;
import game.Enemies.EnemyMovement;
import game.Enemies.Minion;
import game.Enemies.NinjaEnemy;
import game.GameControllers.Controller;
import game.MainCharacter.NinjaPlayer;
import game.MainCharacter.Shuriken;
import game.WorldObjects.*;
import org.jbox2d.common.Vec2;
/*
Abstract Class Handles Level management and World Objects production
 */
public abstract class GameLevel extends World {
    private NinjaPlayer player;
    private BallTrap trap;
    private Shuriken shuriken;
    private SpikeTrap spike;
    private SpikeTimer st;
    private LaserTrap laserTrap;
    private NinjaEnemy enemy;
    private Minion minion;
    private Boss boss;
    private TileMap tilemap;
    private MovingPlatform platform;

    public NinjaPlayer getNinjaPlayer() { return player; }
    public NinjaEnemy getEnemy(){return enemy;}
    public Minion getMinion(){return minion;}

    public void populate(Game game){
        //player prodution
        player = new NinjaPlayer(this, tilemap);
        player.setPosition(startPosition());
        player.setGravityScale(3.5f);
        addStepListener(player);

        //portal production
        Portal portal = new Portal(this);
        portal.setPosition(portalPosition());
        portal.addCollisionListener(new PortalListener(game, portal));
    }

    //abstract methods for setting values in Levels Classes
    public abstract Vec2 startPosition();
    public abstract Vec2 portalPosition();
    public abstract boolean isCompleted();

    //method for enemy production through Tilemap Class
    public void newEnemy(Vec2 pos){
        NinjaEnemy enemy = new NinjaEnemy(this, player);
        enemy.setPosition(pos);
        enemy.addCollisionListener(enemy);
        addStepListener(new EnemyMovement(enemy, player));
    }

    //method spike production through Tilemap Class
    public void newSpikeTrap(Vec2 pos){
        SpikeTrap spike = new SpikeTrap(this, player);
        spike.setPosition(pos);
        spike.addCollisionListener(spike);
    }

    //method for spikeTimer production through Tilemap Class
    public void newSpikeTimer(Vec2 pos){
        SpikeTimer st = new SpikeTimer( this, pos.x, pos.y);
        addStepListener(st);
    }

    //method for laserTrap production through Tilemap Class
    public void newLaserTrap(Vec2 pos){
        LaserTrap laser = new LaserTrap(this, player);
        laser.setPosition(pos);
        addStepListener(laser);
    }

    //method for key production through Tilemap Class
    public void newKey(Vec2 pos) {
        Key key = new Key(this);
        key.setPosition(pos);
        key.addCollisionListener(new Pickup(getNinjaPlayer()));
    }

    //method for cannonBall production through Tilemap Class
    public void newCannonBall(Vec2 pos) {
        CannonBall cannonBall = new CannonBall(this, player);
        addStepListener(cannonBall);
        cannonBall.setPosition(pos);
    }

    //method for minion production through Tilemap Class
    public void newMinion(Vec2 pos) {
        Minion minion = new Minion( this, player);
        minion.setPosition(pos);
        addStepListener(minion);
        minion.addCollisionListener(minion);
    }

    //method for boss production through Tilemap Class
    public void newBoss(Vec2 pos) {
        Boss boss = new Boss(this, player);
        boss.setPosition(pos);
        addStepListener(boss);
        boss.addCollisionListener(boss);
    }

    //method for movingPlatform production through Tilemap Class
    public void newPlatform(Vec2 pos){
        MovingPlatform platform = new MovingPlatform(this, pos);
        platform.setPosition(pos);
        addStepListener(platform);
    }
}
