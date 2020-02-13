package game.Levels;

import game.GAMEPANEL.Game;
import game.GAMEPANEL.GameLevel;
import game.GAMEPANEL.TileMap;
import org.jbox2d.common.Vec2;
/*
Handles Level 2 production
 */
public class Level2 extends GameLevel {
    private static final int NUM_KEYS= 4;

    @Override
    public void populate(Game game) {
        //populate game and load Tilemap
        super.populate(game);
        TileMap tilemap;
        tilemap = new TileMap("data/tileMaps/tileMap2.txt", 2, this,game);
    }

    //Override methods setting game objectives and player/portal positions
    @Override
    public Vec2 startPosition() {

        return new Vec2(18.25936f,-53.985f);
    }

    @Override
    public Vec2 portalPosition() {

        return new Vec2(311.98505f,-20.985f);
    }

    @Override
    public boolean isCompleted() {
        return getNinjaPlayer().getKeyCount() == NUM_KEYS;
    }
}

//        tilemap.loadTiles("data/tileSet/tilesetLEVEL2.png");
//96.05869,-44.985 200.10234,-40.985)
//        for (int i = 0; i < 64 ; i++){
//            newSpikeTrap(new Vec2(i + 95.5f, -46.375f));
//        }
//        for (int i = 0; i < 42 ; i++){
//            newSpikeTrap(new Vec2(i + 254.75f, -46.375f));
//        }
//        for (int i = 0; i < 35 ; i++){
//            newSpikeTrap(new Vec2(i + 198.25f, -42.375f));
//        }
//        newKey(new Vec2( 70, -44));
//        newKey(new Vec2( 100, -44));
//        newEnemy(new Vec2(130, -4));
//
//        newEnemy(new Vec2(60, -40));
//        newEnemy(new Vec2(70, -40));

