/*
 * Generated by classgen, version 1.3
 * 10/26/12 3:24 PM
 */
package org.tree;

public class Idf implements SyntaxNode {

  private SyntaxNode parent;
  public String s;
  public Integer linha;
  public Integer coluna;

  public Idf (String s) {
    this.s = s;
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
    buffer.append("Idf(\n");
    buffer.append("  "+tab+s);
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [Idf]");
    return buffer.toString();
  }
}
