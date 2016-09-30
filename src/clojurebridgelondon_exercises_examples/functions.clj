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




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Functions - older ClojureBridge curiculum


;; EXERCISE: Find per-person share of bill among a group

;; Create a new function called share-per-person.

;; Modify our total-with-tip function, and call the new function share-per-person, that additionally takes in as an argument the number of people in the group for a bill. Have the function return the average bill amount per person.

(defn share-per-person
  "Share the cost of a bill evenly for everyone at the meal"
  [meal-cost people]
  (/ meal-cost people))


(defn total-with-tip-per-person
  "Given subtotal, return total after tax and tip per person (hard-coded)."
  [subtotal tip-percent]
  (share-per-person (* 1.08 subtotal (+ 1 tip-percent)) 2))

(total-with-tip-per-person 12.50 0.18)
(total-with-tip-per-person 12.50 0)



;; EXERCISE: Find the average

;; Create a function called average that takes a vector of bill amounts and returns the average of those amounts.

;; Hint: You will need to use the functions reduce and count.

;; Define a collection of the orders for a group of people at a resteraunt
(def dine-in-orders [12.50 20 21 16 18.40])

;; Define a collection of orders for a take-away for a party
(def take-out-orders [6.00 6.00 7.95 6.25])

;; Use the previously defined function, total-bill, to add tax to each of the order costs in the collection
(map total-bill dine-in-orders)  ;=> [13.5 21.6 22.68 17.28 19.872]
(map total-bill take-out-orders) ;=> [6.48 6.48 8.586 6.75]


;; Lets look at map and reduce functions

(reduce + dine-in-orders)

;; So we can easily get the total cost of the orders, the average per person is simply the total divided by the number of orders

;; Lets get the number of orders from the collection
(count dine-in-orders)

;; So joining these two together we can get the average
(/ (reduce + dine-in-orders) (count dine-in-orders))

;; Lets put this into a function so we have a name for this behaviour which we can call

(defn total-bill-per-person
  "Given subtotal of bill, return total after tax."
  [individual-order-costs]
  (/ (reduce + dine-in-orders) (count dine-in-orders)))

(total-bill-per-person dine-in-orders)


;; But wait, we have forgotten to include sales tax

(defn total-bill-per-person-with-tax
  "Given subtotal of bill, return total after tax."
  [individual-order-costs]
  (/ (reduce + (map total-bill dine-in-orders)) (count dine-in-orders)))

(total-bill-per-person dine-in-orders)

;; Oh no, we forgot the tip as well....

(defn total-bill-per-person-with-tax-and-tip
  "Given subtotal of bill, return total after tax."
  [individual-order-costs]
  (/ (reduce + (map total-with-tip dine-in-orders )) (count dine-in-orders)))

(total-bill-per-person dine-in-orders)

;;; This is not quite right, as we need to pass in the percentage of tip.... not sure how to do that, perhaps a partial function?

;; We can use the function apply instead of reduce.  These functions can seem similar in concept, so lets see what we can do with apply to help you understand the difference


;; Lets get the total cost of the dine-in orders
(apply + dine-in-orders)

;; Now if we divide by the number of orders in the collction, we can get the average
(/ (apply + dine-in-orders) (count dine-in-orders) )

;; This all looks the same, so lets look at the Clojure docs to try understand the difference


;; > Note: Clojure is strict when it comes to functions as arguments.  This means that a function pased as an argument to another function is evaluated before it is passed as the argument.  So a function always recieves values as arguments, because a function always evaluated to a value (even if that value is nil).




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Functions from old ClojureBridge content
;;
;; - includes some more advanced examples

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; More functions


;; Note for existing devs:  Clojure does not use = for assignment, so you dont need hacks like == for assignement.
;; Clojure keeps it simple


;; Naming conventions: Predicates?
;; When a function is asking a question where the answer is either true or false, the naming convention is to add a ? to the end of the function name.  for example
;; (true? (= 1 1))
;; (false? (= 1 2))

;; (vector? [1 2 3])



;; EXERCISE: Modeling Yourself

;; Make a map representing yourself. Make sure it contains your first name and last name. Then, add your hometown to the map using assoc or merge.

;; Hint: First, create a function that returns the name when given a single person's map.  

(def john
  {:firstname "john" :lastname "stevenson"})

(merge john {:hometomwn "Ripon"})
(assoc john :hometomwn "Ripon")


;; EXERCISE 2: Get the names of people

;; Create a function called get-names that takes a vector of maps of people and returns a vector of their names.

;; Here is an example of how it should work:

(get-names [{:first "Margaret" :last "Atwood"}
            {:first "Doris" :last "Lessing"}
            {:first "Ursula" :last "Le Guin"}
            {:first "Alice" :last "Munro"}])

;=> ["Margaret Atwood" "Doris Lessing" "Ursula Le Guin" "Alice Munro"]


(defn get-names [person]
  (let [name (:name person)
        age  (:age person)]
    (str name)))


(macroexpand '(let [name (:name person)
                    age (:age person)]
                (str name)))

;; (macroexpand let*)

;; A shorthand version of get-name function, using the keyword as the get function
(defn get-names-shorthand [person]
  (:name person))

(get-names {:name "john stevenson" :age 21})




;; EXERCISE: Modeling your classmates

;; First, take the map you made about yourself.

;; Then, create a vector of maps containing the first name, last name and hometown of two or three other classmates around you.

;; Lastly, add your map to their information using conj.

;; Use the get-names function from the previous Exercise to output a list of the names.





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Predicates, Sequence comprehension & laziness

;; Predicates
;; A predicate is function that will return true or false.
;; The naming convention in Clojure is to add a ? to the end of the name.

(even? 7)
(even? 8)

;; Range generates an infinate sequence, however using the take function
;; a specific sequence range is returned.
(take 1000 (range))

;; return only the even numbers
(def evens
  (filter even? (take 1000 (range))))


;; Using an anonymous function with the filter function to only return values
;; where the value of dividing a value by 3 has an even remainder
;; (filter (fn [x] (even? (mod x 3))) '(1 2 3 4 5 6))

(def evens
  (filter (fn [x] (even? (mod x 3))) (filter even? (take 1000 (range)))))

evens

;; map, take, drop, filter, rest :: sequence->sequence
;; reduce, first  :: sequence->single
(def evens
  (->>
   (range)
   (take 1000)
   (filter even?)
   (filter (fn [x] (even? (mod x 3))))))


;;;;;;;;;;;;;;;;;;;
;; Threading Macros

;; Using the Threading macros provides a simple way to chain functions together
;; The reader macro for comments can be used to only run specific functions in the chain.
;; In this Thread first example, the value of the previous function is the first
;; argument to the next function
;; Uncomment each line and evaluate to see the value returned by each expression
(->
  {}
  #_(assoc ,,, :x {:x1 8})
  #_(update-in ,,, [:x :x1] inc)
  #_(assoc ,,, :y 99 :z 199999)
  #_(dissoc ,,, :z))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Understanding Clojure macros

;; Macros provide a way to extend the Clojure syntax and are typically used
;; to maintain simplicity in the language.
;; To look under the covers of Clojure macros you can use the macroexpand function
;; (clojure.walk/macroexpand-all ')

(clojure.walk/macroexpand-all '(defn expand-me [args] (str "behaviour of function")))

;; expands to
;; (def expand-me (fn* ([args] (str "behaviour of function"))))
