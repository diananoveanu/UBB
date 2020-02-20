(defun f(x &REST y)
    (cond
        ((NULL y) x)
        (T (append x (mapcar #'car y)))
    )
)

(print (append (f '(1 2)) (f '(3 4) '(5 6) '(7 8))))