/*
 * Generated by classgen, version 1.3
 * 23/10/12 11:31
 */
package org.tree;

public class Scan extends Comando {

  public String str;
  public InList inlst;

  public Scan (String str, InList inlst) {
    this.str = str;
    this.inlst = inlst;
    if (inlst != null) inlst.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (inlst != null) inlst.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (inlst != null) inlst.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (inlst != null) inlst.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Scan(\n");
    buffer.append("  "+tab+str);
    buffer.append("\n");
      if (inlst != null)
        buffer.append(inlst.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [Scan]");
    return buffer.toString();
  }
}
