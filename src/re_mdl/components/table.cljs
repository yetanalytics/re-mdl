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





(defn table [& {:keys [
                       id class attr]
                :as args}])
