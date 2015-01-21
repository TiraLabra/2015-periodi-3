(defproject matrix-calculator "0.1.0-SNAPSHOT"
  :description "Data Structures and algorithms-project: Matrix calculator."
  :url "https://github.com/samutamm/2015-periodi-3"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-http "1.0.1"]]
  :plugins [[lein-cloverage "1.0.2"]]
  :profiles {:uberjar {:aot :all}}
  :test-selectors {:default (complement :integration)
                  :integration :integration
                  :all (fn [_] true)}
  :main matrix_calculator.paaluokka
  :aot [matrix_calculator.paaluokka])
