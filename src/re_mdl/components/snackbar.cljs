(ns re-mdl.components.snackbar
  (:require [reagent.core :as r]
            [re-mdl.util :refer [wrap-mdl]]))

(defn snackbar* [& {:keys [text
                          id class attr]}]
  [:div
   (merge
    {:id id
     :class (cond-> "mdl-snackbar"
              class (str " " class))}
    attr)
   [:div.mdl-snackbar__text
    text]])

(def snackbar (wrap-mdl snackbar*))
