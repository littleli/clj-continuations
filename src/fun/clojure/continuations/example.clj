(ns fun.clojure.continuations.example
  (:import (jdk.internal.vm Continuation ContinuationScope)))

(def current-scope (ContinuationScope. "current-scope"))

(def continuation (Continuation. current-scope
                                 (fn []
                                   (println "Running")
                                   (Continuation/yield current-scope)
                                   (println "Still running"))))

(comment

  (time (println "Start"))

  (time (.run continuation))

  (time (println "Back"))

  (time (.run continuation))

  (time (println "Done"))

  ,)