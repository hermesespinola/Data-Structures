package tree.selection;

public interface SelectionTree<P extends Comparable<? super P>> {
  public Playable<P> getWinner();
  public void replay(int i);
  public Playable<P>[] players();
  public void change(int i, P x);
}
