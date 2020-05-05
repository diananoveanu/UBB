a = 1;
b = 3;
err = 1e-4;

true_value = -1.4260247818;

f = @(x) 100 ./ x .^2 .* sin(10 ./ x);

n1 = 50;
n2 = 100;

val_adquad = adquad(a, b, f, err);
val_simpson1 = simpson(a, b, f, n1);
val_simpson2 = simpson(a, b, f, n2);

printf("Error for adquad: %d\n", abs(val_adquad - true_value));
printf("Error for simpson with n=%d is %d\n", n1, abs(val_simpson1 - true_value));
printf("Error for simpson with n=%d is %d\n", n2, abs(val_simpson2 - true_value));
