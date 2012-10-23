#include <stdio.h>
#include <stdlib.h>
float func() {
	/*retorno diferente do tipo declarado*/
	int y;
	float x;

	return y;
}
int main(){

	int x;
	while(x < 5) {
		x = x + 1;
    }
	printf("x = %d",x);

	return 1;
}