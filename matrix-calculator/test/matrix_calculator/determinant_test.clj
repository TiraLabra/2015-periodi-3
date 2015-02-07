(ns matrix_calculator.determinant-test
  (:require [clojure.test :refer :all]
            [matrix_calculator.determinant :refer :all]
            [matrix_calculator.basic_features :refer :all]
            [matrix_calculator.helpers :refer :all]))

(def m1 [[1 2][2 1]])
(def m2 [[1   2   3]
         [3   2   1]
         [2   6   4]])
(def m3 [[8    5][11   29]])

(def m4 [[1  1  0  0 -1 -1]
         [1  2 -1  1  0  0]
         [2  0 -1  1 -1  0]
         [0  1  0  1 -1  2]
         [2  1  0  0  0 -1]
         [1  1  0  1  2  1]])

(def m5 [[ 4 32  1]
         [90  1 29]
         [ 7 36 29]])

(def m6 [[-2 2 3]
         [-1 1 3]
         [ 2 0 -1]])

(def m7  [[-2 2 3 1]
          [-1 1 3 0]
          [-1 1 3 0]
          [ 2 0 -1 3]])



(deftest multiply.determinant
  (testing "det"
    (is (= (determinant m1)) -3)
    (is (= (determinant m2) 24))
    (is (= (determinant m3) 177))
    (is (= (determinant m4) 10))
    (is (= (determinant m5) -77851))
    (is (= (determinant m6) 6))
    (is (= (determinant m7) 0))))
