/*
 * Generated by classgen, version 1.3
 * 26/10/12 17:14
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
