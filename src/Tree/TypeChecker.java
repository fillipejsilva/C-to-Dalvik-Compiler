package org.tree;

import java.util.Enumeration;
import java.lang.ArrayIndexOutOfBoundsException;

public class TypeChecker extends VisitorAdaptor{
  
  static public SymTable tabela;
  static public StFuncao currFuncao;
  
  //Set da tabela de simbolos
  public void setTabela(SymTable tab){
    this.tabela = tab;
  }

  //Multiplicacao
  @Override
  public void visit(Multiplicacao multiplicacao) {
    //Se analisa a expressão a esquerda e direita
    multiplicacao.exp1.accept(this);
    multiplicacao.exp2.accept(this);
    
    //Checa-se a possibilidade de realizar a operação entre os tipos dados
    if (multiplicacao.exp1.tpo instanceof IntTipo && multiplicacao.exp2.tpo instanceof IntTipo){
      multiplicacao.tpo = new IntTipo();
    }
    else if (multiplicacao.exp1.tpo instanceof IntTipo && multiplicacao.exp2.tpo instanceof FloatTipo){
      multiplicacao.tpo = new FloatTipo();
    }
    else if (multiplicacao.exp1.tpo instanceof FloatTipo && multiplicacao.exp2.tpo instanceof IntTipo){
      multiplicacao.tpo = new FloatTipo();
    }
    else if (multiplicacao.exp1.tpo instanceof FloatTipo && multiplicacao.exp2.tpo instanceof FloatTipo){
      multiplicacao.tpo = new FloatTipo();
    }
    else{
      System.out.println("\n[l" + multiplicacao.linha + "|c" + multiplicacao.coluna +"]:" +
        " Erro semantico, tipos inválidos para multiplicação (válidos: int e float)\n");
      System.exit(1);
    }
  }

  //Divisao
  @Override
  public void visit(Divisao divisao) {
    //Se analisa a expressão a esquerda e direita
    divisao.exp1.accept(this);
    divisao.exp2.accept(this);
    
    //Checa-se a possibilidade de realizar a operação entre os tipos dados
    if (divisao.exp1.tpo instanceof IntTipo && divisao.exp2.tpo instanceof IntTipo){
      divisao.tpo = new IntTipo();
    }
    else if (divisao.exp1.tpo instanceof IntTipo && divisao.exp2.tpo instanceof FloatTipo){
      divisao.tpo = new FloatTipo();
    }
    else if (divisao.exp1.tpo instanceof FloatTipo && divisao.exp2.tpo instanceof IntTipo){
      divisao.tpo = new FloatTipo();
    }
    else if (divisao.exp1.tpo instanceof FloatTipo && divisao.exp2.tpo instanceof FloatTipo){
      divisao.tpo = new FloatTipo();
    }
    else{
      System.out.println("\n[l" + divisao.linha + "|c" + divisao.coluna +"]:" +
        " Erro semantico, tipos inválidos para divisão (válidos: int e float)\n");
      System.exit(1);
    }
  }

  //Adicao
  @Override
  public void visit(Adicao adicao) {
    //Se analisa a expressão a esquerda e direita
    adicao.exp1.accept(this);
    adicao.exp2.accept(this);
    
    //Checa-se a possibilidade de realizar a operação com os tipos dados
    if (adicao.exp1.tpo instanceof IntTipo && adicao.exp2.tpo instanceof IntTipo){
      adicao.tpo = new IntTipo();
    }
    else if (adicao.exp1.tpo instanceof IntTipo && adicao.exp2.tpo instanceof FloatTipo){
      adicao.tpo = new FloatTipo();
    }
    else if (adicao.exp1.tpo instanceof FloatTipo && adicao.exp2.tpo instanceof IntTipo){
      adicao.tpo = new FloatTipo();
    }
    else if (adicao.exp1.tpo instanceof FloatTipo && adicao.exp2.tpo instanceof FloatTipo){
      adicao.tpo = new FloatTipo();
    }
    else{
      System.out.println("\n[l" + adicao.linha + "|c" + adicao.coluna +"]:" +
        " Erro semantico, tipos inválidos para adição (válidos: int e float)\n");
      System.exit(1);
    }
  }

  //Subtração
  @Override
  public void visit(Subtracao subtracao) {
    //Se analisa a expressão a esquerda e direita
    subtracao.exp1.accept(this);
    subtracao.exp2.accept(this);
    
    //Checa-se a possibilidade de realizar a operação com os tipos dados
    if (subtracao.exp1.tpo instanceof IntTipo && subtracao.exp2.tpo instanceof IntTipo){
      subtracao.tpo = new IntTipo();
    }
    else if (subtracao.exp1.tpo instanceof IntTipo && subtracao.exp2.tpo instanceof FloatTipo){
      subtracao.tpo = new FloatTipo();
    }
    else if (subtracao.exp1.tpo instanceof FloatTipo && subtracao.exp2.tpo instanceof IntTipo){
      subtracao.tpo = new FloatTipo();
    }
    else if (subtracao.exp1.tpo instanceof FloatTipo && subtracao.exp2.tpo instanceof FloatTipo){
      subtracao.tpo = new FloatTipo();
    }
    else{
      System.out.println("\n[l" + subtracao.linha + "|c" + subtracao.coluna +"]:" +
        " Erro semantico, tipos inválidos para subtração (válidos: int e float)\n");
      System.exit(1);
    }
  }

  //maiorIgual
  @Override
  public void visit(MaiorIgual maiorIgual) {
    //Se analisa a expressão a esquerda e direita
    maiorIgual.exp1.accept(this);
    maiorIgual.exp2.accept(this);
    
    //Checa-se a possibilidade de realizar a comparação entre os tipos dados
    if (maiorIgual.exp1.tpo instanceof IntTipo && maiorIgual.exp2.tpo instanceof IntTipo){
      maiorIgual.tpo = new IntTipo();
    }
    else if (maiorIgual.exp1.tpo instanceof IntTipo && maiorIgual.exp2.tpo instanceof FloatTipo){
      maiorIgual.tpo = new IntTipo();
    }
    else if (maiorIgual.exp1.tpo instanceof FloatTipo && maiorIgual.exp2.tpo instanceof IntTipo){
      maiorIgual.tpo = new IntTipo();
    }
    else if (maiorIgual.exp1.tpo instanceof FloatTipo && maiorIgual.exp2.tpo instanceof FloatTipo){
      maiorIgual.tpo = new IntTipo();
    }
    else if (maiorIgual.exp1.tpo instanceof IntTipo && maiorIgual.exp2.tpo instanceof CharTipo){
      maiorIgual.tpo = new IntTipo();
    }
    else if (maiorIgual.exp1.tpo instanceof CharTipo && maiorIgual.exp2.tpo instanceof IntTipo){
      maiorIgual.tpo = new IntTipo();
    }
    else if (maiorIgual.exp1.tpo instanceof FloatTipo && maiorIgual.exp2.tpo instanceof CharTipo){
      maiorIgual.tpo = new IntTipo();
    }
    else if (maiorIgual.exp1.tpo instanceof CharTipo && maiorIgual.exp2.tpo instanceof FloatTipo){
      maiorIgual.tpo = new IntTipo();
    }    
    else{
      System.out.println("\n[l" + maiorIgual.linha + "|c" + maiorIgual.coluna +"]:" +
        " Erro semantico, não há operações sobre o tipo void\n");
      System.exit(1);
    }
  }

  @Override   
  public void visit(MenorIgual menorIgual) {
    //Se analisa a expressão a esquerda e direita
    menorIgual.exp1.accept(this);
    menorIgual.exp2.accept(this);
    
    //Checa-se a possibilidade de realizar a comparação entre os tipos dados
    if (menorIgual.exp1.tpo instanceof IntTipo && menorIgual.exp2.tpo instanceof IntTipo){
      menorIgual.tpo = new IntTipo();
    }
    else if (menorIgual.exp1.tpo instanceof IntTipo && menorIgual.exp2.tpo instanceof FloatTipo){
      menorIgual.tpo = new IntTipo();
    }
    else if (menorIgual.exp1.tpo instanceof FloatTipo && menorIgual.exp2.tpo instanceof IntTipo){
      menorIgual.tpo = new IntTipo();
    }
    else if (menorIgual.exp1.tpo instanceof FloatTipo && menorIgual.exp2.tpo instanceof FloatTipo){
      menorIgual.tpo = new IntTipo();
    }
    else if (menorIgual.exp1.tpo instanceof IntTipo && menorIgual.exp2.tpo instanceof CharTipo){
      menorIgual.tpo = new IntTipo();
    }
    else if (menorIgual.exp1.tpo instanceof CharTipo && menorIgual.exp2.tpo instanceof IntTipo){
      menorIgual.tpo = new IntTipo();
    }
    else if (menorIgual.exp1.tpo instanceof FloatTipo && menorIgual.exp2.tpo instanceof CharTipo){
      menorIgual.tpo = new IntTipo();
    }
    else if (menorIgual.exp1.tpo instanceof CharTipo && menorIgual.exp2.tpo instanceof FloatTipo){
      menorIgual.tpo = new IntTipo();
    }    
    else{
      System.out.println("\n[l" + menorIgual.linha + "|c" + menorIgual.coluna +"]:" +
        " Erro semantico, não há operações sobre o tipo void\n");
      System.exit(1);
    }
  }
  
  //Maior
  @Override
  public void visit(Maior maior) {
    //Se analisa a expressão a esquerda e direita
    maior.exp1.accept(this);
    maior.exp2.accept(this);
    
    //Checa-se a possibilidade de realizar a comparação entre os tipos dados
    if (maior.exp1.tpo instanceof IntTipo && maior.exp2.tpo instanceof IntTipo){
      maior.tpo = new IntTipo();
    }
    else if (maior.exp1.tpo instanceof IntTipo && maior.exp2.tpo instanceof FloatTipo){
      maior.tpo = new IntTipo();
    }
    else if (maior.exp1.tpo instanceof FloatTipo && maior.exp2.tpo instanceof IntTipo){
      maior.tpo = new IntTipo();
    }
    else if (maior.exp1.tpo instanceof FloatTipo && maior.exp2.tpo instanceof FloatTipo){
      maior.tpo = new IntTipo();
    }
    else if (maior.exp1.tpo instanceof IntTipo && maior.exp2.tpo instanceof CharTipo){
      maior.tpo = new IntTipo();
    }
    else if (maior.exp1.tpo instanceof CharTipo && maior.exp2.tpo instanceof IntTipo){
      maior.tpo = new IntTipo();
    }
    else if (maior.exp1.tpo instanceof FloatTipo && maior.exp2.tpo instanceof CharTipo){
      maior.tpo = new IntTipo();
    }
    else if (maior.exp1.tpo instanceof CharTipo && maior.exp2.tpo instanceof FloatTipo){
      maior.tpo = new IntTipo();
    }    
    else{
      System.out.println("\n[l" + maior.linha + "|c" + maior.coluna +"]:" +
        " Erro semantico, não há operações sobre o tipo void\n");
      System.exit(1);
    }
  }
 
  //menor
  @Override 
  public void visit(Menor menor) {
    //Se analisa a expressão a esquerda e direita
    menor.exp1.accept(this);
    menor.exp2.accept(this);
    
    //Checa-se a possibilidade de realizar a comparação entre os tipos dados
    if (menor.exp1.tpo instanceof IntTipo && menor.exp2.tpo instanceof IntTipo){
      menor.tpo = new IntTipo();
    }
    else if (menor.exp1.tpo instanceof IntTipo && menor.exp2.tpo instanceof FloatTipo){
      menor.tpo = new IntTipo();
    }
    else if (menor.exp1.tpo instanceof FloatTipo && menor.exp2.tpo instanceof IntTipo){
      menor.tpo = new IntTipo();
    }
    else if (menor.exp1.tpo instanceof FloatTipo && menor.exp2.tpo instanceof FloatTipo){
      menor.tpo = new IntTipo();
    }
    else if (menor.exp1.tpo instanceof IntTipo && menor.exp2.tpo instanceof CharTipo){
      menor.tpo = new IntTipo();
    }
    else if (menor.exp1.tpo instanceof CharTipo && menor.exp2.tpo instanceof IntTipo){
      menor.tpo = new IntTipo();
    }
    else if (menor.exp1.tpo instanceof FloatTipo && menor.exp2.tpo instanceof CharTipo){
      menor.tpo = new IntTipo();
    }
    else if (menor.exp1.tpo instanceof CharTipo && menor.exp2.tpo instanceof FloatTipo){
      menor.tpo = new IntTipo();
    }    
    else{
      System.out.println("\n[l" + menor.linha + "|c" + menor.coluna +"]:" +
        " Erro semantico, não há operações sobre o tipo void\n");
      System.exit(1);
    }   
  }
  
  //Diferente
  @Override
  public void visit(Diferente diferente) {
    //Se analisa a expressão a esquerda e direita
    diferente.exp1.accept(this);
    diferente.exp2.accept(this);
    
    //Checa-se a possiblidade de realizar a comparação entre os tipos dados
    if (diferente.exp1.tpo instanceof IntTipo && diferente.exp2.tpo instanceof IntTipo){
      diferente.tpo = new IntTipo();
    }
    else if (diferente.exp1.tpo instanceof IntTipo && diferente.exp2.tpo instanceof FloatTipo){
      diferente.tpo = new IntTipo();
    }
    else if (diferente.exp1.tpo instanceof FloatTipo && diferente.exp2.tpo instanceof IntTipo){
      diferente.tpo = new IntTipo();
    }
    else if (diferente.exp1.tpo instanceof FloatTipo && diferente.exp2.tpo instanceof FloatTipo){
      diferente.tpo = new IntTipo();
    }
    else if (diferente.exp1.tpo instanceof IntTipo && diferente.exp2.tpo instanceof CharTipo){
      diferente.tpo = new IntTipo();
    }
    else if (diferente.exp1.tpo instanceof CharTipo && diferente.exp2.tpo instanceof IntTipo){
      diferente.tpo = new IntTipo();
    }
    else if (diferente.exp1.tpo instanceof FloatTipo && diferente.exp2.tpo instanceof CharTipo){
      diferente.tpo = new IntTipo();
    }
    else if (diferente.exp1.tpo instanceof CharTipo && diferente.exp2.tpo instanceof FloatTipo){
      diferente.tpo = new IntTipo();
    }    
    else{
      System.out.println("\n[l" + diferente.linha + "|c" + diferente.coluna +"]:" +
        " Erro semantico, não há operações sobre o tipo void\n");
      System.exit(1);
    }       
  }

  //Igualdade
  @Override
  public void visit(Igualdade igualdade) {
    //Se analisa a expressão a esquerda e direita
    igualdade.exp1.accept(this);
    igualdade.exp2.accept(this);
    
    //Checa a possibilidade de realizar a comparação entre os tipos dados
    if (igualdade.exp1.tpo instanceof IntTipo && igualdade.exp2.tpo instanceof IntTipo){
      igualdade.tpo = new IntTipo();
    }
    else if (igualdade.exp1.tpo instanceof IntTipo && igualdade.exp2.tpo instanceof FloatTipo){
      igualdade.tpo = new IntTipo();
    }
    else if (igualdade.exp1.tpo instanceof FloatTipo && igualdade.exp2.tpo instanceof IntTipo){
      igualdade.tpo = new IntTipo();
    }
    else if (igualdade.exp1.tpo instanceof FloatTipo && igualdade.exp2.tpo instanceof FloatTipo){
      igualdade.tpo = new IntTipo();
    }
    else if (igualdade.exp1.tpo instanceof IntTipo && igualdade.exp2.tpo instanceof CharTipo){
      igualdade.tpo = new IntTipo();
    }
    else if (igualdade.exp1.tpo instanceof CharTipo && igualdade.exp2.tpo instanceof IntTipo){
      igualdade.tpo = new IntTipo();
    }
    else if (igualdade.exp1.tpo instanceof FloatTipo && igualdade.exp2.tpo instanceof CharTipo){
      igualdade.tpo = new IntTipo();
    }
    else if (igualdade.exp1.tpo instanceof CharTipo && igualdade.exp2.tpo instanceof FloatTipo){
      igualdade.tpo = new IntTipo();
    }    
    else{
      System.out.println("\n[l" + igualdade.linha + "|c" + igualdade.coluna +"]:" +
        " Erro semantico, não há operações sobre o tipo void\n");
      System.exit(1);
    }   
  }
 
  //E 
  @Override
  public void visit(E e) {
    //Se analisa a expressão a esquerda e direita
    e.exp1.accept(this);
    e.exp2.accept(this);
    
    //Checa-se a possibilidade de comparação entre os tipos dados
    if (e.exp1.tpo instanceof IntTipo && e.exp2.tpo instanceof IntTipo){
      e.tpo = new IntTipo();
    }
    else{
      System.out.println("\n[l" + e.linha + "|c" + e.coluna +"]:" +
        " Erro semantico, operação && somente é possível entre inteiros");
      System.exit(1);
    }
    e.tpo = new IntTipo();
  }
  
  //Ou
  @Override   
  public void visit(Ou ou) {
    //Se analisa a expressão a esquerda e direita
    ou.exp1.accept(this);
    ou.exp2.accept(this);
    
    //Checa-se a possibilidade de comparação entre os tipos dados
    if (ou.exp1.tpo instanceof IntTipo && ou.exp2.tpo instanceof IntTipo){
      ou.tpo = new IntTipo();
    }
    else{
      System.out.println("\n[l" + ou.linha + "|c" + ou.coluna +"]:" +
        " Erro semantico, operação || somente é possível entre inteiros");
      System.exit(1);
    }
    ou.tpo = new IntTipo();
  }
  
  //TermoFuncao
  @Override
  public void visit(TermoFuncao termoFuncao) {
    StFuncao CalledFunc = tabela.funcoes.get(termoFuncao.i.s);
    int i = 0;
    Expressao exp;
    Enumeration e = termoFuncao.explst.elements();
    while(e.hasMoreElements()){
      exp = (Expressao)e.nextElement();
      exp.accept(this);
    }
    try{
      //Caso exista um numero insuficiente de parametros para a funcao chamada o try se faz necessario
      if( CalledFunc.params.isEmpty() ){
      	throw new ArrayIndexOutOfBoundsException();
      }
      for (String key: CalledFunc.params.keySet())
      {
        if(!tabela.compareTypes( CalledFunc.params.get(key).tpo, termoFuncao.explst.elementAt(i).tpo )){
          System.out.println("\n[l" + termoFuncao.linha + "|c" + termoFuncao.coluna +"]:" +
            " Chamada da função "+termoFuncao.i.s+" com expressões de tipo(s) diferente(s) do(s) requisitado(s)\n");
          System.exit(1);
        }
        i++;
      }
      
    }catch(ArrayIndexOutOfBoundsException err){
      System.out.println("\n[l" + termoFuncao.linha + "|c" + termoFuncao.coluna +"]:" +
        " Numero de argumentos da funcao "+termoFuncao.i.s+" incompativel!\n");
      System.exit(1);
    }
    termoFuncao.tpo = CalledFunc.tipo;
  }

  //ExpId   
  @Override
  public void visit(ExpId expId) {
    if(currFuncao.params.containsKey(expId.i.s)){
      expId.tpo = currFuncao.params.get(expId.i.s).tpo;
    }
    else if(currFuncao.locais.containsKey(expId.i.s)){
      expId.tpo = currFuncao.locais.get(expId.i.s).tpo;
    }
    else{
      System.out.println("\n[l" + expId.linha + "|c" + expId.coluna +"]:" +
        " Variavel "+expId.i.s+" é inexistente dentro do escopo\n");
      System.exit(1);
    }
    
  }
  
    //UsoVetor  
  @Override
  public void visit(UsoVetor usoVetor) {
    if(currFuncao.params.containsKey(usoVetor.i.s)){
      usoVetor.tpo = currFuncao.params.get(usoVetor.i.s).tpo;
    }
    else if(currFuncao.locais.containsKey(usoVetor.i.s)){
      usoVetor.tpo = currFuncao.locais.get(usoVetor.i.s).tpo;
    }
    else{
      System.out.println("\n[l" + usoVetor.linha + "|c" + usoVetor.coluna +"]:" +
        " Variavel "+usoVetor.i.s+" é inexistente dentro do escopo\n");
      System.exit(1);
    }
   
  }

  //TermoParenteses   
  @Override
  public void visit(TermoParenteses termoParenteses) {
    termoParenteses.exp.accept(this);
    termoParenteses.tpo = termoParenteses.exp.tpo;
  }

  //TermoNegacao  
  @Override
  public void visit(TermoNegacao termoNegacao) {
    termoNegacao.exp.accept(this);
    if(!(termoNegacao.exp.tpo instanceof IntTipo)){
      System.out.println("\n[l" + termoNegacao.linha + "|c" + termoNegacao.coluna +"]:" +
        " Somente expressões que produzem um resultado booleano podem ser negadas\n");
      System.exit(1);
    }
    termoNegacao.tpo = new IntTipo(); 
  }

  //ExpChar 
  @Override
  public void visit(ExpChar expChar) {
    expChar.tpo = new CharTipo();
  }
  
  //ExpInt
  @Override
  public void visit(ExpInt expInt) {
    expInt.tpo = new IntTipo();
  }

  //ExpFloat  
  @Override
  public void visit(ExpFloat expFloat) {
    expFloat.tpo = new FloatTipo();
  }
  
  //PrintId 
  @Override
  public void visit(PrintId printId) {

    char[] str = null;
    int count = 0;
      
    if(currFuncao.params.containsKey(printId.i.s)) { //Procura se a variável a ser impressa está entre os parametros

      str = printId.str.toCharArray();

      for(int i=0; i<str.length;i++){

        if(str[i] == '%' && str[i+1] == 'd'){ //Verificando se é um inteiro a se imprimir
          count++;

          if(count > 1){ //Verifica se tentou-se imprimir mais de uma variável
            System.out.println("\n[l" + printId.linha + "|c" + printId.coluna +"]:" +
              " Somente é permitido imprimir uma variável de cada vez!");
            System.exit(1);
          }     

          if(!tabela.compareTypes(new IntTipo(),currFuncao.params.get(printId.i.s).tpo)){ //Verificando se o id é Inteiro
            System.out.println("\n[l" + printId.linha + "|c" + printId.coluna +"]:" +
              " Não é permitido imprimir um tipo diferente do que foi fornecido como parâmetro.");
            System.exit(1);
          }               

        }
        else if(str[i] == '%' && str[i+1] == 'c'){ //Verificando se é um char a se imprimir
          count++;

          if(count > 1){ //Verifica se tentou-se impirmir mais de uma variável
            System.out.println("\n[l" + printId.linha + "|c" + printId.coluna +"]:" +
              " Somente é permitido imprimir uma variável de cada vez!");
            System.exit(1);
          }

          if(!tabela.compareTypes(new CharTipo(),currFuncao.params.get(printId.i.s).tpo)){ //Verificando se o id é Char
            System.out.println("\n[l" + printId.linha + "|c" + printId.coluna +"]:" +
              " Não é permitido imprimir um tipo diferente do que foi fornecido como parâmetro.");
            System.exit(1);
          }

        }
        else if(str[i] == '%' && str[i+1] == 'f'){ //Verificando se é um float a se imprimir
          count++;

          if(count > 1){ //Verifica se tentou-se imprimir mais de uma variável
            System.out.println("\n[l" + printId.linha + "|c" + printId.coluna +"]:" +
              " Somente é permitido imprimir uma variável de cada vez!");
            System.exit(1);
          }

          if(!tabela.compareTypes(new FloatTipo(),currFuncao.params.get(printId.i.s).tpo)){ //Verificando se o id é Float
            System.out.println("\n[l" + printId.linha + "|c" + printId.coluna +"]:" +
              " Não é permitido imprimir um tipo diferente do que foi fornecido como parâmetro.");
            System.exit(1);
          }

        }
      }

    }
    else if(currFuncao.locais.containsKey(printId.i.s)) { //Repete o procedimento para os locais
  
      str = printId.str.toCharArray();

      for(int i=0; i<str.length;i++){

        if(str[i] == '%' && str[i+1] == 'd'){ //verificando para Inteiro
          count++;

          if(count > 1){ //verificando se ocorre mais que uma vez
            System.out.println("\n[l" + printId.linha + "|c" + printId.coluna +"]:" +
              " Somente é permitido imprimir uma variável de cada vez!");
            System.exit(1);
          }     

          if(!tabela.compareTypes(new IntTipo(),currFuncao.locais.get(printId.i.s).tpo)){ //verificando se é Inteiro
            System.out.println("\n[l" + printId.linha + "|c" + printId.coluna +"]:" +
              " Não é permitido imprimir um tipo diferente do que foi fornecido como parâmetro.");
            System.exit(1);
          }               

        }
        else if(str[i] == '%' && str[i+1] == 'c'){ //verificando para Char
          count++;

          if(count > 1){ //verificando se ocorre mais que uma vez
            System.out.println("\n[l" + printId.linha + "|c" + printId.coluna +"]:" +
              " Somente é permitido imprimir uma variável de cada vez!");
            System.exit(1);
          }

          if(!tabela.compareTypes(new CharTipo(),currFuncao.locais.get(printId.i.s).tpo)){ //verificando se é Char
            System.out.println("\n[l" + printId.linha + "|c" + printId.coluna +"]:" +
              " Não é permitido imprimir um tipo diferente do que foi fornecido como parâmetro.");
            System.exit(1);
          }

        }
        else if(str[i] == '%' && str[i+1] == 'f'){ //verificando Float
          count++;

          if(count > 1){ //verificando se ocorre mais que uma vez
            System.out.println("\n[l" + printId.linha + "|c" + printId.coluna +"]:" +
              " Somente é permitido imprimir uma variável de cada vez!");
            System.exit(1);
          }

          if(!tabela.compareTypes(new FloatTipo(),currFuncao.locais.get(printId.i.s).tpo)){ //verificando se é Float
            System.out.println("\n[l" + printId.linha + "|c" + printId.coluna +"]:" +
              " Não é permitido imprimir um tipo diferente do que foi fornecido como parâmetro.");
            System.exit(1);
          }

        }
      }

    }
    else {
      System.out.println("\n[l" + printId.i.linha + "|c" + printId.i.coluna +"]:" +
        " Variável "+printId.i.s+ " não declarada no escopo.");
      System.exit(1);
    }
  }

  //Scan
  @Override
  public void visit(Scan scan) { 
  
    char[] str = null;
    int count = 0;  
    if(currFuncao.params.containsKey(scan.inlst.i.s)) { //PARAMS

      str = scan.str.toCharArray();

      for(int i=0; i<str.length;i++){

        if(str[i] == '%' && str[i+1] == 'd'){ //verificando para Inteiro
          count++;

          if(count > 1){ //verificando se ocorre mais que uma vez
            System.out.println("\nSomente é permitido ler uma variável de cada vez!");
            System.exit(1);
          }     

          if(!tabela.compareTypes(new IntTipo(),currFuncao.params.get(scan.inlst.i.s).tpo)){ //verificando se é Inteiro
            System.out.println("\nNão é permitido ler um tipo diferente do que foi fornecido como parâmetro.");
            System.exit(1);
          }               

        }
        else if(str[i] == '%' && str[i+1] == 'c'){ //verificando para Char
          count++;

          if(count > 1){ //verificando se ocorre mais que uma vez
            System.out.println("\nSomente é permitido ler uma variável de cada vez!");
            System.exit(1);
          }

          if(!tabela.compareTypes(new CharTipo(),currFuncao.params.get(scan.inlst.i.s).tpo)){ //verificando se é Char
            System.out.println("\nNão é permitido ler um tipo diferente do que foi fornecido como parâmetro.");
            System.exit(1);
          }

        }
        else if(str[i] == '%' && str[i+1] == 'f'){ //verificando Float
          count++;

          if(count > 1){ //verificando se ocorre mais que uma vez
            System.out.println("\nSomente é permitido ler uma variável de cada vez!");
            System.exit(1);
          }

          if(!tabela.compareTypes(new FloatTipo(),currFuncao.params.get(scan.inlst.i.s).tpo)){ //verificando se é Float
            System.out.println("\nNão é permitido ler um tipo diferente do que foi fornecido como parâmetro.");
            System.exit(1);
          }

        }
      }

    }
    else if(currFuncao.locais.containsKey(scan.inlst.i.s)) { //LOCAIS
  
      str = scan.str.toCharArray();

      for(int i=0; i<str.length;i++){

        if(str[i] == '%' && str[i+1] == 'd'){ //verificando para Inteiro
          count++;

          if(count > 1){ //verificando se ocorre mais que uma vez
            System.out.println("\nSomente é permitido ler uma variável de cada vez!");
            System.exit(1);
          }     

          if(!tabela.compareTypes(new IntTipo(),currFuncao.locais.get(scan.inlst.i.s).tpo)){ //verificando se é Inteiro
            System.out.println("\nNão é permitido ler um tipo diferente do que foi fornecido como parâmetro.");
            System.exit(1);
          }               

        }
        else if(str[i] == '%' && str[i+1] == 'c'){ //verificando para Char
          count++;

          if(count > 1){ //verificando se ocorre mais que uma vez
            System.out.println("\nSomente é ler imprimir uma variável de cada vez!");
            System.exit(1);
          }

          if(!tabela.compareTypes(new CharTipo(),currFuncao.locais.get(scan.inlst.i.s).tpo)){ //verificando se é Char
            System.out.println("\nNão é permitido ler um tipo diferente do que foi fornecido como parâmetro.");
            System.exit(1);
          }

        }
        else if(str[i] == '%' && str[i+1] == 'f'){ //verificando Float
          count++;

          if(count > 1){ //verificando se ocorre mais que uma vez
            System.out.println("\nSomente é permitido ler uma variável de cada vez!");
            System.exit(1);
          }

          if(!tabela.compareTypes(new FloatTipo(),currFuncao.locais.get(scan.inlst.i.s).tpo)){ //verificando se é Float
            System.out.println("\nNão é permitido ler um tipo diferente do que foi fornecido como parâmetro.");
            System.exit(1);
          }

        }
      }
    }
    else {
      System.out.println("\nVariável não declarada no escopo\n");
      System.exit(1);
    }
  
  }

  //Atribuicao
  @Override
  public void visit(Atribuicao atribuicao) {
    //Se analisa a expressão que se está atribuindo
    atribuicao.exp.accept(this);
    
    //Procura a variável sendo atribuida entre os parâmetros e locais e realiza a verificação de tipos    
    if(currFuncao.params.containsKey(atribuicao.i.s)) {
      if(!tabela.compareTypes(atribuicao.exp.tpo,currFuncao.params.get(atribuicao.i.s).tpo)) {
        System.out.println("\nTipo de expressão não condiz com o identificador "+atribuicao.i.s+"\n");
        System.exit(1);
      }
    }
    else if(currFuncao.locais.containsKey(atribuicao.i.s)) {
      if(!tabela.compareTypes(atribuicao.exp.tpo,currFuncao.locais.get(atribuicao.i.s).tpo)) {
        System.out.println("\nTipo de expressão não condiz com o identificador"+atribuicao.i.s+"\n");
        System.exit(1);
      } 
    }
    else {
      System.out.println("\nVariável "+atribuicao.i.s+" não declarada no escopo\n");
      System.exit(1);
    }
  }

  //VetorAtrib
  @Override
  public void visit(VetorAtrib vetorAtrib) { 
    //Se analisa a expressãp que se está atribuindo
    vetorAtrib.exp1.accept(this);
    vetorAtrib.exp2.accept(this);
    
    //Procura a variável sendo atribuida entre os parâmetros e locais e realiza a verificação de tipos
    if(! (vetorAtrib.exp1.tpo instanceof IntTipo)) {
      System.out.println("\nSomente valores inteiros podem ser indice de um vetor\n");
      System.exit(1);
    }
    else if(currFuncao.params.containsKey(vetorAtrib.i.s)) {
      StVariavel var = currFuncao.params.get(vetorAtrib.i.s);
      if(var.tam <= 0){
        System.out.println("\nTal identificador não é um vetor "+vetorAtrib.i.s+"\n");
        System.exit(1);
      }
      else if(!tabela.compareTypes(vetorAtrib.exp2.tpo,currFuncao.params.get(vetorAtrib.i.s).tpo)) {
        System.out.println("\nTipo de expressão não condiz com o identificador "+vetorAtrib.i.s+"\n");
        System.exit(1);
      }
    }
    else if(currFuncao.locais.containsKey(vetorAtrib.i.s)) {
      StVariavel var = currFuncao.locais.get(vetorAtrib.i.s);
      if(var.tam <= 0){
        System.out.println("\nTal identificador não é um vetor "+vetorAtrib.i.s+"\n");
        System.exit(1);
      }
      if(!tabela.compareTypes(vetorAtrib.exp2.tpo,currFuncao.locais.get(vetorAtrib.i.s).tpo)) {
        System.out.println("\nTipo de expressão não condiz com o identificador "+vetorAtrib.i.s+"\n");
        System.exit(1);
      } 
    }
    else {
      System.out.println("\nVariável "+vetorAtrib.i.s+" não declarada no escopo\n");
      System.exit(1);
    }
  }

  //ChamadaFuncao
  @Override
  public void visit(ChamadaFuncao chamadaFuncao){
    StFuncao CalledFunc = tabela.funcoes.get(chamadaFuncao.i.s);
    int i = 0;
    Expressao exp;
    Enumeration e = chamadaFuncao.explst.elements();
    while(e.hasMoreElements()){
      exp = (Expressao)e.nextElement();
      exp.accept(this);
    }
    try{
      //Caso exista um numero insuficiente de parametros para a funcao chamada o try se faz necessario
      if( CalledFunc.params.isEmpty() ){
      	throw new ArrayIndexOutOfBoundsException();
      }
      for (String key: CalledFunc.params.keySet())
      {
        if(!tabela.compareTypes( CalledFunc.params.get(key).tpo, chamadaFuncao.explst.elementAt(i).tpo )){
          System.out.println("\nChamada da função "+chamadaFuncao.i.s+" com expressões de tipo(s) diferente(s) do(s) requisitado(s)\n");
          System.exit(1);
        }
        i++;
      }
      
    }catch(ArrayIndexOutOfBoundsException err){
      System.out.println("\nNumero de argumentos da funcao "+chamadaFuncao.i.s+" incompativel!\n");
      System.exit(1);
    }
  }

  //Funcao    
  @Override
  public void visit(Funcao funcao){
      //Seta a função atual para uso de outros visit()
      currFuncao = tabela.funcoes.get(funcao.i.s);
      //Analisa a expressão de retorno
      funcao.prgm.exp.accept(this);
      
      //Checa se o tipo de retorno se identifica com o tipo identificado do RETURN
      Tipo tpo = funcao.prgm.exp.tpo;
       if(!( currFuncao.tipo instanceof IntTipo && tpo instanceof  IntTipo ||
             currFuncao.tipo instanceof FloatTipo && tpo instanceof FloatTipo ||
             currFuncao.tipo instanceof CharTipo && tpo instanceof CharTipo ||
             currFuncao.tipo instanceof VoidTipo && tpo == null )) {
        System.out.println("\nErro: Retorno da funcao '"+ currFuncao.id + "' diferente do tipo declarado.");
        System.exit(1);
       }
  }

  //FuncaoPrincipal
  @Override
  public void visit(FuncaoPrincipal funcaoPrincipal) {
    //Seta a função main para uso de outros visit()
    currFuncao = tabela.funcoes.get("main");
    
    //Checa o tipo de retorno da função principal, que deve ser sempre inteiro
    if(!(funcaoPrincipal.prgm.exp.tpo instanceof IntTipo 
       || funcaoPrincipal.prgm.exp instanceof ExpInt)) {
      System.out.println("\n[l" + funcaoPrincipal.prgm.exp.linha + "|c" + funcaoPrincipal.prgm.exp.coluna +"]:" +
        " Erro: Retorno da main deve ser do tipo int.");
      System.exit(1);
    }
  }
  
    public void visit() { }

}
