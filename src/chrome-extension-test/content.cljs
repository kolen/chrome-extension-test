(ns chrome-extension-test.content
  (:require [khroma.runtime :as runtime]
            [khroma.log :as console]
            [cljs.core.async :refer [>! <!]]
            [weasel.repl]
            [dommy.core :refer-macros [sel sel1]]
            [cljs.core.async :refer [put! chan <!]])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(defn init []
  (let [bg (runtime/connect)]
    (weasel.repl/connect "ws://localhost:11000" :verbose true)
    (go (>! bg :lol-i-am-a-content-script)
        (console/log "Background said: " (<! bg)))))

(defn ^:export observe [selector options]
  (let [c (chan)
        mut-callback! (fn [records zis] (doseq [r records] put! c r))
        obs (js/MutationObserver. mut-callback!)]
    (.observe obs (sel1 selector) (clj->js options))
    c))

(defn ^:export
  repl-connect []
  (weasel.repl/connect "ws://localhost:11000" :verbose true))
