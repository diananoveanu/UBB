% 4. The list a1...an is given. 
% Write a predicate to determine all sublists strictly ascending 
% of this list a.

% get_all_sublists - wrapper to get all of the sublists into one big list
% get_all_sublists(L, R), L - list
% Flux (i, o)
get_all_sublists(L, R):-
    findall(X, ascending_sublists(L, X), R).


% ascending_sublists - get ascending sublists of a list
% ascending_sublists(L, R), L - list
% Flux (i, o)
ascending_sublists([],[]).

ascending_sublists(L,R):-
    subsets(L, R1),
    ifasc(R1),
    R = R1.


% subsets - get all subsets of a list
% subsets(L, R), L - list
% Flux (i, o)
subsets([],[]).

subsets([H|T],[H|R]):-
    subsets(T,R).

subsets([_|T],R):-
    subsets(T,R).


% ifasc - check if list is ascending
% ifasc(L), L - list
% Flux (i)
ifasc([H1,H2|T]):-
    H1 < H2,
    ifasc([H2|T]),!.

ifasc([H1,H2]):-
    H1 < H2.