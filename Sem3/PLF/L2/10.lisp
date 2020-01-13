; 10. Return the level of a node X in a tree of type (2). The level of the root element is 0.


;; varianta cioban
(defun level(tree nod)
    (cond
        ((null tree) 10000)
        ((eq (car tree) nod) 0)
        (t (+ 1 (min (level (get_right tree) nod)(level (get_left tree) nod))))
    )
)

;; varianta eleganth
(defun lvl(tree nod)
    (cond
        ((eq (car tree) nod) 0)
        ((exists (get_right tree) nod) (+ 1 (lvl (get_right tree) nod)))
        (t (+ 1 (lvl (get_left tree) nod)))
    )
)

(defun exists (tree nod)
    (cond 
        ((null tree) nil)
        ((eq (car tree) nod) t)
        (t (or (exists (get_left tree) nod) (exists (get_right tree) nod)))
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

(print (lvl '(5(4(4(1)))) 1))