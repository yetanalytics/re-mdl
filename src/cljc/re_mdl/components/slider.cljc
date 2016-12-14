(ns re-mdl.components.slider
  (:require #?(:cljs [reagent.core :as r])
            [re-mdl.util :refer [mdl-init! mdl-get-value]]))

(defn slider*
  [& {:keys [min max step model disabled?
             handler-fn
             id class attr]
      :or   {min 0 max 100}
      :as   args}]
  [:input
   (merge
    (cond->
        {:type "range"
         :id   id

         :min  (mdl-get-value min)
         :max  (mdl-get-value max)
         :step (mdl-get-value step)

         #?@(:cljs
             [:on-change
              (fn [e] (handler-fn (.. e -target -value)))
              ]
             :clj [:value (mdl-get-value model)])

         :class (cond-> "mdl-slider mdl-js-slider"
                  class (str " " class))}

      (mdl-get-value disabled?) (assoc :disabled true))
    attr)])

(defn slider [& {:keys [model]
                 :as args}]
  #?(:cljs
     (r/create-class
      {:component-did-mount
       (fn [this]
         (doto (r/dom-node this)
           mdl-init!
           (-> .-MaterialSlider
               (.change (mdl-get-value model)))))
       :component-did-update
       (fn [this _]
         (-> (r/dom-node this)
             .-MaterialSlider
             (.change (mdl-get-value model))))
       :reagent-render
       slider*})
     :clj (apply slider* (flatten args))))
