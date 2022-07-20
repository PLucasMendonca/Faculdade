#include <stdio.h>

int f(int n){
if (n < 0) return 0;
if (n = 0) return 1;
else{
return n + (n+1);
};
}
int main (void){

int n = 8;
printf("f(%d):\n",n);

    return 0;
}