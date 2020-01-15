;get left child of a tree
(defun get_left_child(tree)
    (cond
        ((null tree) nil)
        ((listp (cadr tree)) (cadr tree))
        (t nil)
        )
)
 
;get right child of a tree
(defun get_right_child(tree)
    (cond
        ((null tree) nil)
        ;; get third element -> caddr; 2nd -> cadr 
        ((listp (caddr tree)) (caddr tree))
        (t nil)
        )
)
 
;inorder traversal
(defun inorder_traversal(tree)
    (cond
        ((null tree) tree)
        (t (append (inorder_traversal (get_left_child tree)) (list (car tree)) (inorder_traversal (get_right_child tree))))
        )
    )