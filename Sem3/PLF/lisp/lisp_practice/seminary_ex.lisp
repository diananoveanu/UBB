(defun pare(l n)
    (cond 
        ((and (atom l) (=(mod n 2)0))1)
        ((and (atom l) (=(mod n 2)1))0)
        (t (apply #'+ (mapcar #'(lambda (a) (pare a (+ 1 n)))l)))
    )
)
(print (pare '(3 (4 1) (4(6)) 5) '0))