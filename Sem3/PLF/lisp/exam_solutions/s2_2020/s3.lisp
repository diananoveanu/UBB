(defun repl(l e i)
    (cond
        ((null l) nil)
        ((and (atom l) (=(mod i 2)0))l)
        ((and (atom l) (=(mod i 2)1))e)
        (t (mapcar #'(lambda (r) (repl r e (+ 1 i)))l))
    )
)

(print(repl '(2 (3)(4)(3)(4)(4(5(5(4))))) '9 '-1))