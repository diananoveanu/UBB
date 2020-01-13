
% a. Replace all occurrences of an element from a list with another element e.
% Flux (i,i,i,o)
replace_all([],_,_,[]) :- !.

replace_all([H|T],H,P,[P|R]):-
    replace_all(T,H,P, R),!.

replace_all([H|T],O,P,[H|R]):-
    replace_all(T,O, P, R).



%b. For a heterogeneous list, formed from integer numbers and list of numbers, 
%define a predicate to determine the maximum number of the list, 
%and then to replace this value in sublists with the maximum value of sublist.

%Flux (i,o)
get_max_list([],-1):-!.
get_max_list([H|T],H):-
    get_max_list(T, R),
    H > R, !.
get_max_list([_|T], P):-
    get_max_list(T, P).

%Flux (i,o)
get_max([], -1):-!.
get_max([H|T], P):-
    is_list(H), !,
    get_max(T,P).
get_max([H|T], H):-
    get_max(T,R),
    H>R,!.
get_max([_|T], R):-
    get_max(T,R).

wrapper([],[]):-!.
wrapper(T,P):-
    get_max(T, R),
    wrapper_aux(T, R, P).

wrapper_aux([],_,[]):-!.
wrapper_aux([H|T], R, [K|P]):-
    is_list(H),
    get_max_list(H, M),
    replace_all(H,R,M,K),
    wrapper_aux(T, R, P),!.

wrapper_aux([H|T], R, [H|P]):-
    wrapper_aux(T, R, P).

