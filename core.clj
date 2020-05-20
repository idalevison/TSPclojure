(ns tsp-clo.core
  (:gen-class))

(declare tsp)

;; Some graphs to play with
;; REMEMBER TO CHANGE n AND v ACCORDINGLY
(def graph4 [[0 10 15 20] [10 0 35 25] [15 35 0 30] [20 25 30 0]])

(def graph5 [[0 10 15 20 25] [10 0 35 25 15] [15 35 0 30 10]
  [20 25 30 0 20] [10 15 20 25 0]])

(def graph9 [[0 47 39 75 67 8 42 53 87]
  [47 0 94 42 94 91 36 30 68] [39 94 0 91 48 37 25 1 93]
  [75 42 91 0 56 49 85 4 43] [67 94 48 56 0 64 44 40 81]
  [8 91 37 49 64 0 49 79 59] [42 36 25 85 44 49 0 55 28]
  [53 30 1 4 40 79 55 0 45] [87 68 93 43 81 59 28 45 0]])


(defn helploop
  [graph v currPos n count cost ans i]
  (cond
    (= i n) ans ; if we are at the end -> no more loop

    (and (not (v i)) (> ((graph currPos) i) 0))
      ;then
      (helploop graph v currPos n count cost
        (tsp graph (assoc v i true) i n (inc count) (+ cost ((graph currPos) i)) ans)
        (inc i))
    :else (helploop graph v currPos n count cost ans (inc i)))
)

(defn tsp
    [graph v currPos n count cost ans]
    ;; body
    (if (and (= count n) (> ((graph currPos) 0) 0))
      (min ans (+ cost ((graph currPos) 0)))
      ;; else
      (helploop graph v currPos n count cost ans 0))
)

(defn -main
  [& args]
  (let [n 9
   v [true false false false false false false false false] ; 9 st
   maxAns 500
   ret (tsp graph9 v 0 n 1 0 maxAns)]

   (println (str ret)))
)
