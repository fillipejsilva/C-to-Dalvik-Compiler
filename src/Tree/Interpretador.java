package org.interpreter;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;
import java.util.ArrayList;
import org.tree.*;

public class Interpretador extends VisitorAdaptor {

  public SymTable tabela;
  public StFuncao currFuncao;
  public Scanner s = new Scanner(System.in);
  public HashMap <String, Programa> programas = new HashMap();
  
  public void setTabela( SymTable tab ){
    this.tabela = tab;
  }

  @Override
  public void visit(Arquivo arquivo) {
		arquivo.fncb.accept(this);
    arquivo.fncp.accept(this);
  }

  @Override
  public void visit(FuncaoBloco funcaoBloco){
    funcaoBloco.childrenAccept(this);
  }

  @Override
  public void visit(Funcao funcao){
    programas.put(funcao.i.s,funcao.prgm);
  }

  @Override
  public void visit(FuncaoPrincipal funcao){
    currFuncao = tabela.funcoes.get("main");
    funcao.prgm.accept(this);
  }

  @Override
  public void visit(Programa programa) {
    programa.cmds.accept(this);
  }

  @Override
  public void visit(Comandos comandos) {
    comandos.childrenAccept(this);
  }
  
  @Override
  public void visit(Scan scan) { 

    StVariavel var;
    Integer valInt;
    String valChar;
    Float valFloat;
    
    String char_regex  = "^%c$";
    String int_regex   = "^%d$";
    String float_regex = "^%f$";

    if(scan.str.matches(int_regex)){ 
      
      valInt = s.nextInt();
      
      var = currFuncao.getVariavel(scan.inlst.i.s);
      var.setValor(valInt);
      
    }
    else if(scan.str.matches(char_regex)){ 

      valChar = s.nextLine();
      
      var = currFuncao.getVariavel(scan.inlst.i.s);
      var.setValor(valChar);
      
    }
    else if(scan.str.matches(float_regex)){

      valFloat = s.nextFloat();
      
      var = currFuncao.getVariavel(scan.inlst.i.s);
      var.setValor(valFloat);
      
    }

  }
  
  @Override
  public void visit(ComandoWhile comandoWhile) { 
    //Variavel booleana que guarda a condicao
    boolean condicao;
    //Checa a condicao inicial
    comandoWhile.exp.accept(this);
    if( (Integer) comandoWhile.exp.val >= 1){
      condicao = true;
    }
    else{
      condicao = false;
    }
    //Realiza as acoes do while e checa novamente a condicao a cada passagem
    while(condicao){
      comandoWhile.cmds.accept(this);
      comandoWhile.exp.accept(this);
      if( (Integer) comandoWhile.exp.val >= 1){
        condicao = true;
      }
      else{
        condicao = false;
      }
    }
  }

  @Override
  public void visit(Print print) {
    System.out.println(print.str);
  }
  
  @Override
  public void visit(PrintId printId) {

    StVariavel var;
    String char_regex  = ".*%c.*";
    String int_regex   = ".*%d.*";
    String float_regex = ".*%f.*";
    String val,temp;
    
    if(printId.str.matches(int_regex)){ 
      
      val = Integer.toString( (Integer) currFuncao.getVariavel(printId.i.s).valor );
      temp = printId.str.replaceAll("%d",val);
    
    }
    else if(printId.str.matches(char_regex)){ 

      val = (String) currFuncao.getVariavel(printId.i.s).valor;
      temp = printId.str.replaceAll("%c",val);
            
    }
    else{

      val = Float.toString( (Float) currFuncao.getVariavel(printId.i.s).valor );
      temp = printId.str.replaceAll("%f",val);
      
    }
    
    System.out.println(temp);
    
  }

  @Override
  public void visit(ComandoIf comandoIf) {
    Integer expTrue;
    
    comandoIf.exp.accept(this);
    expTrue = (Integer) comandoIf.exp.val;

    if(expTrue >= 1)
      comandoIf.cmds.accept(this); //ou visit(comandoIf.cmds)
    else
      comandoIf.elss.accept(this);
  }

  @Override
  public void visit(Elsis elsis) {
    elsis.childrenAccept(this);
  }
  
  @Override
  public void visit(VetorAtrib vetorAtrib) {

    vetorAtrib.exp1.accept(this);
    vetorAtrib.exp2.accept(this);

    String varId  = vetorAtrib.i.s;
    int indice    = (Integer) vetorAtrib.exp1.val;
    
    if( currFuncao.getVariavel(varId).valor == null ){
    	if( currFuncao.getVariavel(varId).tpo instanceof IntTipo ){
	    	currFuncao.getVariavel(varId).valor = new ArrayList<Integer>();
    	}
    	else if( currFuncao.getVariavel(varId).tpo instanceof FloatTipo ){
	    	currFuncao.getVariavel(varId).valor = new ArrayList<Float>();
    	}
    	else if( currFuncao.getVariavel(varId).tpo instanceof CharTipo ){
	    	currFuncao.getVariavel(varId).valor = new ArrayList<String>();
    	}
    }
    
   	if( currFuncao.getVariavel(varId).tpo instanceof IntTipo ){
	    (	(ArrayList<Integer>) currFuncao.getVariavel(varId).valor ).add(indice, (Integer) vetorAtrib.exp2.val);
   	}
   	else if( currFuncao.getVariavel(varId).tpo instanceof FloatTipo ){
	    (	(ArrayList<Float>) currFuncao.getVariavel(varId).valor ).add(indice, (Float) vetorAtrib.exp2.val);
   	}
   	else if( currFuncao.getVariavel(varId).tpo instanceof CharTipo ){
	    (	(ArrayList<String>) currFuncao.getVariavel(varId).valor ).add(indice, (String) vetorAtrib.exp2.val);
    }    
    
  }

  @Override
  public void visit(ChamadaFuncao chamadaFuncao) {
    
      StFuncao novo = new StFuncao();
      Stack<StFuncao> escopos = new Stack();
      StFuncao repositorio;

			for(int i = 0; i < chamadaFuncao.explst.size(); i++){
				chamadaFuncao.explst.elementAt(i).accept(this);
			}
			
			//Empilha a função em execução no momento da chamada
			escopos.push(currFuncao);
			
			//Recupera a função que será executada
      currFuncao = tabela.funcoes.get(chamadaFuncao.i.s);
      
      //Como todo escopo é mantido na mesma localidade é dado a função chamada um escopo novo igual ao antigo, o escopo entao é copiado
      //Pelo mesmo motivo a referencia ao escopo antigo é guardada para ser recolocada no lugar do novo escopo depois da execução da função
      copiaEscopo(novo,currFuncao);
			repositorio = currFuncao;
			
			//Troca-se o escopo da função que executará pelo novo criado
			tabela.funcoes.remove(currFuncao.id);
			tabela.funcoes.put(currFuncao.id,novo);
			currFuncao = tabela.funcoes.get(currFuncao.id);
      
      //Recupera-se o programa que a função executará
      Programa prog = programas.get(chamadaFuncao.i.s);
      
      //Realiza a passagem de parametros para dentro do escopo novo
      int i = currFuncao.params.size()-1;
      
      for ( String key: currFuncao.params.keySet() ){
  	      currFuncao.params.get(key).valor = chamadaFuncao.explst.elementAt(i).val;
    	    i--;
      }
      
      //Executa a função
      if(!prog.cmds.isEmpty()){	
				prog.cmds.accept(this);
			}
			
      //Troca o escopo modificado, pelo antigo para manter a integridade do espaço compartilhado
 			tabela.funcoes.remove(currFuncao.id);
			tabela.funcoes.put(currFuncao.id,repositorio);
      
      //Recupera o escopo que estava sendo executada antes da chamada de função
      currFuncao = escopos.pop();
      
  }
  
  @Override
  public void visit(Atribuicao atribuicao) {
    atribuicao.exp.accept(this);
    currFuncao.getVariavel(atribuicao.i.s).valor = atribuicao.exp.val;
  }

  @Override
  public void visit(Adicao adicao) {
    
    Object x = null;
    Object y = null;
    
    adicao.exp1.accept(this);
    x = instancia(adicao.exp1);
    
    adicao.exp2.accept(this);
    y = instancia(adicao.exp2);
    
    if (adicao.exp1.tpo instanceof IntTipo && adicao.exp2.tpo instanceof IntTipo){
      adicao.val = new Integer( (Integer) x + (Integer) y);
    }
    else if (adicao.exp1.tpo instanceof IntTipo && adicao.exp2.tpo instanceof FloatTipo){
      adicao.val = new Float( (Integer) x + (Float) y);
    }
    else if (adicao.exp1.tpo instanceof FloatTipo && adicao.exp2.tpo instanceof IntTipo){
      adicao.val = new Float( (Float) x + (Integer) y);
    }
    else{
      adicao.val = new Float( (Float) x + (Float) y);
    }
    
  }

  @Override
  public void visit(Subtracao subtracao) {
    
    Object x = null;
    Object y = null;
    
    subtracao.exp1.accept(this);
    x = instancia(subtracao.exp1);
    
    subtracao.exp2.accept(this);
    y = instancia(subtracao.exp2);
    
    if (subtracao.exp1.tpo instanceof IntTipo && subtracao.exp2.tpo instanceof IntTipo){
      subtracao.val = new Integer( (Integer) x - (Integer) y);
    }
    else if (subtracao.exp1.tpo instanceof IntTipo && subtracao.exp2.tpo instanceof FloatTipo){
      subtracao.val = new Float( (Integer) x - (Float) y);
    }
    else if (subtracao.exp1.tpo instanceof FloatTipo && subtracao.exp2.tpo instanceof IntTipo){
      subtracao.val = new Float( (Float) x - (Integer) y);
    }
    else{
      subtracao.val = new Float( (Float) x - (Float) y);
    }
    
  }
  
  @Override
  public void visit(Divisao divisao) {
    
		Object x = null;
    Object y = null;
    
    divisao.exp1.accept(this);
    x = instancia(divisao.exp1);
    
    divisao.exp2.accept(this);
    y = instancia(divisao.exp2);
    
    if (divisao.exp1.tpo instanceof IntTipo && divisao.exp2.tpo instanceof IntTipo){
      divisao.val = new Integer( (Integer) x / (Integer) y);
    }
    else if (divisao.exp1.tpo instanceof IntTipo && divisao.exp2.tpo instanceof FloatTipo){
      divisao.val = new Float( (Integer) x / (Float) y);
    }
    else if (divisao.exp1.tpo instanceof FloatTipo && divisao.exp2.tpo instanceof IntTipo){
      divisao.val = new Float( (Float) x / (Integer) y);
    }
    else{
      divisao.val = new Float( (Float) x / (Float) y);
    }
    
  }
  
  @Override
  public void visit(Multiplicacao multiplicacao) {
    
    Object x = null;
    Object y = null;
    
    multiplicacao.exp1.accept(this);
    x = instancia(multiplicacao.exp1);
    
    multiplicacao.exp2.accept(this);
    y = instancia(multiplicacao.exp2);
    
    if (multiplicacao.exp1.tpo instanceof IntTipo && multiplicacao.exp2.tpo instanceof IntTipo){
      multiplicacao.val = new Integer( (Integer) x * (Integer) y);
    }
    else if (multiplicacao.exp1.tpo instanceof IntTipo && multiplicacao.exp2.tpo instanceof FloatTipo){
      multiplicacao.val = new Float( (Integer) x * (Float) y);
    }
    else if (multiplicacao.exp1.tpo instanceof FloatTipo && multiplicacao.exp2.tpo instanceof IntTipo){
      multiplicacao.val = new Float( (Float) x * (Integer) y);
    }
    else{
      multiplicacao.val = new Float( (Float) x * (Float) y);
    }
  
  }  
  
  public Object instancia(Expressao exp){
		Object x;
		if (exp.tpo instanceof IntTipo){
      x = new Integer( ((Integer) exp.val).intValue() );
    }
    else{
      x = new Float( ((Float) exp.val).floatValue() );
    }
	  return x;
  }
  
  @Override
  public void visit(ExpId expId) {

    System.out.println("-->cuurFuncao: " + currFuncao.id);

      if( currFuncao.getVariavel(expId.i.s).tpo instanceof IntTipo){
				expId.val = new Integer ( ( (Integer) currFuncao.getVariavel(expId.i.s).valor).intValue() );
      }
      else if( currFuncao.getVariavel(expId.i.s).tpo instanceof FloatTipo ){
				expId.val = new Float ( ( (Float) currFuncao.getVariavel(expId.i.s).valor).floatValue() );      
      }
      else if( currFuncao.getVariavel(expId.i.s).tpo instanceof CharTipo ){
				expId.val = new String ( (String) currFuncao.getVariavel(expId.i.s).valor );      
      }
  }
  
  @Override
  public void visit(ExpChar expChar) {
    expChar.val = new String(expChar.c);
  }
  
  @Override
  public void visit(ExpInt expInt) {
    expInt.val = new Integer(expInt.it);
  }
  
  @Override
  public void visit(ExpFloat expFloat) {
    expFloat.val = new Float(expFloat.fl);
  }
  
  @Override
  public void visit(TermoFuncao termoFuncao) {
      
      StFuncao novo = new StFuncao();
      Stack<StFuncao> escopos = new Stack();
      StFuncao repositorio;

			for(int i = 0; i < termoFuncao.explst.size(); i++){
				termoFuncao.explst.elementAt(i).accept(this);
			}
			
			//Empilha a função em execução no momento da chamada
			escopos.push(currFuncao);
			
			//Recupera a função que será executada
      currFuncao = tabela.funcoes.get(termoFuncao.i.s);
      
      //Como todo escopo é mantido na mesma localidade é dado a função chamada um escopo novo igual ao antigo, o escopo entao é copiado
      //Pelo mesmo motivo a referencia ao escopo antigo é guardada para ser recolocada no lugar do novo escopo depois da execução da função
      copiaEscopo(novo,currFuncao);
			repositorio = currFuncao;
			
			//Troca-se o escopo da função que executará pelo novo criado
			tabela.funcoes.remove(currFuncao.id);
			tabela.funcoes.put(currFuncao.id,novo);
			currFuncao = tabela.funcoes.get(currFuncao.id);
      
      //Recupera-se o programa que a função executará
      Programa prog = programas.get(termoFuncao.i.s);
      
      //Realiza a passagem de parametros para dentro do escopo novo
      int i = currFuncao.params.size()-1;
      
      for ( String key: currFuncao.params.keySet() ){
  	      currFuncao.params.get(key).valor = termoFuncao.explst.elementAt(i).val;
    	    i--;
      }
      
      //Executa a função
      if(!prog.cmds.isEmpty()){	
				prog.cmds.accept(this);
			}
			
      //Atribui ao termo o valor da função
      prog.exp.accept(this);
      termoFuncao.val = prog.exp.val;
      
      //Troca o escopo modificado, pelo antigo para manter a integridade do espaço compartilhado
 			tabela.funcoes.remove(currFuncao.id);
			tabela.funcoes.put(currFuncao.id,repositorio);
      
      //Recupera o escopo que estava sendo executada antes da chamada de função
      currFuncao = escopos.pop();
      
  }
  
  public void copiaEscopo(StFuncao novo, StFuncao corrente){

  	novo.id = corrente.id;
  	novo.tipo = corrente.tipo;
  		
  	for( String key: corrente.params.keySet() ){
	  	StVariavel copia = new StVariavel();
			copia.id = new String ( corrente.params.get(key).id );
			copia.tam = corrente.params.get(key).tam;
			copia.tpo = corrente.params.get(key).tpo;
			copia.setValor( corrente.params.get(key).valor );
			novo.params.put(key,copia);
			//System.out.println("Copia do parametro: "+novo.params.get(key).id+" valor: "+novo.params.get(key).valor);
  	}
  	
  	for( String key: corrente.locais.keySet() ){
	  	StVariavel copia = new StVariavel();
			copia.id = new String ( corrente.locais.get(key).id );
			copia.tam = corrente.locais.get(key).tam;
			copia.tpo = corrente.locais.get(key).tpo;
			copia.setValor( corrente.locais.get(key).valor );
			novo.locais.put(key,copia);
			//System.out.println("Copia da local: "+novo.locais.get(key).id+" valor: "+novo.locais.get(key).valor);			
  	}
  }  
  
  @Override
  public void visit(TermoParenteses termoParenteses) {
    
    termoParenteses.exp.accept(this);
    termoParenteses.val = termoParenteses.exp.val;
    
  }
  
  @Override
  public void visit(MaiorIgual maiorIgual) {
    
        
    maiorIgual.exp1.accept(this);
    maiorIgual.exp2.accept(this);
    
    if (maiorIgual.exp1.tpo instanceof IntTipo && maiorIgual.exp2.tpo instanceof IntTipo){
      if( (Integer) maiorIgual.exp1.val >=  (Integer) maiorIgual.exp2.val ){
        maiorIgual.val = new Integer(1);
      }
      else{
        maiorIgual.val = new Integer(0);
      }
    }
    else if (maiorIgual.exp1.tpo instanceof IntTipo && maiorIgual.exp2.tpo instanceof FloatTipo){
      if( (Integer) maiorIgual.exp1.val >=  (Float) maiorIgual.exp2.val ){
        maiorIgual.val = new Integer(1);
      }
      else{
        maiorIgual.val = new Integer(0);
      }
    }
    else if (maiorIgual.exp1.tpo instanceof FloatTipo && maiorIgual.exp2.tpo instanceof IntTipo){
      if( (Float) maiorIgual.exp1.val >=  (Integer) maiorIgual.exp2.val ){
        maiorIgual.val = new Integer(1);
      }
      else{
        maiorIgual.val = new Integer(0);
      }
    }
    else if (maiorIgual.exp1.tpo instanceof FloatTipo && maiorIgual.exp2.tpo instanceof FloatTipo){
      if( (Float) maiorIgual.exp1.val >=  (Float) maiorIgual.exp2.val ){
        maiorIgual.val = new Integer(1);
      }
      else{
        maiorIgual.val = new Integer(0);
      }
    }
    else if (maiorIgual.exp1.tpo instanceof IntTipo && maiorIgual.exp2.tpo instanceof CharTipo){
      if( (Integer) maiorIgual.exp1.val >=  ((String) maiorIgual.exp2.val).charAt(0) ){
        maiorIgual.val = new Integer(1);
      }
      else{
        maiorIgual.val = new Integer(0);
      }
    }
    else if (maiorIgual.exp1.tpo instanceof CharTipo && maiorIgual.exp2.tpo instanceof IntTipo){
      if( ((String) maiorIgual.exp1.val).charAt(0) >=  (Integer) maiorIgual.exp2.val ){
        maiorIgual.val = new Integer(1);
      }
      else{
        maiorIgual.val = new Integer(0);
      }
    }
    else if (maiorIgual.exp1.tpo instanceof FloatTipo && maiorIgual.exp2.tpo instanceof CharTipo){
      if( (Float) maiorIgual.exp1.val >=  ((String) maiorIgual.exp2.val).charAt(0) ){
        maiorIgual.val = new Integer(1);
      }
      else{
        maiorIgual.val = new Integer(0);
      }
    }
    else if (maiorIgual.exp1.tpo instanceof CharTipo && maiorIgual.exp2.tpo instanceof FloatTipo){
      if( ((String) maiorIgual.exp1.val).charAt(0) >=  (Float) maiorIgual.exp2.val ){
        maiorIgual.val = new Integer(1);
      }
      else{
        maiorIgual.val = new Integer(0);
      }
    }
    else if (maiorIgual.exp1.tpo instanceof CharTipo && maiorIgual.exp2.tpo instanceof CharTipo){
      if( ((String) maiorIgual.exp1.val).charAt(1) >= ((String) maiorIgual.exp2.val).charAt(1) ){
        maiorIgual.val = new Integer(1);
      }
      else{
        maiorIgual.val = new Integer(0);
      }
    }
    
  }

  @Override
  public void visit(MenorIgual menorIgual) {
    
    menorIgual.exp1.accept(this);
    menorIgual.exp2.accept(this);
    
    if (menorIgual.exp1.tpo instanceof IntTipo && menorIgual.exp2.tpo instanceof IntTipo){
      if( (Integer) menorIgual.exp1.val <=  (Integer) menorIgual.exp2.val ){
        menorIgual.val = new Integer(1);
      }
      else{
        menorIgual.val = new Integer(0);
      }
    }
    else if (menorIgual.exp1.tpo instanceof IntTipo && menorIgual.exp2.tpo instanceof FloatTipo){
      if( (Integer) menorIgual.exp1.val <=  (Float) menorIgual.exp2.val ){
        menorIgual.val = new Integer(1);
      }
      else{
        menorIgual.val = new Integer(0);
      }
    }
    else if (menorIgual.exp1.tpo instanceof FloatTipo && menorIgual.exp2.tpo instanceof IntTipo){
      if( (Float) menorIgual.exp1.val <=  (Integer) menorIgual.exp2.val ){
        menorIgual.val = new Integer(1);
      }
      else{
        menorIgual.val = new Integer(0);
      }
    }
    else if (menorIgual.exp1.tpo instanceof FloatTipo && menorIgual.exp2.tpo instanceof FloatTipo){
      if( (Float) menorIgual.exp1.val <=  (Float) menorIgual.exp2.val ){
        menorIgual.val = new Integer(1);
      }
      else{
        menorIgual.val = new Integer(0);
      }
    }
    else if (menorIgual.exp1.tpo instanceof IntTipo && menorIgual.exp2.tpo instanceof CharTipo){
      if( (Integer) menorIgual.exp1.val <=  ((String) menorIgual.exp2.val).charAt(0) ){
        menorIgual.val = new Integer(1);
      }
      else{
        menorIgual.val = new Integer(0);
      }
    }
    else if (menorIgual.exp1.tpo instanceof CharTipo && menorIgual.exp2.tpo instanceof IntTipo){
      if( ((String) menorIgual.exp1.val).charAt(0) <=  (Integer) menorIgual.exp2.val ){
        menorIgual.val = new Integer(1);
      }
      else{
        menorIgual.val = new Integer(0);
      }
    }
    else if (menorIgual.exp1.tpo instanceof FloatTipo && menorIgual.exp2.tpo instanceof CharTipo){
      if( (Float) menorIgual.exp1.val <=  ((String) menorIgual.exp2.val).charAt(0) ){
        menorIgual.val = new Integer(1);
      }
      else{
        menorIgual.val = new Integer(0);
      }
    }
    else if (menorIgual.exp1.tpo instanceof CharTipo && menorIgual.exp2.tpo instanceof FloatTipo){
      if( ((String) menorIgual.exp1.val).charAt(0) <=  (Float) menorIgual.exp2.val ){
        menorIgual.val = new Integer(1);
      }
      else{
        menorIgual.val = new Integer(0);
      }
    }
    else if (menorIgual.exp1.tpo instanceof CharTipo && menorIgual.exp2.tpo instanceof CharTipo){
      if( ((String) menorIgual.exp1.val).charAt(1) <= ((String) menorIgual.exp2.val).charAt(1) ){
        menorIgual.val = new Integer(1);
      }
      else{
        menorIgual.val = new Integer(0);
      }
    }
        
  }
  
  @Override
  public void visit(Maior maior) {

    maior.exp1.accept(this);
    maior.exp2.accept(this);

    if (maior.exp1.tpo instanceof IntTipo && maior.exp2.tpo instanceof IntTipo){
      if( (Integer) maior.exp1.val > (Integer) maior.exp2.val ){
        maior.val = new Integer(1);
      }
      else{
        maior.val = new Integer(0);
      }
    }
    else if (maior.exp1.tpo instanceof IntTipo && maior.exp2.tpo instanceof FloatTipo){
      if( (Integer) maior.exp1.val > (Float) maior.exp2.val ){
        maior.val = new Integer(1);
      }
      else{
        maior.val = new Integer(0);
      }
    }
    else if (maior.exp1.tpo instanceof FloatTipo && maior.exp2.tpo instanceof IntTipo){
      if( (Float) maior.exp1.val > (Integer) maior.exp2.val ){
        maior.val = new Integer(1);
      }
      else{
        maior.val = new Integer(0);
      }
    }
    else if (maior.exp1.tpo instanceof FloatTipo && maior.exp2.tpo instanceof FloatTipo){
      if( (Float) maior.exp1.val > (Float) maior.exp2.val ){
        maior.val = new Integer(1);
      }
      else{
        maior.val = new Integer(0);
      }
    }
    else if (maior.exp1.tpo instanceof IntTipo && maior.exp2.tpo instanceof CharTipo){
      if( (Integer) maior.exp1.val >  ((String) maior.exp2.val).charAt(0) ){
        maior.val = new Integer(1);
      }
      else{
        maior.val = new Integer(0);
      }
    }
    else if (maior.exp1.tpo instanceof CharTipo && maior.exp2.tpo instanceof IntTipo){
      if( ((String) maior.exp1.val).charAt(0) >  (Integer) maior.exp2.val ){
        maior.val = new Integer(1);
      }
      else{
        maior.val = new Integer(0);
      }
    }
    else if (maior.exp1.tpo instanceof FloatTipo && maior.exp2.tpo instanceof CharTipo){
      if( (Float) maior.exp1.val >  ((String) maior.exp2.val).charAt(0) ){
        maior.val = new Integer(1);
      }
      else{
        maior.val = new Integer(0);
      }
    }
    else if (maior.exp1.tpo instanceof CharTipo && maior.exp2.tpo instanceof FloatTipo){
      if( ((String) maior.exp1.val).charAt(0) >  (Float) maior.exp2.val ){
        maior.val = new Integer(1);
      }
      else{
        maior.val = new Integer(0);
      }
    }
    else if (maior.exp1.tpo instanceof CharTipo && maior.exp2.tpo instanceof CharTipo){
      if( ((String) maior.exp1.val).charAt(1) > ((String) maior.exp2.val).charAt(1) ){
        maior.val = new Integer(1);
      }
      else{
        maior.val = new Integer(0);
      }
    }
  }
  
  @Override
  public void visit(Menor menor) {
  
    menor.exp1.accept(this);
    menor.exp2.accept(this);
  
    if (menor.exp1.tpo instanceof IntTipo && menor.exp2.tpo instanceof IntTipo){
      if( (Integer) menor.exp1.val <  (Integer) menor.exp2.val ){
        menor.val = new Integer(1);
      }
      else{
        menor.val = new Integer(0);
      }
    }
    else if (menor.exp1.tpo instanceof IntTipo && menor.exp2.tpo instanceof FloatTipo){
      if( (Integer) menor.exp1.val <  (Float) menor.exp2.val ){
        menor.val = new Integer(1);
      }
      else{
        menor.val = new Integer(0);
      }
    }
    else if (menor.exp1.tpo instanceof FloatTipo && menor.exp2.tpo instanceof IntTipo){
      if( (Float) menor.exp1.val <  (Integer) menor.exp2.val ){
        menor.val = new Integer(1);
      }
      else{
        menor.val = new Integer(0);
      }
    }
    else if (menor.exp1.tpo instanceof FloatTipo && menor.exp2.tpo instanceof FloatTipo){
      if( (Float) menor.exp1.val <  (Float) menor.exp2.val ){
        menor.val = new Integer(1);
      }
      else{
        menor.val = new Integer(0);
      }
    }
    else if (menor.exp1.tpo instanceof IntTipo && menor.exp2.tpo instanceof CharTipo){
      if( (Integer) menor.exp1.val <  ((String) menor.exp2.val).charAt(0) ){
        menor.val = new Integer(1);
      }
      else{
        menor.val = new Integer(0);
      }
    }
    else if (menor.exp1.tpo instanceof CharTipo && menor.exp2.tpo instanceof IntTipo){
      if( ((String) menor.exp1.val).charAt(0) <  (Integer) menor.exp2.val ){
        menor.val = new Integer(1);
      }
      else{
        menor.val = new Integer(0);
      }
    }
    else if (menor.exp1.tpo instanceof FloatTipo && menor.exp2.tpo instanceof CharTipo){
      if( (Float) menor.exp1.val <  ((String) menor.exp2.val).charAt(0) ){
        menor.val = new Integer(1);
      }
      else{
        menor.val = new Integer(0);
      }
    }
    else if (menor.exp1.tpo instanceof CharTipo && menor.exp2.tpo instanceof FloatTipo){
      if( ((String) menor.exp1.val).charAt(0) <  (Float) menor.exp2.val ){
        menor.val = new Integer(1);
      }
      else{
        menor.val = new Integer(0);
      }
    }
    else if (menor.exp1.tpo instanceof CharTipo && menor.exp2.tpo instanceof CharTipo){
      if( ((String) menor.exp1.val).charAt(1) < ((String) menor.exp2.val).charAt(1) ){
        menor.val = new Integer(1);
      }
      else{
        menor.val = new Integer(0);
      }
    }
  }
  
  @Override
  public void visit(Igualdade igualdade) {
    
    igualdade.exp1.accept(this);
    igualdade.exp2.accept(this);
    
    if (igualdade.exp1.tpo instanceof IntTipo && igualdade.exp2.tpo instanceof IntTipo){
      if( ((Integer) igualdade.exp1.val).intValue() == ((Integer) igualdade.exp2.val).intValue() ) {
        igualdade.val = new Integer(1);
      }
      else{
        igualdade.val = new Integer(0);
      }
    }
    if (igualdade.exp1.tpo instanceof IntTipo && igualdade.exp2.tpo instanceof FloatTipo){
      if( ((Integer) igualdade.exp1.val).floatValue() == ((Float) igualdade.exp2.val).floatValue() ) {
        igualdade.val = new Integer(1);
      }
      else{
        igualdade.val = new Integer(0);
      }
    }
    if (igualdade.exp1.tpo instanceof FloatTipo && igualdade.exp2.tpo instanceof IntTipo){
      if( ((Float) igualdade.exp1.val).floatValue() == ((Integer) igualdade.exp2.val).floatValue() ) {
        igualdade.val = new Integer(1);
      }
      else{
        igualdade.val = new Integer(0);
      }
    }    
    else if (igualdade.exp1.tpo instanceof FloatTipo && igualdade.exp2.tpo instanceof FloatTipo){
      if( ((Float) igualdade.exp1.val).floatValue() == ((Float) igualdade.exp2.val).floatValue() ){
        igualdade.val = new Integer(1);
      }
      else{
        igualdade.val = new Integer(0);
      }
    }
    else if (igualdade.exp1.tpo instanceof IntTipo && igualdade.exp2.tpo instanceof CharTipo){
      if( ((Integer) igualdade.exp1.val).intValue() == ((String) igualdade.exp2.val).charAt(1) ){
        igualdade.val = new Integer(1);
      }
      else{
        igualdade.val = new Integer(0);
      }
    }
    else if (igualdade.exp1.tpo instanceof CharTipo && igualdade.exp2.tpo instanceof IntTipo){
      if( ((String) igualdade.exp1.val).charAt(1) == ((Integer) igualdade.exp2.val).intValue() ){
        igualdade.val = new Integer(1);
      }
      else{
        igualdade.val = new Integer(0);
      }
    }
    else if (igualdade.exp1.tpo instanceof CharTipo && igualdade.exp2.tpo instanceof CharTipo){
      if( ((String) igualdade.exp1.val).charAt(0) == ((String) igualdade.exp2.val).charAt(0) ){
        igualdade.val = new Integer(1);
      }
      else{
        igualdade.val = new Integer(0);
      }
    }
    
  }
  
  @Override
  public void visit(Diferente diferente) {

    diferente.exp1.accept(this);
    diferente.exp2.accept(this);

        if (diferente.exp1.tpo instanceof IntTipo && diferente.exp2.tpo instanceof IntTipo){
      if( ((Integer) diferente.exp1.val).intValue() != ((Integer) diferente.exp2.val).intValue() ) {
        diferente.val = new Integer(1);
      }
      else{
        diferente.val = new Integer(0);
      }
    }
    if (diferente.exp1.tpo instanceof IntTipo && diferente.exp2.tpo instanceof FloatTipo){
      if( ((Integer) diferente.exp1.val).floatValue() != ((Float) diferente.exp2.val).floatValue() ) {
        diferente.val = new Integer(1);
      }
      else{
        diferente.val = new Integer(0);
      }
    }
    if (diferente.exp1.tpo instanceof FloatTipo && diferente.exp2.tpo instanceof IntTipo){
      if( ((Float) diferente.exp1.val).floatValue() != ((Integer) diferente.exp2.val).floatValue() ) {
        diferente.val = new Integer(1);
      }
      else{
        diferente.val = new Integer(0);
      }
    }    
    else if (diferente.exp1.tpo instanceof FloatTipo && diferente.exp2.tpo instanceof FloatTipo){
      if( ((Float) diferente.exp1.val).floatValue() != ((Float) diferente.exp2.val).floatValue() ){
        diferente.val = new Integer(1);
      }
      else{
        diferente.val = new Integer(0);
      }
    }
    else if (diferente.exp1.tpo instanceof IntTipo && diferente.exp2.tpo instanceof CharTipo){
      if( ((Integer) diferente.exp1.val).intValue() != ((String) diferente.exp2.val).charAt(1) ){
        diferente.val = new Integer(1);
      }
      else{
        diferente.val = new Integer(0);
      }
    }
    else if (diferente.exp1.tpo instanceof CharTipo && diferente.exp2.tpo instanceof IntTipo){
      if( ((String) diferente.exp1.val).charAt(1) != ((Integer) diferente.exp2.val).intValue() ){
        diferente.val = new Integer(1);
      }
      else{
        diferente.val = new Integer(0);
      }
    }
    else if (diferente.exp1.tpo instanceof CharTipo && diferente.exp2.tpo instanceof CharTipo){
      if( ((String) diferente.exp1.val).charAt(1) != ((String) diferente.exp2.val).charAt(1) ){
        diferente.val = new Integer(1);
      }
      else{
        diferente.val = new Integer(0);
      }
    }
    
  }
  
  @Override
  public void visit(E e) {
    
    e.exp1.accept(this);
    e.exp2.accept(this);
    
    if( (Integer) e.exp1.val >= 1 && (Integer) e.exp2.val >= 1){
      e.val = new Integer(1);
    }
    else{
      e.val = new Integer(0);
    }
    
  }
  
  @Override
  public void visit(Ou ou) {
    ou.exp1.accept(this);
    ou.exp2.accept(this);
    
    if( (Integer) ou.exp1.val >= 1 || (Integer) ou.exp2.val >= 1){
      ou.val = new Integer(1);
    }
    else{
      ou.val = new Integer(0);
    }
  }
  
  @Override
  public void visit(TermoNegacao termoNegacao) {
    termoNegacao.exp.accept(this);
    if( (Integer) termoNegacao.exp.val >=1){
      termoNegacao.val = new Integer(0);
    }
    else{
      termoNegacao.val = new Integer(1);
    }
  }
  
}
