package gameview;

import java.util.ArrayList;

import dungeon.Location;
import dungeon.Node;
import dungeon.Player;
import dungeon.Treasure;

/**
 * The interface needed for a read-only model for the Dungeon game model.
 */
public interface ReadOnlyDungeonModel {

  Node[][] getGame2dDungeon();

  int getStartCaveLocId();

  int getEndCaveLocId();

  Player getPlayer();

  boolean getMonsterStatus(int locId);

  int getSmell();

  ArrayList<Treasure> getGameTreasure();

  Location getPlayLocFromLocId(int locId);

  int getRows();

  int getColumns();

}
