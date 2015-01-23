(ns matrix_calculator.paaluokka
  (:gen-class))

(defn -main [& args]
  (println "Welcome to my project! These are your args:" args))

(defn invalid-matrix-notification []
  (throw (Exception. "Invalid matrix!")))
(defn matrices-are-not-valid-type []
  (throw (Exception. "The given matrices are not valid type! ")))

(defn take-until
  "Takes as parameters one number x and list of elements.
  Returns an array, where first element is x first element of
  given array and the second element is an array including the rest."
  [how-many elems]
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

(defn make-matrix
  "Takes as a parameters the number of rows x, the number of columns y
  and all elements of matrix in an array.
  Composes a presentation of matrix as an array with x elements which
  presents the rows. Each row contains y elements."
  [rows columns elems]
  (if (not= (* rows columns)(count elems))
    (throw (Exception.
            (str "You should give: amount of rows, amount of columns and array of "
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

(defn add-two-matrices
  "Takes two matrices as parameters. If the matrices are same type, sums them:
  To say, add every element of matrix-a to an according one of b. Returns an new matrix of same type.
  (A + B)(i,j) = A(i, j) + B(i, j) for all i to m and j to n, where m*n is the size/type of the matrices."
  [matrix-a matrix-b]
    (if
      (or
       (not= (count matrix-a) (count matrix-b))
       (not= (count (first matrix-a)) (count (first matrix-b))))
        (matrices-are-not-valid-type)
        (let [add-row (fn [row-a row-b] (map (fn [x y] (+ x y)) row-a row-b))]
           (map add-row matrix-a matrix-b))))

(defn is-matrix?
  "Tests if the given matrix is an array including array(s)."
  [matrix]
  (and (vector? matrix) (vector? (first matrix))))

(defn scalar-product
  "Takes one number and one matrix as parameters. Multiples every element of matrix by the scalar.
  Returns a new matrix, which applies to formula (cA)(i,j) = c * A(i, j) where c is the scalar,
  A a matrix and i and j the indexes of matrix."
  [scalar matrix]
  (if (or (not (is-matrix? matrix)) (not (number? scalar)))
    (throw (Exception. "You should give one number and one valid matrix."))
    (make-matrix (count matrix) (count (first matrix))
                 (map (fn [elem] (* elem scalar))
                      (apply concat matrix)))))

(defn take-column
  "Returns elements in the column of given index"
  [index matrix]
  (vec (map (fn [x] (get x index)) matrix)))

(defn transpose
  "Changes the rows and columns of matrix. Transpose of matrix
  [1 2]  is [1 3 5]
  [3 4]     [2 4 6]
  [5 6]"
  [matrix]
  (if (not (is-matrix? matrix))
    (invalid-matrix-notification)
    (apply map vector matrix)))

(defn get-elem
  "Returns elem in the spot matrix[y][x]."
  [y x matrix]
  (get (get matrix y) x))

(defn set-elem
  [y x matrix new-value]
  (assoc matrix y (assoc (get matrix y) x new-value)))

(defn make-empty-matrix
  [y x]
  (let [row (fn [x] (vec (repeat (first x) 0)))]
    (vec (map row  (repeat y [x])))))

(defn row-iteration
  "Generates every ordered pair of the rows of matrixA and matrixB.
  (a,b) is subset of matrixA * matrixB, where a is a row of matrixA and b is a row of matrixB.
  For each pair, this function calls the given function with a and b as parameters."
  [function matrixA matrixB]
  (map (fn [a]
         (vec (map (fn [b]
                (function a b)) matrixB)) )
       matrixA))

(defn matrix-multiplication
  "This function takes two matrix as parameters and multiplies them as matrices are multiplied
  linear algebra. The first matrix must have as many columns as the second has rows.
  For exemple
  [1 2] and [1 3 5] can be multiplied in both ways. Square matrix are also valids.
  [3 4]     [2 4 6]
  [5 6]
  More about matrix multiplication:
  http://en.wikipedia.org/wiki/Matrix_multiplication "
  [matrixA matrixB]
  (cond
   (not (and (is-matrix? matrixA) (is-matrix? matrixB)))
     (invalid-matrix-notification)
   (not (= (count (first matrixA))(count matrixB)))
     (matrices-are-not-valid-type)
   :else
    (vec
     (row-iteration (fn [x y] (reduce + (map * x y))) matrixA (transpose matrixB)))))
