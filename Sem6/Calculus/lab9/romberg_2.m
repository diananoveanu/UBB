function y=romberg_2(a, b, f, n)
  h = b - a;
  if n == 0
    y = h/2*(f(a) + f(b));
  else
    q_last = romberg_2(a, b, f, n-1);
    rng = (2*[1:2^(n-1)] - 1) ./ 2^n;
    s = sum(f(a + rng .* h));
    y = 1/2 * q_last + h/2^n*s;
  end
  
end