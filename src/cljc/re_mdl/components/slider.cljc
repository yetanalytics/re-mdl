(ns re-mdl.components.slider
  (:require #?(:cljs [reagent.core :as r])
            [re-mdl.util :refer [mdl-init! mdl-get-model]]))

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

         :min  (mdl-get-model min)
         :max  (mdl-get-model max)
         :step (mdl-get-model step)

         #?@(:cljs
             [:on-change
              (fn [e] (handler-fn (.. e -target -value)))
              ]
             :clj [:value (mdl-get-model model)])

         :class (cond-> "mdl-slider mdl-js-slider"
                  class (str " " class))}

      (mdl-get-model disabled?) (assoc :disabled true))
    attr)])

(defn slider [& {:keys [model]
                 :as args}]
  #?(:cljs
     (r/create-class
      {:component-did-mount
       (fn [this]
         (doto (r/dom-node this)
           mdl-init!
           (-> (.. -MaterialSlider)
               (.change (mdl-get-model model)))))
       :component-did-update
       (fn [this _]
         (-> (r/dom-node this)
             (.. -MaterialSlider)
             (.change (mdl-get-model model))))
       :reagent-render
       slider*})
     :clj (apply slider* (flatten args))))
