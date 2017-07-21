(ns re-mdl.components.grid
  (#?(:clj :require
      :cljs :require-macros) [re-mdl.macros :refer [build-class]]))

(defn grid [& {:keys [no-spacing?
                      children
                      id class attr]
               :as   args}]
  (into
   [:div
    (merge
     {:id    id
      :class (build-class "mdl-grid"
                          class       (str " " class)
                          no-spacing? " mdl-grid--no-spacing")}
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
      :class
      (apply str
             (build-class "mdl-cell"
                          class    (str " " class)
                          stretch? (str " mdl-cell--stretch")
                          align    (str " " (align-mdl-class align))
                          offset   (str " mdl-cell--" offset "-offset")
                          order    (str " mdl-cell--" order "-order")
                          col      (str " mdl-cell--" col "-col"))

             (for [[view-type arg-map] (select-keys args [:desktop :tablet :phone])
                   [arg v] arg-map
                   :let [view-str (name view-type)]]
               (case arg
                 :col    (str " mdl-cell--" v "-col-" view-str)
                 :offset (str " mdl-cell--" v "-offset-" view-str)
                 :order  (str " mdl-cell--" v "-order-" view-str)
                 :hide?  (str " mdl-cell--hide-" view-str)
                 (throw (ex-info "Invalid arg for cell view"
                                 {:type ::invalid-arg
                                  :arg arg
                                  :view-type view-type})))))}
     attr)]
   children))
