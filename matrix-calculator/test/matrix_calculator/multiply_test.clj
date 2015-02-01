(ns matrix_calculator.multiply-test
  (:require [clojure.test :refer :all]
            [matrix_calculator.multiply :refer :all]))

(def testcase [[1  2  3  4]
              [5  6  7  8]
              [9  10 11 12]
              [13 14 15 16]])

(deftest multiply.row-iteration
  (testing "if the row iteration returns an matrix with elements defined
    in the next function."
    (let [test-f (fn [a b] (+ (apply + a)(apply + b)))]
      (is (= (row-iteration test-f [[3 4][1 2]] [[5 6][2 3]])
             [[18 12][14 8]]))
      (is (= (row-iteration test-f [[1 2]] [[2 1]])
             [[6]])))))

(deftest multiply.matrix-multipcation.invalid-matrices
  (testing "that error will be thrown with matrices that cannot be multiplied."
    (is (thrown? Exception (matrix-multiplication [[1][2][3]] [[1 2]])))
    (is (thrown? Exception (matrix-multiplication [[1 2][3 4]] [[1 2][3 4][5 6]] )))
    (is (thrown? Exception (matrix-multiplication [1 2 3] [[1 2 3]] )))))

(deftest multiply.matrix-multipcation.valid-input
  (testing "that the function completes the same tasks as GNU Octave."
    (is (= (matrix-multiplication [[3 4 3][11 55 9][43 2 4]]
                                  [[98 5 1][6 7 6][50 19 3]])
          [[468 100 36][1858 611 368][4426 305 67]] ))
    (is (= (matrix-multiplication [[1.2 6.3]] [[9][2.7]])
           [[27.810]]))))

(deftest multiply.sub-matrix.invalid-params
  (testing "that the function do throw exception if the wanted submatrix is out
     of the bounds of the original matrix."
    (is (thrown? Exception (sub-matrix testcase 3 1 2)))
    (is (thrown? Exception (sub-matrix testcase 1 1 5)))))
