(defun get_left (tree)
    (cond
        ((null tree) nil)
        ((listp (cadr tree)) (cadr tree))
        (t nil)
    )
)

(defun get_right (tree)
    (get_left (cdr tree))
)

(defun exists (tree node)
    (cond
        ((null tree) nil)
        ((eq (car tree) node) t)
        (t (or (exists (get_left tree) node) (exists (get_right tree) node)))
    )
)

(defun get_lvl_of_node (tree node)
    (cond
        ((null tree) -1)
        ((eq (car tree) node) 0)
        ((exists (get_left tree) node) (+ 1 (get_lvl_of_node (get_left tree) node)))
        ((exists (get_right tree) node) (+ 1 (get_lvl_of_node (get_right tree) node)))
        (t -1)
    )
)

(print (get_lvl_of_node '(A(B)(C)) 'A))
(print (get_lvl_of_node '(A(B(C)(D))(E)) 'D))
(print (get_lvl_of_node '(A(B (C)(D))(E(F)(G(H)(I)))) 'I))
(print (get_lvl_of_node '(A(B)(C(D)(E))) 'E))


; (1 (2(4)(5))(3 (6(7))))
; (A (B) (C (D)))
; 