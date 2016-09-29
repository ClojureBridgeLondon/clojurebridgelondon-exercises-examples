(ns clojurebridgelondon-exercises-examples.functions)


;; EXERCISE 1: Move turtles using function

;; Uses turtle walk.clj from the welcometoclojurebridge project, not this project.

;; EXERCISE 2: Move turtles using function with parameters

;; Uses turtle walk.clj from the welcometoclojurebridge project, not this project.



;; predicates

;; =, not=
;; >, <, >=, <=
;; true?, false?, empty?, nil?, vector?, map?


;; EXERCISE 3 [BONUS]: Find the average

;; Create a function called average that takes a vector of maps.
;; Use [{:angle 30} {:angle 90} {:angle 50}] as input.
;; Calculate average value of :angle.
;; Hint: You will need to use the functions map, reduce and count.


;; Lets start with a simpler example
;; We create a function that takes three angles
;; We add the angles together and divide by 3
(defn average-simple
  [angle1 angle2 angle3]
  (/ (+ angle1 angle2 angle3) 3))

(average-simple 30 90 50)


;; In the exercise itself, our values are contained within a collection, in this case a map.
;; So to solve this exercise, at some point we need to extract our values.

;; Previously we worked with values in a collection (a map) and used the get function
;; We also used the feature of keywords to help us get values from maps.
;; We can use one of the following to extract values from our collection of angles

(get {:angle 30} :angle)
;; => 30

(:angle {:angle 30})
;; => 30

;; When we have several maps in a vector, we need to iterate over each map
;; and extract each of the values.
;; We can use the map function to extract all the values of each map in the vector
;; however the get function we nomally would use isnt sufficient because the
;; get function requires two arguments, the map & the key

;; Neither of these expressions are correct because they call the get function incorrectly.
(map get [{:angle 30} {:angle 90} {:angle 50}] :angle)
(map get :angle [{:angle 30} {:angle 90} {:angle 50}])

;; To solve this we can use a fuction called partial.
;; The partial function allows you to call another function, but with a missing argument

;; In a simple example we add 90 to every number in the collection
(map (partial + 90) [0 30 60 90])
;; => (90 120 150 180)


;; For the exercise we use partial with the :angle keyword, which is passed each map in turn
;; as a second argument by the map function.
(map (partial :angle) [{:angle 30} {:angle 90} {:angle 50}])
;; => (30 90 50)

;; Now we can get all the values returned in a collection, we need to add them.
;; If we want to take all the values in a collection and get a single value
;; then you can use the reduce function.
;; Here we reduce these values by adding them together
(reduce + [30 90 50])


;; Lets give a name to our angles data, to make it easy to work with
(def angles [{:angle 30} {:angle 90} {:angle 50}])

;; Now we can check our map expression
(map (partial :angle) angles)
;; => (30 90 50)

;; We want the total value of all the angles, so we reduce them with the + function into one value
(reduce + (map (partial :angle) angles))


;; Now we need the average, so we divide the total value by the number of angle values
;; In this example, the count function returns how many maps are in the vector (there is one angle value per map)
(/ (reduce + (map (partial :angle) angles)) (count angles))
;; => 170/3

;; Now we can put this all in a function definition, so we can call it multiple times (if needed)
(defn average [angles]
  (/ (reduce + (map (partial :angle) angles)) (count angles)))

;; Now we call the average function with the arguments from the exercise
(average [{:angle 30} {:angle 90} {:angle 50}])

;; As we use the count function to help us find the average, we can call average with any number of maps in our vector
(average [{:angle 30} {:angle 90} {:angle 50} {:angle 60}])
;; => 115/2

(average [{:angle 30} {:angle 90} {:angle 50} {:angle 60} {:angle 80}])
;; => 62



;;;;;;;;;;;;;;;;;;;;;;;;
;; Alternative - anonymous function

;; Instead of using the partial function, you can use an anonymous function.
;; In this case we are using the short-hand syntax for an anonymous function.
(map #(get % :angle) angles)
;; => (30 90 50)

(reduce + (map #(get % :angle) angles))
;; => 170

(/ (reduce + (map #(get % :angle) angles)) (count angles))
;; => 170/3


;;;;;;;;;;;;;;;;;;;;;;;;
;; Let - local names / bindings

;; Here we use the let function to bind the name number-of-angles
;; to the number of angle maps passed in as the argument.
;; We can then use number-of-angles as the value to divide the total,
;; giving us the average value.
;; Local bindings are only within the scope of the let expression,
;; so calling number-of-angles after the closing perentesis of the let will cause an error.
(defn average [angles]
  (let [number-of-angles (count angles)]
    (/ (reduce + (map (partial :angle) angles)) number-of-angles)))

(average [{:angle 30} {:angle 90} {:angle 50}])
;; => 170/3
