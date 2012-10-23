package org.tree;

import java.util.Vector;

public class ScopeChecker extends VisitorAdaptor {

  private SymTable tabelaDeSimbolos = new SymTable();

  @Override
  public void visit(Arquivo arquivo) {}

  @Override
  public void visit(Funcao funcao) {
    StFuncao func = new StFuncao();
    EntradaFuncao entradaLista;
    DeclaraLista declaraLista;

    func.tipo = funcao.tpo;
    func.id = funcao.i.s;

    entradaLista = funcao.etrdfnc;
    for(int i = 0; i < entradaLista.size(); i++) {
      
      if(!func.params.containsKey(entradaLista.elementAt(i).i.s)) {
        func.params.put(
           entradaLista.elementAt(i).i.s
          ,new StVariavel(entradaLista.elementAt(i)));
      } else {
        System.out.println("Erro: Parametros de mesmo nome encontrados.");
        System.exit(1);
      }  
    }

    declaraLista = funcao.prgm.declst;
    for(int i = 0; i < declaraLista.size(); i++) {

      if(!func.params.containsKey(declaraLista.elementAt(i).i.s) &&
         !func.locais.containsKey(declaraLista.elementAt(i).i.s) ) {

        func.locais.put(
           declaraLista.elementAt(i).i.s
          ,new StVariavel(declaraLista.elementAt(i)));
      } else {
        System.out.println("Erro: Redeclaração de variáveis no mesmo escopo. ("
          +declaraLista.elementAt(i).i.s+")");
        System.exit(1);
      }
    }

    if(!tabelaDeSimbolos.funcoes.containsKey(func.id)) {
      tabelaDeSimbolos.funcoes.put(func.id, func);
    } else {
      System.out.println("Erro: Redeclaração de função.");
      System.exit(1);
    }
  }

  @Override
  public void visit(FuncaoPrincipal funcaoPrincipal) {
    StFuncao func = new StFuncao();
    DeclaraLista declaraLista;

    func.tipo = new IntTipo();
    func.id = "main";

    declaraLista = funcaoPrincipal.prgm.declst;
    for(int i = 0; i < declaraLista.size(); i++) {

      if(!func.locais.containsKey(declaraLista.elementAt(i).i.s) ) {

        func.locais.put(
           declaraLista.elementAt(i).i.s
          ,new StVariavel(declaraLista.elementAt(i)));
      } else {
        System.out.println("Erro: Redeclaração de variáveis no mesmo escopo. ("
          +declaraLista.elementAt(i).i.s+")");
        System.exit(1);
      }
    }

    if(!tabelaDeSimbolos.funcoes.containsKey(func.id)) {
      tabelaDeSimbolos.funcoes.put(func.id, func);
    } else {
      System.out.println("Erro: Redeclaração de função.");
      System.exit(1);
    }
  }

  public SymTable getSymTable() {
    return this.tabelaDeSimbolos;
  }

}
