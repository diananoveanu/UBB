% TODO - format lists to work on numbers of different lenghts

% a. Write a predicate to determine the sum of two numbers 
% written in list representation.

% add_lists(L1,L2,C2,R), L1, L2 - lists, C2 - integer
% Flux (i, i, i, o)
add_lists([],L2,_,L2):-!.

add_lists(L1,[],_,L1):-!.

add_lists([H1],[H2],C2,R):-
    add_pos(H1,H2,0,C2,Rez),
    R = [Rez],!.

add_lists([H1|T1],[H2|T2],C2,[R|R1]):-
    add_lists(T1,T2,C,R1),
    add_pos(H1,H2,C,C2,R),!.

% add_zeros(L, N, R), L - list, N - int
% Flux (i, i, o)


% add_pos(A,B,C,R), all integers
% Flux (i, i, i, o)

add_pos(A,B,C1,C2,R):-
    S is A + B,
    S1 is S + C1,
    S2 is S1 mod 10,
    C3 is S1 div 10,
    C2 is C3,
    R is S2,!.

% b. For a heterogeneous list, formed from integer numbers and list of digits, write a predicate to compute the sum of all numbers represented as sublists.
% Eg.: [1, [2, 3], 4, 5, [6, 7, 9], 10, 11, [1, 2, 0], 6] => [8, 2, 2].

% add_all_sublists(L,R), L - list
% Flux (i, o)
add_all_sublists([],[]):-!.

add_all_sublists([H],H):-
    is_list(H),!.

add_all_sublists([H|T],R2):-
    is_list(H),
    add_all_sublists(T,R1),
    add_lists(H,R1,R2),!.

add_all_sublists([_|T],R):-
    add_all_sublists(T,R).

