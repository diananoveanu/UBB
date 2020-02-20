;2. Write a function that returns the sum of numeric atoms in a list, at any level.


(defun get_sum(l)
    (cond
        ((null l) 0)
        ((numberp l) l)
        ((atom l) 0)
        (t (apply #'+ (mapcar #'get_sum l)))
    )
)

(print (get_sum '(1 2 (3 A 4) 5)))