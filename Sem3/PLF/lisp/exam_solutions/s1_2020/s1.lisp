(defun f1(l1 l2)
    (append (f1 (car l1) l2)
        (cond 
            ((null l1) (cdr l2))
            (T (list (f1 (car l1) l2) (car l2)))
        )
    )
)


(defun f(l1 l2)
    ((lambda (ff)  (append ff l2) 
        (cond
            ((null l1) (cdr l2))
            (T (list ff (car l2)))   
        ) 
        )

    (f(car l1) l2))
)

(print (f '(1) '(1)))