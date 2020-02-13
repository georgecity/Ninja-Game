package game.GameSettings;

import game.GAMEPANEL.Game;
import game.MainCharacter.NinjaPlayer;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Demonstrates how player data can be written to a text file.
 */
public class Saving {

    private String fileName;
    private Game game;

    public Saving(String fileName, Game game) {
        this.fileName = fileName;
        this.game = game;
    }

    public void writeHighScore(String name) throws IOException {
        boolean append = true;
        FileWriter writer = null;
        NinjaPlayer player = game.getNinjaPlayer();

        try {
            writer = new FileWriter(fileName);
            writer.write(name + "," + player.getKeyCount()+ "," + player.getPosition().x + "," + player.getPosition().y + "," + player.getHealth() + "," + game.getLevel() +  "\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}