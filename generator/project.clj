(defproject generator "0.1.0-SNAPSHOT"
  :description "Simple script to generate a website"
  :url "http://syc153.com/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.pegdown/pegdown "1.2.0"]
                 [hiccup "1.0.2"]]
  :main generator.core)
