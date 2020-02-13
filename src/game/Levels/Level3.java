package game.Levels;

import game.GAMEPANEL.Game;
import game.GAMEPANEL.GameLevel;
import game.GAMEPANEL.TileMap;
import org.jbox2d.common.Vec2;
/*
Handles Level 3 production
 */
public class Level3 extends GameLevel {
    private static final int NUM_KEYS = 4;

    @Override
    public void populate(Game game) {
        //populate game and load Tilemap
        super.populate(game);
        TileMap tilemap;
        tilemap = new TileMap("data/tileMaps/tileMap3.txt", 2, this, game);
    }

    //Override methods setting game objectives and player/portal positions
    @Override
    public Vec2 startPosition() {

        return new Vec2(18.177906f,-13.985001f);
    }

    @Override
    public Vec2 portalPosition()
    {
        return new Vec2(72.58521f,-16.985f);
    }

    @Override
    public boolean isCompleted() {

        return getNinjaPlayer().getKeyCount() == NUM_KEYS;
    }
}
