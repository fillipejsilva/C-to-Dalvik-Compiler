/*
 * Generated by classgen, version 1.3
 * 10/26/12 3:32 PM
 */
package org.tree;

public class Ret implements SyntaxNode {

  private SyntaxNode parent;
  public String r;

  public Ret (String r) {
    this.r = r;
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
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    accept(visitor);
  }

  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Ret(\n");
    buffer.append("  "+tab+r);
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [Ret]");
    return buffer.toString();
  }
}
