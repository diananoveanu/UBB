function [a, b] = l_sq(x, y)
  %x - input values
  %y - output values
  
  [n,m] = size(x);
  
  denom = (m * (x * x') - sum(x) * sum(x));
  
  a = m * x * y' - sum(x) * sum(y);
  a = a / denom;
  
  b = (x * x') * sum(y) - (x * y') * sum(x);
  b = b / denom;
  
end