% sort a list without removing the duplicates

% sort_list(L, R), L- list
% Flux (i,o)
sort_list([],[]):-!.

sort_list([T],[T]):-!.

sort_list([H1|T], R):-
    sort_list(T,R1),
    insert_pos(H1, R1, R).

% insert_pos(E,L,R), E - element, L - list
% Flux (i,i,o)
insert_pos(E,[],[E]):-!.

insert_pos(_,[],[]):-!.

insert_pos(E,[H|T],[H|R]):-
    E >= H,
    insert_pos(E,T,R),!.

insert_pos(E,T,[E|T]):-!.