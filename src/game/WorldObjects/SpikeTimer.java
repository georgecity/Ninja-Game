package game.WorldObjects;

import city.cs.engine.CollisionListener;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import game.GAMEPANEL.GameLevel;
import game.GAMEPANEL.TileMap;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
/*
world object that appears every certain time, implements Step Listener
 */
public class SpikeTimer implements StepListener{
    private ArrayList<SpikeTrap> spikes;
    private GameLevel gameLevel;
    private float x;
    private float y;
    private int index=0;
    private int index2=0;
    private boolean drawn=false;

    public SpikeTimer(GameLevel gameLevel, float x, float y) {
        spikes=new ArrayList<>();
        this.gameLevel=gameLevel;
        this.x=x;
        this.y=y;
    }

    //override method create and destroy spikes on a loop
    @Override
    public void preStep(StepEvent stepEvent) {
        index++;
        if(index%60==0 && !drawn) {
            for (int k = 0; k < 2; k = k + 1) {
                SpikeTrap spikeTrap = new SpikeTrap(gameLevel, gameLevel.getNinjaPlayer());
                spikeTrap.setPosition(new Vec2(k + x, y));
                spikes.add(spikeTrap);
                spikeTrap.addCollisionListener(spikeTrap);
            }
            drawn=true;
        }
        if(index%180==0&& drawn) {
            for (int k = 0; k < spikes.size(); k = k + 1) {
                spikes.get(k).destroy();
            }
            drawn=false;
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }
}
