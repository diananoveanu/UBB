a = 0;
b = pi;

f = @(x) 1 ./ (4+sin(20 .*x));

n1 = 10;
n2 = 30;

approx1 = simpson(f, a, b, n1);
approx2 = simpson(f, a, b, n2);

printf("Approximation of f for n=%d is %d\n", n1, approx1);
printf("Approximation of f for n=%d is %d\n", n2, approx2);

