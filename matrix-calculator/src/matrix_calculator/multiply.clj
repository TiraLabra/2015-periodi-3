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

(defn sub-matrix
  "Returns a submatrix of given matrix that starts from the index
  specified by the parameters fromX and fromY and continues to
  right (x grows) and down (y grows) 'how-long' steps."
  [matrix fromX fromY how-long]
  (if (or (< (count matrix) (+ fromY how-long))
          (< (count (first matrix)) (+ fromX how-long)))
    (index-out-of-bound)
    (vec ( map (fn [row]
           (vec (take how-long (drop fromX row))))
         (take how-long (drop fromY matrix))))))

(defn concat-matrices
  "Concat the four given matrices into one (four time)bigger square matrix.

  m11, m21, m12, m22 will be merged to [[m11 m12]
                                        [m21 m22]].
  The given matrices should be same type."
  [m11 m21 m12 m22]
  (let [test-x (fn [x] (count (first x)))
        test-y (fn [y] (count y))]
    (if (not (and (= (test-x m11)(test-x m21)(test-x m12)(test-x m22))
                  (= (test-y m11)(test-y m21)(test-y m12)(test-y m22))))
      (matrices-are-not-valid-type)

      (let [concat-two (fn [upper lower] (vec (concat upper lower)))]
        (vec (map (fn [left right]
             (vec (concat left right)))
             (concat-two m11 m21)
             (concat-two m12 m22)))))))

(defn is-power-of-two? [x]
  "Returns true, if the parameter x can be produced by taking an
  an exponent of two. To say, numbers 2,4,8,16,32,64.. etc returns
  true, others false."
  (cond
   (or (= x 2.0)(= x 2)) true
   (not (or (= (mod x 2) 0) (= (mod x 2) 0.0))) false
   :else
     (recur (float (/ x 2)))))

(defn expand-matrix-by-one
  "Returns an square matrix whose size is one bigger than original.
  New slots are fulfilled with zeros."
  [matrix]
  (let [add-zero (fn [x] (conj x 0))]
    (vec (conj
     (vec (map add-zero matrix))
     (vec (repeat (inc (count matrix))0))))))

(defn find-next-power-of-two
  "With functions is-power-of-two? and expand-matrix-by-one,
  expands the given square matrix with zero rows and columns
  until it's size is power of two. "
  [matrix]
  (if (is-power-of-two? (count matrix))
    matrix
    (recur (expand-matrix-by-one matrix))))

(defn strassen-recur
  [matrixA matrixB]
  (let [n (count matrixA)]
    (if (<= n 2)
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
            p1 (strassen-recur a-result b-result)
            a-result (add-two-matrices a21 a22)
            p2 (strassen-recur a-result b11)
            b-result (subtract-two-matrices b12 b22)
            p3 (strassen-recur a11 b-result)
            b-result (subtract-two-matrices b21 b11)
            p4 (strassen-recur a22 b-result)
            a-result (add-two-matrices a11 a12)
            p5 (strassen-recur a-result b22)
            a-result (subtract-two-matrices a21 a11)
            b-result (add-two-matrices b11 b12)
            p6 (strassen-recur a-result b-result)
            a-result (subtract-two-matrices a12 a22)
            b-result (add-two-matrices b21 b22)
            p7 (strassen-recur a-result b-result)

            c12 (add-two-matrices p3 p5)
            c21 (add-two-matrices p2 p4)
            a-result (add-two-matrices p1 p4)
            b-result (add-two-matrices a-result p7)
            c11 (subtract-two-matrices b-result p5)
            a-result (add-two-matrices p1 p3)
            b-result (add-two-matrices a-result p6)
            c22 (subtract-two-matrices b-result p2)]
        (concat-matrices c11 c21 c12 c22)))))


(defn strassen
  "Multiplies matrices like function matrix-multiplication, but run faster.
  Matrix-multiplication needs O(n^3) operations but strassen do it with
  O(n^2.81). The performance increase is acchieved by splitting matrices to four pieces and
  calculating in O(1) time new matrices (http://en.wikipedia.org/wiki/Strassen_algorithm).
  Algorithm calls itself recursively with new matrices until sizes of matrices are two or lower.

  This implementation of algorithm works only for square matrices, which have the size of power of two(2,4,6,16...).
  Therefore square matrices of different size are expanded to next power of two and after multiplication the
  function returns submatrix that corresponds the size of original matrices."
  [matrixA matrixB]
  (let [original-size (count matrixA)
        expanded-matrix-a (find-next-power-of-two matrixA)
        expanded-matrix-b (find-next-power-of-two matrixB)]
    (sub-matrix
     (strassen-recur expanded-matrix-a expanded-matrix-b)
     0 0 original-size)))
