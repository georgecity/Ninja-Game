package game.Levels;

import game.GAMEPANEL.Game;
import game.GAMEPANEL.GameLevel;
import game.GAMEPANEL.TileMap;
import org.jbox2d.common.Vec2;
/*
Handles Level 4 production
 */
public class Level4 extends GameLevel {
    private static final int NUM_KEYS = 4;

    @Override
    public void populate(Game game) {
        //populate game and load Tilemap
        super.populate(game);
        TileMap tilemap;
        tilemap = new TileMap("data/tileMaps/tileMap4.txt", 2, this, game);
    }


    //Override methods setting game objectives and player/portal positions
    @Override
    public Vec2 startPosition() {
        return new Vec2 (18.096453f,-103.985f);
    }

    @Override
    public Vec2 portalPosition() {

        return new Vec2(131.98503f,-16.985f);
    }

    @Override
    public boolean isCompleted() {

        return getNinjaPlayer().getKeyCount() == NUM_KEYS;
    }
}
