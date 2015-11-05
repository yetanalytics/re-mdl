(ns re-mdl.util
  (:require [reagent.core :as r]))

(defn mdl-init! [elem]
  (.upgradeElement js/componentHandler elem))

(defn mdl-init-mount [this-component]
  (mdl-init! (r/dom-node this-component)))

(defn wrap-mdl [component]
  (vary-meta component
             (fn [meta-m]
               (merge-with
                juxt
                {:component-did-mount
                 mdl-init-mount}))))
