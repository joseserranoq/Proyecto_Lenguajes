:- dynamic dist/3.
dijkstra(Vertex, Ss):-
  create(Vertex, [Vertex], Ds),
  dijkstra_1(Ds, [s(Vertex,0,[])], Ss).

dijkstra_1([], Ss, Ss).
dijkstra_1([D|Ds], Ss0, Ss):-
  best(Ds, D, S),
  delet([D|Ds], [S], Ds1),
  S=s(Vertex,Distance,Path),
  reverse([Vertex|Path], Path1),
  merg(Ss0, [s(Vertex,Distance,Path1)], Ss1),
  create(Vertex, [Vertex|Path], Ds2),
  delet(Ds2, Ss1, Ds3),
  incr(Ds3, Distance, Ds4),
  merg(Ds1, Ds4, Ds5),
  dijkstra_1(Ds5, Ss1, Ss).

% path(Vertex0, Vertex, Path, Dist) is true if Path is the shortest path from
%   Vertex0 to Vertex, and the length of the path is Dist. The graph is defined
%   by e/3.
% e.g. path(penzance, london, Path, Dist)
path(Vertex0, Vertex, Path, Dist):-
  dijkstra(Vertex0, Ss),
  member(s(Vertex,Dist,Path), Ss), !.

% create(Start, Path, Edges) is true if Edges is a list of structures s(Vertex,
%   Distance, Path) containing, for each Vertex accessible from Start, the
%   Distance from the Vertex and the specified Path.  The list is sorted by the
%   name of the Vertex.
create(Start, Path, Edges):-
  setof(s(Vertex,Edge,Path), e(Start,Vertex,Edge), Edges), !.
create(_, _, []).

% best(Edges, Edge0, Edge) is true if Edge is the element of Edges, a list of
%   structures s(Vertex, Distance, Path), having the smallest Distance.  Edge0
%   constitutes an upper bound.
best([], Best, Best).
best([Edge|Edges], Best0, Best):-
  shorter(Edge, Best0), !,
  best(Edges, Edge, Best).
best([_|Edges], Best0, Best):-
  best(Edges, Best0, Best).

shorter(s(_,X,_), s(_,Y,_)):-X < Y.

% delete(Xs, Ys, Zs) is true if Xs, Ys and Zs are lists of structures s(Vertex,
%   Distance, Path) ordered by Vertex, and Zs is the result of deleting from Xs
%   those elements having the same Vertex as elements in Ys.
delet([], _, []).
delet([X|Xs], [], [X|Xs]):-!.
delet([X|Xs], [Y|Ys], Ds):-
  eq(X, Y), !,
  delet(Xs, Ys, Ds).
delet([X|Xs], [Y|Ys], [X|Ds]):-
  lt(X, Y), !, delet(Xs, [Y|Ys], Ds).
delet([X|Xs], [_|Ys], Ds):-
  delet([X|Xs], Ys, Ds).

% merge(Xs, Ys, Zs) is true if Zs is the result of merging Xs and Ys, where Xs,
%   Ys and Zs are lists of structures s(Vertex, Distance, Path), and are
%   ordered by Vertex.  If an element in Xs has the same Vertex as an element
%   in Ys, the element with the shorter Distance will be in Zs.
merg([], Ys, Ys).
merg([X|Xs], [], [X|Xs]):-!.
merg([X|Xs], [Y|Ys], [X|Zs]):-
  eq(X, Y), shorter(X, Y), !,
  merg(Xs, Ys, Zs).
merg([X|Xs], [Y|Ys], [Y|Zs]):-
  eq(X, Y), !,
  merg(Xs, Ys, Zs).
merg([X|Xs], [Y|Ys], [X|Zs]):-
  lt(X, Y), !,
  merg(Xs, [Y|Ys], Zs).
merg([X|Xs], [Y|Ys], [Y|Zs]):-
  merg([X|Xs], Ys, Zs).

eq(s(X,_,_), s(X,_,_)).

lt(s(X,_,_), s(Y,_,_)):-X @< Y.

% incr(Xs, Incr, Ys) is true if Xs and Ys are lists of structures s(Vertex,
%   Distance, Path), the only difference being that the value of Distance in Ys
%   is Incr more than that in Xs.
incr([], _, []).
incr([s(V,D1,P)|Xs], Incr, [s(V,D2,P)|Ys]):-
  D2 is D1 + Incr,
  incr(Xs, Incr, Ys).

% member(X, Ys) is true if the element X is contained in the list Ys.
%member(X, [X|_]).
%member(X, [_|Ys]):-member(X, Ys).

% reverse(Xs, Ys) is true if Ys is the result of reversing the order of the
%   elements in the list Xs.
%reverse(Xs, Ys):-reverse_1(Xs, [], Ys).

%reverse_1([], As, As).
%reverse_1([X|Xs], As, Ys):-reverse_1(Xs, [X|As], Ys).

e(X, Y, Z):-dist(X, Y, Z).
e(X, Y, Z):-dist(Y, X, Z).
dist((1,1),(2,1),1).
dist((1,1),(1,2),1).
dist((1,2),(2,2),1).
dist((1,2),(1,3),1).
dist((1,2),(1,1),1).
dist((1,3),(2,3),1).
dist((1,3),(1,4),1).
dist((1,3),(1,2),1).
dist((1,4),(2,4),1).
dist((1,4),(1,5),1).
dist((1,4),(1,3),1).
dist((1,5),(2,5),1).
dist((1,5),(1,6),1).
dist((1,5),(1,4),1).
dist((1,6),(1,7),1).
dist((1,6),(1,5),1).
dist((1,7),(2,7),1).
dist((1,7),(1,6),1).
dist((1,9),(2,9),1).
dist((1,9),(1,10),1).
dist((1,10),(2,10),1).
dist((1,10),(1,9),1).
dist((1,1),(0,1),1).
dist((2,1),(3,1),1).
dist((2,1),(2,2),1).
dist((1,2),(0,2),1).
dist((2,2),(3,2),1).
dist((2,2),(2,3),1).
dist((2,2),(2,1),1).
dist((1,3),(0,3),1).
dist((2,3),(3,3),1).
dist((2,3),(2,4),1).
dist((2,3),(2,2),1).
dist((1,4),(0,4),1).
dist((2,4),(3,4),1).
dist((2,4),(2,5),1).
dist((2,4),(2,3),1).
dist((1,5),(0,5),1).
dist((2,5),(3,5),1).
dist((2,5),(2,4),1).
dist((1,7),(0,7),1).
dist((2,7),(3,7),1).
dist((1,9),(0,9),1).
dist((2,9),(3,9),1).
dist((2,9),(2,10),1).
dist((1,10),(0,10),1).
dist((2,10),(3,10),1).
dist((2,10),(2,9),1).
dist((2,1),(1,1),1).
dist((3,1),(4,1),1).
dist((3,1),(3,2),1).
dist((2,2),(1,2),1).
dist((3,2),(4,2),1).
dist((3,2),(3,3),1).
dist((3,2),(3,1),1).
dist((2,3),(1,3),1).
dist((3,3),(4,3),1).
dist((3,3),(3,4),1).
dist((3,3),(3,2),1).
dist((2,4),(1,4),1).
dist((3,4),(4,4),1).
dist((3,4),(3,5),1).
dist((3,4),(3,3),1).
dist((2,5),(1,5),1).
dist((3,5),(4,5),1).
dist((3,5),(3,6),1).
dist((3,5),(3,4),1).
dist((3,6),(4,6),1).
dist((3,6),(3,7),1).
dist((3,6),(3,5),1).
dist((2,7),(1,7),1).
dist((3,7),(3,6),1).
dist((2,9),(1,9),1).
dist((3,9),(4,9),1).
dist((3,9),(3,10),1).
dist((2,10),(1,10),1).
dist((3,10),(3,9),1).
dist((3,1),(2,1),1).
dist((4,1),(5,1),1).
dist((4,1),(4,2),1).
dist((3,2),(2,2),1).
dist((4,2),(5,2),1).
dist((4,2),(4,3),1).
dist((4,2),(4,1),1).
dist((3,3),(2,3),1).
dist((4,3),(5,3),1).
dist((4,3),(4,4),1).
dist((4,3),(4,2),1).
dist((3,4),(2,4),1).
dist((4,4),(5,4),1).
dist((4,4),(4,5),1).
dist((4,4),(4,3),1).
dist((3,5),(2,5),1).
dist((4,5),(5,5),1).
dist((4,5),(4,6),1).
dist((4,5),(4,4),1).
dist((3,6),(2,6),1).
dist((4,6),(5,6),1).
dist((4,6),(4,5),1).
dist((4,8),(5,8),1).
dist((4,8),(4,9),1).
dist((3,9),(2,9),1).
dist((4,9),(5,9),1).
dist((4,9),(4,8),1).
dist((4,1),(3,1),1).
dist((5,1),(6,1),1).
dist((5,1),(5,2),1).
dist((4,2),(3,2),1).
dist((5,2),(6,2),1).
dist((5,2),(5,3),1).
dist((5,2),(5,1),1).
dist((4,3),(3,3),1).
dist((5,3),(6,3),1).
dist((5,3),(5,4),1).
dist((5,3),(5,2),1).
dist((4,4),(3,4),1).
dist((5,4),(6,4),1).
dist((5,4),(5,5),1).
dist((5,4),(5,3),1).
dist((4,5),(3,5),1).
dist((5,5),(6,5),1).
dist((5,5),(5,6),1).
dist((5,5),(5,4),1).
dist((4,6),(3,6),1).
dist((5,6),(6,6),1).
dist((5,6),(5,7),1).
dist((5,6),(5,5),1).
dist((5,7),(6,7),1).
dist((5,7),(5,8),1).
dist((5,7),(5,6),1).
dist((4,8),(3,8),1).
dist((5,8),(5,9),1).
dist((5,8),(5,7),1).
dist((4,9),(3,9),1).
dist((5,9),(6,9),1).
dist((5,9),(5,10),1).
dist((5,9),(5,8),1).
dist((5,10),(6,10),1).
dist((5,10),(5,9),1).
dist((5,1),(4,1),1).
dist((6,1),(7,1),1).
dist((6,1),(6,2),1).
dist((5,2),(4,2),1).
dist((6,2),(7,2),1).
dist((6,2),(6,3),1).
dist((6,2),(6,1),1).
dist((5,3),(4,3),1).
dist((6,3),(7,3),1).
dist((6,3),(6,4),1).
dist((6,3),(6,2),1).
dist((5,4),(4,4),1).
dist((6,4),(7,4),1).
dist((6,4),(6,5),1).
dist((6,4),(6,3),1).
dist((5,5),(4,5),1).
dist((6,5),(7,5),1).
dist((6,5),(6,6),1).
dist((6,5),(6,4),1).
dist((5,6),(4,6),1).
dist((6,6),(7,6),1).
dist((6,6),(6,7),1).
dist((6,6),(6,5),1).
dist((5,7),(4,7),1).
dist((6,7),(6,6),1).
dist((5,9),(4,9),1).
dist((6,9),(7,9),1).
dist((6,9),(6,10),1).
dist((5,10),(4,10),1).
dist((6,10),(7,10),1).
dist((6,10),(6,9),1).
dist((6,1),(5,1),1).
dist((7,1),(8,1),1).
dist((7,1),(7,2),1).
dist((6,2),(5,2),1).
dist((7,2),(8,2),1).
dist((7,2),(7,3),1).
dist((7,2),(7,1),1).
dist((6,3),(5,3),1).
dist((7,3),(8,3),1).
dist((7,3),(7,4),1).
dist((7,3),(7,2),1).
dist((6,4),(5,4),1).
dist((7,4),(8,4),1).
dist((7,4),(7,5),1).
dist((7,4),(7,3),1).
dist((6,5),(5,5),1).
dist((7,5),(8,5),1).
dist((7,5),(7,6),1).
dist((7,5),(7,4),1).
dist((6,6),(5,6),1).
dist((7,6),(8,6),1).
dist((7,6),(7,5),1).
dist((7,8),(8,8),1).
dist((7,8),(7,9),1).
dist((6,9),(5,9),1).
dist((7,9),(8,9),1).
dist((7,9),(7,10),1).
dist((7,9),(7,8),1).
dist((6,10),(5,10),1).
dist((7,10),(8,10),1).
dist((7,10),(7,9),1).
dist((7,1),(6,1),1).
dist((8,1),(9,1),1).
dist((8,1),(8,2),1).
dist((7,2),(6,2),1).
dist((8,2),(9,2),1).
dist((8,2),(8,3),1).
dist((8,2),(8,1),1).
dist((7,3),(6,3),1).
dist((8,3),(9,3),1).
dist((8,3),(8,4),1).
dist((8,3),(8,2),1).
dist((7,4),(6,4),1).
dist((8,4),(9,4),1).
dist((8,4),(8,5),1).
dist((8,4),(8,3),1).
dist((7,5),(6,5),1).
dist((8,5),(9,5),1).
dist((8,5),(8,6),1).
dist((8,5),(8,4),1).
dist((7,6),(6,6),1).
dist((8,6),(9,6),1).
dist((8,6),(8,7),1).
dist((8,6),(8,5),1).
dist((8,7),(9,7),1).
dist((8,7),(8,8),1).
dist((8,7),(8,6),1).
dist((7,8),(6,8),1).
dist((8,8),(9,8),1).
dist((8,8),(8,9),1).
dist((8,8),(8,7),1).
dist((7,9),(6,9),1).
dist((8,9),(8,10),1).
dist((8,9),(8,8),1).
dist((7,10),(6,10),1).
dist((8,10),(8,9),1).
dist((8,1),(7,1),1).
dist((9,1),(10,1),1).
dist((9,1),(9,2),1).
dist((8,2),(7,2),1).
dist((9,2),(10,2),1).
dist((9,2),(9,3),1).
dist((9,2),(9,1),1).
dist((8,3),(7,3),1).
dist((9,3),(10,3),1).
dist((9,3),(9,4),1).
dist((9,3),(9,2),1).
dist((8,4),(7,4),1).
dist((9,4),(10,4),1).
dist((9,4),(9,5),1).
dist((9,4),(9,3),1).
dist((8,5),(7,5),1).
dist((9,5),(10,5),1).
dist((9,5),(9,6),1).
dist((9,5),(9,4),1).
dist((8,6),(7,6),1).
dist((9,6),(10,6),1).
dist((9,6),(9,7),1).
dist((9,6),(9,5),1).
dist((8,7),(7,7),1).
dist((9,7),(10,7),1).
dist((9,7),(9,8),1).
dist((9,7),(9,6),1).
dist((8,8),(7,8),1).
dist((9,8),(10,8),1).
dist((9,8),(9,7),1).
dist((9,1),(8,1),1).
dist((10,1),(11,1),1).
dist((10,1),(10,2),1).
dist((9,2),(8,2),1).
dist((10,2),(11,2),1).
dist((10,2),(10,3),1).
dist((10,2),(10,1),1).
dist((9,3),(8,3),1).
dist((10,3),(11,3),1).
dist((10,3),(10,4),1).
dist((10,3),(10,2),1).
dist((9,4),(8,4),1).
dist((10,4),(11,4),1).
dist((10,4),(10,5),1).
dist((10,4),(10,3),1).
dist((9,5),(8,5),1).
dist((10,5),(11,5),1).
dist((10,5),(10,6),1).
dist((10,5),(10,4),1).
dist((9,6),(8,6),1).
dist((10,6),(11,6),1).
dist((10,6),(10,7),1).
dist((10,6),(10,5),1).
dist((9,7),(8,7),1).
dist((10,7),(11,7),1).
dist((10,7),(10,8),1).
dist((10,7),(10,6),1).
dist((9,8),(8,8),1).
dist((10,8),(11,8),1).
dist((10,8),(10,9),1).
dist((10,8),(10,7),1).
dist((10,9),(11,9),1).
dist((10,9),(10,10),1).
dist((10,9),(10,8),1).
dist((10,10),(11,10),1).
dist((10,10),(10,9),1).
dist((10,1),(9,1),1).
dist((11,1),(11,2),1).
dist((10,2),(9,2),1).
dist((11,2),(11,3),1).
dist((11,2),(11,1),1).
dist((10,3),(9,3),1).
dist((11,3),(11,4),1).
dist((11,3),(11,2),1).
dist((10,4),(9,4),1).
dist((11,4),(11,5),1).
dist((11,4),(11,3),1).
dist((10,5),(9,5),1).
dist((11,5),(11,6),1).
dist((11,5),(11,4),1).
dist((10,6),(9,6),1).
dist((11,6),(11,7),1).
dist((11,6),(11,5),1).
dist((10,7),(9,7),1).
dist((11,7),(11,8),1).
dist((11,7),(11,6),1).
dist((10,8),(9,8),1).
dist((11,8),(11,9),1).
dist((11,8),(11,7),1).
dist((10,9),(9,9),1).
dist((11,9),(11,10),1).
dist((11,9),(11,8),1).
dist((10,10),(9,10),1).
dist((11,10),(11,9),1).
dist((11,3),(10,3),1).
dist((12,3),(13,3),1).
dist((12,3),(12,4),1).
dist((11,4),(10,4),1).
dist((12,4),(13,4),1).
dist((12,4),(12,3),1).
dist((11,7),(10,7),1).
dist((12,7),(13,7),1).
dist((12,7),(12,8),1).
dist((11,8),(10,8),1).
dist((12,8),(13,8),1).
dist((12,8),(12,7),1).
dist((13,1),(14,1),1).
dist((13,1),(13,2),1).
dist((13,2),(14,2),1).
dist((13,2),(13,3),1).
dist((13,2),(13,1),1).
dist((12,3),(11,3),1).
dist((13,3),(14,3),1).
dist((13,3),(13,4),1).
dist((13,3),(13,2),1).
dist((12,4),(11,4),1).
dist((13,4),(14,4),1).
dist((13,4),(13,5),1).
dist((13,4),(13,3),1).
dist((13,5),(14,5),1).
dist((13,5),(13,4),1).
dist((12,7),(11,7),1).
dist((13,7),(14,7),1).
dist((13,7),(13,8),1).
dist((12,8),(11,8),1).
dist((13,8),(14,8),1).
dist((13,8),(13,9),1).
dist((13,8),(13,7),1).
dist((13,9),(14,9),1).
dist((13,9),(13,10),1).
dist((13,9),(13,8),1).
dist((13,10),(14,10),1).
dist((13,10),(13,9),1).
dist((13,1),(12,1),1).
dist((14,1),(14,2),1).
dist((13,2),(12,2),1).
dist((14,2),(14,3),1).
dist((14,2),(14,1),1).
dist((13,3),(12,3),1).
dist((14,3),(14,4),1).
dist((14,3),(14,2),1).
dist((13,4),(12,4),1).
dist((14,4),(14,5),1).
dist((14,4),(14,3),1).
dist((13,5),(12,5),1).
dist((14,5),(14,4),1).
dist((13,7),(12,7),1).
dist((14,7),(14,8),1).
dist((13,8),(12,8),1).
dist((14,8),(14,9),1).
dist((14,8),(14,7),1).
dist((13,9),(12,9),1).
dist((14,9),(14,10),1).
dist((14,9),(14,8),1).
dist((13,10),(12,10),1).
dist((14,10),(14,9),1).
