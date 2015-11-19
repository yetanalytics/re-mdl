(ns re-mdl.components.slider
  (:require [reagent.core :as r]
            [re-mdl.util :refer [mdl-init!]]))

(defn slider [& {:keys [init-val]
                 }]
  (r/create-class
   {:component-did-mount
    (fn [this]
      (let [node (r/dom-node this)]
        (doto node
          mdl-init!
          (-> .-MaterialSlider (.change init-val)))))
    :reagent-render
    (fn [& {:keys [min max step
                  handler-fn
                  id class attr]
           :as   args}]
      [:input
       (merge
        {:type "range"
         :id id

         :min min
         :max max
         :step step

         :on-change
         (fn [e] (handler-fn (.-value (.-target e))))

         :class (cond-> "mdl-slider mdl-js-slider"
                  class (str " " class))}
        attr)])}))
