package gameview;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;

import controller.GuiGameConsoleController;

public class dungeonView extends JFrame implements dungeonI {
  private final DungeonPanel dPanel;
  //static JFrame f;
  private JTextField rows, columns, interconnectivity, wrapping, percentage, numOfMonsters;
  private JLabel lblRows, lblCols, lblInt, lblWrap, lblPerc, lblNumOfMon;

  private JButton enter, clear;
  JMenuBar mb;
  JMenu m;
  JMenuItem restart, reset, quit;

  public dungeonView(ReadOnlyDungeonModel dun) {
    JFrame inputPara = new JFrame("Game Inputs");
    inputPara.setSize(1000, 500);
    inputPara.setLayout(new FlowLayout(5));


    lblRows = new JLabel("Rows");
    inputPara.add(lblRows);

    rows = new JTextField(4);
    inputPara.add(rows);

    lblCols = new JLabel("Columns");
    inputPara.add(lblCols);

    columns = new JTextField(4);
    inputPara.add(columns);

    lblInt = new JLabel("Interconnectivity");
    inputPara.add(lblInt);

    interconnectivity = new JTextField(4);
    inputPara.add(interconnectivity);

    lblWrap = new JLabel("Wrapping(Yes/No)");
    inputPara.add(lblWrap);
    wrapping = new JTextField(4);
    inputPara.add(wrapping);

    lblPerc = new JLabel("Percentage of Caves to add Treasure");
    inputPara.add(lblPerc);
    percentage = new JTextField(4);
    inputPara.add(percentage);

    lblNumOfMon = new JLabel("Number of Monsters");
    inputPara.add(lblNumOfMon);
    numOfMonsters = new JTextField(4);
    inputPara.add(numOfMonsters);

    enter = new JButton("Enter");
    enter.setActionCommand("Enter");
    inputPara.add(enter);

    clear = new JButton("clear");
    clear.setActionCommand("Clear");
    inputPara.add(clear);

    inputPara.setVisible(true);
    inputPara.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    this.setTitle("Conquer the Maze Game!");
    //super("Conquer the Maze Game!");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(new Dimension(500, 500));


    mb = new JMenuBar();
    m = new JMenu("Game Settings");
    restart = new JMenuItem("Restart");
    reset = new JMenuItem("Reset");
    quit = new JMenuItem("Quit");
    m.add(restart);
    m.add(reset);
    m.add(quit);
    mb.add(m);
    this.setJMenuBar(mb);
    dPanel = new DungeonPanel(dun);
    this.add(dPanel);
    //JScrollPane scPane = new JScrollPane(dPanel);
    //scPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    //scPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    //scPane.setPreferredSize(new Dimension(1400, 1000));
    //scPane.setVisible(true);
    //this.getContentPane().add(scPane);
    makeVisible();
  }


  @Override
  public void setFeatures(Features f) {
    /*
    // exit program is tied to the exit button
    exitButton.addActionListener(l -> f.exitProgram());
    // toggle color is tied to a toggle button. Originally this functionality
    // was
    // exposed only by a key press. Having a set of callbacks to call gives
    // the view full control over which UI elements to map to which features.
    toggleButton.addActionListener(l -> f.toggleColor());

     */
    //enter.addActionListener(l -> f.processRows(rows.getText()));
    //enter.addActionListener(l -> f.processCols(columns.getText()));
    //enter.addActionListener(l -> f.processInt(interconnectivity.getText()));
    //enter.addActionListener(l -> f.processWrap(wrapping.getText()));
    //enter.addActionListener(l -> f.processPerc(percentage.getText()));
    //enter.addActionListener(l -> f.processNumOfMons(numOfMonsters.getText()));
    enter.addActionListener(l -> f.enter(rows.getText(), columns.getText(),
            interconnectivity.getText(), wrapping.getText(), percentage.getText(),
            numOfMonsters.getText()));
    restart.addActionListener(l -> f.restart() );
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
          int d = Integer.parseInt(popup());
          f.shoot(e, d);
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {

      }

    });


  }

  @Override
  public String popup() {
    String ip = JOptionPane.showInputDialog(this,
            "Enter the distance to shoot:", null);
    return ip;
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

}
