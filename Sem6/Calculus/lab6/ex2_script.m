axis([0 1 0 1]);

[x, y] = ginput(5);

hold on;

plot(x, y, '+');

rng = linspace(0, 1, 5);

xx = spline(rng, x, rng);
yy = spline(rng, y, rng);

plot(xx, yy);

legend('interp points', 'cubic spln');