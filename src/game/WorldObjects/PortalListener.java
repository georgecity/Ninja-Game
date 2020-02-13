package game.WorldObjects;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.GAMEPANEL.Game;
import game.MainCharacter.NinjaPlayer;
/*
Collision listener that allows player to go to a new level.
 */
public class PortalListener implements CollisionListener {
  private Game game;
  private Portal portal;

  public PortalListener(Game game, Portal portal){
      this.game = game;
      this.portal = portal;

  }

  //override method check for collision between the portal and the player
    @Override
    public void collide(CollisionEvent e) {
       NinjaPlayer player = game.getNinjaPlayer();
       if (e.getOtherBody() == player && game.isCurrentLevelCompleted()){
           System.out.println("Level passed!");
           game.goNextLevel(portal.getPortalTP());
       }
    }
}
