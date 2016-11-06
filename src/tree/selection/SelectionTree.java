package tree.selection;

import tree.selection.Playable.GameType;
import java.lang.reflect.Array;

public interface SelectionTree<P extends Comparable<? super P>> {
  public Playable<P> getWinner();
  public void replay(int i);
  public Playable<P>[] players();
  public void change(int i, P x);

  @SuppressWarnings("unchecked")
  public static <T extends Comparable<? super T>> T[] tournamentSort(T[] in, Class<T> c, GameType type) {
    WinnerTree<T> wt = new WinnerTree<T>(in, type);
    T[] ret = (T[]) Array.newInstance(c, in.length);

    Playable<T> winner = wt.getWinner();
    int i = 0;
    while (winner.value != null) {
      ret[i] = winner.value();
      wt.change(winner.index, null);
      wt.replay(winner.index);
      winner = wt.getWinner();
      i++;
    }
    return ret;
  }
}
