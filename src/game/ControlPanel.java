package game;

import game.GAMEPANEL.Game;
import game.GameSettings.Exceptions;
import game.GameSettings.Loading;
import game.GameSettings.Saving;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControlPanel {
    /**
     * Handles Panel ActionListener
     */
    private JButton pauseButton;
    private JButton startButton;
    private JPanel mainPanel;
    private JButton exitButton;
    private JComboBox LvlSelector;
    private JComboBox save_load;
    private JComboBox GameSlot;
    private Game game;


    /**
     * Panel Buttom production
     * @param game produce the panel on game
     */
    public ControlPanel(Game game){
        mainPanel.setBackground(new Color(0, 0,0,0));
        mainPanel.setOpaque(true);

        /**
         * Action Listener for exit button,
         * exit game when pressed
         */
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /**
         * Action Listener for start button,
         * start game when paused
         */
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { game.getWorld().stop();
            }
        });

        /**
         * Action Listener for pause button,
         * pause the game when paused
         */
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getWorld().start();
            }
        });

        /**
         * Action Listener for LevelSelector button,
         * change to a level selected in the dropdown menu.
         */
        LvlSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (LvlSelector.getSelectedIndex()==0){
                    game.lvlNumber(1);
                }
                if (LvlSelector.getSelectedIndex()==1){
                    game.lvlNumber(2);
                }
                if (LvlSelector.getSelectedIndex()==2){
                    game.lvlNumber(3);
                }
                if (LvlSelector.getSelectedIndex()==3){
                    game.lvlNumber(4);
                }

            }
        });

        /**
         * Action Listener for save/load button,
         * save/load on any game slot selected
         */
        save_load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (save_load.getSelectedIndex()==0){
                    try {
                        if (GameSlot.getSelectedIndex()==0){
                            Saving save = new Saving("data/SavedGames/save.txt",game);
                            save.writeHighScore("George");
                        }else if (GameSlot.getSelectedIndex()==1){
                            Saving save = new Saving("data/SavedGames/save1.txt",game);
                            save.writeHighScore("George");
                        }else if (GameSlot.getSelectedIndex()==2){
                            Saving save = new Saving("data/SavedGames/save2.txt",game);
                            save.writeHighScore("George");
                        }
                    }catch (IOException a){
                        a.printStackTrace();
                    }

                }else if (save_load.getSelectedIndex()==1){
                    try {
                        if (GameSlot.getSelectedIndex()==0) {
                            Loading load = new Loading("data/SavedGames/save.txt", game);
                            load.readScores();
                        } else if (GameSlot.getSelectedIndex()==1) {
                            Loading load = new Loading("data/SavedGames/save1.txt", game);
                            load.readScores();
                        } else if (GameSlot.getSelectedIndex()==2) {
                            Loading load = new Loading("data/SavedGames/save2.txt", game);
                            load.readScores();
                        }
                    }catch (Exceptions exceptions) {
                        exceptions.printStackTrace();
                    }
                }
            }
        });
    }
public JPanel getMainPanel(){return  mainPanel;}
}

