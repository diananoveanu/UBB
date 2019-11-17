% wrapper function to get all anwers in a list
wrapper(L, R):-
	findall(R1, perm(L, R1), R).

% function to compute the permutations of a list
% perm(L, R), L - list
% Flux (i, o)
perm([],[]).
perm([H|T],R):-
    perm(T,R1),
    insertAllPos(H,R1,R).

% function to insert an element on all possible positions in a list
% insertAllPos(E, L, R), E - integer, L - list
% Flux (i, i , o)
insertAllPos(E,[],[E]).
insertAllPos(E,[H|T],[E,H|T]).
insertAllPos(E,[H|T],R):-
    insertAllPos(E,T,R1),
    R = [H|R1].


