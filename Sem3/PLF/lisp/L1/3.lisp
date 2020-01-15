;a) Write a function that inserts in a linear list a given atom A after the 2nd, 4th, 6th, ... element.

(defun insert_after(l a ctr)
    (cond 
        ((null l) nil)
        ((eq 0 (mod ctr 2)) (cons (car l) (cons a (insert_after (cdr l) a (+ 1 ctr)))))
        (t (cons (car l) (insert_after (cdr l) a (+ 1 ctr))))
    )
)

;(print (insert_after '(1 2 3 4 5 6 7 8) 10 1))



;b) Write a function to get from a given list the list of all atoms, on any
;level, but reverse order. Example:
;(((A B) C) (D E)) ==> (E D C B A)

(defun liniarize_reverse (l)
    (cond
        ((null l) nil)
        ((listp (car l)) (append (liniarize_reverse (cdr l)) (liniarize_reverse (car l))))
        (t (append (liniarize_reverse (cdr l)) (list(car l))))
    )
)

; (print (liniarize_reverse '(1 ((2)9) 3 4 5 6 7)))



;c) Write a function that returns the greatest common divisor of all numbers in a nonlinear list.
;todo

;d) Write a function that determines the number of occurrences of a given atom in a nonlinear list.
;todo