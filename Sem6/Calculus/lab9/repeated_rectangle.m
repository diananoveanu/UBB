function y = repeated_rectangle(a, b, f, n)
  h = (b-a)/n;
  x1 = a + h/2;
  s = f(x1);
  i = [2:n];
  xi = x1 + (i-1)*h;
  s = s + sum(f(xi(2: end)));
  y = h * s;
end