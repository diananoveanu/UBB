X = [0 : 0.01 : 6];
lambda_f = @(x) exp(sin(x));

plot(X, lambda_f(X), 'Color', 'r'); 

x = linspace(0, 6, 13);
y = Newton(x, lambda_f(x), X);

hold on

plot(X, y', 'Color', 'b')

plot(x, lambda_f(x), 'x', 'Color', 'k')

legend('e^(sinx)', 'newton', 'interp points');