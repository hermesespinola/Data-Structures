package tree.selection;

import tree.selection.Playable.GameType;

@SuppressWarnings("rawtypes")
public class WinnerTree<P extends Comparable<? super P>> implements SelectionTree<P> {

  private static class PlayableNode<P extends Comparable<? super P>> extends Playable<P> {
    public PlayableNode(P value) {
      super(value);
    }
  }

  GameType type;
  Playable<P>[] matchTree;
  Playable<P>[] players;
  private int offset, // right-most internal node at the lowest level.
              lowext, // number of external nodes at the lowest level.
              s;      // left-most internal node at the lowest level.

  @SuppressWarnings("unchecked")
  public WinnerTree(Playable<P>[] players, GameType type) {
    s = (int) Math.pow(2, Math.floor(Math.log(players.length-1) / Math.log(2)));
    lowext = 2 * (players.length - s);
    offset = 2*s - 1;
    matchTree = new Playable[players.length-1];

    this.players = players;
    for (int i = 0; i < players.length; i++) {
      this.players[i].index = i;
      this.players[i].parent = getParent(lowext, offset, players.length, i + 1);
    }
    this.type = type;
    initialize();
  }

  private final void initialize() {
    // first round, from players array to lower external nodes.
    for (Playable<P> player : this.players) {
        matchTree[player.parent] = player.play(matchTree[player.parent], type);
    }

    for (int i = matchTree.length; i > 1; i--) {
      int parent = i / 2 - 1;
      Playable<P> player = matchTree[i-1];
      matchTree[parent] = player.play(matchTree[parent], type);
    }
  }

  public void replay(int i) {
    if (i < 0 || i >= players.length) {
      throw new IndexOutOfBoundsException();
    }
    // first play
    Playable<P> player = players[i];
    Playable<P> bro = (i > 0 && players[i-1].parent == player.parent) ? players[i-1] :
                (i < players.length-1 && players[i+1].parent == player.parent) ? players[i+1] :
                matchTree[(player.parent + 1) * 2 - 1];
    matchTree[player.parent] = player.play(bro, type);

    int match = player.parent;
    int matchParent;
    while (match > 0) {
      matchParent = (match + 1) / 2;
      Playable<P> leftPlayer = matchTree[matchParent * 2 - 1];
      Playable<P> rightPlayer = (matchParent * 2 >= matchTree.length) ? players[2*matchParent+lowext-players.length+1] : matchTree[matchParent * 2];
      matchTree[matchParent-1] = leftPlayer.play(rightPlayer, type);
      match = matchParent-1;
    }
  }

  private static final int getParent(final int lowext, final int offset, final int n, final int child) {
    return (child <= lowext) ? (child + offset)/2 - 1 : (child - lowext + n - 1) / 2 - 1;
  }

  public Playable<P> getWinner() {
    return matchTree[0];
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Players: ");
    for (Playable p : players) {
      sb.append(p).append(", ");
    }

    sb.setLength(sb.length() > 2 ? sb.length() - 2 : sb.length());
    sb.append("\nTree: ");

    for (Playable p : matchTree) {
      sb.append(p).append(", ");
    }

    sb.setLength(sb.length() > 2 ? sb.length() - 2 : sb.length());
    return sb.toString();
  }

  public Playable<P>[] players() {
    return this.players;
  }

  public void change(int i, P x) {
    players[i].value = x;
  }

  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    PlayableNode<Integer>[] pl = (PlayableNode<Integer>[]) new PlayableNode[7];
    for (int i = 0; i < pl.length; i++) {
      pl[i] = new PlayableNode<Integer>(i+1);
    }
    WinnerTree<Integer> wt = new WinnerTree<Integer>(pl, GameType.Max);

    System.out.println(wt.offset);

    Playable<Integer> winner = wt.getWinner();
    System.out.println(wt);
    System.out.println();
    while (winner.value != 0) {
      wt.change(winner.index, winner.value - 1);
      wt.replay(winner.index);
      System.out.println(wt);
      System.out.println();
      winner = wt.getWinner();
    }
  }
}
