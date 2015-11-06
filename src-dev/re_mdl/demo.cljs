(ns ^:figwheel-always re-mdl.demo
  (:require
   [re-mdl.core :as mdl]
   [reagent.core :as r]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (r/atom {:text "Hello world!"}))


(defn badge-demo []
  [:div.badge-demo
   [mdl/badge
    :badge-label "3"
    :child
    "Badge"]
   [mdl/badge
    :badge-label "3"
    :no-background? true
    :child
    "No Background"]
   [mdl/badge
    :badge-label "3"
    :overlap? true
    :child
    "Overlap"]
   [mdl/badge
    :badge-label "♥"
    :icon? true
    :child
    "account_box"]])

(defn button-demo []
  [:div.button-demo
   [mdl/button
    :label "Button"]
   [mdl/button
    :primary? true
    :label "Primary"]
   [mdl/button
    :raised? true
    :label "Raised"]
   [mdl/button
    :fab? true
    :label "Fab"]
   [mdl/button
    :mini-fab? true
    :label [:i.material-icons "add"]]
   [mdl/button
    :ripple-effect? true
    :label "Ripple"]])

(defn card-demo []
  [mdl/card
   :shadow 2
   :children
   [[mdl/card-title
     :expand? true
     :header :h4
     :text "Card"]
    [mdl/card-actions
     :border? true
     :children
     [[:a.mdl-button.mdl-button--colored.mdl-js-button.mdl-js-ripple-effect
       "Action"]
      ]]]])

(defn grid-demo [& kids]
  [mdl/grid
   :children
   (into []
         (for [kid kids]
           [mdl/cell
            :children
            [kid]]))])

(defn mega-footer-demo []
  [mdl/mega-footer
   :logo "Title"
   :middle
   [[mdl/mega-footer-dropdown
     :heading "Features"
     :children
     [[:a {:href "#"} "About"]
      [:a {:href "#"} "Terms"]
      [:a {:href "#"} "Partners"]
      [:a {:href "#"} "Updates"]]]
    [mdl/mega-footer-dropdown
     :heading "Details"
     :children
     [[:a {:href "#"} "Specs"]
      [:a {:href "#"} "Tools"]
      [:a {:href "#"} "Resources"]]]
    [mdl/mega-footer-dropdown
     :heading "Technology"
     :children
     [[:a {:href "#"} "How it works"]
      [:a {:href "#"} "Patterns"]
      [:a {:href "#"} "Usage"]
      [:a {:href "#"} "Products"]
      [:a {:href "#"} "Contracts"]]]
    [mdl/mega-footer-dropdown
     :heading "FAQ"
     :children
     [[:a {:href "#"} "Questions"]
      [:a {:href "#"} "Answers"]
      [:a {:href "#"} "Contact"]]]]
   :bottom
   [[mdl/mega-footer-link-list
    :children
    [[:a {:href "#"} "Help"]
     [:a {:href "#"} "Privacy & Terms"]]]]])

(defn mini-footer-demo []
  [mdl/mini-footer
   :logo "Title"
   :left
   [[mdl/mini-footer-link-list
     :children
     [[:a {:href "#"} "Help"]
      [:a {:href "#"} "Privacy & Terms"]]]]])

(defn tab-demo []
  [mdl/tabs
   :children
   [[mdl/tab-bar
     :children
     [[mdl/tab
      :href "#starks-panel"
       :is-active? true
       :content "Starks"]
      [mdl/tab
       :href "#lannisters-panel"
       :is-active? true
       :content "Lannisters"]
      [mdl/tab
       :href "#targaryens-panel"
       :is-active? true
       :content "Targaryens"]]]
    [mdl/tabs-panel
     :is-active? true
     :id "starks-panel"
     :children
     [[:ul
       [:li "Eddard"]
       [:li "Catelyn"]
       [:li "Rob"]]]]
    [mdl/tabs-panel
     :id "lannisters-panel"
     :children
     [[:ul
       [:li "Tywin"]
       [:li "Cersei"]
       [:li "Jamie"]]]]
    [mdl/tabs-panel
     :id "targaryens-panel"
     :children
     [[:ul
       [:li "Viserys"]
       [:li "Danerys"]]]]]])

(defn demo-layout [& {:keys [demo-map current-demo-ra children]}]
  [mdl/layout
   :fixed-header? true
   :children
   [[mdl/layout-header
     :children
     [[mdl/layout-header-row
       :children
       [[mdl/layout-title :label "Material Design Lite"]
        [mdl/layout-spacer]
        [mdl/layout-nav
         :children
         (into []
               (for [demo (keys demo-map)]
                 [mdl/layout-nav-link
                  :href "#"
                  :content (name demo)
                  :on-click (fn [e]
                              (reset! current-demo-ra demo))]))]]]]]
    [mdl/layout-drawer
     :children
     [[mdl/layout-title :label "Material Design Lite"]
      [mdl/layout-nav
       :children
       (into []
             (for [demo (keys demo-map)]
               [mdl/layout-nav-link
                :href "#"
                :content (name demo)
                :on-click (fn [e]
                            (reset! current-demo-ra demo))]))]]]
    [mdl/layout-content
     :children children]]])


(defn loading-progress-demo []
  (let [pct-done (r/atom 0)]
    (fn []
      [:div.loading-demo
       [mdl/loading-progress
        :indeterminate? true]
       [mdl/button
       :mini-fab? true
       :on-click #(swap! pct-done inc)
       :label [:i.material-icons "add"]]
      [mdl/loading-progress
       :model @pct-done
       ]])))

(defn loading-spinner-demo []
  [mdl/loading-spinner
   :is-active? true])

(defn menu-demo []
  [:div.menu-demo
   [mdl/button
    :id "menu-speed"
    :icon "more_vert"]
   [mdl/menu
    :for "menu-speed"
    :ripple-effect? true
    :children
    [[mdl/menu-item
      :label "Fast"]
     [mdl/menu-item
      :label "Medium"]
     [mdl/menu-item
      :label "Slow"]]]])


(defn slider-demo []
  [:div.slider-demo
   [:p
    {:width "300px"}
    [mdl/slider
     :id "foo"
     :init-val 4
     :min 0
     :max 10
     :step 2
     :handler-fn #(print "slider: " %)]]])


(defn toggles-demo []
  [:div.toggles-demo
   [mdl/toggle-checkbox
    :id "checkbox-demo"
    :checked? true
    :label "Checkbox"
    :handler-fn #(print "checkbox: " %)]
   [mdl/toggle-radios
    :name "radio-demo"
    :handler-fn #(print "radio: " %)
    :choices
    [[:foo "Foo"] [:bar "Bar"] [:baz "Baz"]]]
   [mdl/toggle-icon-toggle
    :id "icon-toggle-demo"
    :labels ["format_bold" "format_italic"]
    :ripple-effect? true
    :handler-fn #(print (str "icon-toggle: " %))]
   [mdl/toggle-switch
    :id "switch-demo"
    :label "On/Off"
    :ripple-effect? true
    :handler-fn #(print (str "switch: " %))]])

(defn table-demo []
  [:div.table-demo
   [mdl/table
    :id "table-demo"
    :selectable? true
    :headers
    [["Foo"] ["Bar"] ["Baz" :non-numeric true]]
    :rows
    [[3 6 "wat"]
     [7 3 "foo"]
     [0 12 "hey"]]]])


(defn text-field-demo []
  [:div.text-field-demo
   [grid-demo
    [mdl/textfield
    :label "Basic Text Field"
    :id "text-field-demo-basic"
    :handler-fn #(print "basic text field: " %)]
   [mdl/textfield
    :label "With Init Val"
    :id "text-field-demo-init-val"
    :handler-fn #(print "init-val text field: " %)
    :init-val "Foo"]
   [mdl/textfield
    :label "Basic Text Area"
    :id "text-field-demo-basic-textarea"
    :type :textarea
    :rows 2
    :handler-fn #(print "basic text area: " %)]
   [mdl/textfield
    :id "text-field-demo-numeric"
    :label "Numeric validation"
    :pattern "-?[0-9]*(\\.[0-9]+)?"
    :invalid-message "Must be a number, yo."
    :handler-fn #(print "numeric text field: " %)]
   [mdl/textfield
    :label "Floating Label"
    :id "text-field-demo-float"
    :handler-fn #(print "floating field: " %)
    :floating-label? true]
   [mdl/textfield
    :label "Expandable"
    :id "text-field-demo-expandable"
    :handler-fn #(print "expandable: " %)
    :expandable? true
    :expand-icon "search"]
   [mdl/textfield
    :label "Floating Multiline Textarea"
    :id "text-field-demo-multiline-textarea"
    :type :textarea
    :handler-fn #(print "multiline textarea: " %)
    :rows 2
    :maxrows 8]]])

(defn tooltip-demo []
  [:div.tooltip-demo
   [grid-demo
    [:div
     {:id "tooltip-demo-div"}
     "A Div"]
    [mdl/tooltip
     :for "tooltip-demo-div"
     :children ["Can have tooltips"]]

    [:p
     {:id "tooltip-demo-p"}
     "A p"]

    [mdl/tooltip
     :for "tooltip-demo-p"
     :children ["Can also have tooltips"]]

    [:span
     {:id "tooltip-demo-span"}
     "A span"]
    [mdl/tooltip
     :for "tooltip-demo-span"
     :large? true
     :children ["Giant tooltip!"]]]])

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
   :table table-demo
   :text-field text-field-demo
   :tooltip tooltip-demo
   ))

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
