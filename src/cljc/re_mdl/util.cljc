(ns re-mdl.util
  #?(:cljs (:require [reagent.core :as r])))

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
                 mdl-init-mount})))
     ;; in clj, a no-op
     :clj component))

(defn wrap-dialog-polyfill [component]
  #?(:cljs
     (vary-meta component
                (fn [meta-m]
                  (merge-with
                   juxt
                   {:component-did-mount
                    (fn [this]
                      (let [node (r/dom-node this)]
                        (.registerDialog js/dialogPolyfill node)
                        (.showModal node)))
                    :component-will-unmount
                    (fn [this]
                      (.close (r/dom-node this)))})))
     ;; in clj, a no-op
     :clj component))
