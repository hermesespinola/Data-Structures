package tournament;
import heap.BinaryHeap;

public class WinnerTree<Player extends Comparable<Player>> {
  BinaryHeap<Player> tree;
  Player[] players;

  public WinnerTree(Player[] players) {
    this.players = players;
  }
}
