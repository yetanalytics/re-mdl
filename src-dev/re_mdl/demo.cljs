(ns ^:figwheel-always re-mdl.demo
  (:require
   [re-mdl.core  :as mdl]
   [reagent.core :as r]
   [cljs.repl    :refer-macros [source]]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (r/atom {:text "Hello world!"}))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; HELPER COMPONENTS AND FUNCTIONS TO GENERATE DEMO COMPONENTS

(defn demo-doc
  "This function will generate the doc by forcing cljs.repl/source
  to return a string instead of printing."
  [doc]
  (with-redefs [println identity]
    (doc)))

(defn demo-doc-component
  "This reagent component generates all of the demo items and then the docs."
  [demo doc]
  [:div
   [:div.mdl-demo-container
    (for [d demo]
      ^{:key d} [:span.mdl-demo
                 d])]
   [:pre
    (clojure.string/join
     "\n\n"
     (mapv demo-doc doc))]])

(defn demo-options
  "This will take a title, a description, and vector of table rows to render."
  [{:keys [title description rows]
    :or   {title "OPTIONS"}}]
  [:div
   [:h6 title]
   [:p description]
   [mdl/table
    :class  "mdl-demo-options"
    :shadow 3
    :headers
    [["Key"      :non-numeric true]
     ["Effect"   :non-numeric true]
     ["Comments" :non-numeric true]]
    :rows
    rows]])

(defn demo-reference
  "This generates a small text component to show reference to MDL site."
  [component]
  [:p "Refer to the MDL "
   [:a {:href
        (str "https://getmdl.io/components/index.html#"
             component
             "-section")}
    component]
   " section for more information."])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; DEMOS BEGIN

(defn intro-demo []
  [:div.intro-demo
   [:h6 "INTRO"]
   [:p
    [:a {:href "https://github.com/yetanalytics/re-mdl"} "Re-mdl"]
    " gives you reusable components for use with Google's "
    [:a {:href "https://getmdl.io"} "Material Design Lite"]
    " in the style of "
    [:a {:href "https://github.com/Day8/re-com"} "re-com"]
    ". Re-mdl is a ClojureScript library that sits atop "
    [:a {:href "https://github.com/reagent-project/reagent"} "Reagent"]
    ". This demo is built using re-mdl to demonstrate and document it's use."]
   [demo-options
    {:title       "SHARED OPTIONS"
     :description "These are the shared options for all re-mdl components."
     :rows
     [[":id"    "The id for the HTML element"            "Optional; String if used"]
      [":class" "The class for the HTML element"         "Optional; String if used"]
      [":attr"  "Any other HTML attributes not provided" "You can also override existing attrs"]]}]
   [:h6 "CHANGE LOG"]
   [mdl/list-coll
    :children
    (into []
          (for [s ["Button"
                   "add card menu"
                   "fix textfields"
                   "docs and demo added to match mdl"
                   "chip and chip inners changed content to child"]]
            [mdl/list-item
             :children
             [[mdl/list-item-primary-content
               :content s]]]))]])

(defn badge-demo-number-icon
  "This badge is an icon that has an overlapped number."
  []
  [mdl/badge
   :badge-label "1"
   :icon?       true
   :overlap?    true
   :child       "account_box"])

(defn badge-demo-icon-icon
  "This badge is an icon with an overlapped icon."
  []
  [mdl/badge
   :badge-label "♥"
   :icon?       true
   :overlap?    true
   :child       "account_box"])

(defn badge-demo-number
  "This badge is text with a number."
  []
  [mdl/badge
   :badge-label "4"
   :child       "Inbox"])

(defn badge-demo-icon
  "This badge is text with an icon."
  []
  [mdl/badge
   :badge-label "♥"
   :child       "Mood"])

(defn badge-demo []
  [:div.badge-demo
   [:h6 "BADGES"]
   [:p "These are small status descriptors on UI elements."]
   [demo-doc-component
    [[badge-demo-number-icon]
     [badge-demo-icon-icon]]
    [#(source badge-demo-number-icon)
     #(source badge-demo-icon-icon)]]
   [demo-doc-component
    [[badge-demo-number]
     [badge-demo-icon]]
    [#(source badge-demo-number)
     #(source badge-demo-icon)]]
   [demo-options
    {:description "These are the options that can be applied to adapt the component's appearance."
     :rows
     [[":el"             "Container element for badge"         "Optional; Defaults to :span"]
      [":child"          "Content inside the :el"              "nil"]
      [":badge-label"    "String value on badge"               "Not a class, but an attribute"]
      [":no-background?" "Apply open-circle effect to badge"   "Optional"]
      [":overlap?"       "Make the badge overlap on child"     "Optional"]
      [":icon?"          "Make the child content and MDL icon" "Optional"]]}]
   [demo-reference "badges"]])

(defn button-demo-colored-fab
  "This is a colored FAB button."
  []
  [mdl/button
   :fab?     true
   :colored? true
   :label    [:i.material-icons "add"]])

(defn button-demo-colored-fab-with-ripple
  "This is a colored FAB button with a ripple effect."
  []
  [mdl/button
   :fab?           true
   :colored?       true
   :label          [:i.material-icons "add"]
   :ripple-effect? true])

(defn button-demo-plain-fab
  "This is a plain FAB button."
  []
  [mdl/button
   :fab?  true
   :label [:i.material-icons "add"]])

(defn button-demo-plain-fab-with-ripple
  "This is a plain FAB button with a ripple effect."
  []
  [mdl/button
   :fab?           true
   :label          [:i.material-icons "add"]
   :ripple-effect? true])

(defn button-demo-plain-fab-disabled
  "This is a plain FAB button that is disabled."
  []
  [mdl/button
   :fab?      true
   :label     [:i.material-icons "add"]
   :disabled? true])

(defn button-demo-raised
  "This is a raised button."
  []
  [mdl/button
   :raised? true
   :label   "Button"])

(defn button-demo-raised-with-ripple
  "This is a raised button with a ripple effect."
  []
  [mdl/button
   :raised?        true
   :label          "Button"
   :ripple-effect? true])

(defn button-demo-raised-disabled
  "This is a raised button that is disabled."
  []
  [mdl/button
   :raised?   true
   :label     "Button"
   :disabled? true])

(defn button-demo-colored-raised
  "This is a raised button that is colored."
  []
  [mdl/button
   :raised?  true
   :colored? true
   :label    "Button"])

(defn button-demo-accent-colored-raised
  "This is a raised button that is accent colored."
  []
  [mdl/button
   :raised? true
   :accent? true
   :label   "Button"])

(defn button-demo-accent-colored-raised-with-ripple
  "This is a raised button that is accent colored with a ripple."
  []
  [mdl/button
   :raised?        true
   :accent?        true
   :label          "Button"
   :ripple-effect? true])

(defn button-demo-flat
  "This is a flat button."
  []
  [mdl/button
   :label "Button"])

(defn button-demo-flat-with-ripple
  "This is a flat button with a ripple."
  []
  [mdl/button
   :label          "Button"
   :ripple-effect? true])

(defn button-demo-flat-disabled
  "This is a flat button that is disabled."
  []
  [mdl/button
   :label     "Button"
   :disabled? true])

(defn button-demo-primary-colored-flat
  "This is a flat button that is primary colored."
  []
  [mdl/button
   :label    "Button"
   :primary? true])

(defn button-demo-accent-colored-flat
  "This is a flat button that is accent colored."
  []
  [mdl/button
   :label   "Button"
   :accent? true])

(defn button-demo-icon
  "This is a button that is an icon."
  []
  [mdl/button
   :icon? true
   :label [:i.material-icons "mood"]])

(defn button-demo-colored-icon
  "This is a button that is an icon and colored."
  []
  [mdl/button
   :icon?    true
   :label    [:i.material-icons "mood"]
   :colored? true])

(defn button-demo-mini-fab
  "This is a mini FAB button."
  []
  [mdl/button
   :fab?      true
   :mini-fab? true
   :label     [:i.material-icons "add"]])

(defn button-demo-colored-mini-fab
  "This is a mini FAB button that is colored."
  []
  [mdl/button
   :fab?      true
   :mini-fab? true
   :colored?  true
   :label     [:i.material-icons "add"]])

(defn button-demo []
  [:div.button-demo
   [:h6 "BUTTONS"]
   [:p "These are a variation of Material Design buttons."]
   [demo-doc-component
    [[button-demo-colored-fab]
     [button-demo-colored-fab-with-ripple]]
    [#(source button-demo-colored-fab)
     #(source button-demo-colored-fab-with-ripple)]]
   [demo-doc-component
    [[button-demo-plain-fab]
     [button-demo-plain-fab-with-ripple]
     [button-demo-plain-fab-disabled]]
    [#(source button-demo-plain-fab)
     #(source button-demo-plain-fab-with-ripple)
     #(source button-demo-plain-fab-disabled)]]
   [demo-doc-component
    [[button-demo-raised]
     [button-demo-raised-with-ripple]
     [button-demo-raised-disabled]]
    [#(source button-demo-raised)
     #(source button-demo-raised-with-ripple)
     #(source button-demo-raised-disabled)]]
   [demo-doc-component
    [[button-demo-colored-raised]
     [button-demo-accent-colored-raised]
     [button-demo-accent-colored-raised-with-ripple]]
    [#(source button-demo-colored-raised)
     #(source button-demo-accent-colored-raised)
     #(source button-demo-accent-colored-with-ripple)]]
   [demo-doc-component
    [[button-demo-flat]
     [button-demo-flat-with-ripple]
     [button-demo-flat-disabled]]
    [#(source button-demo-flat)
     #(source button-demo-flat-with-ripple)
     #(source button-demo-flat-disabled)]]
   [demo-doc-component
    [[button-demo-primary-colored-flat]
     [button-demo-accent-colored-flat]]
    [#(source button-demo-primary-colored-flat)
     #(source button-demo-accent-colored-flat)]]
   [demo-doc-component
    [[button-demo-icon]
     [button-demo-colored-icon]]
    [#(source button-demo-icon)
     #(source button-demo-colored-icon)]]
   [demo-doc-component
    [[button-demo-mini-fab]
     [button-demo-colored-mini-fab]]
    [#(source button-demo-mini-fab)
     #(source button-demo-colored-mini-fab)]]
   [demo-options
    {:description "These are the options that can be applied to adapt the component's appearance."
     :rows
     [[":el"             "Container element for badge"         "Optional; Defaults to :button"]
      [":label"          "Content inside the :el"              "String or hiccup"]
      [":on-click"       "Callback to handle event"            "Optional"]
      [":icon?"          "Apply small circular effect"         "Optional"]
      [":disabled?"      "Disables action of a button"         "Optional"]
      [":raised?"        "Applies the raised effect"           "Optional"]
      [":fab?"           "Applies circular FAB effect"         "Optional"]
      [":mini-fab?"      "Applies smaller circular FAB effect" "Optional"]
      [":colored?"       "Applies colored effect"              "Optional"]
      [":primary?"       "Applies primary colored effect"      "Optional"]
      [":accent?"        "Applies accent colored effect"       "Optional"]
      [":ripple-effect?" "Applies ripple click effect"         "Optional"]
      [":for"            "Applies HTML for attribute"          "Optional; String is used"]]}]
   [demo-reference "buttons"]])

(defn card-demo-wide-with-share
  "This is a wide card with a share button."
  []
  [mdl/card
   :shadow   2
   :children
   [[mdl/card-title
     :header :h2
     :text   "Welcome"]
    [mdl/card-supporting-text
     :text "Lorem ipsum dolor sit amet, consectetur adipiscing elit.
           Mauris sagittis pellentesque lacus eleifend lacinia..."]
    [mdl/card-actions
     :border? true
     :children
     [[mdl/button
        :el             :a
        :colored?       true
        :ripple-effect? true
        :label          "Get Started"]]]]])

(defn card-demo-original
  []
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
       "Action"]]]]])

(defn card-demo []
  [:div.card-demo
   [:h6 "CARDS"]
   [:p "Self-contained pieces of paper with data."]
   [demo-doc-component
    [[card-demo-wide-with-share]
     [card-demo-original]]
    [#(cljs.repl/source card-demo-wide-with-share)
     #(cljs.repl/source card-demo-original)]]])

(defn chip-demo-basic
  "This is a basic chip."
  []
  [mdl/chip
   :children
   [[mdl/chip-text
     :child "Basic Chip"]]])

(defn chip-demo-deletable
  "This is a deletable chip."
  []
  [mdl/chip
   :deletable? true
   :children
   [[mdl/chip-text
     :child "Deletable Chip"]
    [mdl/chip-action
     :type     :button
     :on-click #(js/alert "Delete this chip!")
     :child    [:i.material-icons
                "cancel"]]]])

(defn chip-demo-button
  "This is a chip as a button."
  []
  [mdl/chip
   :button?  :button
   :on-click #(js/alert "You Clicked a Chip!")
   :children
   [[mdl/chip-text
     :child "Button Chip"]]])

(defn chip-demo-contact
  "This is a contact chip."
  []
  [mdl/chip
   :contact? true
   :children
   [[mdl/chip-contact
     :class "mdl-color--teal mdl-color-text--white"
     :child "A"]
    [mdl/chip-text
     :child "Contact Chip"]]])

(defn chip-demo-deletable-contact
  "This is a deletable contact chip."
  []
  [mdl/chip
   :contact?   true
   :deletable? true
   :children
   [[mdl/chip-contact
     :class "mdl-color--teal mdl-color-text--white"
     :child "B"]
    [mdl/chip-text
     :child "Deletable Chip"]
    [mdl/chip-action
     :el    :a
     :attr  {:href "#"}
     :child [:i.material-icons
             "cancel"]]]])

(defn chip-demo []
  [:div.chip-demo
   [:h6 "CHIPS"]
   [:p "Represent complex entities in small blocks."]
   [demo-doc-component
    [[chip-demo-basic]
     [chip-demo-deletable]
     [chip-demo-button]]
    [#(source chip-demo-basic)
     #(source chip-demo-deletable)
     #(source chip-demo-button)]]
   [demo-doc-component
    [[chip-demo-contact]
     [chip-demo-deletable-contact]]
    [#(source chip-demo-contact)
     #(source chip-demo-deletable-contact)]]
   [demo-options
    {:title       "SHARED OPTIONS"
     :description "These are the shared options for all of the chip elements."
     :rows
     [[":el"                "The HTML element for the chip" "Default to :span | Action defaults to :button"]
      [":child | :children" "The descendants of the chip"   "For the chip component this key is :children"]]}]
   [demo-options
    {:title       "CHIP OPTIONS"
     :description "These are the shared options for all of the chip elements."
     :rows
     [[":deletable?" "Allows the chip to be deleted"     "Optional"]
      [":contact?"   "Applies contact view to the chip"  "Optional"]
      [":button?"    "Turns the container into a button" "Optional; this element accepts keys"]]}]
   [demo-options
    {:title       "ACTION OPTIONS"
     :description "These are the shared options for all of the chip elements."
     :rows
     [[":on-click" "Callback function for button event" "Optional"]
      [":type"     "Sets the type of the button"        "Optional"]]}]
   [:h6 "TEXT OPTIONS"]
   [:p "Nothing unique"]
   [:h6 "CONTACT OPTIONS"]
   [:p "Nothing unique"]
   [demo-reference "chips"]])

(defn grid-demo [& kids]
  [mdl/grid
   :children
   (into []
         (for [kid kids]
           [mdl/cell
            :col 12
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

(defn demo-layout-component [{:keys [href demo title]
                              :or   {href "#"}}]
  [mdl/layout-nav-link
   :href     href
   :content  (clojure.string/capitalize (name title))
   :on-click (fn [_]
               (reset! demo title))])

(defn demo-layout [& {:keys [demo-map current-demo-ra children]}]
  [mdl/layout
   :fixed-header? true
   :children
   [[mdl/layout-header
     :children
     [[mdl/layout-header-row
       :children
       [[mdl/layout-title
         :label "Re-mdl"
         :attr  {:on-click (fn [_]
                             (reset! current-demo-ra :intro))
                 :style    {:cursor "pointer"}}]
        [mdl/layout-spacer]
        [mdl/layout-nav
         :children
         (into []
               (for [demo (keys (dissoc demo-map :intro))]
                 (demo-layout-component {:demo  current-demo-ra
                                         :title demo})))]]]]]
    [mdl/layout-drawer
     :children
     [[mdl/layout-title
       :label "Re-mdl"
       :attr  {:on-click (fn [_]
                           (reset! current-demo-ra :intro))
               :style    {:cursor "pointer"}}]
      [mdl/layout-nav
       :children
       (into []
             (for [demo (keys (dissoc demo-map :intro))]
               (demo-layout-component {:demo  current-demo-ra
                                       :title demo})))]]]
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
    :icon "format_bold"
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

(defn snackbar-demo []
  [:div.snackbar-demo
   [mdl/button
    :label "toast!"
    :on-click #(mdl/snackbar! :message
                              "hey there!"
                              :timeout
                              6000)]
   [mdl/snackbar-target]])

(defn dialog-demo []
  (let [open? (r/atom false)]
    (fn []
      [:div.dialog-demo
       [mdl/button
        :label "show-dialog"
        :on-click #(reset! open? true)]

       (when @open?
         [mdl/dialog
          :title "A Dialog"
          :content [[:p "some text"]
                    [:p "some more text"]]
          :actions [[mdl/button
                     :label "OK"
                     :on-click #(.log js/console "fine")]
                    [mdl/button
                     :label "CLOSE"
                     :on-click #(reset! open? false)
                     ]]
          :cancel-fn #(do
                        (.log js/console "Dialog cancelled!")
                        (reset! open? false))])])))

(defn list-demo []
  [mdl/grid
   :children
   [[mdl/cell
     :children
     [[:p "Simple list"]
      [mdl/list-coll
       :children
       (into []
             (for [s ["foo" "bar" "baz"]]
               [mdl/list-item
                :children
                [[mdl/list-item-primary-content
                  :content s]]]))]]]



    [mdl/cell
     :children
     [[:p "Icons"]
      [mdl/list-coll
       :children
       (into []
             (for [[i s] [["person" "foo"] ["people" "bar"] ["star" "baz"]]]
               [mdl/list-item
                :children
                [[mdl/list-item-primary-content
                  :icon i
                  :content s]]]))]]]

    [mdl/cell
     :children
     [[:p "Avatars"]

      [mdl/list-coll
       :children
       (into []
             (for [[i s] [["person" "foo"] ["people" "bar"] ["star" "baz"]]]
               [mdl/list-item
                :children
                [[mdl/list-item-primary-content
                  :avatar i
                  :content s]]]))]]]

    [mdl/cell
     :children
     [[:p "Actions"]

      [mdl/list-coll
       :children
       (into []
             (for [[i s] [["person" "foo"] ["people" "bar"] ["star" "baz"]]]
               [mdl/list-item
                :children
                [[mdl/list-item-primary-content
                  :avatar i
                  :content s]
                 [mdl/list-item-secondary-action
                  :href "#"
                  :el :a
                  :icon "star"]]]))]]]

    [mdl/cell
     :children
     [[:p "Input Actions"]

      [mdl/list-coll
       :children
       (into []
             (for [[i s c] [["person" "foo" [mdl/toggle-checkbox
                                             :id "checkbox-list-demo"
                                             :checked? true
                                             :label "Checkbox"
                                             :handler-fn #(print "checkbox: " %)]]
                            ["people" "bar" [mdl/toggle-radios
                                             :name "radio-list-demo"
                                             :handler-fn #(print "radio: " %)
                                             :choices
                                             [[:foo "Foo"] [:bar "Bar"] [:baz "Baz"]]]]
                            ["star" "baz" [mdl/toggle-switch
                                           :id "switch-list-demo"
                                           :label "On/Off"
                                           :ripple-effect? true
                                           :handler-fn #(print (str "switch: " %))]]]]
               [mdl/list-item
                :children
                [[mdl/list-item-primary-content
                  :avatar i
                  :content s]
                 [mdl/list-item-secondary-action
                  :el :span
                  :content c]]]))]]]

    [mdl/cell
     :children
     [[:p "Two Line"]

      [mdl/list-coll
       :children
       (into []
             (for [[i s subt] [["person" "foo" "subtitle"] ["people" "bar" "subtitle"] ["star" "baz" "subtitle"]]]
               [mdl/list-item
                :item-type :two-line
                :children
                [[mdl/list-item-primary-content
                  :avatar i
                  :content s
                  :children
                  [[mdl/list-item-sub-title
                    :content subt]]]
                 [mdl/list-item-secondary-action
                  :href "#"
                  :el :a
                  :icon "star"]]]))]]]

    [mdl/cell
     :children
     [[:p "Three Line"]

      [mdl/list-coll
       :children
       (into []
             (for [[i s txt] [["person" "foo" "longer text foo bar baz longer text foo bar baz"]
                              ["people" "bar" "longer text foo bar baz longer text foo bar baz"]
                              ["star" "baz" "longer text foo bar baz longer text foo bar baz"]]]
               [mdl/list-item
                :item-type :three-line
                :children
                [[mdl/list-item-primary-content
                  :avatar i
                  :content s
                  :children
                  [[mdl/list-item-text-body
                    :content txt]]]
                 [mdl/list-item-secondary-action
                  :href "#"
                  :el :a
                  :icon "star"]]]))]]]]])


(defn grid-spacing-demo []
  [mdl/grid
   :children
   (into []
         (for [i (range 12)]
           [mdl/cell
            :col 1
            :children ["1"]]))])

(def demo-map
  (sorted-map
   :intro            intro-demo
   :grid             grid-spacing-demo
   :badge            badge-demo
   :button           button-demo
   :card             card-demo
   :chip             chip-demo
   :tab              tab-demo
   :loading-progress loading-progress-demo
   :loading-spinner  loading-spinner-demo
   :menu             menu-demo
   :slider           slider-demo
   :toggles          toggles-demo
   :table            table-demo
   :text-field       text-field-demo
   :tooltip          tooltip-demo
   :snackbar         snackbar-demo
   :dialog           dialog-demo
   :list             list-demo))

(defn app-view []
  (let [current-demo (r/atom :intro)]
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
