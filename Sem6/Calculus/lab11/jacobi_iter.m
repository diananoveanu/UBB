function [x, cnt] = jacobi_iter(A, b, x0, err)
  n = size(b);
  cnt = 0;
  x = x0;
  while cnt >= 0
    cnt = cnt + 1;
    for i = 1:n
      s = 0;
      for j = 1:n
        if (i ~= j)
          s = s + A(i,j)*x0(j);
        end
      end
      x(i) = (b(i) -s)/(A(i,i));
    end
    if norm(x - x0) < err
      return
    end
    x0 = x;
  end
end