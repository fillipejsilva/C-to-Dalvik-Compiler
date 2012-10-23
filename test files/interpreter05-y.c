#include <stdio.h>
#include <stdlib.h>
int main() {
  char w;
  float x;
  char z;
  void oi;

  w = 'a';
  x = 97.1;
  /* y = 97;*/
  /*z = 'a' + y; - y inexistente no escopo*/

  z = 'b';
  if(w == x) {
    printf("iguais\n");
  } else {
    printf("diferentes\n");
  }

  x = x * x;

/*  
  if(!x){
    x = x;
  } else {
    z = z;
  }
 */
 
  printf("asdasdsa: %c", x);
  return 1;
}
