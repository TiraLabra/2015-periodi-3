(ns matrix_calculator.paaluokka
  (:gen-class))

(defn -main [& args]
  (println "Welcome to my project! These are your args:" args))


(defn take-until [how-many elems]
  "Takes as parameters one number x and list of elements.
  Returns an array, where first element is x first element of
  given array and the second element is an array including the rest."
  (loop [x how-many
         elems elems
         result []
         rest-elems elems]
    (if (zero? x)
      [result rest-elems]
      (recur
       (dec x)
       (rest elems)
       (conj result (first elems))
       (rest rest-elems)))))

(defn make-matrix [rows columns & elems]
  "Takes as a parameters the number of rows x, the number of columns y
  and all elements of the matrix.
  Composes a presentation of matrix as an array with x elements which
  presents the rows. Each row contains y elements."
  (if (not= (* rows columns)(count elems))
    (throw (Exception.
            (str "You should give: amount of rows, amount of columns, "
            (* rows columns) " elements.")))
    (loop [i rows
           elems elems
           matrix []]
      (if (zero? i)
        matrix
        (let [row-tookd (take-until columns elems)
              row (first row-tookd)
              rest-elems (second row-tookd)]
          (recur (dec i) rest-elems (conj matrix row)))))))
