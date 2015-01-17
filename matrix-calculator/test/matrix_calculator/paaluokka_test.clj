(ns matrix_calculator.paaluokka-test
  (:require [clojure.test :refer :all]
            [matrix_calculator.paaluokka :refer :all]))

(deftest paaluokka.take-until.zero
  (testing "With parameter zero, returnvalue contains one empty element."
    (is (= (take-until 0 []) [[][]]))
    (is (= (take-until 0 [1 2 3]) [[][1 2 3]]))))

(deftest paaluokka.take-until.smaller
  (testing "With parameter smaller than the size of the given sequence,
    returnvalue contains two nonempty arrays."
    (is (= (take-until 1 [1 2]) [[1] [2]]))
    (is (= (take-until 3 [55 22 33 44 55]) [[55 22 33] [44 55]]))))

(deftest paaluokka.take-until.bigger
  (testing "With parameter bigger than the size of the given sequence,
    returnvalue contains two arrays with some nil values"
    (is (= (take-until 2 []) [[nil nil][]]))
    (is (= (take-until 4 [1 2 3]) [[1 2 3 nil][]]))))

(deftest paaluokka.make-matrix.correct-input
  (testing "With correct parameters, the method returns a reprensation of matrix."
    (is (= (make-matrix 1 1 1) [[1]]))
    (is (= (make-matrix 1 3 1 2 3) [[1 2 3]]))
    (is (= (make-matrix 3 1 4 3 2) [[4][3][2]] ))
    (is (= (make-matrix 3 3 1 2 3 4 5 6 7 8 9) [[1 2 3][4 5 6][7 8 9]]))))

(deftest paaluokka.make-matrix.incorrect-input
  (testing "When first and second parameter are multipled and the result is
    not same as the number of the rest parameters, exeption should be thrown."
    (is (thrown? Exception (make-matrix 1 1 1 2)))
    (is (thrown? Exception (make-matrix 2 2 1 2 3)))))

(deftest paaluokka.add-two-matrices.correct-input
  (testing "When giving two matrices of same type, should got
    the sum of one matrices as a return value."
    (is (= (add-two-matrices (make-matrix 2 2 1 2 3 4)(make-matrix 2 2 4 3 2 1)) [[5 5][5 5]]))
    (is (= (add-two-matrices (make-matrix 3 1 1 2 3)(make-matrix 3 1 11 4 932)) [[12][6][935]]))))

(deftest paaluokka.add-two-matrices.incorrect-input
  (testing "When given two matrices of different type, should get an Exception."
    (is (thrown? Exception (add-two-matrices (make-matrix 1 1 3)(make-matrix 2 1 7 5))))
    (is (thrown? Exception (add-two-matrices (make-matrix 3 3 1 2 3 4 5 6 7 8 9)(make-matrix 1 2 9 9))))))
