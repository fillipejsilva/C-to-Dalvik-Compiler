#include <stdio.h>
#include <stdlib.h>

int main() {

	int x;

	printf("Digite um numero inteiro");
	scanf("%d",&x);

    if(x >= 5) {
      printf("o valor de x é: %d",x);
    } else {
      printf("o valor de x é muito baixo");
    }

	return 1;
}