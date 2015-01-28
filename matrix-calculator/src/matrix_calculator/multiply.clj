(ns matrix_calculator.multiply
  (:use matrix_calculator.helpers)
  (:use matrix_calculator.basic_features))


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
  "This function takes two matrix as parameters and multiplies them as matrices are multiplied in
  linear algebra. The first matrix must have as many columns as the second has rows.
  For exemple
  [1 2] and [1 3 5] can be multiplied in both ways. Square matrices are also valids.
  [3 4]     [2 4 6]
  [5 6]
  More about matrix multiplication:
  http://en.wikipedia.org/wiki/Matrix_multiplication.

  All decimal numbers are rounded to three decimals."
  [matrixA matrixB]
  (cond
   (not (and (is-matrix? matrixA) (is-matrix? matrixB)))
     (invalid-matrix-notification)
   (or
    (not (= (count (first matrixA))(count matrixB)))
    (not (= (count (first matrixB))(count matrixA))))
     (matrices-are-not-valid-type)
   :else
    (vec (row-iteration (fn [x y]
                          (if (integer? (first x))
                            (reduce + (map * x y))
                            (round 3 (reduce + (map * x y))))) matrixA (transpose matrixB)))))
