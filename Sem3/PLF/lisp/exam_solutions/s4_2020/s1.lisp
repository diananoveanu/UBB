(defun f(l)
    (cond
        ((null l) 0)
        ((> (f (car l)) 2) (+ (car l) (f (cdr l))))
        (T (f (car l)))
    )
)


(defun func(l)
    ((lambda (r) 
        (cond
            ((null l) 0)
            ((> r 2) (+ (car l) (func (cdr l))))
            (T r)
        )
    )

    (func (car l)))
)

;(print (f '(3 2 4 3 5)))
(print (func '(1 2 4 3 5)))


