(ns re-mdl.components.button
  (:require [re-mdl.util :refer [wrap-mdl]]
            #?(:clj [re-mdl.macros :refer [build-class]]))
  #?(:cljs (:require-macros [re-mdl.macros :refer [build-class]])))

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
              :class    (build-class "mdl-button mdl-js-button"
                                     class          (str " " class)
                                     raised?        " mdl-button--raised"
                                     fab?           " mdl-button--fab"
                                     mini-fab?      " mdl-button--fab mdl-button--mini-fab"
                                     icon?          " mdl-button--icon"
                                     colored?       " mdl-button--colored"
                                     primary?       " mdl-button--primary"
                                     accent?        " mdl-button--accent"
                                     ripple-effect? " mdl-js-ripple-effect")}
       disabled? (assoc :disabled true)
       for       (assoc :for for))
     attr)
    child]
   children))

(def button (wrap-mdl button*))
