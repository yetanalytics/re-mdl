(ns re-mdl.components.loading
  (:require
   [reagent.core :as r]
   [re-mdl.util :refer [mdl-init-mount]]))

(defn progress* [& {:keys [indeterminate? width
                           model
                           id class attr]
                   :or {width "250px"}
                   :as   args}]
  [:div
   (r/merge-props
    {:id id
     :style {:width width}
     :class (cond-> "mdl-progress mdl-js-progress"
              class (str " " class)
              indeterminate? (str " mdl-progress__indeterminate"))}
    attr)])

(defn get-progress [& {:keys [model]
                       :as   args}]
  model)

(def progress
  (r/create-class
   {:reagent-render progress*
    :component-did-mount
    (fn [this]
      (mdl-init-mount this))
    :component-did-update
    (fn [this new-argv]
      (let [node (r/dom-node this)
            pct-done (apply get-progress (rest new-argv))]
        (-> node
            .-MaterialProgress
            (.setProgress pct-done))))}))
