(ns re-mdl.components.button
  (:require [reagent.core :as r]
            [re-mdl.util :refer [wrap-mdl]]))

(defn button*
  [& {:keys [el label icon on-click
             disabled? raised? fab? mini-fab? colored? primary? accent? ripple-effect?
             class attr]
      :or   {el :button}
      :as   args}]
  [el
   (r/merge-props
    (cond-> {:on-click on-click
             :class (cond-> "mdl-button mdl-js-button"
                      class (str (str " " class))
                      raised? (str " mdl-button--raised")
                      fab? (str " mdl-button--fab")
                      mini-fab? (str " mdl-button--fab mdl-button--mini-fab")
                      icon (str " mdl-button--icon")
                      colored? (str " mdl-button--colored")
                      primary? (str " mdl-button--primary")
                      accent? (str " mdl-button--accent")
                      ripple-effect? (str " mdl-js-ripple-effect"))}
      disabled? (assoc :disabled true))
    attr)
   (if icon
     [:i.material-icons icon]
     label)])

(def button
  (wrap-mdl button*))
