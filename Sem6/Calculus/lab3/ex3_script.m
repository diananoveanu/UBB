X = [0:0.001:10];
lambda_f = @(x) (1 + cos(pi * x)) ./ (1 + x);

plot(X, lambda_f(X), 'Color', 'r')

x = linspace(0, 10, 21);
y = Lagrange(x, lambda_f(x), X);

hold on

plot(X, y', 'Color', 'b')

legend('(1+cos(pi*x))/(1 + x)', 'lagrange')