x = [1: 2: 115];
y = sqrt(x);

[n, m] = size(x);

approx = Aitken(x, y, 115);

printf("Approximation of sqrt(115) using Aitken's algorithm = %f \n",
approx(m, m));