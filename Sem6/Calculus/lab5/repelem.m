function ret = repelem(X, m)
  [_, n] = size(X);
  R = [[1:n]; m * ones(1, n)];
  ret = repelems(X, R);
end