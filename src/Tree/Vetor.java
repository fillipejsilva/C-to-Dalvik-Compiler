/*
 * Generated by classgen, version 1.3
 * 10/26/12 2:56 PM
 */
package org.tree;

public class Vetor implements SyntaxNode {

  private SyntaxNode parent;
  public Integer vt;

  public Vetor (Integer vt) {
    this.vt = vt;
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
    buffer.append("Vetor(\n");
    buffer.append("  "+tab+vt);
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [Vetor]");
    return buffer.toString();
  }
}
