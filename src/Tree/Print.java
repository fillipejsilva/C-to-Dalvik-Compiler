/*
 * Generated by classgen, version 1.3
 * 10/23/12 1:51 PM
 */
package org.tree;

public class Print extends Comando {

  public String str;

  public Print (String str) {
    this.str = str;
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public void childrenAccept(Visitor visitor) {
  }

  public void traverseTopDown(Visitor visitor) {
    accept(visitor);
  }

  public void traverseBottomUp(Visitor visitor) {
    accept(visitor);
  }

  public String toString(String tab) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(tab);
    buffer.append("Print(\n");
    buffer.append("  "+tab+str);
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [Print]");
    return buffer.toString();
  }
}
