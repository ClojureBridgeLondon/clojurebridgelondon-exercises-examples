(ns clojurebridgelondon-exercises-examples.simple-values)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; EXERCISE 1: Basic arithmetic

;; How many minutes have elapsed since you arrived at the workshop today?
;; Convert this value from minutes to seconds.

;; eg. its been 2 hours and 5 minutes.  So that is 60 x 2 + 5 (+ (* 60 2) 5)

;; just putting in the values for 125 minutes since the workshop started
;; multiplying by 60 to get the seconds
(* 125 60)
;; => 7500



;;;;;;;;;;;;;;;;;;;;;;;;
;; To go a little further... we bind names to the values of hours and minutes

(def hours 2)
(def minutes 5)

;; we can then calculate the mintues in the hours so far
(* hours 60)
;; => 120

;; then work our the total time in minutes
(+ (* hours 60) minutes)
;; => 125

;; finally working out the total time in seconds
(* (+ (* hours 60) minutes) 60)
;; => 7500

;; formating to see the code more clearly
(* (+
    (* hours 60)
    minutes)
   60)
;; => 7500


;;;;;;;;;;;;;;;;;;;;;;;;
;; or writing this using the threading macro
(-> minutes
    (+ (* hours 60))
    (* 60))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; EXERCISE 2 [BONUS]: Minutes and seconds

;; Convert 1000 seconds to minutes and seconds.
;; The minutes and the seconds will be separate numbers.
;; (quot x y) will give you the whole number part of x divided by y.
;; (rem x y) will give you the remainder of x divided by y.

;; How many whole minutes in 1000 seconds?
(quot 1000 60)
;; => 16

;; How many seconds remain when you have taken all the whole minutes away from 1000 seconds
(rem 1000 60)
;; => 40

(str "There are " (quot 1000 60) " minutes and " (rem 1000 60) " seconds.")
;; => "There are 16 minutes and 40 seconds."
