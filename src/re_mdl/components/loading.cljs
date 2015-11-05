(ns re-mdl.components.loading
  (:require
   [reagent.core :as r]
   [re-mdl.util :refer [wrap-mdl]]))

(defn progress* [& {:keys [indeterminate? width

                          id class attr]
                   :or {width "250px"}
                   :as   args}]
  [:div
   (r/merge-props
    {:id id
     :style {:width width}
     :class (cond-> "mdl-js-progress"
              class (str " " class)
              indeterminate? (str " mdl-progress__indeterminate"))}
    attr)])

(def progress (wrap-mdl progress*))
