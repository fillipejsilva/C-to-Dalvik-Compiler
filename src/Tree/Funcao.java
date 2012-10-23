/*
 * Generated by classgen, version 1.3
 * 23/10/12 11:31
 */
package org.tree;

public class Funcao implements SyntaxNode {

  private SyntaxNode parent;
  public Tipo tpo;
  public Idf i;
  public EntradaFuncao etrdfnc;
  public Programa prgm;

  public Funcao (Tipo tpo, Idf i, EntradaFuncao etrdfnc, Programa prgm) {
    this.tpo = tpo;
    if (tpo != null) tpo.setParent(this);
    this.i = i;
    if (i != null) i.setParent(this);
    this.etrdfnc = etrdfnc;
    if (etrdfnc != null) etrdfnc.setParent(this);
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
    if (tpo != null) tpo.accept(visitor);
    if (i != null) i.accept(visitor);
    if (etrdfnc != null) etrdfnc.accept(visitor);
    if (prgm != null) prgm.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (tpo != null) tpo.traverseTopDown(visitor);
    if (i != null) i.traverseTopDown(visitor);
    if (etrdfnc != null) etrdfnc.traverseTopDown(visitor);
    if (prgm != null) prgm.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (tpo != null) tpo.traverseBottomUp(visitor);
    if (i != null) i.traverseBottomUp(visitor);
    if (etrdfnc != null) etrdfnc.traverseBottomUp(visitor);
    if (prgm != null) prgm.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString() {
    return toString("");
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Funcao(\n");
      if (tpo != null)
        buffer.append(tpo.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (i != null)
        buffer.append(i.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (etrdfnc != null)
        buffer.append(etrdfnc.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
      if (prgm != null)
        buffer.append(prgm.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [Funcao]");
    return buffer.toString();
  }
}