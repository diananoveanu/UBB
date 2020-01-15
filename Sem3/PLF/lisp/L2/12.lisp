; 12. Determine the list of nodes accesed in preorder in a tree of type (2).

(defun get_left (tree)
    (cond 
        ((null tree) nil)
        ((listp (cadr tree)) (cadr tree))
        (t null)
    )
)

(defun get_right (tree)
    (get_left(cdr tree))
)


(defun preorder (tree)
    (cond
        ((null tree) nil)
        (t (append (list (car tree)) (preorder (get_left tree)) (preorder (get_right tree))))
    )
)
(print (preorder '(1(2(3)(5))(4))))