(ns re-mdl.components.menu
  (:require [re-mdl.util :refer [wrap-mdl]]
            #?(:clj [re-mdl.macros :refer [build-class]]))
  #?(:cljs (:require-macros [re-mdl.macros :refer [build-class]])))

(defn item [& {:keys [child disabled? on-click
                      full-bleed-divider?
                      children
                      id class attr]
               :as   args}]
  (into
   [:li
    (merge
     (cond-> {:id id
              :class (build-class "mdl-menu__item"
                       class (str " " class)
                       full-bleed-divider?
                       " mdl-menu__item--full-bleed-divider")}
       disabled? (assoc :disabled true)
       on-click  (assoc :on-click on-click))
     attr)
    child]
   children))

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
      :class (build-class "mdl-menu mdl-js-menu"
               class          (str " " class)
               ripple-effect? " mdl-js-ripple-effect"
               top-left?      " mdl-menu--top-left"
               top-right?     " mdl-menu--top-right"
               bottom-right?  " mdl-menu--bottom-right")}
     attr)]
   children))

(def menu (wrap-mdl menu*))
