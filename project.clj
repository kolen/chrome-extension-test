(defproject chrome-extension-test "0.1.0-SNAPSHOT"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.48"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [khroma "0.0.2"]
                 [prismatic/dommy "1.1.0"]]
  :source-paths ["src"]
  :profiles {:dev {:plugins [[lein-cljsbuild "1.0.6"]
                             [lein-chromebuild "0.2.1"]
                             [lein-figwheel "0.3.7"]]
                   :cljsbuild {:builds {:main
                                        {:source-paths ["src" "src/chrome-extension-test"]
                                         :compiler {:main "chrome-extension-test.content"
                                                    :output-to "target/unpacked/chrome_extension_test.js"
                                                    :output-dir "target/js"
                                                    :asset-path "chrome-extension://mnibaljpgmlacbpggfffhnpbkbgjhkol"
                                                    :optimizations :none
                                                    :figwheel true
                                                    :pretty-print true}}}}}})
