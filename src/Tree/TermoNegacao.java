/*
 * Generated by classgen, version 1.3
<<<<<<< HEAD
 * 26/10/12 16:39
=======
 * 10/26/12 3:39 PM
>>>>>>> 418173061f0eac84457fe3190233b308f007680a
 */
package org.tree;

public class TermoNegacao extends Expressao {

  public Expressao exp;

  public TermoNegacao (Expressao exp) {
    this.exp = exp;
    if (exp != null) exp.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (exp != null) exp.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (exp != null) exp.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (exp != null) exp.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("TermoNegacao(\n");
      if (exp != null)
        buffer.append(exp.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [TermoNegacao]");
    return buffer.toString();
  }
}
