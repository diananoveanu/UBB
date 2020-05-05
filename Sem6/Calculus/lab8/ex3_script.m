r = 110;
p = 75;
a = 0;
b = 2*pi;
n1 = 5;
n2 = 10;

first_expr = 60*r/(r*r - p*p);

to_be_integrated = @(x) sqrt(1-(p/r)^2*sin(x));

integral_approx1 = repeated_trapezium(a, b, to_be_integrated, n1);
integral_approx2 = repeated_trapezium(a, b, to_be_integrated, n2);

integral_value1 = first_expr * integral_approx1;
integral_value2 = first_expr * integral_approx2;

printf("Integral value for %d is: %d\n", n1, integral_value1);
printf("Integral value for %d is: %d\n", n2, integral_value2);