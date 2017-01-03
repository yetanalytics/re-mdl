(ns re-mdl.components.grid)

(defn grid [& {:keys [no-spacing?
                      children
                      id class attr]
               :as   args}]
  (into
   [:div
    (merge
     {:id    id
      :class (cond-> "mdl-grid"
               class       (str " " class)
               no-spacing? (str " mdl-grid--no-spacing"))}
     attr)]
   children))

(def valid-align #{:top :middle :bottom})

(def align-mdl-class
  {:top    "mdl-cell--top"
   :middle "mdl-cell--middle"
   :bottom "mdl-cell--bottom"})

(defn cell [& {:keys [align offset order col stretch?
                      desktop tablet phone
                      children
                      id class attr]
               :as   args}]
  (when align (assert (valid-align align)))
  (into
   [:div
    (merge
     {:id    id
      :class (cond-> "mdl-cell"
               class    (str " " class)
               stretch? (str " mdl-cell--stretch")
               align    (str " " (align-mdl-class align))
               offset   (str " mdl-cell--" offset "-offset")
               order    (str " mdl-cell--" order "-order")
               col      (str " mdl-cell--" col "-col")
               desktop  (cond->
                            (:col desktop)    (str " mdl-cell--" (:col desktop) "-col-desktop")
                            (:offset desktop) (str " mdl-cell--" (:offset desktop) "-offset-desktop")
                            (:order desktop)  (str " mdl-cell--" (:order desktop) "-order-desktop")
                            (:hide? desktop)  (str " mdl-cell--hide-desktop"))
               tablet   (cond->
                            (:col tablet)    (str " mdl-cell--" (:col tablet) "-col-tablet")
                            (:offset tablet) (str " mdl-cell--" (:offset tablet) "-offset-tablet")
                            (:order tablet)  (str " mdl-cell--" (:order tablet) "-order-tablet")
                            (:hide? tablet)  (str " mdl-cell--hide-tablet"))
               phone    (cond->
                            (:col phone)    (str " mdl-cell--" (:col phone) "-col-phone")
                            (:offset phone) (str " mdl-cell--" (:offset phone) "-offset-phone")
                            (:order phone)  (str " mdl-cell--" (:order phone) "-order-phone")
                            (:hide? phone)  (str " mdl-cell--hide-phone")))}
     attr)]
   children))
