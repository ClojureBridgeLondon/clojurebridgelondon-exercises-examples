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
    (+ ,,, (* hours 60))
    (* ,,, 60))



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

;; (/ 1000 60)





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Simple values from old ClojureBridge curriculum

;;;;;;;;;;;;
;; simple values

"This is a simple string"
"Strings evaluate to themselves"
"Strings are immutable like other data structures in Clojure"
(str "Strings" "can" "be" "joined" "togehter" "using" "the" "str" "function")
(str "but str doesnt add" "spaces" "between strings")
(str "so add your own" " " "spaces")


;; Experimenting with joining strings
(def join-my-strings-with-spaces ["join" "us" "toether" "with" "spaces"])

;; (map str join-my-strings-with-spaces)

;; (apply str join-my-strings-with-spaces)

;; Using another function called interpose, we can place something between each string as we join them together
(apply str (interpose " " join-my-strings-with-spaces))

;; See https://clojuredocs.org/clojure.core/interpose


;; If you come from a Java world, you may want to use println to output a string.  Well you can

(println "This is my string, but caveat emptor, this is a side effect")

;; When you evaluate the above, you do not get a string in return... so what is going on?

;; Discuss evalation verses side effects here?


;; EXERCISE: Store the name of your hometown

"Ripon"

;; Write the name of your hometown as a string, and then assign that string to the name my-hometown.
;; EXERCISE: Make a function to format names

(def my-hometown "Ripon")

my-hometown

;; The str function can take any number of arguments, and it concatenates them together to make a string. Write a function called format-name that takes two arguments, first-name and last-name. This function should output the name like so: Last, First.

(defn format-name
  "Concatenate two strings together"
  [firstname lastname]
  (str firstname " " lastname))

(format-name "John" "Stevenson")

;; Adding in our interpose example from before...

(defn format-name
  "Concatenate two strings together"
  [firstname lastname]
  (interpose " " (str firstname lastname)))

(format-name "John" "Stevenson")

;; Thats not quite right.  If we change the arguments to a vector then we can

(defn format-name
  "Concatenate two strings together"
  [strings-to-join]
  (apply str (interpose " " strings-to-join)))

(format-name ["John" "Stevenson"])

