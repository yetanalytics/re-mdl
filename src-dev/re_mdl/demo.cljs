(ns ^:figwheel-always re-mdl.demo
  (:require
   [re-mdl.core]
   [reagent.core :as r]
   [re-mdl.components.badge :as badge]
   [re-mdl.components.button :as button]
   [re-mdl.components.card :as card]
   [re-mdl.components.grid :as grid]
   [re-mdl.components.layout :as layout]
   [re-mdl.components.loading :as loading]
   [re-mdl.components.menu :as menu]
   [re-mdl.components.slider :as slider]
   [re-mdl.components.toggle :as toggle]
   [re-mdl.components.table :as table]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (r/atom {:text "Hello world!"}))


(defn badge-demo []
  [:div.badge-demo
   [badge/badge
    :badge-label "3"
    :child
    "Badge"]
   [badge/badge
    :badge-label "3"
    :no-background? true
    :child
    "No Background"]
   [badge/badge
    :badge-label "3"
    :overlap? true
    :child
    "Overlap"]
   [badge/badge
    :badge-label "♥"
    :icon? true
    :child
    "account_box"]])

(defn button-demo []
  [:div.button-demo
   [button/button
    :label "Button"]
   [button/button
    :primary? true
    :label "Primary"]
   [button/button
    :raised? true
    :label "Raised"]
   [button/button
    :fab? true
    :label "Fab"]
   [button/button
    :mini-fab? true
    :label [:i.material-icons "add"]]
   [button/button
    :ripple-effect? true
    :label "Ripple"]])

(defn card-demo []
  [card/card
   :shadow 2
   :children
   [[card/title
     :expand? true
     :header :h4
     :text "Card"]
    [card/actions
     :border? true
     :children
     [[:a.mdl-button.mdl-button--colored.mdl-js-button.mdl-js-ripple-effect
       "Action"]
      ]]]])

(defn grid-demo [& kids]
  [grid/grid
   :children
   (into []
         (for [kid kids]
           [grid/cell
            :children
            [kid]]))])

(defn mega-footer-demo []
  [layout/mega-footer
   :logo "Title"
   :middle
   [[layout/mega-drop-down
     :heading "Features"
     :children
     [[:a {:href "#"} "About"]
      [:a {:href "#"} "Terms"]
      [:a {:href "#"} "Partners"]
      [:a {:href "#"} "Updates"]]]
    [layout/mega-drop-down
     :heading "Details"
     :children
     [[:a {:href "#"} "Specs"]
      [:a {:href "#"} "Tools"]
      [:a {:href "#"} "Resources"]]]
    [layout/mega-drop-down
     :heading "Technology"
     :children
     [[:a {:href "#"} "How it works"]
      [:a {:href "#"} "Patterns"]
      [:a {:href "#"} "Usage"]
      [:a {:href "#"} "Products"]
      [:a {:href "#"} "Contracts"]]]
    [layout/mega-drop-down
     :heading "FAQ"
     :children
     [[:a {:href "#"} "Questions"]
      [:a {:href "#"} "Answers"]
      [:a {:href "#"} "Contact"]]]]
   :bottom
   [[layout/mega-link-list
    :children
    [[:a {:href "#"} "Help"]
     [:a {:href "#"} "Privacy & Terms"]]]]])

(defn mini-footer-demo []
  [layout/mini-footer
   :logo "Title"
   :left
   [[layout/mini-link-list
     :children
     [[:a {:href "#"} "Help"]
      [:a {:href "#"} "Privacy & Terms"]]]]])

(defn tab-demo []
  [layout/tabs
   :children
   [[layout/tab-bar
     :children
     [[layout/tab
      :href "#starks-panel"
       :is-active? true
       :content "Starks"]
      [layout/tab
       :href "#lannisters-panel"
       :is-active? true
       :content "Lannisters"]
      [layout/tab
       :href "#targaryens-panel"
       :is-active? true
       :content "Targaryens"]]]
    [layout/tabs-panel
     :is-active? true
     :id "starks-panel"
     :children
     [[:ul
       [:li "Eddard"]
       [:li "Catelyn"]
       [:li "Rob"]]]]
    [layout/tabs-panel
     :id "lannisters-panel"
     :children
     [[:ul
       [:li "Tywin"]
       [:li "Cersei"]
       [:li "Jamie"]]]]
    [layout/tabs-panel
     :id "targaryens-panel"
     :children
     [[:ul
       [:li "Viserys"]
       [:li "Danerys"]]]]]])

(defn demo-layout [& {:keys [demo-map current-demo-ra children]}]
  [layout/layout
   :fixed-header? true
   :children
   [[layout/header
     :children
     [[layout/header-row
       :children
       [[layout/title :label "Material Design Lite"]
        [layout/spacer]
        [layout/nav
         :children
         (into []
               (for [demo (keys demo-map)]
                 [layout/nav-link
                  :href "#"
                  :content (name demo)
                  :on-click (fn [e]
                              (reset! current-demo-ra demo))]))]]]]]
    [layout/drawer
     :children
     [[layout/title :label "Material Design Lite"]
      [layout/nav
       :children
       (into []
             (for [demo (keys demo-map)]
               [layout/nav-link
                :href "#"
                :content (name demo)
                :on-click (fn [e]
                            (reset! current-demo-ra demo))]))]]]
    [layout/content
     :children children]]])


(defn loading-progress-demo []
  (let [pct-done (r/atom 0)]
    (fn []
      [:div.loading-demo
       [loading/progress
        :indeterminate? true]
       [button/button
       :mini-fab? true
       :on-click #(swap! pct-done inc)
       :label [:i.material-icons "add"]]
      [loading/progress
       :model @pct-done
       ]])))

(defn loading-spinner-demo []
  [loading/spinner
   :is-active? true])

(defn menu-demo []
  [:div.menu-demo
   [button/button
    :id "menu-speed"
    :icon "more_vert"]
   [menu/menu
    :for "menu-speed"
    :ripple-effect? true
    :children
    [[menu/item
      :label "Fast"]
     [menu/item
      :label "Medium"]
     [menu/item
      :label "Slow"]]]])


(defn slider-demo []
  [:div.slider-demo
   [:p
    {:width "300px"}
    [slider/slider
     :id "foo"
     :init-val 4
     :min 0
     :max 10
     :step 2
     :handler-fn #(print "slider: " %)]]])


(defn toggles-demo []
  [:div.toggles-demo
   [toggle/checkbox
    :id "checkbox-demo"
    :checked? true
    :label "Checkbox"
    :handler-fn #(print "checkbox: " %)]
   [toggle/radios
    :name "radio-demo"
    :handler-fn #(print "radio: " %)
    :choices
    [[:foo "Foo"] [:bar "Bar"] [:baz "Baz"]]]
   [toggle/icon-toggle
    :id "icon-toggle-demo"
    :labels ["format_bold" "format_italic"]
    :ripple-effect? true
    :handler-fn #(print (str "icon-toggle: " %))]
   [toggle/switch
    :id "switch-demo"
    :label "On/Off"
    :ripple-effect? true
    :handler-fn #(print (str "switch: " %))]])

(defn table-demo []
  [:div.table-demo
   [table/table
    :id "table-demo"
    :selectable? true
    :headers
    [["Foo"] ["Bar"] ["Baz" :non-numeric true]]
    :rows
    [[3 6 "wat"]
     [7 3 "foo"]
     [0 12 "hey"]]]])


(def demo-map
  (sorted-map
   :badge badge-demo
   :button button-demo
   :card card-demo
   :tab tab-demo
   :loading-progress loading-progress-demo
   :loading-spinner loading-spinner-demo
   :menu menu-demo
   :slider slider-demo
   :toggles toggles-demo
   :table table-demo))

(defn app-view []
  (let [current-demo (r/atom :badge)]
    (fn []
     [:div ;; extra wrapper div so mdl doesn't clobber the root
      [demo-layout
       :demo-map demo-map
       :current-demo-ra current-demo
       :children
       [[grid-demo
         [(@current-demo demo-map)]
        ]
       [mega-footer-demo]
       [mini-footer-demo]]]])))


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
