package game.WorldObjects;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
/*
Simple Portal Production
 */
public class Portal extends StaticBody {
    private int portalTP;

    public Portal(World world){
        super(world, new BoxShape(1f, 1.5f));
        addImage(new BodyImage("data/Miscellaneous/Portal.gif", 3.5f));
        this.portalTP = portalTP;
    }

    public int getPortalTP(){return portalTP;}
}
