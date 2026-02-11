#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

float soma(float a, float b) {
    float r;
    r = (a + b);
    return r;
}

int main(void) {
    float x;
    float y;
    float resultado;
    x = 5.0;
    y = 3.0;
    resultado = soma(x, y);
    printf("%f\n", resultado);
    return 0;
}
