/*
 * Generated by classgen, version 1.3
 * 10/26/12 3:24 PM
 */
package org.tree;

public class ComandoWhile extends Comando {

  public Expressao exp;
  public Comandos cmds;

  public ComandoWhile (Expressao exp, Comandos cmds) {
    this.exp = exp;
    if (exp != null) exp.setParent(this);
    this.cmds = cmds;
    if (cmds != null) cmds.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (exp != null) exp.accept(visitor);
    if (cmds != null) cmds.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (exp != null) exp.traverseTopDown(visitor);
    if (cmds != null) cmds.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (exp != null) exp.traverseBottomUp(visitor);
    if (cmds != null) cmds.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("ComandoWhile(\n");
      if (exp != null)
        buffer.append(exp.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (cmds != null)
        buffer.append(cmds.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [ComandoWhile]");
    return buffer.toString();
  }
}
