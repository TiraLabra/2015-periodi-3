; (Testikattavuus työkalu löytyy: https://github.com/lshift/cloverage

(ns matrix_calculator.paaluokka-test
  (:require [clojure.test :refer :all]
            [matrix_calculator.paaluokka :refer :all]
            [matrix_calculator.multiply :refer :all]
            [matrix_calculator.basic_features :refer :all]
            [matrix_calculator.helpers :refer :all]))

(deftest paaluokka.add-two-matrices.correct-input
  (testing "When giving two matrices of same type, should got
    the sum of one matrices as a return value."
    (is (= (add-two-matrices (make-matrix 2 2 [1 2 3 4])(make-matrix 2 2 [4 3 2 1])) [[5 5][5 5]]))
    (is (= (add-two-matrices (make-matrix 3 1 [1 2 3])(make-matrix 3 1 [11 4 932])) [[12][6][935]]))))

(deftest paaluokka.add-two-matrices.incorrect-input
  (testing "When given two matrices of different type, should get an Exception."
    (is (thrown? Exception (add-two-matrices (make-matrix 1 1 [3])(make-matrix 2 1 [7 5]))))
    (is (thrown? Exception (add-two-matrices (make-matrix 3 3 [1 2 3 4 5 6 7 8 9])(make-matrix 1 2 [9 9]))))))


(deftest paaluokka.scalar-product.invalid-input
  (testing "that function throwing exception with invalid input"
    (is (thrown? Exception (scalar-product 33 22)))
    (is (thrown? Exception (scalar-product 134 [3 43 90])))
    (is (thrown? Exception (scalar-product [3 4] [[1][2][3]])))))

(deftest paaluokka.scalar-product.valid-input
  (testing "that function works well with valid input"
    (is (= (scalar-product 2 [[0 0 1]]) [[0 0 2]] ))
    (is (= (scalar-product 3 [[1 2][3 4]]) [[3 6][9 12]] ))))

(deftest paaluokka.transpose.invalid-input
  (testing "that throws exception with invalid matrix"
    (is (thrown? Exception (transpose [1 2 3]) ))
    (is (thrown? Exception (transpose 232 )))))

(deftest paaluokka.transpose.valid-input
  (testing "that function returns the tranposes of the matrices. Tests cases are
    verified with GNU Octave."
    (is (= (transpose [[7 1 6][2 4 3][1 1 1][3 2 1]])
           [[7 2 1 3][1 4 1 2][6 3 1 1]]))
    (is (= (transpose [[1 2 3]]) [[1][2][3]] ))))

