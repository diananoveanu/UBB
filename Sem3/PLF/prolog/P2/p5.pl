%a. Substitute all occurrences of an element of a list 
% with all the elements of another list.
% subst(L, e, S, R)
% Flux (i,i,i,o)s
subst([],_,_,[]):-!.
subst(L,_,[],L):-!.
subst([H|T],H,S,R):-
    insert_list(S,T,R1),
    subst(R1,H,S,R),!.
subst([H|T],E,S,[H|R]):-
    subst(T,E,S,R).

% insert_list(S,L,R) - insert list S at the beginning of list R
% Flux (i,i,o)
insert_list([],R,R):-!.
insert_list(R,[],R):-!.
insert_list([H],T,[H|T]):-!.
insert_list([H1|T1],T,[H1|R1]):-
	insert_list(T1,T,R1).    

%b. For a heterogeneous list, formed from integer numbers and 
%list of numbers, replace every sublist with the position of the
%maximum element from that sublist
% Flux (i,o)

replace_pos([],[]):-!.
replace_pos([H|T],R):-
    

get_pos_max(L,R):-
    get_max(L,R1),
    find_pos(L,R1,R2),
    R is R2,!.

find_pos()

get_max([], -1):-!.
get_max([H|T],R):-
    get_max(T,R1),
    H > R1, 
    R is H, !.
get_max([_|T],R):-
    get_max(T,R1),
    R is R1.
    