(ns re-mdl.components.loading
  (:require
   #?(:cljs [reagent.core :as r])
   [re-mdl.util :refer [wrap-mdl
                        mdl-init!
                        mdl-get-value
                        mdl-get-props]]))

(defn progress* [& {:keys [indeterminate?
                           model buffer
                           children
                           id class attr]
                    :as   args}]
  (let [_ (mdl-get-value model)
        _ (mdl-get-value buffer)]
    (into
     [:div
      (merge
       {:id    id
        :class (cond-> "mdl-progress mdl-js-progress"
                 class (str " " class)
                 #?(:cljs indeterminate?
                    :clj  true) ;; always indeterminate for clj
                 (str " mdl-progress__indeterminate"))}
       attr)]
     children)))

(defn progress [& {:keys [model buffer]
                   :as   args}]
  #?(:cljs
     (r/create-class
      {:component-did-mount
       (fn [this]
         (doto (r/dom-node this)
           mdl-init!
           (-> .-MaterialProgress
               (.setProgress (mdl-get-value model)))
           (-> .-MaterialProgress
               (.setBuffer (mdl-get-value buffer)))))
       :component-did-update
       (fn [this _]
         (let [props (mdl-get-props (r/argv this))]
           (doto (.-MaterialProgress (r/dom-node this))
             (-> (.setProgress (mdl-get-value (:model props))))
             (-> (.setBuffer (mdl-get-value (:buffer props)))))))
       :reagent-render
       progress*})
     :clj (apply progress* (flatten args))))

(defn spinner* [& {:keys [el is-active? single-color?
                          children
                          id class attr]
                   :or   {el :div}
                   :as   args}]
  (into
   [el
    (merge
     {:id id
      :class (cond-> "mdl-spinner mdl-js-spinner"
               class         (str " " class)
               is-active?    (str " is-active")
               single-color? (str " mdl-spinner--single-color"))}
     attr)]
   children))

(def spinner (wrap-mdl spinner*))
