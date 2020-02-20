% wrapper function to get all anwers in a list
wrapper(L, R):-
	findall(R1, perm(L, R1), R).

% function to compute the permutations of a list
% perm(L, R), L - list
% Flux (i, o)
perm([],[]).
perm([H|T],R):-
    perm(T,R1),
    insertAllPos(H,R1,R2).
    isPermOk(R2),
    R is R2.


%modul(X, Y)
%Flux (i,o)
modul(X, Y):-
    X < 0,
    Y is -X.

modul(X,X).

%isPermOk(Perm)

%genPerms(P):-
%    perm(P, R),
%    isPermOk(R)
isPermOk([]):- true,!.

isPermOk([H1,H2|T]):-
    Z is H1-H2,
    modul(Z,Z1),
    Z1 >= 3,!,
    true.


% function to insert an element on all possible positions in a list
% insertAllPos(E, L, R), E - integer, L - list
% Flux (i, i , o)
insertAllPos(E,[],[E]).
insertAllPos(E,[H|T],[E,H|T]).
insertAllPos(E,[H|T],R):-
    insertAllPos(E,T,R1),
    R = [H|R1].


