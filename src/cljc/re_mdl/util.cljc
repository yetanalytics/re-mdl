(ns re-mdl.util
  #?(:cljs (:require [reagent.core :as r])))

(defn mdl-get-value
  "This will get the content of a model, be it atom or value."
  [model]
  #?(:cljs
     (if (satisfies? IDeref model)
       @model
       model)
     :clj
     model))

(defn mdl-get-props
  "This will read in the new props to a componenet and return a map of them."
  [props]
  (apply hash-map (rest props)))

(defn mdl-init! [elem]
  #?(:cljs
     (.upgradeElement js/componentHandler elem)
     :clj ;; no-op
     elem))

(defn mdl-init-mount [this-component]
  #?(:cljs
     (mdl-init! (r/dom-node this-component))
     :clj ;; no-op
     this-component))

(defn wrap-mdl [component]
  #?(:cljs
     (vary-meta component
                (fn [meta-m]
                  (merge-with
                   juxt
                   {:component-did-mount
                    mdl-init-mount
                    :component-did-update
                    mdl-init-mount})))
     ;; in clj, a no-op
     :clj component))

(defn wrap-dialog-polyfill [component]
  #?(:cljs
     (fn [& args]
       (let [{:keys [cancel-fn]} args
             cancel-fn (or cancel-fn #(.warn js/console "Unhandled cancel event for dialog."))]
         (into
          [(vary-meta component
                      (fn [meta-m]
                        (merge-with
                         juxt
                         {:component-did-mount
                          (fn [this]
                            (let [node (r/dom-node this)]
                              (.registerDialog js/dialogPolyfill node)
                              (.addEventListener node "cancel" (fn [e] (.preventDefault e) (cancel-fn e)))
                              (.showModal node)))
                          :component-will-unmount
                          (fn [this]
                            (.close (r/dom-node this)))})))] args)))
     ;; in clj, a no-op
     :clj component))
