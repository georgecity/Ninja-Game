package game.GAMEPANEL;

import city.cs.engine.UserView;
import city.cs.engine.World;
import game.MainCharacter.NinjaPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/*
Handles Game view
 */
public class PlayerView extends UserView {

    private NinjaPlayer player;

    private ImageIcon HealthBar = new ImageIcon("data/Miscellaneous/HealthBar.png");
    private ImageIcon HealthContent = new ImageIcon("data/Miscellaneous/HealthColor.png");
    private ImageIcon key = new ImageIcon("data/Miscellaneous/Key.png");
    private ImageIcon kill = new ImageIcon("data/Miscellaneous/killCount.png");

    private static BufferedImage parallax = null;
    private static int OffsetX = 0;
    private static int OffsetY = 0;


 public PlayerView(World world,NinjaPlayer player, int height, int width) {
        super(world, height, width);
        this.player = player;
    }

    /*
    set parallax scrolling for each background
     */
    @Override
    protected void paintBackground(Graphics2D g) {
        super.paintBackground(g);

        if (parallax != null){
           if (OffsetX < player.getPosition().x /3  - getWidth()){
               OffsetX += getWidth();
           }if (OffsetX > player.getPosition().x /3  + getWidth()){
                OffsetX -= getWidth();
           }

            int y = OffsetY - (int)player.getPosition().y /2;
            int x = OffsetX - (int)player.getPosition().x /3   ;
            int BufferX = 0;

            if (OffsetX > player.getPosition().x /3 ){
               BufferX = OffsetX - getWidth() - (int)player.getPosition().x /3 ;
            }else {
                BufferX = OffsetX + getWidth() - (int)player.getPosition().x /3;
            }

            g.drawImage(parallax, x, 0, getWidth(), getHeight(), this);
            g.drawImage(parallax, BufferX, 0, getWidth(), getHeight(), this);

        }else {
            try {
                    parallax = ImageIO.read(new File("data/Backgrounds/spriteworksCastleDark.gif"));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void setBody(NinjaPlayer player){this.player = player;}

    /*
    set foreground HUD: HealthBar, KillCount and KeyCount
     */
    @Override
    protected void paintForeground(Graphics2D g) {
        super.paintForeground(g);
        g.drawImage(HealthContent.getImage(), 52, 28, player.getHealth(), 20, this);
        g.drawImage(HealthBar.getImage(), 0, 5, 370, 60, this);

        g.drawImage(kill.getImage(),590, 5, 25, 35, this);
        g.setFont(new Font("TimesRoman",Font.BOLD,30));
        g.setColor(Color.WHITE);
        g.drawString(" " + player.getKillCount() ,625,33.5f);

        g.drawImage(key.getImage(), 590, 45, 30, 30, this);
        g.setFont(new Font("TimesRoman",Font.BOLD,30));
        g.setColor(Color.WHITE);
        g.drawString(" " + player.getKeyCount() ,625,71f);
    }

    /**
     * Read Image to load background.
     * @param path Set the File to load
     */
    public  void setParallax(String path) {
     try {
         parallax = ImageIO.read(new File(path));
     }catch (IOException e){
         e.printStackTrace();
     }
 }
}

//    private static ImageIcon background = new ImageIcon("data/Backgrounds/Casttle.gif");
//    private static ImageIcon background2 = new ImageIcon("data/Backgrounds/Level2.gif");
//    private static ImageIcon background3 = new ImageIcon("data/Backgrounds/uwubpsxqpua01.gif");
//        CountDownTimer c = new CountDownTimer();
//        Timer countDown= new Timer(50, c );
//        countDown.start();
//        g.drawImage(HealthContent.getImage(), 0, 5, player.getHealth(), 25, this);
//            if (OffsetY < player.getPosition().y /2  - getHeight()){
//                OffsetY += getHeight();
//            }
//            if (OffsetY > player.getPosition().y /2  + getHeight()){
//                OffsetY -= getHeight();
//            }
//        g.drawImage(background.getImage(), 0, -30, 500, 250, this);
//int BufferY = 0;





