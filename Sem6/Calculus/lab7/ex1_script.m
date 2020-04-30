x = [1:7];
y  = [13, 15, 20, 14, 15, 13, 10];

[a, b] = l_sq(x, y)

plot(x, y, '+');
hold on;

xx = [1:0.001:10];

plot(xx, a*xx + b);
for i = 1 : 7
  plot([x(i), x(i)], [y(i), x(i) * a + b]);
end

E = norm(y - a * x - b)^2