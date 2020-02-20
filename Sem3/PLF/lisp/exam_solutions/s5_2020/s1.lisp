(defun f (l)
    (cond
        ((atom l) -1)
        ((>(f(carl))0) (+ (car l) (f (car l) ) (f (cdr l))))
        (T (f (cdr l)))
   )
)



(defun f(l)
    (cond 
        ((atom l) -1)
        ((lambda (r)
            (cond
                ((> r 0)(+ (car l) r (f (cdr l))))
                (T (f (cdr l)))
            )
        )(f (car l)))
    )
)

(print (f '(2 4 5 1 3)))
