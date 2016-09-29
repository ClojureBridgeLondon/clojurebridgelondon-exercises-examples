(ns clojurebridgelondon-exercises-examples.data-structures)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; EXERCISE 1: See turtle names

;; Uses turtle walk.clj from the welcometoclojurebridge project, not this project.



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; EXERCISE 2: Make a vector

;; Go to myproject’s core.clj and start InstaREPL
;; Make a vector of the high temperatures for the next 7 days in the town where you live.
;; Then use the nth function to get the high temperature for next Tuesday.

;; A simple vector just holding the temperatures in celceus for each day of the week
[23 24 25 29 32 18 17]

;; If we want the temperature of a particular day, we can use one of the sequence fuctions, eg. nth
;; The nth function uses the vectors index to find the value, the vectors index starts at zero.

;; Assuming this vector starts with Monday (position 0), then Tuesday will be at position 1

(nth [23 24 25 29 32 18 17] 1)
;; => 24

;; If we were going to use thes temperature readings multiple times, we could give them a names

(def london-temperatures [23 24 25 29 32 18 17])

(nth london-temperatures 2)
;; => 25

(first london-temperatures)
;; => 23

(last london-temperatures)
;; => 17

;;;;;;;;;;;;;;;;;;;;;;;;
;; Additional: what if we paired the temperatures with their days
;; This would make it easier to reason about the values

;; We could use strings for the keys, for each day of the week
{"Monday" 23 "Tuesday" 24 "Wednesday" 25 "Thursday" 29 "Friday" 32 "Saturday" 18 "Sunday" 17}

;; Lets give this a name (symbol) so we can use it easily in other places
(def temperatures-strings {"Monday" 23 "Tuesday" 24 "Wednesday" 25 "Thursday" 29 "Friday" 32 "Saturday" 18 "Sunday" 17})

;; The get function will return the value assosicate with the specified key
(get temperatures-strings "Monday")
;; => 23

;; We can also write this in shorthand, using the fact that maps can get values given a key
(temperatures-strings "Monday")

;; We can also use keywords as the keys.
{:Monday 23 :Tuesday 24 :Wednesday 25 :Thursday 29 :Friday 32 :Saturday 18 :Sunday 17}

;; And give this a name too
(def temperatures-keywords {:Monday 23 :Tuesday 24 :Wednesday 25 :Thursday 29 :Friday 32 :Saturday 18 :Sunday 17})

;; The get function works the same
(get temperatures-keywords :Monday)
;; => 23

;; As before we can use just the map and keyword
(temperatures-keywords :Monday)
;; => 23

;; As we are using keywords for keys, we can also do a little short-cut
(:Monday temperatures-keywords)
;; => 23



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; EXERCISE 3: See turtles states

;; Go to walk.clj file, not this project


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; EXERCISE 4: Modeling Yourself

;; Use the myproject’s core.clj and InstaREPL
;; Make a map representing yourself
;; Make sure it contains your first name and last name
;; Then, add your hometown to the map using assoc or merge


;; You could create the simplest possible
;; Here the key is my first name and value is my last name
{"John" "Stevenson"}

;; To make the map clearer, you could use keys that define some context for the values
{"name" "John Stevenson"}
{"firstname" "John" "lastname" "Stevenson"}


;; To add a key / value pair to a map you can use assoc or merge

;; (assoc map keys value)

(assoc {"firstname" "John" "lastname" "Stevenson"} "hometown" "Harrogate")
;; => {"firstname" "John", "lastname" "Stevenson", "hometown" "Harrogate"}

;;(merge map map)

(merge {"firstname" "John" "lastname" "Stevenson"} {"hometown" "Harrogate"})
;; => {"firstname" "John", "lastname" "Stevenson", "hometown" "Harrogate"}

;; NOTE: You may have noticed the results have comma seperated pairs.  Commas have no meaning in Clojure, they are just there to help the developer.

;;;;;;;;;;;;;;;;;;;;;;;;
;; Previously we have used strings for the keys, we can also use Clojure keywords for the keys.
;; Keywords point to themselves (as apposed to bindins / names that point to another value).
;; Keywords can be used to make finding things in maps really easy.

;; We could have a simple name key and fullname as a value
{:name "John Stevenson"}

;; Or split the name into first and last keys and values
{:firstname "John" :lastname "Stevenson"}

;; Association still works the same
(assoc {:firstname "John" :lastname "Stevenson"} :hometown "Harrogate")
;; => {:firstname "John", :lastname "Stevenson", :hometown "Harrogate"}

(merge {:firstname "John" :lastname "Stevenson"} {:hometown "Harrogate"})


;;;;;;;;;;;;;;;;;;;;;;;;
;; Taking things further

;; You can go further and create a nested map
{:name {:first "John" :last "Stevenson"}}

;; You can then add other keys at the top level as before
(assoc {:name {:first "John" :last "Stevenson"}} :hometown "Harrogate")
;; => {:name {:first "John", :last "Stevenson"}
;;     :hometown "Harrogate"}

(merge {:name {:first "John" :last "Stevenson"}} {:hometown "Harrogate"})
;; => {:name {:first "John", :last "Stevenson"}
;;     :hometown "Harrogate"}


;; The assoc and merge add values to a map.
;; You can also update the values in a map using the update and update-in functions

(def johns-details {:name {:first "John" :last "Stevenson"}
                    :hometown "Harrogate"
                    :age 24})

;; The update function takes a map, key and function as arguemnts.
;; The function takes the existing value as an argument.
;; In this example we increment my age by 1

(update johns-details :age inc)
;; => {:name {:first "John", :last "Stevenson"}
;;     :hometown "Harrogate"
;;     :age 25}



;; EXERCISE 5 [BONUS]: Modeling your classmates

;; First, take the map you made about yourself in previous exercise.
;; Then, create a vector of maps containing the first name, last name and hometown of two or three other classmates around you.
;; Lastly, add your map to their information using conj.

{:firstname "John" :lastname "Stevenson"}

[{:firstname "John"  :lastname "Stevenson"}
 {:firstname "Betty" :lastname "Smith"}
 {:firstname "Jane"  :lastname "Aire"}]

(def people [{:firstname "John"  :lastname "Stevenson"}
             {:firstname "Betty" :lastname "Smith"}
             {:firstname "Jane"  :lastname "Aire"}])


;; To get values from such a data structure, you can get individual maps
(first people)
;; => {:firstname "John", :lastname "Stevenson"}

(get (first people) :firstname)
;; => "John"

(conj people {:firstname "Susan" :lastname "Davros"})
;; => [{:firstname "John", :lastname "Stevenson"}
;;     {:firstname "Betty", :lastname "Smith"}
;;     {:firstname "Jane", :lastname "Aire"}
;;     {:firstname "Susan", :lastname "Davros"}]


;;;;;;;;;;;;;;;;;;;;;;;;
;; Additional examples

;; To get all the first names in the data structure we use the get function,
;; however, as we are working over a collection, we wrap the get function
;; in an anonymous function.  Then we map this anonymous function over each
;; element of the collection and collect the results in a list that we return
;; as the result.
;; The anonymous function uses the % to indicate where each element is placed
;; in turn, just before the get function is evaluated.

(map #(get % :firstname) people)
;; => ("John" "Betty" "Jane")

;; and similarly for all the last names
(map #(get % :lastname) people)
;; => ("Stevenson" "Smith" "Aire")
