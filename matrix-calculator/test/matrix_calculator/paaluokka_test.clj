; (Testikattavuus työkalu löytyy: https://github.com/lshift/cloverage

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
    (is (= (make-matrix 1 1 [1]) [[1]]))
    (is (= (make-matrix 1 3 [1 2 3]) [[1 2 3]]))
    (is (= (make-matrix 3 1 [4 3 2]) [[4][3][2]] ))
    (is (= (make-matrix 3 3 [1 2 3 4 5 6 7 8 9]) [[1 2 3][4 5 6][7 8 9]]))))

(deftest paaluokka.make-matrix.incorrect-input
  (testing "When first and second parameter are multipled and the result is
    not same as the size of the given array, exeption should be thrown."
    (is (thrown? Exception (make-matrix 1 1 [1 2])))
    (is (thrown? Exception (make-matrix 2 2 [1 2 3])))))

(deftest paaluokka.add-two-matrices.correct-input
  (testing "When giving two matrices of same type, should got
    the sum of one matrices as a return value."
    (is (= (add-two-matrices (make-matrix 2 2 [1 2 3 4])(make-matrix 2 2 [4 3 2 1])) [[5 5][5 5]]))
    (is (= (add-two-matrices (make-matrix 3 1 [1 2 3])(make-matrix 3 1 [11 4 932])) [[12][6][935]]))))

(deftest paaluokka.add-two-matrices.incorrect-input
  (testing "When given two matrices of different type, should get an Exception."
    (is (thrown? Exception (add-two-matrices (make-matrix 1 1 [3])(make-matrix 2 1 [7 5]))))
    (is (thrown? Exception (add-two-matrices (make-matrix 3 3 [1 2 3 4 5 6 7 8 9])(make-matrix 1 2 [9 9]))))))

(deftest paaluokka.is-matrix?
  (testing "if this function works with some valid and invalid input"
    (is (is-matrix? [[1 4][2 5][3 6]]))
    (is (not (is-matrix? [1 2 3 4 5])))
    (is (not (is-matrix? 875425)))))

(deftest paaluokka.scalar-product.invalid-input
  (testing "that function throwing exception with invalid input"
    (is (thrown? Exception (scalar-product 33 22)))
    (is (thrown? Exception (scalar-product 134 [3 43 90])))
    (is (thrown? Exception (scalar-product [3 4] [[1][2][3]])))))

(deftest paaluokka.scalar-product.valid-input
  (testing "that function works well with valid input"
    (is (= (scalar-product 2 [[0 0 1]]) [[0 0 2]] ))
    (is (= (scalar-product 3 [[1 2][3 4]]) [[3 6][9 12]] ))))

(deftest paaluokka.take-column
  (testing "that helper function works correctly."
    (is (= (take-column 0 [[1][2]]) [1 2] ))
    (is (= (take-column 1 [[2 3][4 5][6 7]]) [3 5 7] ))))

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

(deftest paaluokka.row-iteration
  (testing "if the row iteration returns an matrix with elements defined
    in the next function."
    (let [test-f (fn [a b] (+ (apply + a)(apply + b)))]
      (is (= (row-iteration test-f [[3 4][1 2]] [[5 6][2 3]])
             [[18 12][14 8]]))
      (is (= (row-iteration test-f [[1 2]] [[2 1]])
             [[6]])))))

(deftest paaluokka.matrix-multipcation.invalid-matrices
  (testing "that error will be thrown with matrices that cannot be multiplied."
    (is (thrown? Exception (matrix-multiplication [[1][2][3]] [[1 2]])))
    (is (thrown? Exception (matrix-multiplication [[1 2][3 4]] [[1 2][3 4][5 6]] )))
    (is (thrown? Exception (matrix-multiplication [1 2 3] [[1 2 3]] )))))

(deftest paaluokka.matrix-multipcation.valid-input
  (testing "that the function completes the same tasks as GNU Octave."
    (is (= (matrix-multiplication [[3 4 3][11 55 9][43 2 4]]
                                  [[98 5 1][6 7 6][50 19 3]])
          [[468 100 36][1858 611 368][4426 305 67]] ))
    (is (= (matrix-multiplication [[1.2 6.3]] [[9][2.7]])
           [[27.810]]))))

