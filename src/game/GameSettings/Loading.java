package game.GameSettings;

import game.GAMEPANEL.Game;
import org.jbox2d.common.Vec2;

import java.io.*;

/**
 * Handles Loading and Demonstrates how player data can be read from a text
 * file and printed to the terminal.
 */

public class Loading {
    private String fileName;
    private Game game;

    /**
     * Initialise a new Loading reader
     * @param fileName the name of the Loading file
     */

    public Loading(String fileName, Game game) {
        this.fileName = fileName;
        this.game = game;
    }

    /**
     * Read the player data from the Saving file and print it to
     * the terminal window.
     */

    public void readScores() throws Exceptions {
//        FileReader fr = null;
//        BufferedReader reader = null;

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            if (line == null) {
                throw new Exceptions("no data in the file");
            } else {
                String[] split = line.split(",");
                if (split.length != 6) {
                    throw new Exceptions("line needs 3 items");
                } else {
                    String playerName = split[0];
                    int score = 0;
                    float posX = 0;
                    float posY = 0;
                    String level = split[5];
                    int HP = 0;

                    try {
                        score = Integer.parseInt(split[1]);
                    } catch (NumberFormatException e) {
                        throw new Exceptions("third item needs to be a number");
                    }

                    try {
                        posX = Float.parseFloat(split[2]);
                    } catch (NumberFormatException e) {
                        throw new Exceptions("third item needs to be a number");
                    }

                    try {
                        posY = Float.parseFloat(split[3]);
                    } catch (NumberFormatException e) {
                        throw new Exceptions("third item needs to be a number");
                    }

                    try {
                        HP = Integer.parseInt(split[4]);
                    } catch (NumberFormatException e) {
                        throw new Exceptions("third item needs to be a number");
                    }

                    game.lvlNumber(Integer.parseInt(level));
                    game.getNinjaPlayer().setPosition(new Vec2(posX, posY));
                    game.getNinjaPlayer().setHealth(HP);
                    System.out.println(game.getNinjaPlayer().getHealth());
                }
            }

        }catch (FileNotFoundException e){
            throw new Exceptions("file does not exists");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

//        try {
//            System.out.println("Reading " + fileName + " ...");
//            fr = new FileReader(fileName);
//            reader = new BufferedReader(fr);
//            String line = reader.readLine();
//
//            if (line == null){
//            }
//            while (line != null) {
//                // file is assumed to contain one name, score pair per line
//                String[] tokens = line.split(",");
//                String name = tokens[0];
//                int score = Integer.parseInt(tokens[1]);
//                System.out.println("Name: " + name + ", Score: " + score);
//                line = reader.readLine();
//            }
//            System.out.println("...done.");
//        } finally {
//            if (reader != null) {
//                reader.close();
//            }
//            if (fr != null) {
//                fr.close();
//            }
//        }