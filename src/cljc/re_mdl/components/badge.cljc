(ns re-mdl.components.badge)

(defn badge
  [& {:keys [el child badge-label no-background? overlap? icon?
             children
             id class attr]
      :or   {el :span}
      :as   args}]
  (into
   [el
    (merge
     (cond-> {:id id
              :class (cond-> "mdl-badge"
                       class          (str (str " " class))
                       no-background? (str " mdl-badge--no-background")
                       overlap?       (str " mdl-badge--overlap")
                       icon?          (str " material-icons"))}
       badge-label (assoc :data-badge badge-label))
     attr)
    child]
   children))
