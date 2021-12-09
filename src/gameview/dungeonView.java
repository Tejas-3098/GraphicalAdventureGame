package gameview;

//import java.awt.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.util.Objects;

import javax.swing.*;

import controller.GuiGameConsoleController;

public class dungeonView extends JFrame implements dungeonI {
  private final JTextField rows;
  private final JTextField columns;
  private final JTextField interconnectivity;
  private final JTextField wrapping;
  private final JTextField percentage;
  private final JTextField numOfMonsters;

  private final JButton start;
  private final JButton clear;
  JMenuBar mb;
  JMenu m;
  JMenuItem restart, newGame, quit;

  public dungeonView(ReadOnlyDungeonModel dun) {
    JFrame inputPara = new JFrame("Game Inputs");
    inputPara.setSize(1000, 800);
    inputPara.setLayout(new FlowLayout(5));


    JLabel lblRows = new JLabel("Rows");
    inputPara.add(lblRows);

    rows = new JTextField(4);
    rows.setText("5");
    inputPara.add(rows);

    JLabel lblCols = new JLabel("Columns");
    inputPara.add(lblCols);

    columns = new JTextField(4);
    columns.setText("5");
    inputPara.add(columns);

    JLabel lblInt = new JLabel("Interconnectivity");
    inputPara.add(lblInt);

    interconnectivity = new JTextField(4);
    interconnectivity.setText("8");
    inputPara.add(interconnectivity);

    JLabel lblWrap = new JLabel("Wrapping(Yes/No)");
    inputPara.add(lblWrap);
    wrapping = new JTextField(4);
    wrapping.setText("no");
    inputPara.add(wrapping);

    JLabel lblPerc = new JLabel("Percentage of Caves to add Treasure");
    inputPara.add(lblPerc);
    percentage = new JTextField(4);
    percentage.setText("85");
    inputPara.add(percentage);

    JLabel lblNumOfMon = new JLabel("Number of Monsters");
    inputPara.add(lblNumOfMon);
    numOfMonsters = new JTextField(4);
    numOfMonsters.setText("10");
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
    this.setSize(new Dimension(1000, 700));


    mb = new JMenuBar();
    m = new JMenu("Game Settings");
    restart = new JMenuItem("Restart");
    newGame = new JMenuItem("New Game");
    quit = new JMenuItem("Quit");
    m.add(restart);
    m.add(newGame);
    m.add(quit);
    mb.add(m);
    this.setJMenuBar(mb);
    DungeonPanel dPanel = new DungeonPanel(dun);
    this.add(dPanel);
    JScrollPane scPane = new JScrollPane(dPanel);
    scPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scPane.setPreferredSize(new Dimension(1400, 1000));
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
    restart.addActionListener(l -> f.restart(rows.getText(), columns.getText(),
            interconnectivity.getText(), wrapping.getText(), percentage.getText(),
            numOfMonsters.getText()));
    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {

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

      }

    });


  }

  @Override
  public String popDialog() {
    return JOptionPane.showInputDialog(this,
            "Enter the distance to shoot:", null);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setCommandButtonListener(ActionListener actionEvent) {

  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void addClickListener(GuiGameConsoleController guiGameConsoleController) {
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
      JOptionPane.showMessageDialog(this,"Chomp Chomp Chomp," +
              " you are eaten by an Otyugh! Please go to game settings" +
              " and click on Restart to start a new game!");
    }
    else if (Objects.equals(s, "player")) {
      JOptionPane.showMessageDialog(this,"Congrats! You have won the game! " +
              "Please go to game settings and click on Restart to start a new game!");
    }
  }

}
