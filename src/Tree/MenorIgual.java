/*
 * Generated by classgen, version 1.3
<<<<<<< HEAD
 * 26/10/12 16:39
=======
 * 10/26/12 3:39 PM
>>>>>>> 418173061f0eac84457fe3190233b308f007680a
 */
package org.tree;

public class MenorIgual extends Expressao {

  public Expressao exp1;
  public Expressao exp2;

  public MenorIgual (Expressao exp1, Expressao exp2) {
    this.exp1 = exp1;
    if (exp1 != null) exp1.setParent(this);
    this.exp2 = exp2;
    if (exp2 != null) exp2.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (exp1 != null) exp1.accept(visitor);
    if (exp2 != null) exp2.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (exp1 != null) exp1.traverseTopDown(visitor);
    if (exp2 != null) exp2.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (exp1 != null) exp1.traverseBottomUp(visitor);
    if (exp2 != null) exp2.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("MenorIgual(\n");
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
    buffer.append(") [MenorIgual]");
    return buffer.toString();
  }
}
