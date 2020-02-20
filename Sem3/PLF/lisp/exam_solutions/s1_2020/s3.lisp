; remove all atoms divisible by 3


(defun rem_div3(l)
    (cond
        ((null l) '())
        ((and (atom l) (= (mod l 3) 0)) '())
        ((atom l) l)
        (T (mapcar #'rem_div3 l))
    )
)

(defun rem(l)
    (cond
        ((null l) nil)
        ((and (listp (car l))(null (car l))) (rem (cdr l)))
        ((listp (car l))  (cons (rem (car l)) (rem (cdr l))))
        (T (cons (car l) (rem (cdr l))))
    )
)


(print  (rem (rem_div3 '(1 2 3 (5 6 5 (9 5 4)) (12 5 3 4 7 99)))))