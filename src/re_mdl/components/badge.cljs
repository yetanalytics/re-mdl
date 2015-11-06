(ns re-mdl.components.badge
  (:require [reagent.core :as r]))

(defn badge
  [& {:keys [el child badge-label no-background? overlap? icon?
             id class attr]
      :or   {el :span}
      :as   args}]
  [el
   (merge
    (cond-> {:id id
             :class (cond-> "mdl-badge"
                      class (str (str " " class))
                      no-background? (str " mdl-badge--no-background")
                      overlap? (str " mdl-badge--overlap")
                      icon? (str " material-icons"))}
      badge-label (assoc :data-badge badge-label))
    attr)
   child])
