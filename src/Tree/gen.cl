package org.tree;

attr Tipo tpo with expressao;
attr Object val with expressao;
attr Integer linha with expressao;
attr Integer coluna with expressao;
attr Integer linha with idf;
attr Integer coluna with idf;
attr Integer linha with comando;
attr Integer coluna with comando;

arquivo ::= funcaoBloco:fncb funcaoPrincipal:fncp

funcaoBloco ::=	funcao*

funcao	::= tipo:tpo idf:i entradaFuncao:etrdfnc programa:prgm

funcaoPrincipal ::= programa:prgm

tipo ::=
       {CharTipo}
	 | {IntTipo}
	 | {FloatTipo}
	 | {VoidTipo}

entradaFuncao ::= entrada*

entrada ::= tipo:tpo idf:i

programa ::= declaraLista:declst comandos:cmds ret:r expressao:exp

ret ::= "String":r

declaraLista ::= declara*

declara ::=	tipo:tpo vetor:vt idf:i

vetor ::=	"Integer":vt

comandos ::=	comando*

comando ::=	
		  {Print}         "String":str
		| {PrintId}       "String":str idf:i
		| {Scan}          "String":str inList:inlst
		| {ComandoWhile}  expressao:exp comandos:cmds
		| {ComandoIf}     expressao:exp comandos:cmds elsis:elss
		| {Atribuicao}    idf:i expressao:exp
		| {VetorAtrib}    idf:i expressao:exp1 expressao:exp2
		| {ChamadaFuncao} idf:i expressaoLista:explst

inList ::= 	idf:i

elsis ::=	comandos*

expressao ::= 
					{Igualdade}       expressao:exp1 expressao:exp2
        | {Adicao}          expressao:exp1 expressao:exp2
	      | {Subtracao}       expressao:exp1 expressao:exp2
	      | {Divisao}         expressao:exp1 expressao:exp2
	      | {Multiplicacao}   expressao:exp1 expressao:exp2
	      | {MaiorIgual}      expressao:exp1 expressao:exp2
	      | {MenorIgual}      expressao:exp1 expressao:exp2
	      | {Maior}           expressao:exp1 expressao:exp2
	      | {Menor}           expressao:exp1 expressao:exp2
	      | {Diferente}       expressao:exp1 expressao:exp2
	      | {E}               expressao:exp1 expressao:exp2
	      | {Ou}              expressao:exp1 expressao:exp2
	      | {TermoFuncao}     idf:i expressaoLista:explst
	      | {ExpId}           idf:i
	      | {TermoParenteses} expressao:exp
	      | {TermoNegacao}    expressao:exp
	      | {ExpChar}         "String":c
        | {ExpInt}          "Integer":it
        | {ExpFloat}        "Float":fl
				| {UsoVetor}				idf:i expressao:exp
				
expressaoLista ::= expressao*

idf ::= "String":s

methods of idf {
 public String toString() {
  return s;
 }
}
