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

;Problem 28
;Write a function which flattens a sequence.
(def __
  (fn [xs] (if-not (some coll? xs)
             xs
             (recur (mapcat #(if (coll? %) % [%]) xs)))))

(= (__ '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
(= (__ ["a" ["b"] "c"]) '("a" "b" "c"))
(= (__ '((((:a))))) '(:a))

;↓ 他人の解答
#_(def __  (fn f [x] (if (coll? x) (mapcat f x) [x])))
;↑再帰なってる
#_((fn fac [x]
     (if (zero? x)
       0
       (+ x (fac (dec x))))) 5)

#_(def __ #(filter (complement sequential?)
                   (rest (tree-seq sequential? seq %))))
;complement 引数fを取り、fと同じ引数を取り、(あれば)同じ副作用があり、反対の真偽値を返すfnを返します。
;(map even? '(1 2 3 4))
;=> (false true false true)
;(map (complement even?) '(1 2 3 4))
;=> (true false true false)
;squential? collがSequentialを実装している場合はtrueを返します
;tree-seq

;Problem 29
;Write a function which takes a string and returns a new string containing only the capital letters.
(def __ #(apply str (re-seq #"[A-Z]" %)))

(= (__ "HeLlO, WoRlD!") "HLOWRD")
(empty? (__ "nothing"))
(= (__ "$#A(*&987Zf") "AZ")


;Problem 30
;Write a function which removes consecutive duplicates from a sequence.
(def __ #(reduce (fn [xs v] (if (= v (last xs))
                              xs
                              (conj xs v))) [] %))

(= (apply str (__ "Leeeeeerrroyyy")) "Leroy")
(= (__ [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
(= (__ [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))

;他人の解答
#_#(map first (partition-by identity %))
;(partition-by identity "Leeeeeerrroyyy")
;=>((\L) (\e \e \e \e \e \e) (\r \r \r) (\o) (\y \y \y))

;Problem 31
;Write a function which packs consecutive duplicates into sub-lists.
(def __  #(partition-by identity %))

(= (partition-by identity [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
(= (__ [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
(= (__ [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))

;Problem 32
;Write a function which duplicates each element of a sequence.
(def __ (fn [xs] (apply concat (map #(vector % %) xs))))

(= (__ [1 2 3]) '(1 1 2 2 3 3))
(= (__ [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
(= (__ [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
(= (__ [44 33]) [44 44 33 33])

;他人の解答
#_(def __ #(interleave % %))
;(interleave [:a :b :c] [1 2 3])
;=> (:a 1 :b 2 :c 3)

;Problem 33
;Write a function which replicates each element of a sequence a variable number of times.
(def __ (fn [xs n] (mapcat #(repeat n %) xs)))

(= (__ [1 2 3] 2) '(1 1 2 2 3 3))
(= (__ [:a :b] 4) '(:a :a :a :a :b :b :b :b))
(= (__ [4 5 6] 1) '(4 5 6))
(= (__ [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))
(= (__ [44 33] 2) [44 44 33 33])

;他人の解答
#_(def __ #(mapcat (partial repeat %2) %1))

;Problem 34
;Write a function which creates a list of all integers in a given range.

(def __ #(take (- %2 %1) (iterate inc %1)))

(= (__ 1 4) '(1 2 3))
(= (__ -2 2) '(-2 -1 0 1))
(= (__ 5 8) '(5 6 7))

;Problem 38
;Write a function which takes a variable number of parameters and returns the maximum value.
(def __ (fn [& xs] (-> xs sort last)))

(= (__ 1 8 3 4) 8)
(= (__ 30 20) 30)
(= (__ 45 67 11) 67)

;Problem 39
;Write a function which takes two sequences and returns the first item from each, then the second item from each, then the third, etc.
;その1
(fn  [xs a b]
  (let [fa (first a)
        fb (first b)]
    (if-not (and fa fb)
      xs
      (recur (into xs [fa fb]) (rest a) (rest b))))) []
;その2
(def __ (fn [a b] (-> (map #(vector %1 %2)  a b) flatten)))


(+ 1 2)
(= (__ [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))
(= (__ [1 2] [3 4 5 6]) '(1 3 2 4))
(= (__ [1 2 3 4] [5]) [1 5])
(= (__ [30 20] [25 15]) [30 25 20 15])

;他の人の解答
#_ #(flatten (map list % %2))
;mapcat list

;Problem 40
;Write a function which separates the items of a sequence by an arbitrary value.

(def __ #(-> (interleave %2 (repeat  %1)) drop-last)) 
                                                     
(= (__ 0 [1 2 3]) [1 0 2 0 3])
(= (apply str (__ ", " ["one" "two" "three"])) "one, two, three")
(= (__ :z [:a :b :c :d]) [:a :z :b :z :c :z :d])

;Problem 41
;Write a function which drops every Nth item from a sequence.

(def __ (fn [xs n]
          (mapcat #(->> % drop-last (remove nil?)) (partition n n  (repeat nil) xs))))


(= (__ [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])
(= (__ [:a :b :c :d :e :f] 2) [:a :c :e])
(= (__ [1 2 3 4 5 6] 4) [1 2 3 5 6])

;他人の回答
#_#(apply concat (partition-all (dec %2) %2 %))
#_(fn [coll n]
  (mapcat #(take (dec n) %) (partition-all n coll)))

;Problem 42
;Write a function which calculates factorials.
(def __ #(apply * (range 1 (inc %))))

(= (__ 1) 1)
(= (__ 3) 6)
(= (__ 5) 120)
(= (__ 8) 40320)

;Problem 47
;The contains? function checks if a KEY is present in a given collection. 
;This often leads beginner clojurians to use it incorrectly 
;with numerically indexed collections like vectors and lists.

;contains? は与えられたコレクションの「キー」があるかをチェックする
(contains? #{4 5 6} __)
(contains? [1 1 1 1 1] 4) ;=> true インデックスは4まで
(contains? {4 :a 2 :b} 4) ;=> true 
(not (contains? [1 2 4] 4)) ;true インデックスは2まで


;Problem 44
;Write a function which can rotate a sequence in either direction.
;(take -2 [1 2 3 4 5])
;(take )(repeat %2)
#_(def __ #(->> (cycle %2)
              (drop (+ (count %2) %1))
              (take (count %2))))
(def __ #(let [c (count %2)]
           (->> (cycle %2)
                (drop  (mod %1 c))
                (take c))))

(= (__ 2 [1 2 3 4 5]) '(3 4 5 1 2))
(= (__ -2 [1 2 3 4 5]) '(4 5 1 2 3))
(= (__ 6 [1 2 3 4 5]) '(2 3 4 5 1))
(= (__ 1 '(:a :b :c)) '(:b :c :a))
(= (__ -4 '(:a :b :c)) '(:c :a :b))


;Problem 49
;Write a function which will split a sequence into two parts.
;(take  3 [1 2 3 4 5 6])
;(take 2 [[1 2] [3 4] [5 6]])
;(drop  3 [1 2 3 4 5 6])
(def __ #(vector (take %1 %2) (drop %1 %2)))

(= (__ 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])
(= (__ 1 [:a :b :c :d]) [[:a] [:b :c :d]])
(= (__ 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]])

 ;他の人の答え
#_(juxt take drop)


(= [1 2 [3 4 5] [1 2 3 4 5]] 
   (let 
    [[a b & c :as d] [1 2 3 4 5]]
    #_[a 1
     b 2
     c [3 4 5]
     d [1 2 3 4 5]] [a b c d]))