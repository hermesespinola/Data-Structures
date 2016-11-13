package graph.undirected;

import list.LinkedList;
import graph.Vertex;

class ALVertex implements Vertex<ALVertex> {
  int value;
  Integer distance = null;
  ALVertex previous = null;
  LinkedList<ALVertex> adjacentVertices;

  public ALVertex(int value) {
    this.value = value;
    this.adjacentVertices = new LinkedList<>();
  }

  public ALVertex(int value, LinkedList<ALVertex> adjacentVertices) {
    this.value = value;
    this.adjacentVertices = adjacentVertices;
  }

  public void connectVertex(ALVertex other) {
    this.adjacentVertices.add(0, other);
  }

  public void removeAdjacentVertex(ALVertex other) {
    this.adjacentVertices.remove(adjacentVertices.indexOf(other));
  }

  public LinkedList<ALVertex> adjacentVertices() {
    return this.adjacentVertices;
  }

  public ALVertex previous() {
    return this.previous;
  }

  public void setPrevious(ALVertex prev) {
    this.previous = prev;
  }

  public Integer distance() {
    return this.distance;
  }

  public void setDistance(Integer d) {
    this.distance = d;
  }

  public int getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder().append(this.value).append(": [ ");
    for (ALVertex v : adjacentVertices) {
      sb.append(v.value).append(' ');
    }
    return sb.append(']').toString();
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    if (other == this) return true;
    if (!(other instanceof ALVertex)) return false;
    return this.value == ((ALVertex)other).value;
  }

  @Override
  public int hashCode() {
    return ((Integer)this.value).hashCode();
  }
}
