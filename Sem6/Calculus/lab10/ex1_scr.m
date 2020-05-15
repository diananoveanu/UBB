A = [1 1 1 1; 2 3 1 5; -1 1 -5 3; 3 1 7 -2];
b = [10 31 -2 18]';

printf("System solution...\n");
display(gaussPivoting(A, b));