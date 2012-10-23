/*
 * Generated by classgen, version 1.3
 * 10/23/12 1:51 PM
 */
package org.tree;

public class FuncaoPrincipal implements SyntaxNode {

  private SyntaxNode parent;
  public Programa prgm;

  public FuncaoPrincipal (Programa prgm) {
    this.prgm = prgm;
    if (prgm != null) prgm.setParent(this);
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
    if (prgm != null) prgm.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (prgm != null) prgm.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (prgm != null) prgm.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("FuncaoPrincipal(\n");
      if (prgm != null)
        buffer.append(prgm.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [FuncaoPrincipal]");
    return buffer.toString();
  }
}
