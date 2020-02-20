;Write a function that substitutes an element E with all elements of a list L1 at all levels of a given list L.

(defun subst (l l1 e)
    (cond
        ((null l) nil)
        ((and (atom l) (eq l e)) l1)
        ((atom l) l)
        (t (apply #'list (mapcar #'(lambda (r) (subst r (getAllElem l1) e) )l)))
    )
)

(defun getAllElem(l)
    (cond
        ((null l) nil)
        ((atom (car l)) (cons (car l) (getAllElem (cdr l))))
        ((listp (car l)) (append (getAllElem (car l)) (getAllElem (cdr l))))
    )
)

(print (subst '(1 2 3 5 1 (3 1)) '(5 5 (9 4) 5 5) '1))
;(print (getAllElem '(1 2 3 5 1 (3 1))))