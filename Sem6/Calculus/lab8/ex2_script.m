a=1.4;
b = 2;
c = 1;
d = 1.5;
f = @(x, y) log(x + 2*y);

integral = trapezium_double(a, b, c, d, f);

printf("Integral approximation of Int[%d,%d] Int[%d,%d] ln(x + 2*y) = %d\n", a, b, c, d, integral);
