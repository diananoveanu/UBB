erf_val = erf(0.5);
a = 0;
b = 0.5;
n1 = 4;
n2 = 10;

f = @(x) e .^ (-x .^2);

approx1 = 2/sqrt(pi)*simpson(f, a, b, n1);
approx2 = 2/sqrt(pi)*simpson(f, a, b, n2);

err1 = abs(approx1 - erf_val);
err2 = abs(approx2 - erf_val);

printf("Error for n1=%d is %d\n", n1, err1);
printf("Error for n2=%d is %d\n", n2, err2);

