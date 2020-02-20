(defun G(L)
    (mapcon #'list L)
)


(print (apply #'append (mapcon #'G '(1 2))))