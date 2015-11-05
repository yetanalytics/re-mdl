(ns re-mdl.components.slider
  (:require [reagent.core :as r]
            [re-mdl.util :refer [wrap-mdl]]))

(defn slider* [& {:keys [min max value step
                         id class attr]
                  :as   args}]
  [:input
   (r/merge-props
    {:type "range"
     :id id

     :min (str min)
     :max (str max)
     ;; :value (str value)
     :step (str step)

     :class (cond-> "mdl-slider mdl-js-slider"
              class (str " " class))}
    attr)])

(def slider (wrap-mdl slider*))
