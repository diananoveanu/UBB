(defun get_min_num(l)
    (cond
        ((null l) 100001)
        ((numberp (car l))  (min (car l) (get_min_num (cdr l))))
        (t (get_min_num (cdr l)))
    )
)


(defun impmin2(l k)
    (cond
        ((null l) 0)
        ((and (and (listp l) (eq(mod k 2)0))(eq(mod (get_min_num l)2)0)) 1)
        ((atom l) 0)
        (t (apply #'+ (mapcar #'(lambda (r) (impmin2 r (- 1 k)))l)))
    )
)

(print (impmin2 '(A (B 2) (C 3 (3 2 (2 8))) 4) '-1))


;(print (get_min_num '(3 1 R (1 A))))