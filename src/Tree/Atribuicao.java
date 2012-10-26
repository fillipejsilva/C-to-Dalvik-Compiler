/*
 * Generated by classgen, version 1.3
 * 26/10/12 16:39
 */
package org.tree;

public class Atribuicao extends Comando {

  public Idf i;
  public Expressao exp;

  public Atribuicao (Idf i, Expressao exp) {
    this.i = i;
    if (i != null) i.setParent(this);
    this.exp = exp;
    if (exp != null) exp.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (i != null) i.accept(visitor);
    if (exp != null) exp.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (i != null) i.traverseTopDown(visitor);
    if (exp != null) exp.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (i != null) i.traverseBottomUp(visitor);
    if (exp != null) exp.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Atribuicao(\n");
      if (i != null)
        buffer.append(i.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (exp != null)
        buffer.append(exp.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [Atribuicao]");
    return buffer.toString();
  }
}
