%exists(E, L) - functie care verifica daca elementul E apartine listei L
%E - element
%L - lista
%FLux (i, i), (o, i)
exists(E, [E|_]).
exists(E, [_|T]):-
	exists(E, T).
    
% getNmemb(N, L):-functie care extrage un element din multimea
% tutror functiilor definite pe multimea A cu 3 elemente si multimea B cu 2*N + 1 elemente
% getNmemb(N, L), L - list, N - integer
% Flux (i, o)
getNmemb(0, []) :- !.

getNmemb(N, [X|Rez]):-
    N1 is N-1,
    exists(X, [-1, 0, 1]),
    getNmemb(N1, Rez).

valid([]):-!.
valid([_]):-!.
valid([H1,H2|T]):-
    H1 =\= H2,!,
    valid([H2|T]).
     

%genPar(N, Rez) - functie care genereaza toate sirurile corecte de 2*N + 1 elemente
% N - integer, Rez - result
genAllSubstr(0, []):-!.

genAllSubstr(N, Rez):-
    K is 2*N,
    K1 is K + 1,
    getNmemb(K1, Rez),
    valid(Rez).



