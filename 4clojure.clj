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

(= (reduce (fn [a b] (inc a)) 0 '(1 2 3 3 1)) 5)
(= (__ "Hello World") 11)
(= (__ [[1 2] [3 4] [5 6]]) 3)
(= (__ '(13)) 1)
(= (__ '(:a :b :c)) 3)

;Problem 23
;Write a function which reverses a sequence.
(def __ (fn [xs] (reduce #(cons %2 %1) [] xs)))

(= (reduce #(cons %2 %1) [] [1 2 3 4 5]) [5 4 3 2 1])
(= (__ (sorted-set 5 7 2 7)) '(7 5 2))
(= (__ [[1 2] [3 4] [5 6]]) [[5 6] [3 4] [1 2]])

;Problem 24
;rite a function which returns the sum of a sequence of numbers.
(def __ #(reduce + 0 %))

(= (reduce + 0 [1 2 3]) 6)
(= (__ (list 0 -2 5 5)) 8)
(= (__ #{4 2 1}) 7)
(= (__ '(0 0 -1)) -1)
(= (__ '(1 10 3)) 14)

;Problem 25
;Write a function which returns only the odd numbers from a sequence.
(def __ #(filter odd? %))

(= (filter odd? #{1 2 3 4 5}) '(1 3 5))
(= (__ [4 2 1 6]) '(1))
(= (__ [2 2 4 6]) '())
(= (__ [1 1 1 3]) '(1 1 1 3))

;Problem 26
;Write a function which returns the first X fibonacci numbers.
(def __ #(->> (iterate (fn [[a b]] [b (+ a b)]) [1 1])
            (take %)
            (map first)))

(= (__ 3) '(1 1 2))
(= (__ 6) '(1 1 2 3 5 8))
(= (__ 8) '(1 1 2 3 5 8 13 21))

;Problem 27
;Write a function which returns true if the given sequence is a palindrome. Hint: "racecar" does not equal '(\r \a \c \e \c \a \r)
(def __ #(= (seq %) (reverse %)))

(false? (__ '(1 2 3 4 5)))
(true? (__ "racecar"))
(true? (__ [:foo :bar :foo]))
(true? (__ '(1 1 3 3 1 1)))
(false? (__ '(:a :b :c)))

