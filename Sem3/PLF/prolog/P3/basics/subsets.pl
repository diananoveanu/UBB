% function to generate all subsets of a list
% find_subsets(L, R), L - list
% Flux (i, o)
find_subsets(L, R):-
    findall(R1, subsets(L, R1), R).

% function to get subsets of a list
% subsets(L, R), L - list
% Flux (i, o)
subsets([],[]).
subsets([_|T],R):-
    subsets(T,R).
subsets([H|T],[H|R]):-
    subsets(T,R).

% isSubsetOk(S) :- True, False

%generateSets(L, Sum, S):-
%    subsets(L, S),
%    isSubsetOk(S, Sum).

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


% subsets that have the sum equal to a given sum
% subsets(L, K, R), L - list, K - integer
% Flux (i, i, o)
combSum(_,0,[]).
combSum([_|T],K,R):-
    K>0,
    combSum(T,K,R).
combSum([H|T],K,[H|R]):-
    K2 is K-H,
    K2 >=0,
    combSum(T,K2,R).

