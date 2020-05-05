a = 0;
b = 1;
x = [a:0.001:b];

f = @(x) 2 ./(1+x.^2);

integral = simpson(f, a, b, 200);

printf("Approximation of Int[%d , %d] f = % d\n", a,b, integral);

