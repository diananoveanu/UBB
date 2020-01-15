%1.a Write a predicate to determine the lowest common multiple of a list formed from integer numbers.

% cmmmc_list(L, R), L - list
% Flux (i,o)
cmmmc_list([H], R):-
    R is H.

cmmmc_list([H|T], R) :-
    cmmmc_list(T,R2),
    cmmmc(H,R2,R3),
    R is R3,!.

% cmmmc(A,B,R), A,B integers
% Flux (i,i,o)
cmmmc(A, B, R):-
    cmmdc(A,B,R1),
    R2 is A * B,
    R3 is R2 div R1,
    R is R3.

% cmmdc(A,B,R), A, B integers
% Flux(i,i,o)
cmmdc(H1,0,H1):-!.
cmmdc(A,B,C):-
    D is A mod B,
    cmmdc(B,D,C).
