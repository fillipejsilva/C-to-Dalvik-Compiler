/*
 * Generated by classgen, version 1.3
 * 26/10/12 16:39
 */
package org.tree;

public class ExpInt extends Expressao {

  public Integer it;

  public ExpInt (Integer it) {
    this.it = it;
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
    buffer.append("ExpInt(\n");
    buffer.append("  "+tab+it);
    buffer.append("\n");
    buffer.append(tab);
    buffer.append(") [ExpInt]");
    return buffer.toString();
  }
}
