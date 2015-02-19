(ns matrix_calculator.determinant
  (:use matrix_calculator.helpers)
  (:use matrix_calculator.basic_features))

(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#)) ; for debugging

(defn change-values
  "Takes value matrix[a][b] as a new-elem. If given parameter b < i,
  smaller[a-1][b] = new-elem. If b > i, smaller[a-1][b-1] = new-elem.
  Otherwise smaller is returned without modifications."
  [matrix smaller i a b]
  (let [new-elem (get-elem matrix a b)]
   (cond
    (< b i) (set-elem smaller (dec a) b new-elem)
    (> b i) (set-elem smaller (dec a) (dec b) new-elem)
    :else smaller)))

(defn iterate-matrix-b
  "Iterates."
  [matrix smaller i a]
  (loop [matrix matrix
         smaller smaller
         i i
         a a
         b 0]
    (if (= b (count matrix))
      smaller
      (recur matrix (change-values matrix smaller i a b) i a (inc b)))))

(defn iterate-matrix-a
  "Iterates."
  [matrix smaller i]
  (loop [matrix matrix
         smaller smaller
         i i
         a 1]
    (if (= a (count matrix))
      smaller
      (recur matrix (iterate-matrix-b matrix smaller i a) i (inc a)))))


(defn determinant
  "Solves the determinant of the given matrix. This implementation is solving problem with brute force in time O(n!).
  That makes algorithm very slow with bigger than 8x8 matrices.

  http://en.wikipedia.org/wiki/Determinant"
  [matrix]
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
           (= i until) sum
           even (recur until matrix (+ sum (* 1 (get-elem matrix 0 i) (determinant new-smaller))) new-smaller (inc i))
           :else (recur until matrix (+ sum (* -1 (get-elem matrix 0 i) (determinant new-smaller))) new-smaller (inc i))))))))


 (defn calcul-rows-and-columns
  "Helper function for LU-decompose function."
  [matrix j i until]
  (loop [p 0
         result 0]
    (if (= p until)
      result
      (recur (inc p)
             (+ result (* (get-elem matrix p i)(get-elem matrix j p)))))))



(defn LU-decompose
  "Transforms the given matrix to LU-decomposition with the
  doolittle-algorithm. Runs in O(n^3) time. This is used to solve determinant efficiently.

  http://en.wikipedia.org/wiki/LU_decomposition"
  [matrix]
  (reduce
   (fn [matrix-b j]
     (let [a (reduce
      (fn [matrix-c i]
        (let [new-elem (- (get-elem matrix-c j i)
                          (calcul-rows-and-columns matrix-c j i i))]
          (set-elem matrix-c j i (/ new-elem (get-elem matrix-c i i)))))
      matrix-b
      (range j))]
       (reduce
        (fn [matrix-d i]
          (let [new-elem (- (get-elem matrix-d j i)
                            (calcul-rows-and-columns matrix-d j i j))]
            (set-elem matrix-d j i new-elem)))
        a
        (range j (count matrix)))))
   matrix
   (range (count matrix))))


(defn change-to-zero
  [matrix i j]
  (if (> i j)
    (set-elem matrix i j 0)
    matrix))

(defn u-matrix
  [matrix]
  (reduce
   (fn [matrix-b i]
     (reduce
      (fn [matrix-c j]
        (change-to-zero matrix-c i j))
      matrix-b
      (range (count matrix))))
   matrix
   (range (count matrix))))

(defn LU-determinant
  [matrix]
  (let [u (dbg (u-matrix (LU-decompose matrix)))]
    (apply
     *
     (map
     (fn [i]
       (get-elem u i i))
     (range (count u))))))
