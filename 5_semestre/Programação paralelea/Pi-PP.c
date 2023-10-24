#include <stdio.h>
#include <omp.h>

static long num_steps = 1000000;
double step;

int main() {
    int num_threads = omp_get_max_threads();
    step = 1.0 / (double)num_steps;

    double sum = 0.0;
    double start_time = omp_get_wtime();

    #pragma omp parallel for reduction(+:sum)
    for (int i = 0; i < num_steps; i++) {
        double x = (i + 0.5) * step;
        double f_x = 4.0 / (1.0 + x * x);
        sum += f_x;
    }

    double pi = step * sum;
    double end_time = omp_get_wtime();

    printf("Estimated pi = %f\n", pi);
    printf("Time taken: %f seconds\n", end_time - start_time);

    return 0;
}