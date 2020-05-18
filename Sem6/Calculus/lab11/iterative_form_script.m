
n = 6;
line = [3, -1, zeros(1, n-2)];


A = toeplitz(line);
b = [2 1 1 1 1 2]';


x0 = zeros(size(b));
maxIter = 100;
xe = A\b;
omega = 1.1;
err = 1e-3;

[x_jacobi, iter_jacobi] = jacobi_iter(A, b, x0, err);
[x_gauss, iter_gauss] = gaussSeidel_iter(A, b, x0, err);
[x_relax, iter_relax] =  relax_iter(A, b, omega, x0, err);

printf("norm(x_jacobi - xe)/norm(xe) = %d, with %d iterations\n", norm(x_jacobi - xe)/norm(xe), iter_jacobi);
printf("norm(x_gauss - xe)/norm(xe) = %d, with %d iterations\n", norm(x_gauss - xe)/norm(xe), iter_gauss);
printf("norm(x_relax - xe)/norm(xe) = %d, with %d iterations\n", norm(x_relax - xe)/norm(xe), iter_relax);