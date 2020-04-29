x = [64 81 121];
y = [8 9 11];
xx = [115];

yy = Lagrange(x, y, xx);

printf("Approximate value of sqrt(115) = %f \n", yy(1));
