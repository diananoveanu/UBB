a = 0;
b = 1;
x = [a:0.001:b];

f = @(x) 2 ./(1+x.^2);


hold on;
plot(x, f(x), 'Color', 'r');
coords = [[0, 0]; [0, f(0)]; [1, f(1)]; [1, 0]];
patch(coords(:, 1), coords(:, 2), 'b');

legend('f(x)', 'trapezium');