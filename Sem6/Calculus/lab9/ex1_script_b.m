a = 1;
b = 1.5;
x = [a:0.001:b];

f = @(x) e .^ (-x .^2);



hold on;
axis([a b f(b) f(a)])
plot(x, f(x), 'Color', 'r');
rectangle('Position', [a 0 b f((a + b)/ 2)], 'FaceColor', [0 .5 .5]);
legend('f(x)', 'rectangle');