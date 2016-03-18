(ns re-mdl.components.dialog
  (:require [re-mdl.util :refer [wrap-dialog-polyfill]]))

(defn dialog*
  [& {:keys [title-el title content actions
             actions-full-width?
             id class attr]
      :or   {title-el :h3}
      :as   args}]
  (cond-> [:dialog
           (merge
            {:id id
             :class (cond-> "mdl-dialog"
                      class (str (str " " class)))}
            attr)]
    title (conj [title-el
                 {:class "mdl-dialog__title"}
                 title])
    content (conj
             (into [:div.mdl-dialog__content] content))
    actions (conj
             (into [:div
                    {:class (cond-> "mdl-dialog__actions"
                              actions-full-width?
                              (str " mdl-dialog__actions--full-width"))}]
                   actions))))

(def dialog (wrap-dialog-polyfill dialog*))
