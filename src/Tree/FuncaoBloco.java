/*
 * Generated by classgen, version 1.3
<<<<<<< HEAD
 * 26/10/12 16:39
=======
 * 10/26/12 3:39 PM
>>>>>>> 418173061f0eac84457fe3190233b308f007680a
 */
package org.tree;

import java.util.Vector;
import java.util.Enumeration;

public class FuncaoBloco implements SyntaxNode {

  private Vector items;
  private SyntaxNode parent;

  public FuncaoBloco() {
    items = new Vector();
  }

  public FuncaoBloco(Funcao anItem) {
    this();
    append(anItem);
  }

  public FuncaoBloco append(Funcao anItem) {
    if (anItem == null) return this;
    anItem.setParent(this);
    items.addElement(anItem);
    return this;
  }

  public Enumeration elements() {
    return items.elements();
  }

  public Funcao elementAt(int index) {
    return (Funcao) items.elementAt(index);
  }

  public void setElementAt(Funcao item, int index) {
    item.setParent(this);
    items.setElementAt(item, index);
  }

  public void insertElementAt(Funcao item, int index) {
    item.setParent(this);
    items.insertElementAt(item, index);
  }

  public void removeElementAt(int index) {
    items.removeElementAt(index);
  }

  public int size() { return items.size(); }

  public boolean isEmpty() { return items.isEmpty(); }

  public boolean contains(Funcao item) {
    int size = items.size();
    for (int i = 0; i < size; i++)
      if ( item.equals(items.elementAt(i)) ) return true;
    return false;
  }

  public int indexOf(Funcao item) {
    return items.indexOf(item);
  }

  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("FuncaoBloco (\n");
    int size = items.size();
    for (int i = 0; i < size; i++) {
      buffer.append(((Funcao) items.elementAt(i)).toString("  "+tab));
      buffer.append("\n");
    }
    buffer.append(tab);
    buffer.append(") [FuncaoBloco]");
    return buffer.toString();
  }

  public SyntaxNode getParent() {
    return parent;
  }

  public void setParent(SyntaxNode parent) {
    this.parent = parent;
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    for (int i = 0; i < size(); i++)
      if (elementAt(i) != null) elementAt(i).accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    this.accept(visitor);
    for (int i = 0; i < size(); i++)
      if (elementAt(i) != null) elementAt(i).traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    for (int i = 0; i < size(); i++)
      if (elementAt(i) != null) elementAt(i).traverseBottomUp(visitor);
    this.accept(visitor);
  }

}
