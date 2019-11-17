%a. Merge two sorted lists with removing the double values.

% merge_lists(L1,L2,R), L1, L2 lists
% Flux (i,i,o)
merge_lists(L1,[],L1):-!.

merge_lists([],L2,L2):-!.

merge_lists([H1|T1],[H2|T2],R):-
    exists(H1,T1) ;  exists(H1, T2),
    merge_lists(T1,[H2|T2],R),!.

merge_lists([H1|T1],[H2|T2],R):-
    exists(H2,T1) ;  exists(H2,T2),
    merge_lists([H1|T1],T2,R),!.

merge_lists([H1|T1],[H2|T2],[H1|R]):-
    H1 =< H2,
    merge_lists(T1,[H2|T2],R),!.

merge_lists([H1|T1],[H2|T2],[H2|R]):-
    merge_lists([H1|T1],T2,R),!.


% exists(E, L, R), E - integer, L - list
% Flux(i,i,o)
exists(H,[H|_]):-!.

exists(E,[_|T]):-
    exists(E,T).


%b. For a heterogeneous list, formed from integer numbers and 
%list of numbers, merge all sublists with removing the double values.
%[1, [2, 3], 4, 5, [1, 4, 6], 3, [1, 3, 7, 9, 10], 5, [1, 1, 11], 8] =>
%[1, 2, 3, 4, 6, 7, 9, 10, 11].

% merge_all_sublists(L,R), L - list
% Flux (i, o)
merge_all_sublists([],[]):-!.

merge_all_sublists([H],H):-
    is_list(H),!.

merge_all_sublists([H|T],R2):-
    is_list(H),
    merge_all_sublists(T,R1),
    merge_lists(H,R1,R2),!.

merge_all_sublists([_|T],R):-
    merge_all_sublists(T,R).

