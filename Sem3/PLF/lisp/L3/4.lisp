;4. Write a function that returns the product of numeric atoms in a list, at any level.

(defun prod(l)
    (cond
        ((null l) 1)
        ((numberp l) l)
        ((atom l) 1)
        (t (apply #'* (mapcar #'prod l)))
    )
)

(print (prod '(1 2 3 5 (3 A))))