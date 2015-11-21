(ns re-mdl.components.slider
  (:require #?(:cljs [reagent.core :as r])
            [re-mdl.util :refer [mdl-init!]]))

(defn slider*
  [& {:keys [min max step
             handler-fn
             id class attr
             init-val]
      :as   args}]
  [:input
   (merge
    {:type "range"
     :id id

     :min min
     :max max
     :step step

     #?@(:cljs
         [:on-change
          (fn [e] (handler-fn (.-value (.-target e))))]
         :clj [:value init-val])

     :class (cond-> "mdl-slider mdl-js-slider"
              class (str " " class))}
    attr)])

(defn slider [& {:keys [init-val]
                 :as args}]
  #?(:cljs
     (r/create-class
      {:component-did-mount
       (fn [this]
         (let [node (r/dom-node this)]
           (doto node
             mdl-init!
             (-> .-MaterialSlider (.change init-val)))))
       :reagent-render
       slider*})
     :clj (apply slider* (flatten args))))
