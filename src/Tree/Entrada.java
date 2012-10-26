/*
 * Generated by classgen, version 1.3
 * 10/26/12 3:39 PM
 */
package org.tree;

public class Entrada implements SyntaxNode {

  private SyntaxNode parent;
  public Tipo tpo;
  public Idf i;

  public Entrada (Tipo tpo, Idf i) {
    this.tpo = tpo;
    if (tpo != null) tpo.setParent(this);
    this.i = i;
    if (i != null) i.setParent(this);
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
    if (tpo != null) tpo.accept(visitor);
    if (i != null) i.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (tpo != null) tpo.traverseTopDown(visitor);
    if (i != null) i.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (tpo != null) tpo.traverseBottomUp(visitor);
    if (i != null) i.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Entrada(\n");
      if (tpo != null)
        buffer.append(tpo.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (i != null)
        buffer.append(i.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [Entrada]");
    return buffer.toString();
  }
}
