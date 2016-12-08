(ns re-mdl.components.slider
  (:require #?(:cljs [reagent.core :as r])
            [re-mdl.util :refer [mdl-init!]]))

(defn slider*
  [& {:keys [min max step
             handler-fn model
             id class attr
             disabled?]
      :as   args}]
  [:input
   (merge
    (cond->
        {:type "range"
         :id   id

         :min  min
         :max  max
         :step step

         #?@(:cljs
             [:on-change
              (fn [e] (handler-fn (.. e -target -value)))]
             :clj [:value @model])

         :class (cond-> "mdl-slider mdl-js-slider"
                  class (str " " class))}
      disabled? (assoc :disabled true))
    attr)])

(defn slider [& {:keys [model]
                 :as args}]
  #?(:cljs
     (r/create-class
      {:component-did-mount
       (fn [this]
         (let [node (r/dom-node this)]
           (doto node
             mdl-init!
             (-> .-MaterialSlider (.change @model)))))
       :reagent-render
       slider*})
     :clj (apply slider* (flatten args))))
