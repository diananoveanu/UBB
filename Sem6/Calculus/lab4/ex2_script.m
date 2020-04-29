x = [1 2 3 4 5];
y = [22 23 25 30 28];
xx = [2.5];

yy = Newton(x, y, xx);

printf("Pounds of potatoes for 2.5 pounds fertilizer = %f\n", yy(1));

xx = [1: 0.01 : 5];
yy = Newton(x, y, xx);


plot(x, y, 'x', 'Color', 'b');
hold on
plot(xx, yy, 'Color', 'r');
