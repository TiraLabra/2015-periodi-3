; (Testikattavuus työkalu löytyy: https://github.com/lshift/cloverage

(ns matrix_calculator.paaluokka-test
  (:require [clojure.test :refer :all]
            [matrix_calculator.paaluokka :refer :all]
            [matrix_calculator.multiply :refer :all]
            [matrix_calculator.basic_features :refer :all]
            [matrix_calculator.helpers :refer :all]))

(def m1 [[1,2][3,4]])

(deftest paaluokka.main
  (is (nil? (-main ["HELPPIS"])))
  (is (nil? (-main))))

(deftest paaluokka.as-matrix
  (is (= (as-matrix [[3]]) [[3]]))
  (is (= (as-matrix [[1,2][3,2]]) [[1 2][3 2]])))

(deftest paaluokka.handle-commands
  (testing "This does not test anything useful :(. I could use some mocking technics in testing this paaluokka function, but I have no time."
  (is (thrown? Exception (handle-commands ["HELP", 1 2 3])))
  (is (nil? (handle-commands ["ADD" m1 m1])))
  (is (nil? (handle-commands ["HELP"])))
  (is (nil? (handle-commands ["SUBTRACT" m1 m1])))
  (is (nil? (handle-commands ["TRANSPOSE" m1])))
  (is (nil? (handle-commands ["SCALAR-PRODUCT" 4 m1])))
  (is (nil? (handle-commands ["MULTIPLY" m1 m1])))
  (is (nil? (handle-commands ["MULTIPLY-STRASSEN" m1 m1])))
  (is (nil? (handle-commands ["DETERMINANT" m1])))
  (is (nil? (handle-commands ["LU-DETERMINANT" m1])))
  (is (nil? (handle-commands ["ADDOAJSA" m1 m1])))))
