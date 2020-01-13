(defun my_gcd(a b)
    (cond 
        ((eq b 0) a)
        (t (my_gcd b (mod a b)))
    )
)


(defun list_gcd (l)
    (cond
        ((null l) 0)
        (t (gcd (car l) (list_gcd (cdr l))))
    )
)

(print (my_gcd 1 6))