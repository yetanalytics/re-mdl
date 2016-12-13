(ns re-mdl.components.slider
  (:require #?(:cljs [reagent.core :as r])
            [re-mdl.util :refer [mdl-init-mount mdl-get-model]]))

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

         :min  (mdl-get-model min)
         :max  (mdl-get-model max)
         :step (mdl-get-model step)

         #?@(:cljs
             [:on-change
              (fn [e] (handler-fn (.. e -target -value)))
              :value (mdl-get-model model)]
             :clj [:value (mdl-get-model model)])

         :class (cond-> "mdl-slider mdl-js-slider"
                  class (str " " class))}
      disabled? (assoc :disabled true))
    attr)])

(defn slider [& {:keys [model]
                 :as args}]
  #?(:cljs
     (r/create-class
      {:component-did-mount
       mdl-init-mount
       :component-did-update
       (fn [this _]
         (-> (r/dom-node this)
             (.. -MaterialSlider)
             (.change (mdl-get-model model))))
       :reagent-render
       slider*})
     :clj (apply slider* (flatten args))))
