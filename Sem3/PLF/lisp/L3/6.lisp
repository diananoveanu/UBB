;6. Write a function that returns the maximum of numeric atoms in a list, at any level.


(defun maximum (l)
    (cond
        ((null l) -100000)
        ((numberp l) l)
        (t (apply #'max (mapcar #'maximum l)))
    )
)

(print (maximum '(3 (3) (4 (5 (6 6 2 8))))))