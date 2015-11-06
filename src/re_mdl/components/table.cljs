(ns re-mdl.components.table
  (:require [reagent.core :as r]
            [re-mdl.util :refer [wrap-mdl]]))

(defn table-stub []
  [:table {:class "mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp"}
   [:thead
    [:tr
     [:th {:class "mdl-data-table__cell--non-numeric"} "Material"]
     [:th "Quantity"]
     [:th "Unit price"] ] ]
   [:tbody
    [:tr
     [:td {:class "mdl-data-table__cell--non-numeric"} "Acrylic (Transparent)"]
     [:td "25"]
     [:td "$2.90"] ]
    [:tr
     [:td {:class "mdl-data-table__cell--non-numeric"} "Plywood (Birch)"]
     [:td "50"]
     [:td "$1.25"] ]
    [:tr
     [:td {:class "mdl-data-table__cell--non-numeric"} "Laminate (Gold on Blue)"]
     [:td "10"]
     [:td "$2.35"] ] ] ])



(def valid-shadows #{2 3 4 6 8 16})

;; TODO: Do something with selection state

(defn table* [& {:keys [selectable?
                        headers rows children shadow
                        id class attr]
                 :as args}]
  (when shadow (assert (valid-shadows shadow)))
  (let [non-numerics (into #{}
                           (filter identity
                                   (map-indexed
                                    (fn [idx [header & {:keys [non-numeric]}]]
                                      (when non-numeric
                                        idx))
                                    headers)))
        header-labels (map first headers)]
    (into
    [:table
     (r/merge-props
      {:id id
       :class (cond-> "mdl-data-table mdl-js-data-table"
                class (str " " class)
                selectable? (str " mdl-data-table--selectable")
                shadow (str " mdl-shadow--" shadow "dp"))}
      attr)
     [:thead
      (into [:tr]
            (map-indexed
             (fn [idx label]
               [:th
                {:class (when (non-numerics idx)
                          "mdl-data-table__cell-non-numeric")}
                label])
             header-labels))]
     (into [:tbody]
           (for [row rows]
             (into [:tr]
                   (map-indexed
                    (fn [idx datum]
                      [:td
                       {:class (when (non-numerics idx)
                                 "mdl-data-table__cell-non-numeric")}
                       datum])
                    row))))
     ]
    children)))

(def table (wrap-mdl table*))
