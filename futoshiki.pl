/**
 * Copyright 2018 Alex Scown
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

/**
 * predicate in_range(X) is det
 *
 * true if X is in the list [1,2,3,4,5]
 */
in_range(X) :- member(X,[1,2,3,4,5]).

/**
 * predicate in_set(X) is det
 *
 * true if the list X is a set
 */
is_set([]).
is_set([H|T]) :- is_set(T), \+ member(H,T).

/**
 * predicate futoshiki(A_A, A_B, A_C, A_D, A_E, B_A, B_B, B_C, B_D, B_E, C_A, C_B, C_C, C_D, C_E, D_A, D_B, D_C, D_D, D_E, E_A, E_B, E_C, E_D, E_E)
 *
 * true if the variables form a valid solution to this particular futoshiki instance
 */
futoshiki(
    A_A,
    A_B,
    A_C,
    A_D,
    A_E,
    B_A,
    B_B,
    B_C,
    B_D,
    B_E,
    C_A,
    C_B,
    C_C,
    C_D,
    C_E,
    D_A,
    D_B,
    D_C,
    D_D,
    D_E,
    E_A,
    E_B,
    E_C,
    E_D,
    E_E
) :-
    in_range(A_A),
    in_range(A_B),
    is_set([A_A,A_B]),
    in_range(A_C),
    is_set([A_A,A_B,A_C]),
    in_range(A_D),
    is_set([A_A,A_B,A_C,A_D]),
    in_range(A_E),
    is_set([A_A,A_B,A_C,A_D,A_E]),
    in_range(B_A),
    is_set([A_A,B_A]),
    A_A > B_A,
    in_range(B_B),
    is_set([B_A,B_B]),
    is_set([A_B,B_B]),
    in_range(B_C),
    is_set([B_A,B_B,B_C]),
    is_set([A_C,B_C]),
    in_range(B_D),
    is_set([B_A,B_B,B_C,B_D]),
    is_set([A_D,B_D]),
    B_D > A_D,
    in_range(B_E),
    is_set([B_A,B_B,B_C,B_D,B_E]),
    is_set([A_E,B_E]),
    B_E > B_D,
    in_range(C_A),
    is_set([A_A,B_A,C_A]),
    in_range(C_B),
    is_set([C_A,C_B]),
    is_set([A_B,B_B,C_B]),
    C_B > C_A,
    in_range(C_C),
    is_set([C_A,C_B,C_C]),
    is_set([A_C,B_C,C_C]),
    in_range(C_D),
    is_set([C_A,C_B,C_C,C_D]),
    is_set([A_D,B_D,C_D]),
    C_D > B_D,
    in_range(C_E),
    is_set([C_A,C_B,C_C,C_D,C_E]),
    is_set([A_E,B_E,C_E]),
    B_E > C_E,
    in_range(D_A),
    is_set([A_A,B_A,C_A,D_A]),
    in_range(D_B),
    is_set([D_A, D_B]),
    is_set([A_B,B_B,C_B,D_B]),
    D_B > C_B,
    in_range(D_C),
    is_set([D_A, D_B, D_C]),
    is_set([A_C,B_C,C_C,D_C]),
    D_C > D_B,
    in_range(D_D),
    is_set([D_A, D_B, D_C,D_D]),
    is_set([A_D,B_D,C_D,D_D]),
    in_range(D_E),
    is_set([D_A,D_B,D_C,D_D,D_E]),
    is_set([A_E,B_E,C_E,D_E]),
    C_E > D_E,
    in_range(E_A),
    is_set([A_A,B_A,C_A,D_A,E_A]),
    in_range(E_B),
    is_set([E_A,E_B]),
    is_set([A_B,B_B,C_B,D_B,E_B]),
    E_B > E_A,
    E_B > D_B,
    in_range(E_C),
    is_set([E_A,E_B,E_C]),
    is_set([A_C,B_C,C_C,D_C,E_C]),
    D_C > E_C,
    in_range(E_D),
    is_set([E_A,E_B,E_C,E_D]),
    is_set([A_D,B_D,C_D,D_D,E_D]),
    in_range(E_E),
    is_set([E_A,E_B,E_C,E_D,E_E]),
    is_set([A_E,B_E,C_E,D_E,E_E]).

/**
 * predicate futoshiki_slow(A_A, A_B, A_C, A_D, A_E, B_A, B_B, B_C, B_D, B_E, C_A, C_B, C_C, C_D, C_E, D_A, D_B, D_C, D_D, D_E, E_A, E_B, E_C, E_D, E_E)
 *
 * true if the variables form a valid solution to this particular futoshiki instance
 *
 * Very slow.
 */
futoshiki_slow(
    A_A,
    A_B,
    A_C,
    A_D,
    A_E,
    B_A,
    B_B,
    B_C,
    B_D,
    B_E,
    C_A,
    C_B,
    C_C,
    C_D,
    C_E,
    D_A,
    D_B,
    D_C,
    D_D,
    D_E,
    E_A,
    E_B,
    E_C,
    E_D,
    E_E
) :-
    in_range(A_A),
    in_range(A_B),
    in_range(A_C),
    in_range(A_D),
    in_range(A_E),
    in_range(B_A),
    in_range(B_B),
    in_range(B_C),
    in_range(B_D),
    in_range(B_E),
    in_range(C_A),
    in_range(C_B),
    in_range(C_C),
    in_range(C_D),
    in_range(C_E),
    in_range(D_A),
    in_range(D_B),
    in_range(D_C),
    in_range(D_D),
    in_range(D_E),
    in_range(E_A),
    in_range(E_B),
    in_range(E_C),
    in_range(E_D),
    in_range(E_E),
    is_set([A_A,A_B,A_C,A_D,A_E]),
    is_set([B_A,B_B,B_C,B_D,B_E]),
    is_set([C_A,C_B,C_C,C_D,C_E]),
    is_set([D_A,D_B,D_C,D_D,D_E]),
    is_set([E_A,E_B,E_C,E_D,E_E]),
    is_set([A_A,B_A,C_A,D_A,E_A]),
    is_set([A_B,B_B,C_B,D_B,E_B]),
    is_set([A_C,B_C,C_C,D_C,E_C]),
    is_set([A_D,B_D,C_D,D_D,E_D]),
    is_set([A_E,B_E,C_E,D_E,E_E]),
    A_A > B_A,
    B_D > A_D,
    B_E > B_D,
    C_B > C_A,
    C_D > B_D,
    B_E > C_E,
    D_B > C_B,
    D_C > D_B,
    C_E > D_E,
    E_B > E_A,
    E_B > D_B,
    D_C > E_C.
