(ns matrix_calculator.helpers-test
  (:require [clojure.test :refer :all]
            [matrix_calculator.helpers :refer :all]))

(def test-m
  [[1 2 3]
   [3 4 5]
   [6 7 8]])

(deftest helpers.take-until.zero
  (testing "With parameter zero, returnvalue contains one empty element."
    (is (= (take-until 0 []) [[][]]))
    (is (= (take-until 0 [1 2 3]) [[][1 2 3]]))))

(deftest helpers.take-until.smaller
  (testing "With parameter smaller than the size of the given sequence,
    returnvalue contains two nonempty arrays."
    (is (= (take-until 1 [1 2]) [[1] [2]]))
    (is (= (take-until 3 [55 22 33 44 55]) [[55 22 33] [44 55]]))))

(deftest helpers.take-until.bigger
  (testing "With parameter bigger than the size of the given sequence,
    returnvalue contains two arrays with some nil values"
    (is (= (take-until 2 []) [[nil nil][]]))
    (is (= (take-until 4 [1 2 3]) [[1 2 3 nil][]]))))

(deftest helpers.make-matrix.correct-input
  (testing "With correct parameters, the method returns a reprensation of matrix."
    (is (= (make-matrix 1 1 [1]) [[1]]))
    (is (= (make-matrix 1 3 [1 2 3]) [[1 2 3]]))
    (is (= (make-matrix 3 1 [4 3 2]) [[4][3][2]] ))
    (is (= (make-matrix 3 3 [1 2 3 4 5 6 7 8 9]) [[1 2 3][4 5 6][7 8 9]]))))

(deftest helpers.make-matrix.incorrect-input
  (testing "When first and second parameter are multipled and the result is
    not same as the size of the given array, exeption should be thrown."
    (is (thrown? Exception (make-matrix 1 1 [1 2])))
    (is (thrown? Exception (make-matrix 2 2 [1 2 3])))))

(deftest helpers.is-matrix?
  (testing "if this function works with some valid and invalid input"
    (is (is-matrix? [[1 4][2 5][3 6]]))
    (is (not (is-matrix? [1 2 3 4 5])))
    (is (not (is-matrix? 875425)))))

(deftest helpers.take-column
  (testing "that helper function works correctly."
    (is (= (take-column 0 [[1][2]]) [1 2] ))
    (is (= (take-column 1 [[2 3][4 5][6 7]]) [3 5 7] ))))

(deftest helpers.get-elem
  (testing "getter called get-elem")
  (is (= (get-elem test-m 2 2) 8)))

(deftest helpers.set-elem
  (testing "setter called set-elem"
    (is (= (get-elem (set-elem test-m 0 0 100) 0 0) 100))))

(deftest helpers.exp
  (testing "that returns correct values"
    (is (= (exp 2 6) 64))
    (is (= (exp 10 3) 1000))))

(deftest helpers.make-empty-matrix
  (testing "that matrix has zeros inside."
    (let [m (make-empty-matrix 2 1)]
      (is (= (get-elem m 0 0) 0))
      (is (= (get-elem m 1 0) 0)))))
