;;Eliminate all occurences of the maximum number from a list

(defun maxim(l)
    (cond
        ((null (cdr l)) (car l))
        ((> (car l) (maxim (cdr l))) (car l))
        (t (maxim (cdr l)))
    )
)

(defun remove_el(l e)
    (cond
        ((null l) nil)
        ((listp (car l)) (maxim (cons (maxim (car l) (maxim (cdr l))))))
        ((eq e (car l)) (remove_el (cdr l) e))
        (t (cons (car l) (remove_el(cdr l) e)))
    )
)

(defun wrapper(l)
    (remove_el l (maxim l))
)

;(cons (x, y), (a, b, c)) -> ((x,y) a, b, c)
;(append (x, y) (a, b, c)) -> (x, y, a, b, c)
;(list (x, y) (a, b)) -> ((x, y), (a b))



(print (wrapper '(1 2 3 4 5)))

