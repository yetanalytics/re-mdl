(ns re-mdl.components.table
  (:require [re-mdl.util :refer [wrap-mdl]]))

(def valid-shadows #{2 3 4 6 8 16})

;; TODO: Do something with selection state

(defn table* [& {:keys [selectable?
                        headers rows shadow
                        children
                        id class attr]
                 :as args}]
  (when shadow (assert (valid-shadows shadow)))
  (let [non-numerics (into #{}
                           (filter identity
                                   (map-indexed
                                    (fn [idx [_ & {:keys [non-numeric]}]]
                                      (when non-numeric
                                        idx))
                                    headers)))]
    (into
     [:table
      (merge
       {:id    id
        :class (cond-> "mdl-data-table mdl-js-data-table"
                 class       (str " " class)
                 selectable? (str " mdl-data-table--selectable")
                 shadow      (str " mdl-shadow--" shadow "dp"))}
       attr)
      [:thead
       (into [:tr]
             (map-indexed
              (fn [idx [label & {:keys [non-numeric
                                       ascending
                                       descending]}]]
                [:th
                 {:class (cond-> ""
                           non-numeric (str " mdl-data-table__cell--non-numeric")
                           ascending   (str " mdl-data-table__header--sorted-ascending")
                           descending  (str " mdl-data-table__header--sorted-descending"))}
                 label])
              headers))]
      (into [:tbody]
            (for [row rows]
              (into [:tr]
                    (map-indexed
                     (fn [idx datum]
                       [:td
                        {:class (when (non-numerics idx)
                                  "mdl-data-table__cell--non-numeric")}
                        datum])
                     row))))]
     children)))

(def table (wrap-mdl table*))
