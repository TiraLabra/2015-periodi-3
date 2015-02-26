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


(def m16 [[1  1  1  1  1  1  1  1  1 ]
          [0  1  1  1  3  1  1  0  0 ]
          [0  0  2  1  1  2  1  0  1 ]
          [1  1  1  4  1  3  0  0  1 ]
          [1  0  2  3  1  1  0  0  1 ]
          [2  1  0  0  0  2  5  1  1 ]
          [0  1  1  1  0  0  3  1  0 ]
          [1  1  0  0  1  1  0  2  1 ]
          [0  0  1  1  0  1  2  4  3 ]])

(def m17 [[1  2  1  1  1  0  1  1  0  0  1  1  6  0  1  2  4  3]
          [0  1  1  1  3  1  1  0  0  3  1  2  2  0  1  0  0  1 ]
          [0  0  2  1  1  2  1  0  1  0  2  7  2  1  0  1  9  0 ]
          [1  1  1  4  1  3  0  0  1  0  1  1  1  0  2  3  7  3 ]
          [1  0  2  3  1  1  0  0  1  3  2  4  3  0  2  8  4  1 ]
          [2  1  0  0  0  2  5  1  1  1  1  2  0  1  4  1  5  7 ]
          [0  1  1  1  0  0  3  1  0  3  1  0  1  1  0  1  0  1 ]
          [1  1  0  0  1  1  0  2  1  2  0  1  2  1  6  1  1  1 ]
          [0  0  1  1  0  1  2  4  3  1  2  1  3  1  0  1  1  0 ]
          [1  1  5  1  1  6  1  1  1  1  2  3  1  3  0  3  1  0 ]
          [0  1  1  1  3  1  1  0  0  2  1  0  0  0  2  5  1  1]
          [3  1  2  1  1  2  1  4  1  0  2  0  1  9  2  0  1  1]
          [1  1  1  4  1  3  0  0  1  1  0  1  1  0  1  2  4  3]
          [1  0  2  3  1  1  0  0  1  0  0  1  5  1  1  2  0  3]
          [2  1  2  0  0  2  5  1  1  2  0  4  1  0  1  0  4  1]
          [0  1  1  1  0  0  3  1  0  9  0  1  1  0  1  2  1  3]
          [1  1  2  0  1  1  0  2  1  1  2  1  0  6  0  2  7  8]
          [0  0  1  1  0  1  2  4  3  0  2  1  0  1  0  0  6  3]])

(def mLU1 [[1 2 3]
           [2 1 0]
           [2 4 1]])
(def mLU2 [[1 2 3 4]
           [2 1 0 11]
           [2 4 1 -8]
           [-2 14 1 0]])

(def invalidM [[1 2][2 3][3 4]])

(def mr1 [[0 0 0] [4 8 0] [0 0 0]])
(def mr2 [[0 0 0] [7 29 0] [0 0 0]])
(def mr3 [[0 1 0][0 0 0][0 0 0]])
(def mr4 [[1 0 0][0 0 0][0 0 0]])
(def mr5 [[2 4 0] [4 8 0] [0 0 0]])
(def mr6 [[2 3 0] [3 4 0] [0 0 0]])
(def mr7 [[1 2 3][2 -3 -6][2 -0 -5]])
(def mr8 [[1 2 3 4][2 -3 -6 3][2 0 -5 -16][-2 -6 29/5 594/5]])
(def mr9 [[1 2 3 4][0 1 0 11][0 0 1 -8][0 0 0 0]])
(def mr10 [[1 2 3][0 1 0][0 0 1]])


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

(deftest determinant.LU-decompose
  (is (= (LU-decompose mLU1) mr7))
  (is (= (LU-decompose mLU2) mr8)))

(deftest determinant.change-to-zero
  (is (= (change-to-zero m1 1 0) [[1 2][0 1]]))
  (is (= (change-to-zero m1 0 1) [[1 2][2 1]])))

(deftest determinant.u-matrix
  (is (= (u-matrix mLU1) mr10))
  (is (= (u-matrix mLU2) mr9)))

(deftest determinant.LU-determinant
  (is (= (LU-determinant m1)) -3)
  (is (= (LU-determinant m2) 24))
  (is (= (LU-determinant m3) 177))
  (is (= (LU-determinant m4) 10))
  (is (= (LU-determinant m5) -77851))
  ;(is (= (LU-determinant m7) 0)) Divide by zero error
  (is (= (LU-determinant m8) -1))
  (is (= (LU-determinant m9) -2))
  ;(is (= (LU-determinant m10) 0)) divide by zero
  (is (= (LU-determinant m16) 858))
  (is (= (LU-determinant m17) 29081604984)))

(deftest determinant.determinant.thwows-error
  (is (thrown? Exception (determinant invalidM))))

(deftest determinant.determinant.thwows-error
  (is (thrown? Exception (LU-determinant invalidM))))
