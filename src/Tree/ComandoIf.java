/*
 * Generated by classgen, version 1.3
 * 10/26/12 3:24 PM
 */
package org.tree;

public class ComandoIf extends Comando {

  public Expressao exp;
  public Comandos cmds;
  public Elsis elss;

  public ComandoIf (Expressao exp, Comandos cmds, Elsis elss) {
    this.exp = exp;
    if (exp != null) exp.setParent(this);
    this.cmds = cmds;
    if (cmds != null) cmds.setParent(this);
    this.elss = elss;
    if (elss != null) elss.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (exp != null) exp.accept(visitor);
    if (cmds != null) cmds.accept(visitor);
    if (elss != null) elss.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (exp != null) exp.traverseTopDown(visitor);
    if (cmds != null) cmds.traverseTopDown(visitor);
    if (elss != null) elss.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (exp != null) exp.traverseBottomUp(visitor);
    if (cmds != null) cmds.traverseBottomUp(visitor);
    if (elss != null) elss.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("ComandoIf(\n");
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
      if (elss != null)
        buffer.append(elss.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [ComandoIf]");
    return buffer.toString();
  }
}
