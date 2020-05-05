a=1; b = 2;
f = @(x) x .* log(x);
n = 1;
true_value = 0.636294368858383;
err = 1e-4;

while abs(repeated_trapezium(a, b, f, n) - true_value) > err
  n = n + 1;
end

printf("Minimum value of n is %d and the approximation is: %d\n", n, repeated_trapezium(a, b, f, n));