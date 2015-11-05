(ns re-mdl.components.menu
  (:require [reagent.core :as r]
            [re-mdl.util :refer [wrap-mdl]]))


(defn item [& {:keys [label disabled? top-left? top-right? bottom-right?
                      id class attr]
               :as   args}]
  [:li
   (r/merge-props
    (cond-> {:id id
             :class (cond-> "mdl-menu__item"
                      class (str " " class)
                      top-left? (str " mdl-menu--top-left")
                      top-right? (str " mdl-menu--top-right")
                      bottom-right? (str " mdl-menu--bottom-right"))}
      disabled? (assoc :disabled true))
    attr)
   label])

(defn menu* [& {:keys [for ripple-effect?
                       children
                       id class attr]
                :as   args}]
  (into
   [:ul
    (r/merge-props
     {:id id
      :for for
      :class (cond-> "mdl-menu mdl-js-menu"
               class (str " " class)
               ripple-effect? (str " mdl-js-ripple-effect"))}
     attr)]
   children))

(def menu (wrap-mdl menu*))
