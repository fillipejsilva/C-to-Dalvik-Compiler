/*
 * Generated by classgen, version 1.3
 * 10/26/12 3:06 PM
 */
package org.tree;

public interface Visitor {

  public void visit(FuncaoBloco funcaoBloco);
  public void visit(EntradaFuncao entradaFuncao);
  public void visit(DeclaraLista declaraLista);
  public void visit(Comandos comandos);
  public void visit(Elsis elsis);
  public void visit(ExpressaoLista expressaoLista);
  public void visit(Arquivo arquivo);
  public void visit(Funcao funcao);
  public void visit(FuncaoPrincipal funcaoPrincipal);
  public void visit(Entrada entrada);
  public void visit(Programa programa);
  public void visit(Ret ret);
  public void visit(Declara declara);
  public void visit(Vetor vetor);
  public void visit(InList inList);
  public void visit(Idf idf);
  public void visit(Tipo tipo);
  public void visit(CharTipo charTipo);
  public void visit(IntTipo intTipo);
  public void visit(FloatTipo floatTipo);
  public void visit(VoidTipo voidTipo);
  public void visit(Comando comando);
  public void visit(Print print);
  public void visit(PrintId printId);
  public void visit(Scan scan);
  public void visit(ComandoWhile comandoWhile);
  public void visit(ComandoIf comandoIf);
  public void visit(Atribuicao atribuicao);
  public void visit(VetorAtrib vetorAtrib);
  public void visit(ChamadaFuncao chamadaFuncao);
  public void visit(Expressao expressao);
  public void visit(Igualdade igualdade);
  public void visit(Adicao adicao);
  public void visit(Subtracao subtracao);
  public void visit(Divisao divisao);
  public void visit(Multiplicacao multiplicacao);
  public void visit(MaiorIgual maiorIgual);
  public void visit(MenorIgual menorIgual);
  public void visit(Maior maior);
  public void visit(Menor menor);
  public void visit(Diferente diferente);
  public void visit(E e);
  public void visit(Ou ou);
  public void visit(TermoFuncao termoFuncao);
  public void visit(ExpId expId);
  public void visit(TermoParenteses termoParenteses);
  public void visit(TermoNegacao termoNegacao);
  public void visit(ExpChar expChar);
  public void visit(ExpInt expInt);
  public void visit(ExpFloat expFloat);

}
