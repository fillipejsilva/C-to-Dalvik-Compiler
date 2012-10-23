/*
 * Generated by classgen, version 1.3
 * 23/10/12 13:40
 */
package org.tree;

public class VetorAtrib extends Comando {

  public Idf i;
  public Expressao exp1;
  public Expressao exp2;

  public VetorAtrib (Idf i, Expressao exp1, Expressao exp2) {
    this.i = i;
    if (i != null) i.setParent(this);
    this.exp1 = exp1;
    if (exp1 != null) exp1.setParent(this);
    this.exp2 = exp2;
    if (exp2 != null) exp2.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (i != null) i.accept(visitor);
    if (exp1 != null) exp1.accept(visitor);
    if (exp2 != null) exp2.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (i != null) i.traverseTopDown(visitor);
    if (exp1 != null) exp1.traverseTopDown(visitor);
    if (exp2 != null) exp2.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (i != null) i.traverseBottomUp(visitor);
    if (exp1 != null) exp1.traverseBottomUp(visitor);
    if (exp2 != null) exp2.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("VetorAtrib(\n");
      if (i != null)
        buffer.append(i.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (exp1 != null)
        buffer.append(exp1.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (exp2 != null)
        buffer.append(exp2.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [VetorAtrib]");
    return buffer.toString();
  }
}
