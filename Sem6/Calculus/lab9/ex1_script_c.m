a = 1;
b = 1.5;
x = [a:0.001:b];

f = @(x) e .^ (-x .^2);

n1 = 150;
n2 = 500;

printf("Approximation of f with n=%d is %d\n", n1, repeated_rectangle(a, b, f, n1));
printf("Approximation of f with n=%d is %d\n", n2, repeated_rectangle(a, b, f, n2));