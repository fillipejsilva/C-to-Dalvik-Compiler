/*
 * Generated by classgen, version 1.3
 * 10/26/12 3:24 PM
 */
package org.tree;

public class VoidTipo extends Tipo {


  public VoidTipo () {
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
    buffer.append("VoidTipo(\n");
    buffer.append(tab);
    buffer.append(") [VoidTipo]");
    return buffer.toString();
  }
}
