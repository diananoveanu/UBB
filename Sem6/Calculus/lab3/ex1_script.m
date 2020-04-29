x = [1930 1940 1950 1960 1970 1980];
y = [123203 131669 150697 179323 203212 226505];
xx = [1955 1995];

yy = Lagrange(x, y, xx);

printf("Approximate value of f(1955) = %f, approximate value of f(1995) = %f \n"
, yy(1), yy(2));
