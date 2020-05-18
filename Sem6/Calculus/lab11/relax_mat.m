function [xx,i]=relax_mat(A,b,omega,x0,err,niter)
  % A - matricea sistemului
  % b - vectorul solutie
  % x0 - valoarae initiala a lui x
  % err - erroarea
  % niter - numar de iteratii
  %x - solutia finala
  
  [m,n] = size(A);
  x = x0(:);
  M = 1/omega * diag(diag(A)) + tril(A, -1);
  N = M-A;
  for i=1:niter
   xn = M\(N*x+b);
   if norm(xn -x ,inf) < err * norm(xn, inf) 
      xx = xn;
      return
   end
   x = xn;
end