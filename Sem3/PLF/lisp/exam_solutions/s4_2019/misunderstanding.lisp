(defun nlist(l)
    (cond
        ((null l) 0)
        ((and (listp l) (null (cdr l))) 1)
        ((atom l) 0)
        (t (apply #'+ (mapcar #'nlist l)))
    )
)
(print (nlist '(1 2 3 (4 (5 (6))) (5))))