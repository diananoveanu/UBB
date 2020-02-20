% Flow(i,o)
f([], 0).

f([H|T],S):- 
    f([H|T],S1), 
    S1 < H, !, 
    S is H.

f([_|T], S):-
    f(T, S1),
    S is S1.
