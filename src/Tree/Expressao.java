/*
 * Generated by classgen, version 1.3
 * 10/26/12 3:32 PM
 */
package org.tree;

public abstract class Expressao implements SyntaxNode {

  public Tipo tpo;
  public Object val;
  public Integer linha;
  public Integer coluna;
  private SyntaxNode parent;

  public SyntaxNode getParent() {
    return parent;
  }

  public void setParent(SyntaxNode parent) {
    this.parent = parent;
  }

  public abstract void accept(Visitor visitor);
  public abstract void childrenAccept(Visitor visitor);
  public abstract void traverseTopDown(Visitor visitor);
  public abstract void traverseBottomUp(Visitor visitor);
  public String toString() {
    return toString("");
  }

  public abstract String toString(String tab);
}
