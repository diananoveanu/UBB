function A = Aitken(x, y, xx)
  %x - input points
  %y - corrseponding points
  %xx - test input
  %A - result of Aitken's algorithm
  [n, m] = size(x);
  f = [y'];
  for i = 0:m-1
    for j = 0:i-1
      d = det([f(j + 1, j + 1), x(j + 1) - xx; f(i + 1, j + 1), x(i + 1) - xx]);
      f(i + 1, j + 2) = 1 / (x(i + 1) - x(j + 1))* d;
    end
  end
  
  A = f;
end