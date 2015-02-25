(ns matrix_calculator.paaluokka
  (:gen-class)
  (:use [matrix_calculator.helpers]
        [matrix_calculator.basic_features]
        [matrix_calculator.multiply]
        [matrix_calculator.determinant]))


(defn print-commands
  []
  (do
    (println (str "\n\n"
                  "    Welcome to Samu's matrix calculator.\n"
                  "    The available commands are: \n"
                  "    HELP - Prints these instructions. \n"
                  "    ADD [matrix1 matrix2] - Adds two matrices. \n"
                  "    SUBTRACT [matrix1 matrix2] - Subtracts two matrices. \n"
                  "    SCALAR-PRODUCT [scalar matrix] - Multiplies all elements of matrix by scalar. \n"
                  "    TRANSPOSE [matrix] - Returns the transpose of given matrix.\n"
                  "    MULTIPLY [matrix1 matrix2] - Multiplies two matrices in O(n^3) time. \n"
                  "    MULTIPLY-STRASSEN [matrix1 matrix2] - Multiplies two matrices in O(n^2.71) time. \n"
                  "    DETERMINANT [matrix] - Finds the determinant of matrix in O(n!) time. Works only for squarematrices smaller than 8 rows. \n"
                  "    LU-DETERMINANT [matrix] - Finds the determinant of matrix in O(n^3) time. Works only with positive numbers."
                  "\n\n"))))

(defn as-matrix
  [arg]
  (read-string (apply str (map (fn [x] (if (= x \,) " " x)) (str arg)))))



(defn handle-commands
  [args]
  (if (> (count args) 3)
    (println (str "Too many arguments! Max 3, you gave:" (count args)))
    (let [command (first args)
          arg1 (second args)
          arg2 (last args)]
      (do (println command)
        (println arg1)
        (if (= 3 (count args)) (println arg2))
        (println "----------")
        (case command
         "HELP" (print-commands)
         "ADD" (println (add-two-matrices (as-matrix arg1)(as-matrix arg2)))
         "SUBTRACT" (println (subtract-two-matrices (as-matrix arg1)(as-matrix arg2)))
         "TRANSPOSE" (println (transpose arg1))
         "SCALAR-PRODUCT" (println (scalar-product as-matrix arg1 (as-matrix arg2)))
         "MULTIPLY" (println (matrix-multiplication (as-matrix arg1)(as-matrix arg2)))
         "MULTIPLY-STRASSEN" (println (strassen (as-matrix arg1) (as-matrix arg2)))
         "DETERMINANT" (println (determinant (as-matrix arg1)))
         "LU-DETERMINANT" (println (LU-determinant (as-matrix arg1)))
         "No such a command.")))))

(defn -main [& args]
  (if (zero? (count args))
    (print-commands)
    (handle-commands args)))
