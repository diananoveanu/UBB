T = [0, 10, 20, 30, 40, 60, 80, 100];
P = [0.0061, 0.0123, 0.0234, 0.0424, 0.0738, 0.1992, 0.4736, 1.0133];

P1 = polyfit(T, P, 2);
P2 = polyfit(T, P, 4);

T1 = polyval(P1, 45);
T2 = polyval(P2, 45);
TrueVal = 0.095848;

printf("Approx Error for degree 1 %f\n", (TrueVal - T1)^2);
printf("Approx Errpr for degree 4 %f\n", (TrueVal - T2)^2);

xx = [0:0.1:100];

hold on;
plot(T, P, '+');

plot(xx, polyval(P1, xx), 'Color', 'r');
plot(xx, polyval(P2, xx), 'Color', 'b');

legend('interp points', 'P1', 'P2');