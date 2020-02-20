;1. Write a function to check if an atom is member of a list (the list is non-liniar)

(defun search(l e)
    (cond
        ((null l) 0)
        ((and (atom l) (eq l e)) 1)
        ((atom l) 0)
        (t (apply #'+ (mapcar #'(lambda (r) (search r e)) l)))
    )
)

(defun wrapper_search(l e)
    (cond
        ((> (search l e) 0) t)
        (t nil)
    )
)

(print (wrapper_search '(a (b (c)) (d) (e (f)))  'a))
