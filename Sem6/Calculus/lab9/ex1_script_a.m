a = 1;
b = 1.5;
x = [a:0.001:b];

f = @(x) e .^ (-x .^2);


printf("Approximation for f using rectangle formula: %d\n", rectangle_formula(a, b, f));