function Y=Lagrange(x, y, xx)
  %x - input points
  %y - corrseponding points
  %xx - test input for result
  %Y - result 
  
  [m, n] = size(x);
  A = [];
  for i = 1:n
    difference = x(i) - x;
    A = [A, 1/(prod(difference(1 : i - 1)) * prod(difference(i + 1: end)))];
  end
  
  Y = sum(A.* y ./ (xx' - x), ind=2) ./ sum(A ./ (xx' - x), ind=2);
  
end