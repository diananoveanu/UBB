;15. Determine the list of nodes accesed in postorder in a tree of type (2).


(defun get_left (tree)
    (cond 
        ((null tree) nil)
        ((listp (cadr tree)) (cadr tree))
        (t nil)
    )
)

(defun get_right (tree)
    (get_left(cdr tree))
)

(defun postorder(tree)
    (cond
        ((null tree) nil)
        (t (append (postorder (get_left tree)) (postorder (get_right tree)) (list (car tree))))
    )
)

(print (postorder '(1(2(3)(5))(4))))