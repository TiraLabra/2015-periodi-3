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

(def m8 [[1 1 2]
         [2 3 4]
         [3 4 5]])

(def m9 [[1 1 2 0]
         [2 3 4 0]
         [3 4 5 0]
         [0 0 0 2]])

(def m10 [[1 1 2]
         [2 2 4]
         [4 4 8]])

(def m30 [[0 0 0]
         [0 0 0]
         [0 0 0]])

(def m15 [[1  1  1  1  1  1  1  1  1 ]
          [0  1  1  1 -1  1  1  0  0 ]
          [0  0  0  1  1 -1  1  0  1 ]
          [1  1  1 -1 -1 -1  0  0  1 ]
          [1  0 -1 -1  1  1  0  0  1 ]
          [-1 1  0  0  0  0  0  1  1 ]
          [0  1  1  1  0  0 -1 -1  0 ]
          [1  1  0  0  1  1  0  0  1 ]
          [0  0  1  1  0  1 -1 -1 -1 ]])

(def mr1 [[0 0 0] [4 8 0] [0 0 0]])
(def mr2 [[0 0 0] [7 29 0] [0 0 0]])
(def mr3 [[0 1 0][0 0 0][0 0 0]])
(def mr4 [[1 0 0][0 0 0][0 0 0]])
(def mr5 [[2 4 0] [4 8 0] [0 0 0]])
(def mr6 [[2 3 0] [3 4 0] [0 0 0]])



(deftest determinant.determinant
  (testing "determinants"
    (is (= (determinant m1)) -3)
    (is (= (determinant m2) 24))
    (is (= (determinant m3) 177))
    (is (= (determinant m4) 10))
    (is (= (determinant m5) -77851))
    (is (= (determinant m6) 6))
    (is (= (determinant m7) 0))
    (is (= (determinant m8) -1))
    (is (= (determinant m9) -2))
    (is (= (determinant m10) 0))))

(deftest determinant.iterate-matrix-b
  (testing "that function works."
    (is (= (iterate-matrix-b m10 m30 0 2) mr1))
    (is (= (iterate-matrix-b m5 m30 1 2) mr2))))

(deftest determinant.iterate-matrix-a
  (testing "that function works."
    (is (= (iterate-matrix-a m10 m30 1) mr5))
    (is (= (iterate-matrix-a m8 m30 2) mr6))))

(deftest determinant.change-values
  (testing "helper function"
    (is (= (change-values m7 m30 1 1 1) m30))
    (is (= (change-values m7 m30 3 1 1) mr3))
    (is (= (change-values m7 m30 0 1 1) mr4))))

(deftest determinant.calcul-rows-and-columns
  (testing "helper function"
    (is (= (calcul-rows-and-columns m8 0 0 2) 3))
    (is (= (calcul-rows-and-columns m7 2 2 3) 9))))
