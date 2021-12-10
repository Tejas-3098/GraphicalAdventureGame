package gameview;

import dungeon.Location;
import dungeon.LocationType;
import dungeon.Node;
import dungeon.Treasure;
import dungeon.TreasureI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

class DungeonPanel extends JPanel {
  private final ReadOnlyDungeonModel dun;

  public static final int DNODE_SIZE = 100;
  public static final int DIFF = 100;
  public static final int FONT_SIZE = 15;
  public static final String FONT_STYLE = "ComicSansMS";

  DungeonPanel(ReadOnlyDungeonModel model) {
    this.dun = model;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    HashMap<String, String> images = new HashMap<>();
    images.put("E N S W ", "/dungeon-images/color-cells/NSEW.png");
    images.put("E ", "/dungeon-images/color-cells/E.png");
    images.put("E W ", "/dungeon-images/color-cells/EW.png");
    images.put("N ", "/dungeon-images/color-cells/N.png");
    images.put("E N ", "/dungeon-images/color-cells/NE.png");
    images.put("E N W ", "/dungeon-images/color-cells/NEW.png");
    images.put("N S ", "/dungeon-images/color-cells/NS.png");
    images.put("E N S ", "/dungeon-images/color-cells/NSE.png");
    images.put("N S W ", "/dungeon-images/color-cells/NSW.png");
    images.put("N W ", "/dungeon-images/color-cells/NW.png");
    images.put("S ", "/dungeon-images/color-cells/S.png");
    images.put("E S ", "/dungeon-images/color-cells/SE.png");
    images.put("E S W ", "/dungeon-images/color-cells/SEW.png");
    images.put("S W ", "/dungeon-images/color-cells/SW.png");
    images.put("W ", "/dungeon-images/color-cells/W.png");


    Node[][] node = dun.getGame2dDungeon();


    BufferedImage img = null;
    int x = 20;
    int y = 170;
    for (int i = 0; i < dun.getRows(); i++) {
      x = 20;
      for (int j = 0; j < dun.getColumns(); j++) {
        if (!node[i][j].getVisitedStatus()) {
          img = (BufferedImage) this.imageRead("/dungeon-images/blank.png");
          g2d.drawImage(img, x, y, 120, 120, this);
        } else {
          String imageToDraw = images.get(node[i][j].getLocMove());
          img = (BufferedImage) this.imageRead(imageToDraw);
          g2d.drawImage(img, x, y, 120, 120, this);

          //Displaying the player
          int pid = dun.getPlayer().getPlayerLocId();
          //System.out.println("Player starts at :"+pid);
          Location l = dun.getPlayLocFromLocId(pid);
          if (node[i][j].getLoc() == l) {
            img = (BufferedImage) this.imageRead("/dungeon-images/color-cells/player.jpg");
            g2d.drawImage(img, x + 40, y + 10, 30, 30, this);
          }

          //Displaying Player Description
          g2d.setFont(new Font(FONT_STYLE, Font.PLAIN, FONT_SIZE));
          g2d.setColor(Color.BLUE);
          g2d.drawString("Player Description", 10, 20);
          g2d.setColor(Color.BLACK);
          g2d.drawString(dun.getPlayer().toString(), 10, 40);

          //Displaying the node details
          g2d.setFont(new Font("TimesNewRoman", Font.PLAIN, FONT_SIZE));
          g2d.setColor(Color.BLUE);
          g2d.drawString("Location Description", 10, 60);
          g2d.setColor(Color.BLACK);
          if (node[i][j].getLocType() == LocationType.TUNNEL && node[i][j].getLoc() == l) {
            g2d.drawString("Location Type: TUNNEL", 10, 80);
          } else if (node[i][j].getLocType() == LocationType.CAVE && node[i][j].getLoc() == l) {
            g2d.drawString("Location Type: CAVE", 10, 80);
          }
          if (dun.getMonsterStatus(node[i][j].getLocId())
                  && node[i][j].getMonster().get(0).getHealth() != 0
                  && node[i][j].getLoc() == l) {
            g2d.drawString("Monster : PRESENT! You're about to be eaten!", 200, 80);
          } else if (!dun.getMonsterStatus(node[i][j].getLocId()) && node[i][j].getLoc() == l) {
            g2d.drawString("Monster : NOT PRESENT! You're safe for now!", 200, 80);
          }
          if (dun.getSmell() == 1) {
            g2d.drawString("Smell : Extremely pungent! Monster very close!", 10, 100);
          } else if (dun.getSmell() == 2) {
            g2d.drawString("Smell : Faint, Monster present 2 locations away!", 10, 100);
          } else {
            g2d.drawString("Smell : No smell, there is no monster nearby!", 10, 100);
          }
          //Displaying the treasure
          ArrayList<TreasureI> treList = dun.getGame2dDungeon()[i][j].getTreasure();
          //System.out.println(tList);
          for (int s = 0; s < treList.size(); s++) {
            if (treList.get(s).getTypeOfTreasure() == Treasure.DIAMOND) {
              img = (BufferedImage) this.imageRead("/dungeon-images/diamond.png");
              g2d.drawImage(img, x + 60, y + 30, 20, 20, this);

            } else if (treList.get(s).getTypeOfTreasure() == Treasure.RUBY) {
              img = (BufferedImage) this.imageRead("/dungeon-images/ruby.png");
              g2d.drawImage(img, x + 45, y + 25, 20, 20, this);

            } else if (treList.get(s).getTypeOfTreasure() == Treasure.SAPPHIRE) {
              img = (BufferedImage) this.imageRead("/dungeon-images/emerald.png");
              g2d.drawImage(img, x + 52, y + 27, 20, 20, this);
            }
          }
          //Displaying the monster
          if (dun.getMonsterStatus(node[i][j].getLocId())
                  && dun.getGame2dDungeon()[i][j].getMonster().get(0).getHealth() > 0) {
            img = (BufferedImage) this.imageRead("/dungeon-images/otyugh.png");
            g2d.drawImage(img, x + 25, y + 50, 30, 30, this);
          }
          //Displaying the smell

          if (dun.getSmell() == 1 && node[i][j].getLoc() == l) {
            img = (BufferedImage) this.imageRead("/dungeon-images/"
                    + "color-cells/smellPungent.jfif");
            g2d.drawImage(img, x + 55, y + 55, 30, 30, this);
          } else if (dun.getSmell() == 2 && node[i][j].getLoc() == l) {

            img = (BufferedImage) this.imageRead("/dungeon-images/color-cells/"
                    + "smellFaint.jfif");
            g2d.drawImage(img, x + 55, y + 55, 30, 30, this);
          }
          //Displaying the arrows
          if (node[i][j].getArrowStatus()) {
            img = (BufferedImage) this.imageRead("/dungeon-images/color-cells/arrow.png");
            g2d.drawImage(img, x + 70, y + 25, 15, 15, this);
          }
        }

        x = x + 120;
      }
      y = y + 120;
    }

  }

  private Image imageRead(String filePath) {
    try {
      InputStream imageStream = getClass().getResourceAsStream(filePath);
      return ImageIO.read(imageStream);
    } catch (IOException e) {
      throw new IllegalStateException("Cannot find base location image!");
    }
  }
}
