(ns re-mdl.components.grid
  (:require [reagent.core :as r]))


(defn grid
  [& {:keys [children
             no-spacing?
             id class attr]
      :as   args}]
  (into [:div
         (merge
          {:id id
           :class (cond-> "mdl-grid"
                    class (str " " class)
                    no-spacing? (str " mdl-grid--no-spacing"))}
          attr)]
        children))

(def valid-align #{:top :middle :bottom})

(def align-mdl-class
  {:top "mdl-cell--top"
   :middle "mdl-cell--middle"
   :bottom "mdl-cell--bottom"})

(defn cell
  [& {:keys [children align
             col
             desktop tablet phone
             stretch?
             id class attr]
      ;; :or {stretch? true}
      :as   args}]
  (when align (assert (valid-align align)))
  (into [:div
         (merge
          {:id id
           :class (cond-> "mdl-cell"
                    class (str " " class)
                    stretch? (str " mdl-cell--stretch")
                    align (str " " (align-mdl-class align))
                    col (str " mdl-cell--" col "-col")
                    desktop (cond->
                                (:col desktop) (str " mdl-cell--" (:col desktop) "-col-desktop")
                                (:hide? desktop) (str " mdl-cell--hide-desktop"))
                    tablet (cond->
                               (:col tablet) (str " mdl-cell--" (:col tablet) "-col-tablet")
                               (:hide? tablet) (str " mdl-cell--hide-tablet"))
                    phone (cond->
                              (:col phone) (str " mdl-cell--" (:col phone) "-col-phone")
                              (:hide? phone) (str " mdl-cell--hide-phone")))}
          attr)]
        children))
