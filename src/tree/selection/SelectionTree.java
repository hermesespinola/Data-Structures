package tree.selection;

import java.lang.reflect.Array;

public interface SelectionTree<P extends Comparable<? super P>> {
  public Playable<P> getWinner();
  public void replay(int i);
  public Playable<P>[] players();
  public void change(int i, P x);

  public static enum Type {
    Max(1), Min(-1);

    private final int value;
    private Type(int value) {
      this.value = value;
    }
    public int value() {
      return value;
    }
  }

  public static <T extends Comparable<? super T>> void tournamentSort(T[] in, T[] out, Type type) {
    WinnerTree<T> wt = new WinnerTree<T>(in, type);

    Playable<T> winner = wt.getWinner();
    int i = 0;
    while (winner.value != null) {
      out[i] = winner.value();
      wt.change(winner.index, null);
      wt.replay(winner.index);
      winner = wt.getWinner();
      i++;
    }
  }
}
