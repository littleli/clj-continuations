(ns fun.clojure.continuations.wearemillions
  (:import (java.lang Thread)))

(def a-set (atom #{}))

(def threads
  (->> (range 1000000)
       (map (fn [i]
              (.unstarted (Thread/ofVirtual) #(swap! a-set conj i))))))

(def runs (map #(.start %1) threads))

(comment
  
  (time (count threads))
  ;; "Elapsed time: 247.7279 msecs"

  (time (count runs)) 
  ;; "Elapsed time: 4665.0366 msecs"

  (time (count @a-set)) 
  ;; "Elapsed time: 0.0277 msecs"

  )
