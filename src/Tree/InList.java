/*
 * Generated by classgen, version 1.3
 * 10/26/12 5:26 PM
 */
package org.tree;

public class InList implements SyntaxNode {

  private SyntaxNode parent;
  public Idf i;

  public InList (Idf i) {
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
    if (i != null) i.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (i != null) i.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (i != null) i.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("InList(\n");
      if (i != null)
        buffer.append(i.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [InList]");
    return buffer.toString();
  }
}
