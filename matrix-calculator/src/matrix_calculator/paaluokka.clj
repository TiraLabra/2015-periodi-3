(ns matrix_calculator.paaluokka
  (:gen-class))

(defn -main [& args]
  (println "Welcome to my project! These are your args:" args))


(defn take-until [how-many elems]
  "Takes as parameters one number x and list of elements.
  Returns an array, where first element is x first element of
  given array and the second element is an array including the rest."
  (loop [x how-many
         elems elems
         result []
         rest-elems elems]
    (if (zero? x)
      [result rest-elems]
      (recur
       (dec x)
       (rest elems)
       (conj result (first elems))
       (rest rest-elems)))))

(defn make-matrix [rows columns & elems]
  "Takes as a parameters the number of rows x, the number of columns y
  and all elements of the matrix.
  Composes a presentation of matrix as an array with x elements which
  presents the rows. Each row contains y elements."
  (if (not= (* rows columns)(count elems))
    (throw (Exception.
            (str "You should give: amount of rows, amount of columns, "
            (* rows columns) " elements.")))
    (loop [i rows
           elems elems
           matrix []]
      (if (zero? i)
        matrix
        (let [row-tookd (take-until columns elems)
              row (first row-tookd)
              rest-elems (second row-tookd)]
          (recur (dec i) rest-elems (conj matrix row)))))))

(defn add-two-matrices [matrix-a matrix-b]
  "Takes two matrices as parameters. If the matrices are same type, sums them:
  To say, add every element of matrix-a to an according one of b. Returns an new matrix of same type.
  (A + B)(i,j) = A(i, j) + B(i, j) for all i to m and j to n, where m*n is the size/type of the matrices."
  (let [error (fn [] (throw (Exception. "Matrices are not same type!")))]
    (cond
     (not= (count matrix-a) (count matrix-b)) (error)
     (not= (count (first matrix-a)) (count (first matrix-b))) (error)
     :else
     (let [add-row (fn [row-a row-b] (map (fn [x y] (+ x y)) row-a row-b))]
       (map add-row matrix-a matrix-b)))))

(defn scalar-product [scalar matrix]
  "Takes one number and one matrix as parameters. Multiples every element of matrix by the scalar.
  DOES NOT WORK. Change make-matrix"
  (let [f (fn [m] (concat [(count m)(count (first m))] (map (fn [x] (* x scalar))(apply concat m))))]
    (apply f matrix)))
