;5. Write a function that computes the sum of even numbers and the decrease the sum of odd numbers, 
;at any level of a list.

(defun sums (l)
    (cond
        ((null l) 0)
        ((and (numberp l) (eq (mod l 2) 0)) l)
        ((numberp l) (- l (* l 2)))
        ((atom l) 0)
        (t (apply #'+ (mapcar #'sums l)))
    )
)

(print (sums '(2 3 5 A (6))))