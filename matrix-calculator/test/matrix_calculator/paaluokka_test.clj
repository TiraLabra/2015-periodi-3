(ns matrix_calculator.paaluokka-test
  (:require [clojure.test :refer :all]
            [matrix_calculator.paaluokka :refer :all]))

(deftest paaluokka.take-until
  (testing "With parameter zero, returnvalue contains one empty element."
    (is (take-until 0 []))))
