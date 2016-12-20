(ns re-mdl.components.dialog
  (:require [re-mdl.util :refer [wrap-dialog-polyfill]]))

(defn title [& {:keys [child el
                       children
                       id class attr]
                :or   {el :h3}
                :as   args}]
  (into
   [el
    (merge
     {:id    id
      :class (cond-> "mdl-dialog__title"
               class (str " " class))}
     attr)
    child]
   children))

(defn content [& {:keys [el
                         children
                         id class attr]
                  :or   {el :div}
                  :as   args}]
  (into
   [el
    (merge
     {:id    id
      :class (cond-> "mdl-dialog__content"
               class (str " " class))}
     attr)]
   children))

(defn actions [& {:keys [el
                         full-width?
                         children
                         id class attr]
                  :or   {el :div}
                  :as   args}]
  (into
   [el
    (merge
     {:id    id
      :class (cond-> "mdl-dialog__actions"
               class       (str " " class)
               full-width? (str " mdl-dialog__actions--full-width"))}
     attr)]
   children))

(defn dialog* [& {:keys [children
                         id class attr]
                  :as   args}]
  (into
   [:dialog
    (merge
     {:id    id
      :class (cond-> "mdl-dialog"
               class (str " " class))}
     attr)]
   children))

(def dialog (wrap-dialog-polyfill dialog*))
