/*
 * Generated by classgen, version 1.3
 * 23/10/12 11:31
 */
package org.tree;

public class TermoFuncao extends Expressao {

  public Idf i;
  public ExpressaoLista explst;

  public TermoFuncao (Idf i, ExpressaoLista explst) {
    this.i = i;
    if (i != null) i.setParent(this);
    this.explst = explst;
    if (explst != null) explst.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (i != null) i.accept(visitor);
    if (explst != null) explst.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (i != null) i.traverseTopDown(visitor);
    if (explst != null) explst.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (i != null) i.traverseBottomUp(visitor);
    if (explst != null) explst.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("TermoFuncao(\n");
      if (i != null)
        buffer.append(i.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (explst != null)
        buffer.append(explst.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [TermoFuncao]");
    return buffer.toString();
  }
}
