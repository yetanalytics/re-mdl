(ns re-mdl.components.tooltip
  (:require [reagent.core :as r]
            [re-mdl.util :refer [wrap-mdl]]))


(defn tooltip* [& {:keys [for large?
                          children
                          id class attr]}]
  (into
   [:span
    (merge
     {:id id
      :for for
      :class (cond-> "mdl-tooltip"
               class (str " " class)
               large? (str " mdl-tooltip--large"))}
     attr)]
   children))

(def tooltip (wrap-mdl tooltip*))
