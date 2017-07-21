(ns re-mdl.components.button
  (:require [re-mdl.util :refer [wrap-mdl]]))

(defn button*
  [& {:keys [el child on-click
             icon? disabled? raised? fab? mini-fab?
             colored? primary? accent? ripple-effect?
             for
             children
             id class attr]
      :or   {el :button}
      :as   args}]
  (into
   [el
    (merge
     (cond-> {:on-click on-click
              :id       id
              :class    (str "mdl-button mdl-js-button"
                             (and class          (str " " class))
                             (and raised?        " mdl-button--raised")
                             (and fab?           " mdl-button--fab")
                             (and mini-fab?      " mdl-button--fab mdl-button--mini-fab")
                             (and icon?          " mdl-button--icon")
                             (and colored?       " mdl-button--colored")
                             (and primary?       " mdl-button--primary")
                             (and accent?        " mdl-button--accent")
                             (and ripple-effect? " mdl-js-ripple-effect"))}
       disabled? (assoc :disabled true)
       for       (assoc :for for))
     attr)
    child]
   children))

(def button (wrap-mdl button*))
