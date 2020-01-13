;16. Determine if a tree of type (2) is ballanced (the difference between the depth of two
; subtrees is equal to 1).

(defun balance(tree)
    (cond
        ((null tree) t)
        ((> (abs (- (depth (get_left tree)) (depth (get_right tree)))) 1) nil)
        (t (and (balance (get_right tree)) (balance (get_left tree))))
    )
)

(defun depth(tree)
    (cond 
        ((null tree) 0)
        (t (+ 1 (max (depth (get_left tree)) (depth(get_right tree)))))
    )
)

(defun get_left(tree)
    (cond 
        ((null tree) nil)
        ((listp (cadr tree)) (cadr tree))
        (t nil)
    )
)

(defun get_right(tree)
    (get_left (cdr tree))
)

(print (balance '(1 (2 (2 (3))) (3))))
