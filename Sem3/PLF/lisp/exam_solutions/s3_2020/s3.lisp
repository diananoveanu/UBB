(defun repl_k (l k)
    (cond
        ((null l) nil)
        ((and (atom l) (= k 0)) 0)
        ((and (atom l) (not (= k 0))) l)
        (t (mapcar #'(lambda (r) (repl_k r (- k 1)))l))
    )
)

(print (repl_k '(0 1 2 3 4 (3 2 (1 1 (5)))) '2))