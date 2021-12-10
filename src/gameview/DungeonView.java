package gameview;

import controller.GuiGameConsoleController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * The view class for the game which displays the dungeon created by the user,
 * the player at the start cave, monsters at random caves, treasure at random caves,
 * arrows at random nodes. It also contains methods to display dialogs when a particular action like
 * move, pickup or shoot is performed.
 */
public class DungeonView extends JFrame implements DungeonI {
  private final JTextField rows;
  private final JTextField columns;
  private final JTextField interconnectivity;
  private final JTextField wrapping;
  private final JTextField percentage;
  private final JTextField numOfMonsters;

  private final JButton start;
  private final JButton clear;
  JMenuBar mb;
  JMenu menu;
  JMenuItem restart;
  JMenuItem newGame;
  JMenuItem quit;

  /**
   * Constructor for the view which takes in the ReadOnlyDungeonModel dun. View only has access to
   * the read-only model and cannot access other methods of the model.
   *
   * @param dun the read-only model.
   */
  public DungeonView(ReadOnlyDungeonModel dun) {
    JFrame inputPara = new JFrame("Game Inputs");
    inputPara.setSize(1000, 830);
    inputPara.setLayout(new FlowLayout(5));


    JLabel lblRows = new JLabel("Rows");
    inputPara.add(lblRows);

    rows = new JTextField(4);
    //rows.setText("5");
    inputPara.add(rows);

    JLabel lblCols = new JLabel("Columns");
    inputPara.add(lblCols);

    columns = new JTextField(4);
    //columns.setText("5");
    inputPara.add(columns);

    JLabel lblInt = new JLabel("Interconnectivity");
    inputPara.add(lblInt);

    interconnectivity = new JTextField(4);
    //interconnectivity.setText("8");
    inputPara.add(interconnectivity);

    JLabel lblWrap = new JLabel("Wrapping(Yes/No)");
    inputPara.add(lblWrap);
    wrapping = new JTextField(4);
    //wrapping.setText("no");
    inputPara.add(wrapping);

    JLabel lblPerc = new JLabel("Percentage of Caves to add Treasure");
    inputPara.add(lblPerc);
    percentage = new JTextField(4);
    //percentage.setText("85");
    inputPara.add(percentage);

    JLabel lblNumOfMon = new JLabel("Number of Monsters");
    inputPara.add(lblNumOfMon);
    numOfMonsters = new JTextField(4);
    //numOfMonsters.setText("10");
    inputPara.add(numOfMonsters);

    start = new JButton("Start Game");
    start.setActionCommand("Start Game");
    inputPara.add(start);

    clear = new JButton("Clear");
    clear.setActionCommand("Clear");
    inputPara.add(clear);

    inputPara.setVisible(true);
    inputPara.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    this.setTitle("Conquer the Maze Game!");
    //super("Conquer the Maze Game!");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(new Dimension(1000, 830));


    mb = new JMenuBar();
    menu = new JMenu("Game Settings");
    restart = new JMenuItem("Restart");
    newGame = new JMenuItem("New Game");
    quit = new JMenuItem("Quit");
    menu.add(restart);
    menu.add(newGame);
    menu.add(quit);
    mb.add(menu);
    this.setJMenuBar(mb);
    DungeonPanel dunPanel = new DungeonPanel(dun);
    this.add(dunPanel);
    JScrollPane scPane = new JScrollPane(dunPanel);
    scPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scPane.setPreferredSize(new Dimension(400, 500));
    scPane.setVisible(true);
    this.getContentPane().add(scPane);
    //makeVisible();
  }

  @Override
  public void setFeatures(Features f) {

    start.addActionListener(l -> f.startGame(rows.getText(), columns.getText(),
            interconnectivity.getText(), wrapping.getText(), percentage.getText(),
            numOfMonsters.getText()));
    clear.addActionListener(l -> f.clear());
    quit.addActionListener(l -> f.quitGame());
    newGame.addActionListener(l -> f.newGame());
    restart.addActionListener(l -> f.restart(rows.getText(), columns.getText(),
            interconnectivity.getText(), wrapping.getText(), percentage.getText(),
            numOfMonsters.getText()));
    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        //As there is no action performed when a key is typed, this method is empty.
      }

      @Override
      public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT
                || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_UP) {
          f.move(e);
        } else if (e.getKeyCode() == 'P' || e.getKeyCode() == 'T') {
          f.pickup(e);
        } else if (e.getKeyCode() == 'W' || e.getKeyCode() == 'A' || e.getKeyCode() == 'S'
                || e.getKeyCode() == 'D') {
          int d = Integer.parseInt(popDialog());
          f.shoot(e, d);
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        //As there is no action performed when a key is released, this method is empty.
      }

    });


  }

  @Override
  public void createInpParaScreen() {
    JFrame inputPara = new JFrame("Game Inputs");
    inputPara.setSize(1000, 800);
    inputPara.setLayout(new FlowLayout(5));


    JLabel lblRows = new JLabel("Rows");
    inputPara.add(lblRows);

    JTextField rows = new JTextField(4);
    rows.setText("5");
    inputPara.add(rows);

    JLabel lblCols = new JLabel("Columns");
    inputPara.add(lblCols);

    JTextField columns = new JTextField(4);
    columns.setText("5");
    inputPara.add(columns);

    JLabel lblInt = new JLabel("Interconnectivity");
    inputPara.add(lblInt);

    JTextField interconnectivity = new JTextField(4);
    interconnectivity.setText("8");
    inputPara.add(interconnectivity);

    JLabel lblWrap = new JLabel("Wrapping(Yes/No)");
    inputPara.add(lblWrap);
    JTextField wrapping = new JTextField(4);
    wrapping.setText("no");
    inputPara.add(wrapping);

    JLabel lblPerc = new JLabel("Percentage of Caves to add Treasure");
    inputPara.add(lblPerc);
    JTextField percentage = new JTextField(4);
    percentage.setText("85");
    inputPara.add(percentage);

    JLabel lblNumOfMon = new JLabel("Number of Monsters");
    inputPara.add(lblNumOfMon);
    JTextField numOfMonsters = new JTextField(4);
    numOfMonsters.setText("10");
    inputPara.add(numOfMonsters);

    inputPara.setVisible(true);
  }

  @Override
  public String popDialog() {
    return JOptionPane.showInputDialog(this,
            "Enter the distance to shoot:", null);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void makeNonVisible() {
    this.setVisible(false);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void addClickListener(GuiGameConsoleController guiGameConsoleController) {
    //empty method as no use yet.
  }

  @Override
  public void clearInputParams() {
    rows.setText("");
    columns.setText("");
    interconnectivity.setText("");
    wrapping.setText("");
    percentage.setText("");
    numOfMonsters.setText("");
  }

  @Override
  public void showResult(String s) {
    if (Objects.equals(s, "monster")) {
      JOptionPane.showMessageDialog(this, "Chomp Chomp Chomp,"
              + " you are eaten by an Otyugh! Please go to game settings"
              + " to start a new game!");
    } else if (Objects.equals(s, "player")) {
      JOptionPane.showMessageDialog(this, "Congrats! You have won the game! "
              + "Please go to game settings to start a new game!");
    } else if (Objects.equals(s, "injured")) {
      JOptionPane.showMessageDialog(this, "Monster got injured!"
              + " You hear a howl!");
    } else if (Objects.equals(s, "killed")) {
      JOptionPane.showMessageDialog(this, "Monster got Killed!"
              + " You hear a deafening howl which ceases after 2 seconds!");
    } else if (Objects.equals(s, "missed")) {
      JOptionPane.showMessageDialog(this, "Player has missed the shot!"
              + " Please try shooting again!");
    } else if (Objects.equals(s, "no path")) {
      JOptionPane.showMessageDialog(this, "Cannot shoot arrow in this "
              + "direction! Please shoot in other direction!");
    }

  }

}
