a=0;
b=1;

f = @(x)  2 ./(1 + x .^2);

val = trapezium(a, b, f);

printf("Approximation usint trapezium: %d\n", val);

