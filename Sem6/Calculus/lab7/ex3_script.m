axis([0, 3, 0, 5]);

[x, y] = ginput(10)

p = polyfit(x, y, 2);

hold on;

plot(x, y, 'x');
xx = [0: 0.01: 3];

plot(xx, polyval(p, xx));