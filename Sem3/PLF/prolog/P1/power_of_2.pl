% check if a number is a power of 2
% is_power_of_2(N), N - integer
% Flux (i)
is_power_of_2(N):-
    N1 is N - 1,
    K is N /\ N1,
    K is 0.