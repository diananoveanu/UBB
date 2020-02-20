(defun change (l k)
    (cond
        ((null l) nil)
        ((and (atom l) (= k 0)) 0)
        ((and (atom l) (not (= k 0))) l)
        (t (mapcar #'(lambda (r) (change r (- k 1)))l))
    )
)

(print (change '(1 2 (3)) 1))