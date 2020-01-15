;13. For a given tree of type (2) return the path from the root node to a certain given node X.


(defun get_left (l)
    (cond
        ((null l) nil)
        ((listp (cadr l)) (cadr l))
        (t nil)
    )
)

(defun get_right (l)
    (get_left (cdr l))
)

(defun contains (l X)
    (cond 
        ((null l) nil)
        ((eq (car l) X) t)
        (t (or (contains (get_left l) X) (contains (get_right l) X)))
    )
)

(defun path(l X)
    (cond
        ((null l) nil)
        ((eq (car l) X) (list (car l)))
        ((contains (get_left l) X) (cons (car l) (path (get_left l) X)))
        ((contains (get_right l) X) (cons (car l) (path (get_right l) X)))
    )
)

(print (path '(1 (2 (3 (4) (5))(6))) 5))