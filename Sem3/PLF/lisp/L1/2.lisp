;; (a) Dot product of two vectors 2.
(defun dotProduct(u v)
    (cond
        ((null v) 0)
        (t (+ (* (car v) (car u)) (dotProduct (cdr u) (cdr v))))
     )
)
 
(print(dotProduct '(3 1 2) '(6 2 1)))



;; (b) Depth of a list 2.
(defun depth(l)
    (cond
        ((null l) 1)
        ((listp (car l)) (max (+ 1 (depth (car l))) (depth (cdr l))))
        (t (depth (cdr l)))
     )
)
 
(print(depth '(2 3 4 (5 (3)))))



;; (d) Intersection of two sets.

;; function to check if element e is present in list l
(defun exists(l e)
    (cond
        ((null l) nil)
        ((equal (car l) e) t)
        (t (exists (cdr l) e))
    )
)
 
(defun intersect (s l)
    (cond
        ((null s) nil)
        ((exists l (car s)) (cons (car s) (intersect (cdr s) l)))
        (t (intersect (cdr s) l))
    )
)
 
 
(print (intersect '(1 2 3 4) '(3 4 5)))


;; (c) Sort a list without keeping double values.

;; function to get the length of a list

(defun len(l)
    (cond
        ((null l) 0)
        (t (+ 1 (len (cdr l))))
     )
)
 
;; get first n elements of a list
(defun getFirstN(l n)
    (cond
        ((= n 0) nil)
        (t (cons (car l) (getFirstN (cdr l) (- n 1))))
    )
)
 
;; get last n elements of a list 
(defun getAfterNAux(l n p)
    (cond
        ((null l) nil)
        ((>= p n) (cons (car l) (getAfterNAux (cdr l) n (+ p 1))))
        (t (getAfterNAux (cdr l) n (+ p 1)))
     )
)
 
;; wrapper function
(defun getAfterN(l n)
    (getAfterNAux l n 0)
)
 

;; get first half
(defun getFirstHalf(l)
    (getFirstN l (truncate (len l) 2))
)
 
;; get second half  
(defun getSecondHalf(l)
    (getAfterN l (truncate (len l) 2))
)
 
 
;;;;Function which inverts a list
(defun invers_aux(l col)
 (COND
   ((NULL l) col)
   ((invers_aux (cdr l) (cons (car l) col)))
  )
)
 
;;; Wrapper function for invers_aux
(defun invers(l)
  (invers_aux l nil)
)
 
;;Function for merging two sorted lists
(defun merge_aux(l a col)
  (cond
    ((and (NULL l) (NULL a)) (invers col))
    ((equal (car l) (car col)) (merge_aux (cdr l) a col))
    ((equal (car a) (car col)) (merge_aux l (cdr a) col))
    ((NULL l) (merge_aux l (cdr a) (cons (car a) col)))
    ((NULL a) (merge_aux (cdr l) a  (cons (car l) col)))
    ((<=(car a) (car l)) (merge_aux l (cdr a) (cons (car a) col)))
    ((< (car l) (car a)) (merge_aux (cdr l) a (cons (car l) col)))
    )
)
 
;; Wrapper function for merge_aux
(defun merge_lists(l a)
  (merge_aux l a nil)
  )
 
 
;;mergeSort
(defun mergeSort(l)
    (cond
        ((null l) nil)
        ((null (cdr l)) l)
        (t (merge_lists (mergeSort (getFirstHalf l)) (mergeSort (getSecondHalf l))))
     )
)
 
(print (mergeSort '(3 1 2 3 3 1 7)))
 