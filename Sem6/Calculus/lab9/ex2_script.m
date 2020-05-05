a = 0;
b = 1;

f = @(x) 2 ./ (1 + x .^ 2);
err = 1e-4;
true_value = pi/2;

n = 1;
rmb_2 = romberg_2(a, b, f, n);

while abs(rmb_2 - true_value) > err
  n = n + 1;
  rmb_2 = romberg_2(a, b, f, n);
end

printf("Convergence with romberg_2 after n = %d, with value = %d\n", n, rmb_2);



n = 1;
rmb_5 = romberg_5(a, b, f, n);

while abs(rmb_5 - true_value) > err
  n = n + 1;
  rmb_5 = romberg_5(a, b, f, n);
end

printf("Convergence with romberg_5 after n = %d, with value = %d\n", n, rmb_5);