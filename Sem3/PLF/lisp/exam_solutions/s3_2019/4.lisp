(defun rmvN(l k i)
    (cond
        ((null l) nil)
        ((eq 0 i) (rmvN (cdr l) k k))
        (t (cons (car l) (rmvN (cdr l) k (- i 1) )))
    )
)

(print (rmvN '(1 2 3 4 5) '1 '1))