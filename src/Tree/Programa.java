/*
 * Generated by classgen, version 1.3
 * 26/10/12 16:39
 */
package org.tree;

public class Programa implements SyntaxNode {

  private SyntaxNode parent;
  public DeclaraLista declst;
  public Comandos cmds;
  public Ret r;
  public Expressao exp;

  public Programa (DeclaraLista declst, Comandos cmds, Ret r, Expressao exp) {
    this.declst = declst;
    if (declst != null) declst.setParent(this);
    this.cmds = cmds;
    if (cmds != null) cmds.setParent(this);
    this.r = r;
    if (r != null) r.setParent(this);
    this.exp = exp;
    if (exp != null) exp.setParent(this);
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
    if (declst != null) declst.accept(visitor);
    if (cmds != null) cmds.accept(visitor);
    if (r != null) r.accept(visitor);
    if (exp != null) exp.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (declst != null) declst.traverseTopDown(visitor);
    if (cmds != null) cmds.traverseTopDown(visitor);
    if (r != null) r.traverseTopDown(visitor);
    if (exp != null) exp.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (declst != null) declst.traverseBottomUp(visitor);
    if (cmds != null) cmds.traverseBottomUp(visitor);
    if (r != null) r.traverseBottomUp(visitor);
    if (exp != null) exp.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Programa(\n");
      if (declst != null)
        buffer.append(declst.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (cmds != null)
        buffer.append(cmds.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (r != null)
        buffer.append(r.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (exp != null)
        buffer.append(exp.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [Programa]");
    return buffer.toString();
  }
}
