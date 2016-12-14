(ns re-mdl.components.loading
  (:require
   #?(:cljs [reagent.core :as r])
   [re-mdl.util :refer [wrap-mdl
                        mdl-init!
                        mdl-get-value]]))

(defn progress* [& {:keys [indeterminate? width
                           model buffer
                           id class attr]
                    :or {width "250px"}
                    :as   args}]
  [:div
   (merge
    {:id    id
     :style {:width width}
     :class (cond-> "mdl-progress mdl-js-progress"
              class (str " " class)
              #?(:cljs indeterminate?
                 :clj true) ;; always indeterminate for clj
              (str " mdl-progress__indeterminate"))}
    attr)])

(defn progress [& {:keys [model buffer]
                   :as   args}]
  #?(:cljs
     (let [set-progress #(doto (-> %1 .-MaterialProgress)
                           (.setProgress (mdl-get-value %2))
                           (.setBuffer   (mdl-get-value %3)))]
       (r/create-class
        {:component-did-mount
         (fn [this]
           (let [node      (r/dom-node this)
                 _         (mdl-init! node)
                 get-model #(set-progress node model buffer)]
             (r/replace-state this
                              {:model-tracker (r/track! get-model)})))
         :component-will-unmount
         (fn [this]
           (-> (r/state this) :model-tracker r/dispose!))
         :component-will-update
         (fn [this new-argv]
           (let [node  (r/dom-node this)
                 props (apply hash-map (rest new-argv))]
             (set-progress node (:model props) (:buffer props))))
         :reagent-render
         progress*}))
     :clj progress*))

(defn spinner* [& {:keys [el is-active? single-color?
                          id class attr]
                   :or {el :div}
                   :as   args}]
  [el
   (merge
    {:id id
     :class (cond-> "mdl-spinner mdl-js-spinner"
              class         (str " " class)
              is-active?    (str " is-active")
              single-color? (str " mdl-spinner--single-color"))}
    attr)])

(def spinner (wrap-mdl spinner*))
