% a. Sort a list with keeping double values in resulted list. 
% E.g.: [4 2 6 2 3 4] --> [2 2 3 4 4 6]


% sort_list(L, R), L- list
% Flux (i,o)
sort_list([],[]):-!.

sort_list([T],[T]):-!.

sort_list([H1|T], R):-
    sort_list(T,R1),
    insert(H1, R1, R).


% insert(E,L,R), E - element, L - list
% Flux (i,i,o)
insert(E,[],[E]):-!.

insert(_,[],[]):-!.

insert(E,[H|T],[H|R]):-
    E >= H,
    insert(E,T,R),!.

insert(E,T,[E|T]):-!.


% b. For a heterogeneous list, formed from integer numbers and list of numbers, 
% write a predicate to sort every
% sublist, keeping the doubles.
% Eg.: [1, 2, [4, 1, 4], 3, 6, [7, 10, 1, 3, 9], 5, [1, 1, 1], 7] => [
% 1, 2, [1, 4, 4], 3, 6, [1, 3, 7, 9, 10], 5, [1, 1, 1], 7].

% sort_sublists(L,R), L - list
% Flux (i,o)
sort_sublists([],[]):-!.

sort_sublists([H|T],[R1|R2]):-
    is_list(H),
    sort_list(H,R1),
    sort_sublists(T, R2),!.

sort_sublists([H|T],[H|R]):-
    sort_sublists(T,R),!.