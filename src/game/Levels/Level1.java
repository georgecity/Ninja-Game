package game.Levels;

import game.GAMEPANEL.Game;
import game.GAMEPANEL.GameLevel;
import game.GAMEPANEL.TileMap;
import org.jbox2d.common.Vec2;
/*
Handles Level 1 production
 */
public class Level1 extends GameLevel {
    private static final int NUM_KEYS = 3;

    //Populate world
    @Override
    public void populate(Game game) {
        //populate game and load Tilemap
        super.populate(game);
        TileMap tilemap;
        tilemap = new TileMap("data/tileMaps/testMap.txt", 2, this,game);

    }

    //Override methods setting game objectives and player/portal positions
    @Override
    public Vec2 startPosition() {
        return new Vec2(18.25936f,-87.985f);
    }
//(91.985016,-31.985)
//        (18.25936,-87.985)
//        (81.985016,-101.985)
    @Override
    public Vec2 portalPosition() {
        return new Vec2(161.98503f,-15.011809f);
    }

    @Override
    public boolean isCompleted() {
        return getNinjaPlayer().getKeyCount() == NUM_KEYS;
    }
}

//        tilemap.loadTiles("data/tileSet/tilesetLEVEL2.png");
//        tilemap.draw();


//        for (int i = 0; i < 64 ; i++){
//newSpikeTrap(new Vec2(i +13.6f, -50.55f));
//        }
//        for (int i = 0; i < 36 ; i++){
//            newSpikeTrap(new Vec2(i + 75.6f, -96.5f));
//        }
//        for (int i = 0; i < 28 ; i++){
//            newSpikeTrap(new Vec2(i + 119.6f, -96.5f));
//        }
//        LaserTrap arrow= new LaserTrap((GameLevel)this,getNinjaPlayer());
//        arrow.setPosition(new Vec2(5, -78));
//        addStepListener(arrow);

// addStepListener(new SpikeTimer(this,5,-78));
//newEnemy(new Vec2(130, -4));
//        for (int i = 0; i < 3 ; i++){
//            newKey(new Vec2(i*10 + 130, -4));
//        }
//        newCannonBall(new Vec2(65.5f, -91.5f));
//65.99502,-91.995)
//        newKey(new Vec2( 130, -4));