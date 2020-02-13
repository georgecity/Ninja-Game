package game.GAMEPANEL;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Handles map production for every level
 */
public class TileMap  {
    /**
     * Handles tile separation and set width and height values for map
     */
    private int x;
    private int y;
    private int tileSize;
    private String[][] map;
    private int mapWidth;
    private int mapHeight;
    private World world;

    /**
    Load every level map texture
     */
    private static final BodyImage tileGround = new BodyImage("data/tileSet/tilesLevel1/tileBorderMiddle.png", 2.1f);
    private static final BodyImage tileLeftBorder = new BodyImage("data/tileSet/tilesLevel1/tileLeftBorder.png", 2.1f);
    private static final BodyImage tileRightBorder = new BodyImage("data/tileSet/tilesLevel1/tileRightBorder.png", 2.1f);
    private static final BodyImage tileLeftWall = new BodyImage("data/tileSet/tilesLevel1/tileLeftWall.png", 2.1f);
    private static final BodyImage tileRightWall = new BodyImage("data/tileSet/tilesLevel1/tileRightWall.png", 2.1f);
    private static final BodyImage tileCentreGround = new BodyImage("data/tileSet/tilesLevel1/tileGround.png", 2.1f);
    private static final BodyImage tileGroundShadow = new BodyImage("data/tileSet/tilesLevel1/tileGroundShadow.png", 2.1f);
    private static final BodyImage tileRightBottomBorder = new BodyImage("data/tileSet/tilesLevel1/tileRightBottomBorder.png", 2.1f);
    private static final BodyImage tileLeftBottomBorder = new BodyImage("data/tileSet/tilesLevel1/tileLeftBottomBorder.png", 2.1f);

    private static final BodyImage GroundLvl2 = new BodyImage("data/tileSet/tilesLevel2/groundLevel2.png", 2.1f);
    private static final BodyImage LeftBorderLvl2 = new BodyImage("data/tileSet/tilesLevel2/LeftBorderLevel2.png", 2.1f);
    private static final BodyImage RightBorderLvl2 = new BodyImage("data/tileSet/tilesLevel2/RightBorderLevel2.png", 2.1f);
    private static final BodyImage LeftWallLvl2 = new BodyImage("data/tileSet/tilesLevel2/LeftWallLevel2.png", 2.1f);
    private static final BodyImage RightWallLvl2 = new BodyImage("data/tileSet/tilesLevel2/RightWallLevel2.png", 2.1f);
    private static final BodyImage CentreGroundLvl2 = new BodyImage("data/tileSet/tilesLevel2/midGroundLevel2.png", 2.1f);
    private static final BodyImage groundShadowLvl2 = new BodyImage("data/tileSet/tilesLevel2/shadowGroundLevel2.png", 2.1f);
    private static final BodyImage RightBottomBorderLvl2 = new BodyImage("data/tileSet/tilesLevel2/bottomRightBorderLevel2.png", 2.1f);
    private static final BodyImage LeftBottomBorderLvl2 = new BodyImage("data/tileSet/tilesLevel2/bottomLeftBorderLevel2.png", 2.1f);

    private static final BodyImage GroundLvl3 = new BodyImage("data/tileSet/tilesLevel3/groundLvl3.png", 2.1f);
    private static final BodyImage LeftBorderLvl3 = new BodyImage("data/tileSet/tilesLevel3/leftBorderLvl3.png", 2.1f);
    private static final BodyImage RightBorderLvl3 = new BodyImage("data/tileSet/tilesLevel3/rightBorderLvl3.png", 2.1f);
    private static final BodyImage LeftWallLvl3 = new BodyImage("data/tileSet/tilesLevel3/leftWallLvl3.png", 2.1f);
    private static final BodyImage RightWallLvl3 = new BodyImage("data/tileSet/tilesLevel3/rightWallLvl3.png", 2.1f);
    private static final BodyImage CentreGroundLvl3 = new BodyImage("data/tileSet/tilesLevel3/centreGroundLvl3.png", 2.1f);
    private static final BodyImage groundShadowLvl3 = new BodyImage("data/tileSet/tilesLevel3/bottomGroundLvl3.png", 2.1f);
    private static final BodyImage RightBottomBorderLvl3 = new BodyImage("data/tileSet/tilesLevel3/rightBottomBorderLvl3.png", 2.1f);
    private static final BodyImage LeftBottomBorderLvl3 = new BodyImage("data/tileSet/tilesLevel3/leftBottomBorderLvl3.png", 2.1f);

    private static final BodyImage GroundLvl4 = new BodyImage("data/tileSet/tilesLevel4/groundLvl4.gif", 2.1f);
    private static final BodyImage LeftBorderLvl4 = new BodyImage("data/tileSet/tilesLevel4/leftBorderLvl4.gif", 2.1f);
    private static final BodyImage RightBorderLvl4 = new BodyImage("data/tileSet/tilesLevel4/rightBorderLvl4.gif", 2.1f);
    private static final BodyImage LeftWallLvl4 = new BodyImage("data/tileSet/tilesLevel4/leftWallLvl4.gif", 2.1f);
    private static final BodyImage RightWallLvl4 = new BodyImage("data/tileSet/tilesLevel4/rightWallLvl4.gif", 2.1f);
    private static final BodyImage CentreGroundLvl4 = new BodyImage("data/tileSet/tilesLevel4/centreGroundLvl4.gif", 2.1f);
    private static final BodyImage groundShadowLvl4 = new BodyImage("data/tileSet/tilesLevel4/bottomGroundLvl4.gif", 2.1f);
    private static final BodyImage RightBottomBorderLvl4 = new BodyImage("data/tileSet/tilesLevel4/rightBottomBorderLvl4.gif", 2.1f);
    private static final BodyImage LeftBottomBorderLvl4 = new BodyImage("data/tileSet/tilesLevel4/leftBottomBorderLvl4.gif", 2.1f);
    private static final BodyImage platform = new BodyImage("data/Miscellaneous/platform.png", 1.125f);

    private Game game;

    public int getWidth() { return map.length; }
    public int getHeight() { return map[0].length; }

    /**
     *Initialise Map reader
     * @param s select which file to read from to load the map
     * @param tileSize separation between each tile block
     * @param world produce the map on world
     * @param game populate the map
     */
    public TileMap(String s, int tileSize, World world, Game game) {

        this.tileSize = tileSize;
        this.world = world;
        this.game = game;

        /**
         * Initialise a new Buffering reader, read first 2 lines to set width and height
         * creates a String array and reads all the tokens inside, taking into account 1 space separation.
         */

        try {
            BufferedReader br = new BufferedReader(new FileReader(s));
            mapWidth = Integer.parseInt(br.readLine());
            mapHeight = Integer.parseInt(br.readLine());
            map = new String[mapHeight][mapWidth];

            String delimiters = " ";

            for (int row = 0; row < mapHeight; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delimiters);

                for (int col = 0; col < mapWidth; col++) {
                    map[row][col] = (tokens[col]);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
/**
 * Initialise draw of tile Blocks
 */
        draw();
    }

    public int getx(){return x;}
    public int gety(){return y;}

    public int getColTile(int x){return x /  tileSize;}
    public int getRowTile(int y){return y /  tileSize;}
    public int getTile(int row, int col){return Integer.parseInt(map[row][col]);}
    public int getTileSize(){return tileSize;}

    /**
     * assign a textured tile or World Object to a token and produce it on
     * World depending the level in which the player is on
     */
    public void draw(){

        for (int row = 0; row < mapHeight; row++) {
            for (int col = 0; col < mapWidth; col++) {
                String rc = map[row][col];

                if (rc.equals("0")  ) {
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    if (game.getLevel()==1) { tile.addImage(tileCentreGround);}
                    else if(game.getLevel()==2){tile.addImage(CentreGroundLvl2);}
                    else if(game.getLevel()==3){tile.addImage(CentreGroundLvl3);}
                    else if(game.getLevel()==4){tile.addImage(CentreGroundLvl4);}
                }

                if (rc.equals("2")){
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    if(game.getLevel()==1) { tile.addImage(tileLeftBorder);}
                    if(game.getLevel()==2){tile.addImage(LeftBorderLvl2);}
                    if(game.getLevel()==3){tile.addImage(LeftBorderLvl3);}
                    if(game.getLevel()==4){tile.addImage(LeftBorderLvl4);}
                }

                if (rc.equals("3") ){
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    if(game.getLevel()==1) { tile.addImage(tileRightBorder);}
                    else if(game.getLevel()==2){tile.addImage(RightBorderLvl2);}
                    if(game.getLevel()==3){tile.addImage(RightBorderLvl3);}
                    if(game.getLevel()==4){tile.addImage(RightBorderLvl4);}
                }

                if (rc.equals("4") ){
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    if(game.getLevel()==1) { tile.addImage(tileLeftWall);}
                    if(game.getLevel()==2){tile.addImage(LeftWallLvl2);}
                    else if(game.getLevel()==3){tile.addImage(LeftWallLvl3);}
                    if(game.getLevel()==4){tile.addImage(LeftWallLvl4);}
                }

                if (rc.equals("5") ){
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    if(game.getLevel()==1) { tile.addImage(tileRightWall);}
                    if(game.getLevel()==2){tile.addImage(RightWallLvl2);}
                    if(game.getLevel()==3){tile.addImage(RightWallLvl3);}
                    else if(game.getLevel()==4){tile.addImage(RightWallLvl4);}
                }

                if (rc.equals("6") ){
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    if(game.getLevel()==1) { tile.addImage(tileGround);}
                    else if(game.getLevel()==2){tile.addImage(GroundLvl2);}
                    if(game.getLevel()==3){tile.addImage(GroundLvl3);}
                    else if(game.getLevel()==4){tile.addImage(GroundLvl4);}
                }

                if (rc.equals("7") && game.getLevel() == 1){
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    tile.addImage(tileGroundShadow);
                }if (rc.equals("7") && game.getLevel()==2) {
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    tile.addImage(groundShadowLvl2);
                }if (rc.equals("7") && game.getLevel()==3) {
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    tile.addImage(groundShadowLvl3);
                }if (rc.equals("7") && game.getLevel()==4) {
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    tile.addImage(groundShadowLvl4);
                }

                if (rc.equals("8") && game.getLevel() ==1){
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    tile.addImage(tileRightBottomBorder);
                }if (rc.equals("8") && game.getLevel()==2) {
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    tile.addImage(RightBottomBorderLvl2);
                }if (rc.equals("8") && game.getLevel()==3) {
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    tile.addImage(RightBottomBorderLvl3);
                }if (rc.equals("8") && game.getLevel()==4) {
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    tile.addImage(RightBottomBorderLvl4);
                }

                if (rc.equals("9") && game.getLevel() ==1){
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    tile.addImage(tileLeftBottomBorder);
                }if (rc.equals("9") && game.getLevel()==2) {
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    tile.addImage(LeftBottomBorderLvl2);
                }if (rc.equals("9") && game.getLevel()==3) {
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    tile.addImage(LeftBottomBorderLvl3);
                }if (rc.equals("9") && game.getLevel()==4) {
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 1));
                    tile.setPosition(new Vec2(col * tileSize, -row * tileSize));
                    tile.addImage(LeftBottomBorderLvl4);
                }

                if (rc.equals("q") ){
                    ((GameLevel)world).newEnemy(new Vec2(col * tileSize, -row *tileSize));
                }

                if (rc.equals("w") ){
                    for (int i=0; i<2; i++) {
                        ((GameLevel) world).newSpikeTrap(new Vec2((i +col * tileSize) -0.4f, -row * tileSize -0.53f));
                    }
                }

                if (rc.equals("e") ){
                    ((GameLevel)world).newCannonBall(new Vec2(col * tileSize, -row *tileSize));
                }

                if (rc.equals("r") ){
                    ((GameLevel)world).newKey(new Vec2(col * tileSize, -row *tileSize));
                }

                if (rc.equals("t") ){
                    StaticBody pm = new StaticBody(world, new BoxShape(1, 0.5f));
                    pm.setPosition(new Vec2(col * tileSize, -row * tileSize + 0.5f ));
                    pm.addImage(platform);
                }

                if (rc.equals("y")){
                    ((GameLevel)world).newMinion(new Vec2(col * tileSize, -row *tileSize));
                }

                if (rc.equals("u") ){
                    ((GameLevel)world).newBoss(new Vec2(col * tileSize, -row *tileSize));
                }

                if (rc.equals("i") ){
                    ((GameLevel)world).newPlatform(new Vec2(col * tileSize, -row *tileSize));
                }

                if (rc.equals("p") ){
                    ((GameLevel)world).newSpikeTimer(new Vec2(col * tileSize- 0.4f, -row *tileSize - 0.425f));
                }

                if (rc.equals("a") ){
                    ((GameLevel)world).newLaserTrap(new Vec2(col * tileSize, -row *tileSize));
                }

                if (rc.equals("s") && game.getLevel() ==1){
                    StaticBody tile = new StaticBody(world, new BoxShape(1, 0.5f));
                    tile.setFillColor(Color.BLACK);
                    tile.setPosition(new Vec2(col * tileSize, (-row * tileSize)-0.85f));
                    tile.addImage(platform).setScale(1.15f);
                }

            }
        }
    }
}