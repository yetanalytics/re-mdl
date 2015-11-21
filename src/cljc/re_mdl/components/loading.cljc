(ns re-mdl.components.loading
  (:require
   #?(:cljs [reagent.core :as r])
   [re-mdl.util :refer [wrap-mdl
                        mdl-init-mount]]))

(defn progress* [& {:keys [indeterminate? width
                           model
                           id class attr]
                   :or {width "250px"}
                   :as   args}]
  [:div
   (merge
    {:id id
     :style {:width width}
     :class (cond-> "mdl-progress mdl-js-progress"
              class (str " " class)
              #?(:cljs indeterminate?
                 :clj true) ;; always indeterminate for clj
              (str " mdl-progress__indeterminate"))}
    attr)])

(defn get-progress [& {:keys [model]
                       :as   args}]
  model)

(def progress
  #?(:cljs
     (r/create-class
      {:reagent-render progress*
       :component-did-mount
       (fn [this]
         (mdl-init-mount this))
       :component-did-update
       (fn [this new-argv]
         (let [node (r/dom-node this)
               pct-done (apply get-progress (rest new-argv))]
           (-> node
               .-MaterialProgress
               (.setProgress pct-done))))})
     :clj progress*))


(defn spinner* [& {:keys [el is-active? single-color?
                          id class attr]
                   :or {el :div}
                   :as   args}]
  [el
   (merge
    {:id id
     :class (cond-> "mdl-spinner mdl-js-spinner"
              class (str " " class)
              is-active? (str " is-active")
              single-color? (str " mdl-spinner--single-color"))}
    attr)])

(def spinner (wrap-mdl spinner*))
