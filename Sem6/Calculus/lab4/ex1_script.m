x = [1 1.5 2 3 4];
y = [0 0.17609 0.30103 0.47712 0.60206];
xx = [2.5 3.25];

yy = Newton(x, y, xx);

printf("Approximation of lg(2.5) = %f, approximation of lg(3.25) = %f \n",
yy(1), yy(2));

y_i = [10:35] ./ 10;

printf("Maximum interpolation error is %f \n",
max(abs(log10(y_i) - Newton(x, y, y_i))))