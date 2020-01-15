;11. Return the level (and coresponded list of nodes) with maximum number of nodes for a tree of type (2).
;The level of the root element is 0.

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

(defun get_nodes_from_level(tree lvl)
    (cond
        ((null tree) nil)
        ((eq lvl 0) (list (car tree)))
        ((> lvl 0) (append (get_nodes_from_level (get_left tree) (- lvl 1)) (get_nodes_from_level (get_right tree) (- lvl 1))))
    )
)

(defun get_number_of_levels (tree)
    (cond
        ((null tree) 0)
        (t (+ 1(max (get_number_of_levels (get_left tree)) (get_number_of_levels (get_right tree)))))
    )
)

(defun count_nodes (tree lvls)
    (cond
        ((< 0 lvls) (max (length (get_nodes_from_level tree lvls)) (count_nodes tree (- lvls 1))))
        (t 0)
    )
)

(defun wrapper (tree lev levls)
    (cond
        ((and (< 0 levls) (eq (length (get_nodes_from_level tree levls)) lev)) (list (get_nodes_from_level tree levls) levls))
        (t (wrapper tree lev (- levls 1)))
    )
)

(defun solution (tree)
    (wrapper tree (count_nodes tree (get_number_of_levels tree)) (get_number_of_levels tree))
)

(print (solution '(1 (2 (4) (5)) (3 (6)))))


