/*
 * Generated by classgen, version 1.3
 * 10/26/12 3:06 PM
 */

package org.tree;

public interface SyntaxNode {

  public void accept(Visitor visitor);

  public void childrenAccept(Visitor visitor);
  public void traverseBottomUp(Visitor visitor);
  public void traverseTopDown(Visitor visitor);

  public SyntaxNode getParent();
  public void setParent(SyntaxNode parent);
}
