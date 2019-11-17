%aranjamente - sunt de fapt permutari de submultimi :)
%aranjamente(L, K, R) - aranjamentele listei L de lungime K
aranjamente(L, K, R):-
    comb(L, K, Rez),
    perm(Rez, R).


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


% function to get subsets of a list
% subsets(L, R), L - list
% Flux (i, o)
subsets([],[]).
subsets([_|T],R):-
    subsets(T,R).
subsets([H|T],[H|R]):-
    subsets(T,R).


% function to generate all subsets of a given length of a list
% find_len_subsets(L, K, R), L - list, K - integer
% Flux (i, i, o)
find_len_subsets(_,0,[]).
find_len_subsets([_|T],K,R):-
    K > 0,
    find_len_subsets(T,K,R).
find_len_subsets([H|T],K,[H|R]):-
    K2 is K-1,
    K > 0,
    find_len_subsets(T,K2,R).