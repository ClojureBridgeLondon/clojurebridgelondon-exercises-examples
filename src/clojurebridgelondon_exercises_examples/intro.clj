(ns clojurebridgelondon-exercises-examples.intro)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Introduction to Programming with Clojure
;;
;; - part of the older ClojureBridge curriculum


;; Some really basic concepts

;; Everything is a list in Clojure.  List stands for List Processing.  So what is a list and does it work

;; Really simplistic example - a shopping list

;; (shopping-in-tesco "eggs" "bacon" "cheese" "cerial" "milk") 

;; We have a list of things we want to buy.  The first item in the list defines how we are going to get these things

;; We need to write shopping-in-tesco function to make this work



;; EXERCISE: Basic arithmetic

;; - Take your height in feet and inches and convert it to inches using arithmetic in Clojure.
;; - Then convert that to centimeters. There are 2.54 centimeters in an inch.
;; - Lastly, ask two people near you for their height in centimeters. Find the average of your heights.

;; - Bonus: Convert that average back to feet and inches. The feet and the inches will be separate numbers. (quot x y) will give you the whole number part when dividing two numbers. (mod x y) will give you the remainder when dividing two numbers.

;; Define a persons height in feet and inches using a map and Clojure keywords for the keys
(def john {:feet 6 :inches 2})

;; Or to give this data more contenxt, an allow for further definitions of data for john, lets put the feet and inches data as the value for the height key
(def john {:height {:feet 6 :inches 2}})

john


(defn convert-feet-and-inches-to-feet
  "Convert feet and inches values to feet"
  [feet inches]
  (+ (* feet 12) inches))

(convert-feet-and-inches-to-feet (:feet (john :height))
                                 (:inches (john :height)))


(defn convert-inches-to-centimeters
  "Convert inches to centimeters"
  [inches]
  (* inches 2.54))

(convert-inches-to-centimeters 74)


;; This function could also be written using a local symbol for the conversion multiplier (a local symbol is like a local variable, but immutable of course because this is Clojure)
(defn convert-inches-to-centimeters-alternative
  "Convert inches to centimeters - using let to define a local symbol"
  [inches]
  (let [centimeter-multiplier 2.54]
    (* inches centimeter-multiplier)))

(convert-inches-to-centimeters-alternative 74)



;; The height for two other people
(def Anna  {:height {:feet 5 :inches 10}})
(def Bruce {:height {:feet 6 :inches 8}})


;; Calculate the average hight for all three people, John, Anna, Bruce

;; Steps to implement:
;; convert each person to centimeters < just start with Centimeters
;; calculate the average
;; convert back to feet & inches

;; clojure-bridge-exercises.core> (def heights [10 20 30])
;; #'clojure-bridge-exercises.core/heights
;; clojure-bridge-exercises.core> (count heights)
;; 3
;; clojure-bridge-exercises.core> (apply + heights)
;; 60


(defn average-height-in-centimeters
  "Given peoples height in centimeters, return the average height in centimeters"
  [heights]
  (/ (apply + heights) (count heights)))

(average-height-in-centimeters [180 220 140])

;; If the average is not a whole nuber, we get an interesting result
(average-height-in-centimeters [180 220 160])

;; Lets tweak the values and get a decimal number
(average-height-in-centimeters [180 220 160.0])


;; clojure-bridge-exercises.core> (quot 15 7)
;; 2
;; clojure-bridge-exercises.core> (mod 15 7)
;; 1

(defn convert-centimeters-to-feet-and-inches
  "Convert values in centimeters to feet and inches"
  [value]
  (let [feet (quot (/ value 2.54) 12)
        inches (mod (/ value 2.54) 12)]
    {:feet feet :inches inches}))

(defn average-height-in-feet-given-centimeters
  "Calculate the average hight of three people"
  [people]
  (convert-centimeters-to-feet-and-inches (average-height-in-centimeters people)))

(average-height-in-feet-given-centimeters [160 180 200])
