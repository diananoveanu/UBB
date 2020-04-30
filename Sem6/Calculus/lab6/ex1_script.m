x = [0 pi/2 pi 3 * pi/2 2*pi];
y = sin(x);
dy = cos(x);

sp1 = spline(x, y, pi/4);
sp2 = spline(x, [dy(1) y dy(5)], pi/4);

printf("Value of function at pi/4: %f\n", sin(pi/4));
printf("Value of cubic natural spline function at pi/4: %f\n", sp1);
printf("Value of cubic clamped spline function at pi/4: %f\n", sp2);

xx = [0:0.01:pi];
hold on;

plot(x, y, '+');

plot(xx, sin(xx), 'Color', 'r');
plot(xx, spline(x, y, xx), 'Color', 'b');
plot(xx, spline(x, [dy(1), y, dy(5)], xx), 'Color', 'k');

legend('Interp points', 'sin(x)', 'cubic spln', 'clamped spln');