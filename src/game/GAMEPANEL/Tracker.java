package game.GAMEPANEL;

import city.cs.engine.*;
import game.MainCharacter.NinjaPlayer;
import org.jbox2d.common.Vec2;

/**
 * Pan the view to follow the game player.
 */
public class Tracker implements StepListener {
    /** The view */
    private UserView view;

    /** The body */
    private NinjaPlayer body;

    public Tracker(UserView view, NinjaPlayer body) {
        this.view = view;
        this.body = body;
    }

    @Override
    public void preStep(StepEvent e) {
    }

    /**
     * set player in the centre of the view
     * @param e
     */
    @Override
    public void postStep(StepEvent e) {
        view.setCentre(new Vec2(body.getPosition()));
    }

    public void setBody(NinjaPlayer body){this.body = body;}
}

//    private TileMap tilemap;
//tileMap.setx((int) (GamePanel.WIDTH / 2 - x));
//        tileMap.sety((int) (GamePanel.HEIGHT / 2 - y));
//    public static float camX= 0;
//    public static float camY= 0;
