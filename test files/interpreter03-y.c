#include <stdio.h>
#include <stdlib.h>
int main() {
  float x;
  int y;

  x = 1.5;
  y = 4;

  x = y / x;
  printf("%f", x);

  return 1;
}

