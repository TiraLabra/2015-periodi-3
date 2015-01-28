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

(defn sub-matrix [matrix fromX fromY how-long]
  (vec ( map (fn [row]
         (vec (take how-long (drop fromX row))))
       (take how-long (drop fromY matrix)))))

(defn concat-matrices
  [m11 m21 m12 m22]
  (let [concat-two (fn [upper lower] (vec (concat upper lower)))]
    (vec (map (fn [left right]
           (vec (concat left right)))
         (concat-two m11 m21)
         (concat-two m12 m22)))))

(defn strassen
  [matrixA matrixB]
  (let [n (count matrixA)]
    (if (= n 2)
      (matrix-multiplication matrixA matrixB)
      (let [new-size (/ n 2)
            a11 (sub-matrix matrixA 0 0 new-size)
            a12 (sub-matrix matrixA new-size 0 new-size)
            a21 (sub-matrix matrixA 0 new-size new-size)
            a22 (sub-matrix matrixA new-size new-size new-size)
            b11 (sub-matrix matrixB 0 0 new-size)
            b12 (sub-matrix matrixB new-size 0 new-size)
            b21 (sub-matrix matrixB 0 new-size new-size)
            b22 (sub-matrix matrixB new-size new-size new-size)

            a-result (add-two-matrices a11 a22)
            b-result (add-two-matrices b11 b22)
            p1 (strassen a-result b-result)
            a-result (add-two-matrices a21 a22)
            p2 (strassen a-result b11)
            b-result (subtract-two-matrices b12 b22)
            p3 (strassen a11 b-result)
            b-result (subtract-two-matrices b21 b11)
            p4 (strassen a22 b-result)
            a-result (add-two-matrices a11 a12)
            p5 (strassen a-result b22)
            a-result (subtract-two-matrices a21 a11)
            b-result (add-two-matrices b11 b12)
            p6 (strassen a-result b-result)
            a-result (subtract-two-matrices a12 a22)
            b-result (add-two-matrices b21 b22)
            p7 (strassen a-result b-result)

            c12 (add-two-matrices p3 p5)
            c21 (add-two-matrices p2 p4)
            a-result (add-two-matrices p1 p4)
            b-result (add-two-matrices a-result p7)
            c11 (subtract-two-matrices b-result p5)
            a-result (add-two-matrices p1 p3)
            b-result (add-two-matrices a-result p6)
            c22 (subtract-two-matrices b-result p2)]
        (concat-matrices c11 c21 c12 c22)))))
