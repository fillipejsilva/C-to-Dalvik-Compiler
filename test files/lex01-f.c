#include <stdio.h>
#include <stdlib.h>
float func() {
	//HAHA
	float z;
    printf( " Ola meu nome e Joao " );
	return z;
}
int main(){

	/**/

	int x;
	int i;
	int soma;

	scanf("%d",&x);

	i = 0;
	soma = 0;

    while(i < 4) {
		soma = soma + x;
		i = i + 1;
    }

	printf("4 x numero digitado = %d",soma);

	return 1;
}
