;Problem 19 
;Write a function which returns the last element in a sequence.
(def __ #(-> % reverse first))

(= (__ [1 2 3 4 5]) 5)
(= (__ '(5 4 3)) 3)
(= (__ ["b" "c" "d"]) "d")

;Problem 20
;Write a function which returns the second to last element from a sequence.
(def __ #(-> % reverse second))

(= (__ (list 1 2 3 4 5)) 4)
(= (__ ["a" "b" "c"]) "b")
(= (__ [[1 2] [3 4]]) [1 2])


;Problem 21
;Write a function which returns the Nth element from a sequence.
(def __ #(->> %1 (drop %2) first))
(def __ #(->> %1 (take (inc %2)) last))

(= (__ '(4 5 6 7) 2) 6)
(= (__ [:a :b :c] 0) :a)
(= (__ [1 2 3 4] 1) 2)
(= (__ '([1 2] [3 4] [5 6]) 2) [5 6])

;Problem 22
;Write a function which returns the total number of elements in a sequence.
(def __ #(reduce (fn [a b] (inc a)) 0 %))

(= (__ '(1 2 3 3 1)) 5)
(= (__ "Hello World") 11)
(= (__ [[1 2] [3 4] [5 6]]) 3)
(= (__ '(13)) 1)
(= (__ '(:a :b :c)) 3)

