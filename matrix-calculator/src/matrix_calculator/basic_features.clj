(ns matrix_calculator.basic_features
  (:use matrix_calculator.helpers))

(defn each-elem-in-matrices
  [matrix-a matrix-b function]
    (if
      (or
       (not= (count matrix-a) (count matrix-b))
       (not= (count (first matrix-a)) (count (first matrix-b))))
        (matrices-are-not-valid-type)
        (let [add-row (fn [row-a row-b] (vec (map function row-a row-b)))]
           (vec (map add-row matrix-a matrix-b)))))

(defn add-two-matrices
  "Takes two matrices as parameters. If the matrices are same type, sums them:
  To say, add every element of matrix-a to an according one of b. Returns an new matrix of same type.
  (A + B)(i,j) = A(i, j) + B(i, j) for all i to m and j to n, where m*n is the size/type of the matrices."
  [matrix-a matrix-b]
    (each-elem-in-matrices matrix-a matrix-b (fn [x y] (+ x y))))
; HUOM alla olevaan testit ja dokumentaatio kommentti

(defn subtract-two-matrices
    [matrix-a matrix-b]
    (each-elem-in-matrices matrix-a matrix-b (fn [x y] (- x y))))

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


(defn transpose
  "Changes the rows and columns of matrix. Transpose of matrix
  [1 2]  is [1 3 5]
  [3 4]     [2 4 6]
  [5 6]"
  [matrix]
  (if (not (is-matrix? matrix))
    (invalid-matrix-notification)
    (apply map vector matrix)))
