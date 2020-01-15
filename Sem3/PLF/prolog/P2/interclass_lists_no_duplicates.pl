%Function to interclass two lists
%sort(A, B, C)
%A - list
%B - list
%C - list
%Flux (i, i, o)
sort([],L,L):-!.
sort(L,[],L):-!.
sort([Head1|Tail1], [Head2|Tail2], [Head1|L]) :- 
    Head1 =< Head2, !,
    sort(Tail1, [Head2|Tail2], L).

sort([Head1|Tail1], [Head2|Tail2], [Head2|L]) :-
    Head2 < Head1,  
    sort([Head1|Tail1], Tail2, L).

%Function to remove the duplicates on consecutive positions from a sorted list
%remove_dup(A, B) 
%A - list
%B - list
%Flux (i, o)
remove_dup([], []):-!.
remove_dup([A], [A]):-!.

remove_dup([H,H1|T], R):-
    H =:= H1, !,
    remove_dup([H1|T], R).

remove_dup([H,H1|T], [H|R]):-
    remove_dup([H1|T], R).

%Function to interclass two lists without keeping the duplicates
%merge(A, B, C)
%A - list
%B - list
%C - list
%Flux (i, i, o)
merge(A, B, C):-
    sort(A, B, R),
    remove_dup(R, C).

%Function to interclass the sublists of a list
%merge_lists(A, B)
%A - heterogenous list
%B - list
%Flux(i, o)
merge_lists([], []).

merge_lists([H|T], R):-
    is_list(H), !,
    merge_lists(T, R1),
    merge(H, R1, R).

merge_lists([_|T], R):-
    merge_lists(T, R).

