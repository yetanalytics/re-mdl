(ns re-mdl.components.slider
  (:require #?(:cljs [reagent.core :as r])
            [re-mdl.util :refer [mdl-init-mount
                                 mdl-get-value
                                 mdl-get-props]]))

(defn slider*
  [& {:keys [min max step model disabled?
             handler-fn
             id class attr]
      :or   {min 0 max 100
             handler-fn (constantly nil)}
      :as   args}]
  (let [_ (mdl-get-value model)]
    [:input
     (merge
      (cond->
          {:type "range"
           :id   id
           :min  (mdl-get-value min)
           :max  (mdl-get-value max)
           :step (mdl-get-value step)
           :class (cond-> "mdl-slider mdl-js-slider"
                    class (str " " class))
           #?@(:cljs
               [:on-change
                (fn [e] (handler-fn (.. e -target -value)))
                :defaultValue
                (mdl-get-value model)]
               :clj [:defaultValue (mdl-get-value model)])}
        (mdl-get-value disabled?) (assoc :disabled true))
      attr)]))

(defn slider [& {:keys [model]
                 :as args}]
  #?(:cljs
     (r/create-class
      {:component-did-mount
       mdl-init-mount
       :component-will-update
       (fn [this new-argv]
         (-> (r/dom-node this)
             .-MaterialSlider
             (.change
              (-> new-argv mdl-get-props :model mdl-get-value))))
       :reagent-render
       slider*})
     :clj (apply slider* (flatten args))))
