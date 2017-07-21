(ns re-mdl.components.badge
  (#?(:clj :require
      :cljs :require-macros) [re-mdl.macros :refer [build-class]]))

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
              :class
              (build-class "mdl-badge"
                           class          (str  " " class)
                           no-background? " mdl-badge--no-background"
                           overlap?       " mdl-badge--overlap"
                           icon?          " material-icons")}
       badge-label (assoc :data-badge badge-label))
     attr)
    child]
   children))
