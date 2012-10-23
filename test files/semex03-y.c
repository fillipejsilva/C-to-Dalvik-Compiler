#include <stdio.h>
#include <stdlib.h>
float func() {
	int y;
	float x;
	/*variável inexistente dentro do escopo*/
	return z;
}
int main(){

	int x;
	while(x < 5) {
		x = x + 1;
    }
	printf("x = %d",x);

	return 1;
}