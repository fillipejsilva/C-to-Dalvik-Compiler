package org;
import org.lexer.*;
import org.parser.*;
import org.tree.*;
import org.interpreter.*;
import java_cup.runtime.Symbol;
import java.io.*;


public class Maluco{
	public static void main(String args[]){
		
		Arquivo result;
		Interpretador interpretador = new Interpretador();
		TypeChecker semantico = new TypeChecker();
		ScopeChecker scopeChecker = new ScopeChecker();
			
		try { 
		
			parser p = new parser(new Lexico(new FileReader(args[0])));
			result = (Arquivo) p.parse().value;
			if(p.erro > 0){
				System.out.println("\nCorrija os erros sintáticos e tente outra vez\n");
				System.exit(1);
			}
			System.out.println(" Terminada a análise léxica e sintática \n Árvore resultante:\n" + result.toString("") + "\n");
			System.out.println("\n Preenchendo a tabela de símbolos\n");
			result.traverseTopDown(scopeChecker);
			System.out.println("\n Tabela preenchida\n");
			System.out.println("  Resultado:\n");
			System.out.println(scopeChecker.getSymTable().toString());
			System.out.println("\n Iniciando a análise semântica\n");
			semantico.setTabela(scopeChecker.getSymTable());
			result.traverseTopDown(semantico);
			System.out.println("\n Terminada a análise semântica o programa está correto\n");
			System.out.println("\n Iniciando a fase de interpretação do programa\n");
			interpretador.setTabela(scopeChecker.getSymTable());
			result.accept(interpretador);
			System.out.println("\n Fim da interpretação do programa\n");
			
			
		
		} catch(ArrayIndexOutOfBoundsException e) {
		
			System.out.println("Faltou digitar o nome do arquivo no final do comando!");
			System.exit(1);
		
		} catch (Exception e) {
		
			System.out.println("Erro: ");
			e.printStackTrace();
			System.exit(1);
		}
	}
}
