(ns matrix_calculator.paaluokka
  (:gen-class))

(defn -main [& args]
  (if (zero? (count args))
    (print-commands)
    ()))

(defn print-commands
  []
  (do
    (println (str "Welcome to Samu's matrix calculator.\n"
                  "The available commands are: \n"
                  "HELP - Prints these instructions. \n"
                  "ADD [matrix1 matrix2] - Adds two matrices. \n"
                  "SUBTRACT [matrix1 matrix2] - Subtracts two matrices. \n"
                  "TRANSPOSE [matrix] - Returns the transpose of given matrix."
                  "MULTIPLY [matrix1 matrix2] - Multiplies two matrices in O(n^3) time. \n"
                  "MULTIPLY-STRASSEN [matrix1 matrix2] - Multiplies two matrices in O(n^2.71) time. \n"
                  "DETERMINANT [matrix] - Finds the determinant of matrix in O(n!) time. Works only for squarematrices smaller than 8 rows. \n"
                  "LU-DETERMINANT [matrix] - Finds the determinant of matrix in O(n^3) time. Works only with positive numbers."
                  ))))
