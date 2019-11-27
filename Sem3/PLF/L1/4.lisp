 
;;Sum of two vectors 4 a);
(defun vectorSum(u v)
    (cond
        ((null v) nil)
        (t (cons (+ (car v) (car u)) (vectorSum (cdr u) (cdr v))))
     )
)
 
;;Get All Toms 4 b);
(defun getAtoms (l)
    (cond
        ((null l) nil)
        ((listp (car l)) (append (getAtoms (car l)) (getAtoms (cdr l))))
        (t (cons (car l) (getAtoms (cdr l))))
     )
)