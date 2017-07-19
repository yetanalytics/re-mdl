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
              :class    (cond-> "mdl-button mdl-js-button"
                          class          (str (str " " class))
                          raised?        (str " mdl-button--raised")
                          fab?           (str " mdl-button--fab")
                          mini-fab?      (str " mdl-button--fab mdl-button--mini-fab")
                          icon?          (str " mdl-button--icon")
                          colored?       (str " mdl-button--colored")
                          primary?       (str " mdl-button--primary")
                          accent?        (str " mdl-button--accent")
                          ripple-effect? (str " mdl-js-ripple-effect"))}
       disabled? (assoc :disabled true)
       for       (assoc :for for))
     attr)
    child]
   children))

(def button (wrap-mdl button*))
