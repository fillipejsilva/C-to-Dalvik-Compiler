/*
 * Generated by classgen, version 1.3
 * 23/10/12 11:31
 */
package org.tree;

import java.util.Vector;
import java.util.Enumeration;

public class Comandos implements SyntaxNode {

  private Vector items;
  private SyntaxNode parent;

  public Comandos() {
    items = new Vector();
  }

  public Comandos(Comando anItem) {
    this();
    append(anItem);
  }

  public Comandos append(Comando anItem) {
    if (anItem == null) return this;
    anItem.setParent(this);
    items.addElement(anItem);
    return this;
  }

  public Enumeration elements() {
    return items.elements();
  }

  public Comando elementAt(int index) {
    return (Comando) items.elementAt(index);
  }

  public void setElementAt(Comando item, int index) {
    item.setParent(this);
    items.setElementAt(item, index);
  }

  public void insertElementAt(Comando item, int index) {
    item.setParent(this);
    items.insertElementAt(item, index);
  }

  public void removeElementAt(int index) {
    items.removeElementAt(index);
  }

  public int size() { return items.size(); }

  public boolean isEmpty() { return items.isEmpty(); }

  public boolean contains(Comando item) {
    int size = items.size();
    for (int i = 0; i < size; i++)
      if ( item.equals(items.elementAt(i)) ) return true;
    return false;
  }

  public int indexOf(Comando item) {
    return items.indexOf(item);
  }

  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Comandos (\n");
    int size = items.size();
    for (int i = 0; i < size; i++) {
      buffer.append(((Comando) items.elementAt(i)).toString("  "+tab));
      buffer.append("\n");
    }
    buffer.append(tab);
    buffer.append(") [Comandos]");
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
