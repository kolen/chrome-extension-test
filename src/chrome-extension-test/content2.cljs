(ns chrome-extension-test.content2
  (:require [khroma.runtime :as runtime]
            [khroma.log :as console]
            [cljs.core.async :refer [>! <!]]
            [cljs.core.async :as async]
            [dommy.core :as dommy]
            [dommy.core :refer-macros [sel sel1]]
            [cljs.core.async :refer [put! chan <! pipe]])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(defn init []
  (let [bg (runtime/connect)]
    (go (>! bg :lol-i-am-a-content-script)
        (console/log "Background said: " (<! bg))

        (observe :#ac_performer {}))))

(defn observe [selector options]
  (let [ch (chan)
        mut-callback! (fn [records zis] (doseq [r records] (put! ch r)))
        obs (js/MutationObserver. mut-callback!)]
    (.observe obs (sel1 selector) (clj->js options))
    ch))

(defn observe-text [selector]
  "Observe node text contents"
  (let [ch (observe selector {:characterData true :subtree true})
        extract-text (fn [el] (-> el (aget "target") (dommy/text)))]
    (async/map extract-text [ch])))

; (def ch (observe-text :#ac_performer))
; (go (while true (.log js/console (<! ch))))
