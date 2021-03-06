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

;??? ???????????????
#_(def __  (fn f [x] (if (coll? x) (mapcat f x) [x])))
;?????????????????????
#_((fn fac [x]
     (if (zero? x)
       0
       (+ x (fac (dec x))))) 5)

#_(def __ #(filter (complement sequential?)
                   (rest (tree-seq sequential? seq %))))
;complement ??????f????????????f???????????????????????????(?????????)??????????????????????????????????????????????????????fn??????????????????
;(map even? '(1 2 3 4))
;=> (false true false true)
;(map (complement even?) '(1 2 3 4))
;=> (true false true false)
;squential? coll???Sequential??????????????????????????????true???????????????
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

;???????????????
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

;???????????????
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

;???????????????
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
;??????1
(fn  [xs a b]
  (let [fa (first a)
        fb (first b)]
    (if-not (and fa fb)
      xs
      (recur (into xs [fa fb]) (rest a) (rest b))))) []
;??????2
(def __ (fn [a b] (-> (map #(vector %1 %2)  a b) flatten)))


(+ 1 2)
(= (__ [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))
(= (__ [1 2] [3 4 5 6]) '(1 3 2 4))
(= (__ [1 2 3 4] [5]) [1 5])
(= (__ [30 20] [25 15]) [30 25 20 15])

;??????????????????
#_#(flatten (map list % %2))
;mapcat list

;Problem 40
;Write a function which separates the items of a sequence by an arbitrary value.

(def __ #(-> (interleave %2 (repeat  %1)) drop-last))

(= (__ 0 [1 2 3]) [1 0 2 0 3])
(= (apply str (__ ", " ["one" "two" "three"])) "one, two, three")
(= (__ :z [:a :b :c :d]) [:a :z :b :z :c :z :d])

;???????????????
(def __ #(rest (mapcat vector (repeat %1) %2)))

;Problem 41
;Write a function which drops every Nth item from a sequence.
(def __ (fn [xs n]
          (mapcat #(->> % drop-last (remove nil?)) (partition n n  (repeat nil) xs))))

(= (__ [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])
(= (__ [:a :b :c :d :e :f] 2) [:a :c :e])
(= (__ [1 2 3 4 5 6] 4) [1 2 3 5 6])

;???????????????
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


;Problem 43
;Write a function which reverses the interleave process into x number of subsequences.
;(take-nth 2 [0 1 2 3 4 5])
(def __ (fn [xs i]
          (loop [ys xs
                 j i
                 result '()]
            (if (zero? j)
              (reverse result)
              (recur (rest ys) (dec j)
                     (conj result (take-nth i ys)))))))

(= (__ [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6)))
(= (__ (range 9) 3) '((0 3 6) (1 4 7) (2 5 8)))
(= (__ (range 10) 5) '((0 5) (1 6) (2 7) (3 8) (4 9)))

;????????????
#(apply map list (partition %2 %1))
;(partition 2 [1 2 3 4 5 6]) ;((1 2) (3 4) (5 6))
;(apply map list '((1 2) (3 4) (5 6)))
;(map list  '(1 2) '(3 4) '(5 6)) 
;((1 3 5) (2 4 6))

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

;????????????
#(let [x (mod %1 (count %2))]
   (concat (drop x %2) (take x %2)))

;Problem 46
;Write a higher-order function which flips the order of the arguments of an input function.
(def __  #(fn [a b] (% b a)))

(= 3 ((__ nth) 2 [1 2 3 4 5]))
(= true ((__ >) 7 8))
(= 4 ((__ quot) 2 8))
(= [1 2 3] ((__ take) [1 2 3 4 5] 3))


;Problem 47
;The contains? function checks if a KEY is present in a given collection. 
;This often leads beginner clojurians to use it incorrectly 
;with numerically indexed collections like vectors and lists.

;contains? ????????????????????????????????????????????????????????????????????????????????????
(contains? #{4 5 6} __)
(contains? [1 1 1 1 1] 4) ;=> true ?????????????????????4??????
(contains? {4 :a 2 :b} 4) ;=> true 
(not (contains? [1 2 4] 4)) ;true ?????????????????????2??????

;Problem 49
;Write a function which will split a sequence into two parts.
;(take  3 [1 2 3 4 5 6])
;(take 2 [[1 2] [3 4] [5 6]])
;(drop  3 [1 2 3 4 5 6])
(def __ #(vector (take %1 %2) (drop %1 %2)))

(= (__ 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])
(= (__ 1 [:a :b :c :d]) [[:a] [:b :c :d]])
(= (__ 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]])

 ;??????????????????
#_(juxt take drop)
; ((juxt a b c) x) => [(a x) (b x) (c x)]


;Problem 50
;Write a function which takes a sequence consisting of items with different types and splits them up into a set of homogeneous sub-sequences. The internal order of each sub-sequence should be maintained, but the sub-sequences themselves can be returned in any order (this is why 'set' is used in the test cases) .
(def __ #(->> (group-by type %)
              vals))

(= (set (__ [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})
(= (set (__ [:a "foo"  "bar" :b])) #{[:a :b] ["foo" "bar"]})
(= (set (__ [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]})

;type x??????type????????????????????????????????????????????????????????????????????????
;class x???????????????????????????
#_(doseq [a [1 1.0 2/3 \a "a" nil true [] {} #{}]]
    (println (str a " type-> " (type a) " class-> " (class a))))

;Problem 51
(= [1 2 [3 4 5] [1 2 3 4 5]]
   (let
    [[a b & c :as d] [1 2 3 4 5]]
     #_[a 1
        b 2
        c [3 4 5]
        d [1 2 3 4 5]] [a b c d]))

;Problem 56
;Write a function which removes the duplicates from a sequence. Order of the items must be maintained.

(reduce (fn [xs v]
          (if (empty? (filter #(= % v) xs))
            (conj xs v)
            xs)) [] [1 2 1 3 1 2 4] )

(= (__ [1 2 1 3 1 2 4]) [1 2 3 4])
(= (__ [:a :a :b :b :c :c]) [:a :b :c])
(= (__ '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))
(= (__ (range 50)) (range 50))

;????????????
reduce #(if (some #{%2} %1) %1 (conj %1 %2)) []
; some 
;coll????????????????????????x??????????????? (pred x) ??????????????????????????????????????????????????????nil??????????????????
;????????????????????? :fred ???????????????????????????????????? :fred ????????????????????????????????? nil ?????????????????? (some #{:fred} coll)
(some even? [1 2 3 4]) ;true
(some even? [1 3 5 7]) ;nil
(some #(when (even? %) %) '(1 2 3 4)) ;;2

;Problem 55
;Write a function which returns a map containing the number of occurences of each distinct item in a sequence.
(type {:oen :two}) ;clojure.lang.PersistentArrayMap
(type (first {:one :two})) ;clojure.lang.MapEntry

(#(zipmap (keys %) (map count (vals %))) (first {1 [1 1 1]}))
(def __ (fn [xs] (->> (group-by identity xs)
                      (reduce #(assoc %1 (key %2) (count (val %2))) {}))))

(= (__ [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1})
(= (__ [:b :a :b :a :b]) {:a 2, :b 3})
(= (__ '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2})

;????????????
reduce #(assoc %1 %2 (inc (%1 %2 0))) {}
;(reduce #(assoc %1 %2 (inc (%1 %2 0))) {} [1 1 2 3 2 1 1])
;(assoc {} 1 (inc ({} 1 0)))
;(assoc {1 1} 1 (inc ({1 1} 1 0)))
;(assoc {1 2} 1 (inc ({1 2} 2 0)))
;(assoc {1 2, 2 1} 1 (inc ({1 2, 2 1} 3 0)))

;Problem 61
;Write a function which takes a vector of keys and a vector of values and constructs a map from them.
(def __ (fn [ks vs]
          (->>  (map #(hash-map %1 %2) ks vs)
                (reduce merge))))


(= (__ [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3})
(= (__ [1 2 3 4] ["one" "two" "three"]) {1 "one", 2 "two", 3 "three"})
(= (__ [:foo :bar] ["foo" "bar" "baz"]) {:foo "foo", :bar "bar"})

;????????????
#_#(apply hash-map (interleave %1 %2))
#_#(apply hash-map (mapcat list %1 %2))
#_#(into {} (map vector %1 %2))





;Problem 66
;Given two integers, write a function which returns the greatest common divisor.
;(mod 1023 853)
;(mod 853 170)
;(mod 170 3)
;(mod 3 2) 
;(mod 2 1) 1

;(mod 10 5) 5

;(mod 2 4)
;(mod 4 2) 2

(def __ (fn [a b]
          (let [c (mod a b)]
            (if (zero? c)
              b
              (recur b c)))))


(= (__ 2 4) 2)
(= (__ 10 5) 5)
(= (__ 5 7) 1)
(= (__ 1023 858) 33)

;????????????
#_(fn [a b]
    (if (zero? b)
      a
      (recur b (mod a b))))

;Problem 81
;Write a function which returns the intersection of two sets. ;
;The intersection is the sub-set of items that each set has in common.

(def __ #(let [u (clojure.set/union %1 %2)
               d1 (clojure.set/difference %1 %2)
               d2 (clojure.set/difference %2 %1)]
           (->> u
                (remove d1)
                (remove d2)
                set)))

(= (__ #{0 1 2 3} #{2 3 4 5}) #{2 3})
(= (__ #{0 1 2} #{3 4 5}) #{})
(= (__ #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d})
(= (__ #{0 1 2 3} #{2 3 4 5}) #{2 3})

;????????????
(comp set filter)
#(-> (filter %1 %2)
     set)
clojure.set/select


;Problem 83
;Write a function which takes a variable number of booleans. 
;Your function should return true if some of the parameters are true, 
;but not all of the parameters are true. 
;Otherwise your function should return false.

(def __ (fn [& xs]
          (not (or
                (every? false? xs)
                (every? true? xs)))))

(= false (__ false false))
(= true (__ true false))
(= false (__ true))
(= true (__ false true false))
(= false (__ true true true))
(= true (__ true true true false))

;????????????
not=


;Problem 88
;Write a function which returns the symmetric difference of two sets. 
;The symmetric difference is the set of items belonging to one but not both of the two sets.
(def __ #(let [s1 (remove %1 %2)
               s2 (remove %2 %1)]
           (set (clojure.set/union s1 s2))))


(= (__ #{1 2 3 4 5 6} #{1 3 5 7}) #{2 4 6 7})
(= (__ #{:a :b :c} #{}) #{:a :b :c})
(= (__ #{} #{4 5 6}) #{4 5 6})
(= (__ #{[1 2] [2 3]} #{[2 3] [3 4]}) #{[1 2] [3 4]})

;????????????
;#(into (set (remove %1 %2)) (remove %2 %1))
;reduce #((if (%1 %2) disj conj) %1 %2)  
;  if (%1 %2) ??? if (#{1 2 3} 1) -> 1???if (#{1 2 3} 4) -> nil


;Problem 90
;Write a function which calculates the Cartesian product of two sets.
(def __ #(set (for [a %1 b %2] [a b])))

(= (__ #{"ace" "king" "queen"} #{"???" "???" "???" "???"})
   #{["ace"   "???"] ["ace"   "???"] ["ace"   "???"] ["ace"   "???"]
     ["king"  "???"] ["king"  "???"] ["king"  "???"] ["king"  "???"]
     ["queen" "???"] ["queen" "???"] ["queen" "???"] ["queen" "???"]})
(= (__ #{1 2 3} #{4 5})
   #{[1 4] [2 4] [3 4] [1 5] [2 5] [3 5]})
(= 300 (count (__ (into #{} (range 10))
                  (into #{} (range 30)))))

;Problem 107
;Given a positive integer n, return a function (f x) which computes xn.

(def __ #(fn [x]
           (int (Math/pow x %))))
;???Math/pow????????????????

;(reduce * 1 (repeat 8 2))
;(apply * (repeat 8 2))
(def __ #(fn [x]
           (apply * (repeat % x))))


(= 256 ((__ 2) 16), ((__ 8) 2))
(= [1 8 27 64] (map (__ 3) [1 2 3 4]))
(= [1 2 4 8 16] (map #((__ %) 2) [0 1 2 3 4]))


