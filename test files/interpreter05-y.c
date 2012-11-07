#include <stdio.h>
#include <stdlib.h>
/*int _2mais2(int x, float x) { Parametros de mesmo nome encontrados*/
int _2mais2() {
  return 2 + 2;
}
int main() {
  char w;
  float x;
  /*float x; redeclaracao de variáveis no mesmo escopo*/
  char z;
  void oi;
  int inteiro;

  w = 'a';
  x = 97.1;
  /* y = 97;*/
  /*z = 'a' + y; - y inexistente no escopo*/

  z = 'b';
  /*
  if(w == x) {
    printf("iguais\n");
  } else {
    printf("diferentes\n");
  }
 */
  x = x * x;

/*  
  if(!x){
    x = x;
  } else {
    z = z;
  }
 */
  /**
   * Não é permitido imprimir um tipo diferente do que foi fornecido como parâmetro.
   * printf("asdasdsa: %c", x);
   */
  
  /**
   * Não é permitido ler um tipo diferente do que foi fornecido como parâmetro.
   * scanf("%c", &x);
   */

  /**
   * Tipo de expressão não condiz com o identificador w
   * w = 1.5 + 1; 
   */
  
  /**
   * Erro na chamada de Funcao, este tipo de chamada não pode passar para a parte
   * do interpretador
   */
  inteiro = _2mais2();

  /* return w; Retorno da main deve ser do tipo int.*/

  return 0;
}
