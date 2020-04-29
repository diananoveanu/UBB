function Y = Newton(x, y, xx)
  %x - input points
  %y - corrseponding points
  %xx - test input for result
  %Y - result
  
  [m, n] = size(x);
  A = x';
  A = [(0 : n - 1)', A, y'];
  
  for i = 1 : n-1
    A = [A, [(A(2: (end - i + 1), end) - A(1: end - i, end))./(A(i + 1: end, 2) 
    .- A(1: end - i, 2)); NaN(i, 1)]];
  end
 
 A = A(1, 3: end);
 
 Y = A * [ones(size(xx')), cumprod(xx' - x(1 : end - 1), dim=2)]';

end