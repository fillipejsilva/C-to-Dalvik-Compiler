package org.lexer;
import java_cup.runtime.*;
import org.parser.sym;

%%

%class Lexico
%cup
%public
%line
%column
%standalone

%{

  private Symbol symbol(int type){
	return new Symbol(type, yyline, yycolumn);
  }

  private Symbol symbol(int type, Object value){
	return new Symbol(type, yyline, yycolumn, value);
  }

  StringBuffer string = new StringBuffer();
  int nCmt = 0;
%}

SHARP = "#"

INCLUDE = "include <stdio.h>"|"include <stdlib.h>"|"include<stdio.h>"|"include<stdlib.h>"

BRANCO = [\n| |\t]

ID = [_|a-z|A-Z][a-z|A-Z|0-9|_]*

LINT = 0|[1-9][0-9]*

LFLOAT = [0-9][0-9]*"."[0-9]+

LCHAR = "'"[:jletter:]"'"

INICIOCOMENTARIOA = "/*"

INICIOCOMENTARIOL = "//" [a-z|A-Z|BRANCO]* [\n|\r|\r\n]?

%state STRING

%state COMENTARIOA

%%

<YYINITIAL> "char" {/*System.out.println("CHAR");*/ return symbol(sym.CHAR);  }

<YYINITIAL> "float" {/*System.out.println("FLOAT");*/ return symbol(sym.FLOAT);  }

<YYINITIAL> "else" {/*System.out.println("ELSE");*/ return symbol(sym.ELSE);  }

<YYINITIAL> "if" {/*System.out.println("IF");*/ return symbol(sym.IF);  }

<YYINITIAL> "int" {/*System.out.println("INT");*/ return symbol(sym.INT);  }

<YYINITIAL> "int main" {/*System.out.println("MAIN");*/ return symbol(sym.MAIN);  }

<YYINITIAL> "printf" {/*System.out.println("PRINTF");*/ return symbol(sym.PRINTF);  }

<YYINITIAL> "scanf" {/*System.out.println("SCANF");*/ return symbol(sym.SCANF);  }

<YYINITIAL> "return" {/*System.out.println("RETURN");*/ return symbol(sym.RETURN,yytext());  }

<YYINITIAL> "void" {/*System.out.println("VOID");*/ return symbol(sym.VOID);  }

<YYINITIAL> "while" {/*System.out.println("WHILE");*/ return symbol(sym.WHILE);  }

<YYINITIAL> "!" {/*System.out.println("NEG");*/ return symbol(sym.NEG);  }

<YYINITIAL> "=" {/*System.out.println("ATT");*/ return symbol(sym.ATRIB);  }

<YYINITIAL> "||" {/*System.out.println("OU");*/ return symbol(sym.OU);  }

<YYINITIAL> "&&" {/*System.out.println("E");*/ return symbol(sym.E);  }

<YYINITIAL> ">=" {/*System.out.println("GET");*/ return symbol(sym.MAIEQ);  }

<YYINITIAL> "<=" {/*System.out.println("LET");*/ return symbol(sym.MENEQ);  }

<YYINITIAL> ">" {/*System.out.println("GT");*/ return symbol(sym.MAIOR);  }

<YYINITIAL> "<" {/*System.out.println("LT");*/ return symbol(sym.MENOR);  }

<YYINITIAL> "!=" {/*System.out.println("DIF");*/ return symbol(sym.DIF);  }

<YYINITIAL> "==" {/*System.out.println("EQ");*/ return symbol(sym.EQ);  }

<YYINITIAL> "/" {/*System.out.println("SLASH");*/ return symbol(sym.DIV);  }

<YYINITIAL> "*" {/*System.out.println("STAR");*/ return symbol(sym.MULT);  }

<YYINITIAL> "-" {/*System.out.println("MINUS");*/ return symbol(sym.SUB);  }

<YYINITIAL> "+" {/*System.out.println("PLUS");*/ return symbol(sym.SOMA);  }

<YYINITIAL> ";" {/*System.out.println("SC");*/ return symbol(sym.PTVIRG);  }

<YYINITIAL> "(" {/*System.out.println("LP");*/ return symbol(sym.LPAREN);  }

<YYINITIAL> ")" {/*System.out.println("RP");*/ return symbol(sym.RPAREN);  }

<YYINITIAL> "{" {/*System.out.println("BEGIN");*/ return symbol(sym.LCHAVE);  }

<YYINITIAL> "}" {/*System.out.println("END");*/ return symbol(sym.RCHAVE);  }

<YYINITIAL> "[" {/*System.out.println("LB");*/ return symbol(sym.LCOLCH);  }

<YYINITIAL> "]" {/*System.out.println("RB");*/ return symbol(sym.RCOLCH);  }

<YYINITIAL> "," {/*System.out.println("COMMA");*/ return symbol(sym.VIRG);  }

<YYINITIAL> \"  {string.setLength(0); yybegin(STRING); }

<YYINITIAL> "&" {/*System.out.println("AD");*/ return symbol(sym.ECOM);  }

<YYINITIAL> {SHARP} {/*System.out.println("SHARP");*/ return symbol(sym.SHARP);}

<YYINITIAL> {INCLUDE} {/*System.out.println("INCLUDE");*/ return symbol(sym.INCLUDE);}

<YYINITIAL> {BRANCO} {/*nothing to do here*/}

<YYINITIAL> {ID} {/*System.out.println("IDF");*/ return symbol(sym.IDF,new String(yytext()));  }

<YYINITIAL> {LINT} {/*System.out.println("LINT");*/ return symbol(sym.LINT,new Integer(yytext()));  }

<YYINITIAL> {LFLOAT} {/*System.out.println("LFLOAT");*/ return symbol(sym.LFLOAT,new Float(yytext()));  }

<YYINITIAL> {LCHAR} {/*System.out.println("LCHAR");*/ return symbol(sym.LCHAR,yytext());  }

<YYINITIAL> {INICIOCOMENTARIOA} { nCmt++; yybegin(COMENTARIOA);}

<YYINITIAL> {INICIOCOMENTARIOL} {/*nothing to do here*/}

<STRING> {
  \" 		{ yybegin(YYINITIAL); return symbol(sym.STRING,string.toString() );}
  [^\n\r\"\\]+  { string.append( yytext() ); }
  "\\b"         { string.append('\b'); }
  "\\t"         { string.append('\t'); }
  "\\n"         { string.append('\n'); }
  "\\r"         { string.append('\r'); }
  "\\\""        { string.append('\"'); }
  "\\'"         { string.append('\''); }
  "\\\\"        { string.append('\\'); }
  \\.           { System.out.println("Sequencia ilegal " + yytext() + " na linha " + (yyline+1)); System.exit(1); }
  \n|\r|\r\n    { System.out.println("A string encontrada na linha " +(yyline+1)+ " nao foi fechada"); System.exit(1);}
  <<EOF>>       { System.out.println("A string encontrada na linha "+(yyline+1)+ " nao foi fechada"); System.exit(1);}
}

<COMENTARIOA>{
"/*"   {nCmt++;}
"*/"   {nCmt--; if(nCmt == 0){yybegin(YYINITIAL);}}
<<EOF>>  {System.out.println("Verifique a existencia de comentarios aninhados nao propriamente terminados!"); System.exit(1);}
.|\n*  {/* nothing to do here */} 
}

. {System.out.println("Erro lexico caracter invalido " + yytext() + " na linha " + (yyline+1) +" coluna "+ (yycolumn+1)); System.exit(1);}
