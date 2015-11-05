(ns ^:figwheel-always re-mdl.demo
  (:require
   [re-mdl.core]
   [reagent.core :as r]
   [re-mdl.components.badge :refer [badge-demo]]
   [re-mdl.components.button :refer [button-demo]]
   [re-mdl.components.card :refer [card-demo]]
   [re-mdl.components.grid :refer [grid-demo]]
   [re-mdl.components.layout :refer [demo-layout
                                     tab-demo]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (r/atom {:text "Hello world!"}))


(defn app-view []
  [:div ;; extra wrapper div so mdl doesn't clobber the root
   [demo-layout
    [grid-demo
     [badge-demo]
     [button-demo]
     [card-demo]
     [tab-demo]]]])


(defn ^:export run []
  (r/render [app-view]
            (js/document.getElementById "app")))



(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  ;; (run)
  (run))

(run)
