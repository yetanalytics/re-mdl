(ns re-mdl.components.tooltip
  (:require [re-mdl.util :refer [wrap-mdl]]))


(defn tooltip* [& {:keys [for large? left? right? top? bottom?
                          children
                          id class attr]}]
  (into
   [:span
    (merge
     {:id id
      :for for
      :class (cond-> "mdl-tooltip"
               class (str " " class)
               large? (str " mdl-tooltip--large")
               left? (str " mdl-tooltip--left")
               right? (str " mdl-tooltip--right")
               top? (str " mdl-tooltip--top")
               bottom? (str " mdl-tooltip--bottom"))}
     attr)]
   children))

(def tooltip (wrap-mdl tooltip*))
