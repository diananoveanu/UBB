(defun fct(f l)
    (cond
        ((null l) nil)
        ((funcall f (car l)) (cons (funcall f (car l)) (fct f (cdr l))))
        (T nil)
    )
)

(print (fct 'car '((2) (4) (3))))


(defun functie(f l)
    ((lambda (r)    
        (cond

            ((null l) nil)
            (r (cons r (functie f (cdr l))))
            (T nil)
        )
    )
    
    (funcall f (car l)))
)

(print (functie 'car '((2) (4) (3))))
