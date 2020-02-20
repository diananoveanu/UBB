(defun ch(l)
    (cond
        ((null l) 0)
        ((atom l) 0)
        ((and (listp l)(eq (first5 l) t)) (+ 1 (apply #'+ (mapcar #'ch l))))
        (t (apply #'+ (mapcar #'ch l)))
    
    )
)


(defun first5(l)
(cond
    ((and (numberp (car l))(= (car l) 5)) t)
    (t nil)
))

(print (ch '(5 2 3 4 (5 1 (7 4) (5 3)))))