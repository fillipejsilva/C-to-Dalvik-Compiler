#! /bin/bash

# script para compilar porque é muito chato trabalhar com isso 
# pelo terminal
# o script funciona considerando que o cup e o jflex ja estão instalados e a 
# seguinte hierarquia de diretorios de codigo 
# fonte:
# +src
# |Java_cup (contem o "java-cup-11a-runtime.jar" e o "java-cup-11a.jar")
# |Lexer (contem o .flex)
# |Parser (contem o .cup)
######################## Use a seu próprio risco ############################
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}")" && pwd )"
SRC="src"
BUILD_DIR="Jdc"

#main
	
main()
{
	clear	
	
	echo 'criando o diretório de instalação...'
	mkdir $BUILD_DIR
	mkdir $BUILD_DIR/lib/
	cp $SRC/Java_cup/java-cup-11a-runtime.jar $BUILD_DIR/lib
	verifica

	echo '-->copiando o semantico e interpretador'
	cp $SRC/Semantico/*.java $SRC/Tree
	cp $SRC/Interpretador/*.java $SRC/Tree
	verifica	

	echo '-->compilando AST e classes para controle e criação da tabela de simbolos'
	export CLASSGEN_HOME=$DIR/classgen
	export PATH=${PATH}:${CLASSGEN_HOME}/bin
	classgen -public -visitor -f $SRC/Tree/gen.cl
	mv org/tree/*.java $SRC/Tree
	javac -d $BUILD_DIR $SRC/Tree/*.java
	verifica
		
	echo '-->gerando o parser e os simbolos...'
	java -cp :$BUILD_DIR -jar $SRC/Java_cup/java-cup-11a.jar -expect 1 $SRC/Parser/mini_c.cup
	verifica
	mv sym.java $SRC/Parser
	mv parser.java $SRC/Parser
	verifica
	
	echo '-->compilando os simbolos do parser...'
	javac -d $BUILD_DIR $SRC/Parser/sym.java
	verifica
	
	echo '-->compilando o parser...'
	javac -d $BUILD_DIR -cp $BUILD_DIR:$BUILD_DIR/lib/* $SRC/Parser/parser.java
	verifica
	
	echo '-->gerando o analisador léxico...'
	jflex -nobak $SRC/Lexer/Lexer.flex
	verifica
	
	echo '-->compilando o analisador léxico...'
	javac -d $BUILD_DIR -cp $BUILD_DIR:$BUILD_DIR/lib/* $SRC/Lexer/Lexico.java;
	verifica

	echo '-->compilando o Jdc...'
	javac -d $BUILD_DIR -cp $BUILD_DIR:$BUILD_DIR/lib/* $SRC/Maluco/Maluco.java
	verifica

	echo '-->gerando o .jar'
	#cd Jdc/
	#jar cfm jdc.jar ../src/Manifest.txt org/
	jar cfm $BUILD_DIR/jdc.jar $SRC/Manifest.txt -C $BUILD_DIR org/
	verifica
	
	echo '-->limpeza!'
	#rm -r lib/
	#rm -r $BUILD_DIR/org/
	#cp $SRC/README.txt $BUILD_DIR
	verifica

	echo 'BUILD SUCCESSFUL'
	read
	exit 0
}

# verifica se o ultimo comando executado retornou um exit code de sucesso
# '$?' retorna o exit status do ultimo comando executado

verifica()
{
	if [ $? == 0 ]
	then
		echo '';
	else
		echo 'BUILD FAILED';
		read;
		exit 1;
	fi
}

main
