(ns re-mdl.components.table
  (:require [reagent.core :as r]
            [re-mdl.util :refer [wrap-mdl]]))


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
     (merge
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
