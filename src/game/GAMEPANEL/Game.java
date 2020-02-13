package game.GAMEPANEL;

import city.cs.engine.*;
import game.*;
import game.Enemies.NinjaEnemy;
import game.GameControllers.Controller;
import game.GameControllers.MouseController;
import game.Levels.Level1;
import game.Levels.Level2;
import game.Levels.Level3;
import game.Levels.Level4;
import game.MainCharacter.NinjaPlayer;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;



import java.awt.*;
import java.io.IOException;

/**
 * The computer playable game.
 */
public class Game {
    /** The World in which the bodies move and interact. */
    private GameLevel world;
    /** A graphical display of the world (a specialised JPanel). */
    private PlayerView view;
    private SoundClip soundTrack;
    private int level;
    private int levelNumber;
    private int keyCount;
    private int killCount;
    private Controller controller;
    private MouseController mouseController;
    private Tracker tracker;

    /** Initialise a new Game. */
    public Game() {
        keyCount = 0;
        killCount = 0;

        /**Make the first level*/
        level = 1;
        world = new Level1();
        world.populate(this);
        playSoundtrack("data/Soundtrack/SoundtrackLevel1.wav");

        /**Make a view of the world*/
        view = new PlayerView(world, world.getNinjaPlayer(), 550, 500);
        view.setBody(getNinjaPlayer());

        // uncomment this to draw a 1-metre grid over the view
        //view.setGridResolution(1);

        /** display the view in a frame and display Control Panel*/
         final JFrame frame = new JFrame("Ninja game");
         view.setBackground(Color.black);
         ControlPanel buttons = new ControlPanel(this);
         frame.add(buttons.getMainPanel(), BorderLayout.NORTH);

        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // display the world in the window
        frame.add(view);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);
        // get keyboard focus
        frame.requestFocus();
        // give keyboard focus to the frame whenever the mouse enters the view
        view.addMouseListener(new GiveFocus(frame));

        //levels settings implementation
        controller = new Controller(world.getNinjaPlayer());
        frame.addKeyListener(controller);
        tracker = new Tracker(view,getNinjaPlayer());
        mouseController = new MouseController(view, world);
        view.addMouseListener(mouseController);

        // make the view track the bird
        world.addStepListener(tracker);

        // uncomment this to make a debugging view
//        JFrame debugView = new DebugViewer(world, 1080, 750);
        /**
         * Start the first Level.
         */
        world.start();
    }

    //CurrentLevel Completed and world/player getter
    public NinjaPlayer getNinjaPlayer(){return world.getNinjaPlayer();}
    public GameLevel getWorld(){return world;}
    public boolean isCurrentLevelCompleted(){return world.isCompleted();}

    /**
     * Handles going to next Level when player interacts with portal.
     * @param level Level number
     */
    public void goNextLevel(int level){
        world.stop();
        this.level = level;

        if (level == 1){
            level++;
            world = new Level2();
            world.populate(this);
            controller.setBody(world.getNinjaPlayer());
            mouseController.setBody(world);
            view.setWorld(world);
            view.setParallax("data/Backgrounds/spriteworksCastleDark.gif");
            view.setBody(getNinjaPlayer());
            tracker.setBody(getNinjaPlayer());
            world.addStepListener(tracker);
            playSoundtrack("data/Soundtrack/SoundtrackLevel2.wav");
            world.start();

        } else if (level == 2){
            level++;
            world = new Level3();
            world.populate(this);
            controller.setBody(world.getNinjaPlayer());
            mouseController.setBody(world);
            view.setWorld(world);
            view.setParallax("data/Backgrounds/sea.jpg");
            view.setBody(getNinjaPlayer());
            tracker.setBody(getNinjaPlayer());
            world.addStepListener(tracker);
            playSoundtrack("data/Soundtrack/SoundtrackLevel3.wav");
            world.start();

        }else if (level == 3){
            level++;
            world = new Level4();
            world.populate(this);
            getNinjaPlayer().setGravityScale(2);
            controller.setBody(world.getNinjaPlayer());
            mouseController.setBody(world);
            view.setWorld(world);
            view.setParallax("data/Backgrounds/space.gif");
            view.setBody(getNinjaPlayer());
            tracker.setBody(getNinjaPlayer());
            world.addStepListener(tracker);
            playSoundtrack("data/Soundtrack/SoundtrackLevel4.wav");
            world.start();

        } else if (level==5) {
            System.exit(0);
            System.out.println("THE END.");
            world.stop();
        }
    }

    /**
     * Handles each Level when selected from the Game panel Button( LvlSelector)
     * @param level Level number
     */
    public void lvlNumber(int level){
        this.level =level;
        getNinjaPlayer().setHealth(150);
        if (level == 1){
            levelNumber = 1;
            world = new Level1();
            world.populate(this);
            controller.setBody(world.getNinjaPlayer());
            mouseController.setBody(world);
            view.setWorld(world);
            view.setParallax("data/Backgrounds/spriteworksCastleDark.gif");
            view.setBody(getNinjaPlayer());
            tracker.setBody(getNinjaPlayer());
            world.addStepListener(tracker);
            playSoundtrack("data/Soundtrack/SoundtrackLevel1.wav");
            world.start();
//                    JFrame debugView = new DebugViewer(world, 1080, 750);

        } else if (level == 2){
            levelNumber = 2;
            world = new Level2();
            world.populate(this);
            controller.setBody(world.getNinjaPlayer());
            mouseController.setBody(world);
            view.setWorld(world);
            view.setParallax("data/Backgrounds/Level2.gif");
            view.setBody(getNinjaPlayer());
            tracker.setBody(getNinjaPlayer());
            world.addStepListener(tracker);
            playSoundtrack("data/Soundtrack/SoundtrackLevel2.wav");
            world.start();
//            JFrame debugView = new DebugViewer(world, 1080, 750);


        }else if (level == 3){
            levelNumber = 3;
            world = new Level3();
            world.populate(this);
            controller.setBody(world.getNinjaPlayer());
            mouseController.setBody(world);
            view.setWorld(world);
            view.setParallax("data/Backgrounds/sea.jpg");
            view.setBody(getNinjaPlayer());
            tracker.setBody(getNinjaPlayer());
            world.addStepListener(tracker);
            playSoundtrack("data/Soundtrack/SoundtrackLevel3.wav");
            world.start();
//                    JFrame debugView = new DebugViewer(world, 1080, 750);

        } else if (level==4) {
            levelNumber = 4;
            world = new Level4();
            world.populate(this);
            getNinjaPlayer().setGravityScale(2);
//            getNinjaPlayer().setJumpingSpeed(9.5f);
            controller.setBody(world.getNinjaPlayer());
            mouseController.setBody(world);
            view.setWorld(world);
            view.setParallax("data/Backgrounds/space.gif");
            view.setBody(getNinjaPlayer());
            tracker.setBody(getNinjaPlayer());
            world.addStepListener(tracker);
            playSoundtrack("data/Soundtrack/SoundtrackLevel4.wav");
            world.start();
//                    JFrame debugView = new DebugViewer(world, 1080, 750);

        }

    }

    /**
     * Play the Soundtrack of each level
     * @param music specify which sound file to play
     */
    public void playSoundtrack(String music){
        if (soundTrack != null){
            soundTrack.stop();
        }
        try{
            soundTrack = new SoundClip(music);
            soundTrack.play();
            soundTrack.loop();
        }
        catch(UnsupportedAudioFileException| IOException| LineUnavailableException e){
            e.printStackTrace();
            System.out.println("No Soundtrack available");

        }
    }

    /** Run the game. */
    public static void main(String[] args) { new Game(); }
    public Controller getController() { return controller; }
    public int getKeyCount() { return keyCount; }
    public int getLevel() {
        return level;
    }

}
