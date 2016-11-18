package tree.selection;

// TODO: make Tournament tree abstract class
@SuppressWarnings("rawtypes")
public class LoserTree<P extends Comparable<? super P>> implements SelectionTree<P> {
  private static class PlayableNode<P extends Comparable<? super P>> extends Playable<P> {
    private PlayableNode(P value) {
      super(value);
    }
  }

  private static class Entry<P extends Comparable<? super P>> {
    Playable<P> winner;
    Playable<P> loser;
  }

  Type type;
  Entry<P>[] matchTree;
  Playable<P>[] players;
  private int offset, // right-most internal node at the lowest level.
              lowext, // number of external nodes at the lowest level.
              s;      // left-most internal node at the lowest level.

  @SuppressWarnings("unchecked")
  public LoserTree(P[] players, Type type) {
    s = (int) Math.pow(2, Math.floor(Math.log(players.length-1) / Math.log(2)));
    lowext = 2 * (players.length - s);
    offset = 2*s - 1;

    matchTree = new Entry[players.length-1];
    this.players = new PlayableNode[players.length];
    for (int i = 0; i < players.length; i++) {
      this.players[i] = new PlayableNode<P>(players[i]);
      this.players[i].index = i;
      this.players[i].parent = SelectionTree.getParent(lowext, offset, players.length, i + 1);
    }
    this.type = type;
    initialize();
  }

  private final void initialize() {
    // first round, from players array to lower external nodes.
    for (Playable<P> player : this.players) {
      Entry<P> e = new Entry<>();
      e.winner = player.play(matchTree[player.parent].winner, type);
      e.loser = (player == e.winner) ? matchTree[player.parent].winner : player;
      matchTree[player.parent] = e;
    }

    for (int i = matchTree.length; i > 1; i--) {
      int parent = i / 2 - 1;
      Playable<P> player = matchTree[i-1].winner;
      Entry<P> e = new Entry<>();
      e.winner = player.play(matchTree[parent].winner, type);
      e.loser = (player == e.winner) ? matchTree[parent].winner : player;
      matchTree[parent] = e;
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
                matchTree[(player.parent + 1) * 2 - 1].winner;

    // first match
    Entry<P> e = new Entry<>();
    e.winner = player.play(bro, type);
    e.loser = (player == e.winner) ? bro : player;
    matchTree[player.parent] = e;

    int match = player.parent;
    int matchParent;
    while (match > 0) {
      matchParent = (match + 1) / 2;
      Playable<P> leftPlayer = matchTree[matchParent * 2 - 1].winner;
      Playable<P> rightPlayer = (matchParent * 2 >= matchTree.length) ?
        players[2*matchParent+lowext-players.length+1] : matchTree[matchParent * 2].winner;

      // play a match
      e = new Entry<>();
      e.winner = leftPlayer.play(rightPlayer, type);
      e.loser = (leftPlayer == e.winner) ? rightPlayer : leftPlayer;
      matchTree[matchParent-1] = e;
      match = matchParent-1;
    }
  }

  public Playable<P> getWinner() {
    return matchTree[0].winner;
  }

  // from 0 up to n - 1 (where n is the number of players)
  public Playable<P> getLoser(int matchIdx) {
    return matchTree[matchIdx].loser;
  }

  public Playable<P>[] players() {
    return this.players;
  }

  public void change(int i, P x) {
    players[i].value = x;
  }
}
