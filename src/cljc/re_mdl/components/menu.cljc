(ns re-mdl.components.menu
  (:require [re-mdl.util :refer [wrap-mdl]]))


(defn item [& {:keys [label disabled? on-click
                      full-bleed-divider?
                      id class attr]
               :as   args}]
  [:li
   (merge
    (cond-> {:id id
             :class (cond-> "mdl-menu__item"
                      class (str " " class)
                      full-bleed-divider?
                      (str " mdl-menu__item--full-bleed-divider"))}
      disabled? (assoc :disabled true)
      on-click (assoc :on-click on-click))
    attr)
   label])

(defn menu* [& {:keys [for ripple-effect?
                       top-left? top-right? bottom-right?
                       children
                       id class attr]
                :as   args}]
  (into
   [:ul
    (merge
     {:id id
      :for for
      :class (cond-> "mdl-menu mdl-js-menu"
               class          (str " " class)
               ripple-effect? (str " mdl-js-ripple-effect")
               top-left?      (str " mdl-menu--top-left")
               top-right?     (str " mdl-menu--top-right")
               bottom-right?  (str " mdl-menu--bottom-right"))}
     attr)]
   children))

(def menu (wrap-mdl menu*))
