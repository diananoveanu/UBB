X = [-5: 0.01: 5];

lambda_f = @(x) sin(2*x);
lambda_df = @(x) 2*cos(2*x);

plot(X, lambda_f(X), 'Color', 'r');

hold on

x = linspace(-5, 5, 15);
f = lambda_f(x);
df = lambda_df(x);

plot(x, f, 'x', 'Color', 'k');


[y,_] = Hermite(x, [f; df], X);



plot(X, y, 'Color', 'b');

legend('sin(2*x)', 'interp points','hermite');