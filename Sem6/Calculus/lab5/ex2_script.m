x = [1, 2];
f = [0, 0.6931];
df = [1, 0.5];

[approx_value,_] = Hermite(x, [f; df], [1.5]);

true_value = log(1.5);

printf("Absolute error for ln(1.5) = %f \n", abs(true_value - approx_value));