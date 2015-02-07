(ns matrix_calculator.determinant
  (:use matrix_calculator.helpers)
  (:use matrix_calculator.basic_features))


(defn change-values
  [matrix smaller i a b]
  (let [new-elem (get-elem matrix a b)]
   (cond
    (< b i) (set-elem smaller (dec a) b new-elem)
    (> b i) (set-elem smaller (dec a) (dec b) new-elem)
    :else smaller)))


(defn iterate-matrix-b
  [matrix smaller i a]
  (loop [matrix matrix
         smaller smaller
         i i
         a a
         b 0]
    (if (= b (count matrix))
      smaller
      (recur matrix
             (change-values
              matrix smaller i a b)
             i a (inc b)))))

(defn iterate-matrix-a
  [matrix smaller i]
  (loop [matrix matrix
         smaller smaller
         i i
         a 1]
    (if (= a (count matrix))
      smaller
      (recur matrix
             (iterate-matrix-b
                matrix smaller i a)
             i (inc a)))))


(defn determinant [matrix]
  (let [length (count matrix)]
    (if (= length 1)
      (get-elem matrix 0 0) ;The determinant of one sized matrix is the only number inside.
      (loop [until length
             matrix matrix
             sum 0
             smaller (make-empty-matrix (dec length)(dec length))
             i 0]
        (let [even (= (mod i 2) 0)
              new-smaller (iterate-matrix-a matrix smaller i)]
          (cond
           (= i length) sum
           even (recur
                 length
                 matrix
                 (+ sum (* (get-elem matrix 0 i) (determinant new-smaller)))
                 new-smaller
                 (inc i))
           :else (recur
                  length
                  matrix
                  (- sum (* (get-elem matrix 0 1) (determinant new-smaller)))
                  new-smaller
                  (inc i))))))))

;
