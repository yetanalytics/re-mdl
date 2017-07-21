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
              :class (str "mdl-badge"
                          (and class          (str  " " class))
                          (and no-background? " mdl-badge--no-background")
                          (and overlap?       " mdl-badge--overlap")
                          (and icon?          " material-icons"))}
       badge-label (assoc :data-badge badge-label))
     attr)
    child]
   children))
