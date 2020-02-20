(defun nonNum(l)
    (cond
        ((null l)0)
        ((and (atom (car l)) (not (numberp (car l)))) (+ 1 (nonNum (cdr l))))
        (t (nonNum (cdr l)))
    )
)

;(print (nonNum '(1 3 4 5 (B C D))))

(defun nonNList (l k)
    (cond 
        ((null l) 0)
        ((and (and (eq 0 (mod k 2)) (listp l)) (eq(mod(nonNum l) 2)1)) 1)
        ((atom l) 0)
        (t (apply #'+ (mapcar #'(lambda (r) (nonNList r (- k 1)))l)))
    )
)

(print (nonNList '(1 2 3 A (0 (C D 0)))'1))