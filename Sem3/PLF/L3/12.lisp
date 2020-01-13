; 12. Write a function that substitutes an element through another one at all levels of a given list.

(defun substitutes (l e r)
    (cond
        ((and (atom l)(eq l e)) r)
        ((atom l) l)
        ((null l) nil)
        (t (mapcar #'(lambda (a)(substitutes a e r)) l))
    )
)

(print (substitutes '(1 3 4 (1 (3 4 (1)))) 1 9))