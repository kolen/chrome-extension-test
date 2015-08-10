(defproject chrome-extension-test "0.1.0-SNAPSHOT"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.48"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [khroma "0.0.2"]
                 [prismatic/dommy "1.1.0"]
                 [weasel "0.7.0"]]
  :source-paths ["src"]
  :profiles {:dev {:plugins [[com.cemerick/austin "0.1.6"]
                             [lein-cljsbuild "1.0.6"]
                             [lein-chromebuild "0.2.1"]]
                   :dependencies [[com.cemerick/piggieback "0.2.1"]
                                  [org.clojure/tools.nrepl "0.2.10"]]
                   :nrepl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :injections [(require '[cemerick.piggieback :as pb]
                                         '[weasel.repl.websocket :as ws])
                                (defn browser-repl []
                                  (pb/cljs-repl
                                   (ws/repl-env :ip "0.0.0.0" :port 11000)))] ;; Choose your port
                   :cljsbuild {:builds {:main
                                        {:source-paths ["src"]
                                         :compiler {:output-to "target/unpacked/chrome_extension_test.js"
                                                    :output-dir "target/js"
                                                    :optimizations :whitespace
                                                    :pretty-print true}}}
                               :repl-listen-port 9013}}})
