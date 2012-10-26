/*
 * Generated by classgen, version 1.3
 * 10/26/12 2:56 PM
 */
package org.tree;

public class PrintId extends Comando {

  public String str;
  public Idf i;

  public PrintId (String str, Idf i) {
    this.str = str;
    this.i = i;
    if (i != null) i.setParent(this);
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
    if (i != null) i.accept(visitor);
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
    if (i != null) i.traverseTopDown(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    if (i != null) i.traverseBottomUp(visitor);
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("PrintId(\n");
    buffer.append("  "+tab+str);
    buffer.append("\n");
      if (i != null)
        buffer.append(i.toString("  "+tab));
      else
        buffer.append(tab+"  null");
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [PrintId]");
    return buffer.toString();
  }
}
