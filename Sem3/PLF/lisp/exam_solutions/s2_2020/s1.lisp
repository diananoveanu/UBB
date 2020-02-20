(defun f1(l)
    (cond
        ((null l) nil)
        ((listp (car l)) (append (f1 (car l)) (f1 (cdr l)) (car(f1(car l)))))
        (T (list (car l)))
    )
)


(defun f(l)
    (cond
        ((null l) nil)
        ((listp (car l))
            ((lambda(func) (append func (f (cdr l)) (car func))) (f (car l)))
        )
        (T (list (car l)))
    )
)

(print (f1 '((3 (1) 3) 5 1 4 6 5)))