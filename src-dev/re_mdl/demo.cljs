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
  [{:keys [title description rows]}]
  [:div
   [:h6 (str title " OPTIONS")]
   [:p description]
   (if rows
     [mdl/table
      :class  "mdl-demo-options"
      :shadow 3
      :headers
      [["Key"      :non-numeric true]
       ["Effect"   :non-numeric true]
       ["Comments" :non-numeric true]]
      :rows
      rows]
     "Nothing unique")])

(defn demo-reference
  "This generates a small text component to show reference to MDL site."
  [& [section sub-section]]
  [:p {:style {:padding-top "30px"}}
   "Refer to the MDL "
   [:a {:href
        (str "https://getmdl.io/components/index.html#"
             section
             "-section/"
             sub-section)}
    (or sub-section section)]
   " section for more information."])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; DEMOS BEGIN

(defn intro-demo
  "Intro page sharing common features and overview of re-mdl."
  []
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
    {:title       "SHARED"
     :description "These are the shared options for all re-mdl components."
     :rows
     [[":id"       "The id for the HTML element"            "Optional; String if used"]
      [":class"    "The class for the HTML element"         "Optional; String if used"]
      [":attr"     "Any other HTML attributes not provided" "You can also override existing attrs"]
      [":children" "Nested items for this component"        "Optional; Vector of components"]]}]
   [:h6 "CHANGE LOG"]
   [mdl/list-coll
    :children
    (into []
          (for [s ["Button"
                   "add card menu"
                   "docs and demo added to match mdl"
                   "chip and chip inners changed content to child"
                   "list 'content' renamed to 'child'"
                   "list 'children' added to remaining components"
                   "added el key to list component options where applicable"
                   "tooltip done"]]
            [mdl/list-item
             :children
             [[mdl/list-item-primary-content
               :child s]]]))]])

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
    {:title       "badge"
     :description "These are the options that can be applied to adapt the component's appearance."
     :rows
     [[":el"             "Container element for badge"         "Optional; Defaults to :span"]
      [":child"          "Content inside the :el"              "Required; String or hiccup"]
      [":badge-label"    "String value on badge"               "Not a class, but an attribute"]
      [":no-background?" "Apply open-circle effect to badge"   "Optional; boolean"]
      [":overlap?"       "Make the badge overlap on child"     "Optional; boolean"]
      [":icon?"          "Make the child content and MDL icon" "Optional; boolean"]]}]
   [demo-reference "badges"]])

(defn button-demo-colored-fab
  "This is a colored FAB button."
  []
  [mdl/button
   :fab?     true
   :colored? true
   :child    [:i.material-icons "add"]])

(defn button-demo-colored-fab-with-ripple
  "This is a colored FAB button with a ripple effect."
  []
  [mdl/button
   :fab?           true
   :colored?       true
   :child          [:i.material-icons "add"]
   :ripple-effect? true])

(defn button-demo-plain-fab
  "This is a plain FAB button."
  []
  [mdl/button
   :fab?  true
   :child [:i.material-icons "add"]])

(defn button-demo-plain-fab-with-ripple
  "This is a plain FAB button with a ripple effect."
  []
  [mdl/button
   :fab?           true
   :child          [:i.material-icons "add"]
   :ripple-effect? true])

(defn button-demo-plain-fab-disabled
  "This is a plain FAB button that is disabled."
  []
  [mdl/button
   :fab?      true
   :child     [:i.material-icons "add"]
   :disabled? true])

(defn button-demo-raised
  "This is a raised button."
  []
  [mdl/button
   :raised? true
   :child   "Button"])

(defn button-demo-raised-with-ripple
  "This is a raised button with a ripple effect."
  []
  [mdl/button
   :raised?        true
   :child          "Button"
   :ripple-effect? true])

(defn button-demo-raised-disabled
  "This is a raised button that is disabled."
  []
  [mdl/button
   :raised?   true
   :child     "Button"
   :disabled? true])

(defn button-demo-colored-raised
  "This is a raised button that is colored."
  []
  [mdl/button
   :raised?  true
   :colored? true
   :child    "Button"])

(defn button-demo-accent-colored-raised
  "This is a raised button that is accent colored."
  []
  [mdl/button
   :raised? true
   :accent? true
   :child   "Button"])

(defn button-demo-accent-colored-raised-with-ripple
  "This is a raised button that is accent colored with a ripple."
  []
  [mdl/button
   :raised?        true
   :accent?        true
   :child          "Button"
   :ripple-effect? true])

(defn button-demo-flat
  "This is a flat button."
  []
  [mdl/button
   :child "Button"])

(defn button-demo-flat-with-ripple
  "This is a flat button with a ripple."
  []
  [mdl/button
   :child          "Button"
   :ripple-effect? true])

(defn button-demo-flat-disabled
  "This is a flat button that is disabled."
  []
  [mdl/button
   :child     "Button"
   :disabled? true])

(defn button-demo-primary-colored-flat
  "This is a flat button that is primary colored."
  []
  [mdl/button
   :child    "Button"
   :primary? true])

(defn button-demo-accent-colored-flat
  "This is a flat button that is accent colored."
  []
  [mdl/button
   :child   "Button"
   :accent? true])

(defn button-demo-icon
  "This is a button that is an icon."
  []
  [mdl/button
   :icon? true
   :child [:i.material-icons "mood"]])

(defn button-demo-colored-icon
  "This is a button that is an icon and colored."
  []
  [mdl/button
   :icon?    true
   :child    [:i.material-icons "mood"]
   :colored? true])

(defn button-demo-mini-fab
  "This is a mini FAB button."
  []
  [mdl/button
   :fab?      true
   :mini-fab? true
   :child     [:i.material-icons "add"]])

(defn button-demo-colored-mini-fab
  "This is a mini FAB button that is colored."
  []
  [mdl/button
   :fab?      true
   :mini-fab? true
   :colored?  true
   :child     [:i.material-icons "add"]])

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
     #(source button-demo-accent-colored-raised-with-ripple)]]
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
    {:title       "button"
     :description "These are the options that can be applied to adapt the component's appearance."
     :rows
     [[":el"             "Container element for badge"         "Optional; Defaults to :button"]
      [":child"          "Content inside the :el"              "Required; String or hiccup"]
      [":on-click"       "Callback to handle event"            "Optional; function"]
      [":icon?"          "Apply small circular effect"         "Optional; boolean"]
      [":disabled?"      "Disables action of a button"         "Optional; boolean"]
      [":raised?"        "Applies the raised effect"           "Optional; boolean"]
      [":fab?"           "Applies circular FAB effect"         "Optional; boolean"]
      [":mini-fab?"      "Applies smaller circular FAB effect" "Optional; boolean"]
      [":colored?"       "Applies colored effect"              "Optional; boolean"]
      [":primary?"       "Applies primary colored effect"      "Optional; boolean"]
      [":accent?"        "Applies accent colored effect"       "Optional; boolean"]
      [":ripple-effect?" "Applies ripple click effect"         "Optional; boolean"]
      [":for"            "Applies HTML for attribute"          "Optional; :id of element"]]}]
   [demo-reference "buttons"]])

(defn card-demo-wide-with-share
  "This is a wide card with a share button."
  []
  [mdl/card
   :attr   {:style {:width  "512px"}}
   :shadow 2
   :children
   [[mdl/card-title
     :attr   {:style {:height     "176px"
                      :color      "#fff"
                      :background "url(http://www.getmdl.io/assets/demos/welcome_card.jpg) center / cover"}}
     :header :h2
     :child  "Welcome"]
    [mdl/card-supporting-text
     :children ["Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                 Mauris sagittis pellentesque lacus eleifend lacinia..."]]
    [mdl/card-actions
     :border? true
     :children
     [[mdl/button
       :el             :a
       :colored?       true
       :ripple-effect? true
       :child          "Get Started"]]]
    [mdl/card-menu
     :children [[mdl/button
                 :attr           {:style {:color "white"}}
                 :icon?          true
                 :ripple-effect? true
                 :child          [:i.material-icons "share"]]]]]])

(defn card-demo-square
  "This is a square card."
  []
  [mdl/card
   :attr   {:style {:width  "320px"
                    :height "320px"}}
   :shadow 2
   :children
   [[mdl/card-title
     :attr    {:style {:color      "#fff"
                       :background "url(http://www.getmdl.io/assets/demos/dog.png) bottom right 15% no-repeat #46B6AC"}}
     :header  :h2
     :child   "Update"
     :expand? true]
    [mdl/card-supporting-text
     :children ["Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                 Aenan convallis"]]
    [mdl/card-actions
     :border? true
     :children
     [[mdl/button
       :el             :a
       :colored?       true
       :ripple-effect? true
       :child          "View updates"]]]]])

(defn card-demo-expand
  "This is a card with expanded title."
  []
  [mdl/card
   :attr   {:style {:width      "256px"
                    :height     "256px"
                    :background "url(http://www.getmdl.io/assets/demos/image_card.jpg) center /cover"}}
   :shadow 2
   :children
   [[mdl/card-title
     :expand? true]
    [mdl/card-actions
     :attr {:style {:background "rgba(0, 0, 0, 0.2)"
                    :padding    "16px"
                    :height     "52px"}}
     :children
     [[:span
       {:style {:color       "#fff"
                :font-size   "14px"
                :font-weight "500"}}
       "Image.jpg"]]]]])

(defn card-demo-event
  "This is a card with expanded title for an event."
  []
  [mdl/card
   :attr   {:style {:width            "256px"
                    :height           "256px"
                    :background-color "#3E4EB8"}}
   :shadow 2
   :children
   [[mdl/card-title
     :attr    {:style {:align-items "flex-start"
                       :color       "#fff"}}
     :expand? true
     :children
     [[:h4 {:style {:margin-top "0px"}}
       "Featured event:" [:br]
       "May 24, 2016" [:br]
       "7-11pm"]]]
    [mdl/card-actions
     :attr    {:style {:border-color "rgba(255, 255, 255, 0.2)"
                       :display      "flex"
                       :box-sizing   "border-box"
                       :align-items  "center"
                       :color        "#fff"}}
     :border? true
     :children
     [[mdl/button
       :attr           {:style {:color "#fff"}}
       :el             :a
       :colored?       true
       :ripple-effect? true
       :child          "Add to Calender"]
      [mdl/layout-spacer]
      [:i.material-icons
       "event"]]]]])

(defn card-demo []
  [:div.card-demo
   [:h6 "CARDS"]
   [:p "Self-contained pieces of paper with data."]
   [demo-doc-component
    [[card-demo-wide-with-share]]
    [#(cljs.repl/source card-demo-wide-with-share)]]
   [demo-doc-component
    [[card-demo-square]]
    [#(source card-demo-square)]]
   [demo-doc-component
    [[card-demo-expand]
     [card-demo-event]]
    [#(source card-demo-expand)
     #(source card-demo-event)]]
   [demo-options
    {:title       "card"
     :description "These are properties that can be applied to the container card component."
     :rows
     [[":el"     "Container element for the card" "Optional; Defaults to :div"]
      [":shadow" "Shadow depth of the card"       "Optional; Valid options [2 3 4 6 8 16]"]]}]
   [demo-options
    {:title       "card-menu"
     :description "Component that renders in top-right of the card."
     :rows
     [[":el" "Container for element" "Optional; Defaults to :div"]]}]
   [demo-options
    {:title       "card-actions"
     :description "A container within a card element for action content."
     :rows
     [[":el"      "Container for element"   "Optional; Defaults to :div"]
      [":border?" "Defines a border on top" "Optional; boolean"]]}]
   [demo-options
    {:title       "card-supporting-text"
     :description "Additional content between the title and action."
     :rows
     [[":el"      "Container for element"   "Optional; Defaults to :div"]
      [":border?" "Defines a border on top" "Optional; boolean"]]}]
   [demo-options
    {:title       "card-media"
     :description "Defines element as a media container."
     :rows
     [[":el"      "Container for element"   "Optional; Defaults to :div"]
      [":border?" "Defines a border on top" "Optional; boolean"]]}]
   [demo-options
    {:title       "card-title"
     :description "Title content for the card. To use mdl-card__title-text style, supply :header and :child. Otherwise you can use :children."
     :rows
     [[":el"      "Container element for the title"                        "Optional; Defaults to :div"]
      [":child"   "Content of the :el"                                     "String or hiccup"]
      [":header"  "Defines what header value"                              "[h1 - h6]; Defaults to :h1"]
      [":border?" "Applies a border on top"                                "Optional; boolean"]
      [":expand?" "Defines the title container to use all available space" "Optional; boolean"]]}]
   [demo-options
    {:title       "card-subtitle"
     :description "Subtitle element. This should be a child of the title component."
     :rows
     [[":el"    "Container element for the subtitle" "Optional; Defaults to :div"]
      [":child" "Content inside of :el"              "String or hiccup"]]}]
   [demo-reference "cards"]])

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
     :attr  {:style {:background "url(https://placekitten.com/150/150) 0 0 / cover"}}]
    [mdl/chip-text
     :child "Deletable Contact Chip"]
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
    {:title       "SHARED"
     :description "These are the shared options for all of the chip elements."
     :rows
     [[":el"                "The HTML element for the chip" "Default to :span | Action defaults to :button"]
      [":child | :children" "The descendants of the chip"   "For the chip component this key is :children"]]}]
   [demo-options
    {:title       "chip"
     :description "These are options unique to the chip component, the overall wrapping component."
     :rows
     [[":deletable?" "Allows the chip to be deleted"     "Optional"]
      [":contact?"   "Applies contact view to the chip"  "Optional"]
      [":button?"    "Turns the container into a button" "Optional; this element accepts keys"]]}]
   [demo-options
    {:title       "chip-action"
     :description "These are the options for the action component, which is inside a chip."
     :rows
     [[":on-click" "Callback function for button event" "Optional"]
      [":type"     "Sets the type of the button"        "Optional"]]}]
   [demo-options
    {:title       "chip-text"
     :description "Text component rendered in the chip. Primary component."}]
   [demo-options
    {:title       "chip-contact"
     :description "Left-most information about the contact. Primarily image or icon."}]
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

(defn footer-demo-mega []
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

(defn footer-demo-mini []
  [mdl/mini-footer
   :logo "Title"
   :left
   [[mdl/mini-footer-link-list
     :children
     [[:a {:href "#"} "Help"]
      [:a {:href "#"} "Privacy & Terms"]]]]])

(defn footer-demo
  []
  [:div.footer-demo
   [:h6 "Footers"]
   [:p "Content that resides at the bottom of the page."]
   [demo-doc-component
    [[footer-demo-mega]]
    [#(source footer-demo-mega)]]
   [demo-doc-component
    [[footer-demo-mini]]
    [#(source footer-demo-mini)]]
   [demo-options]
   [demo-reference "layout" "footer"]])

(defn navigation-demo-transparent
  "This is a navigation layout with a transparent header."
  []
  [mdl/layout
   :attr          {:style {:width    "100%"
                           :position "relative"
                           :height   "300px"}}
   :children
   [[mdl/layout-header
     :transparent? true
     :children
     [[mdl/layout-header-row
       :children
       [[mdl/layout-title
         :label "Title"]
        [mdl/layout-spacer]
        [mdl/layout-nav
         :children
         (into []
               (for [i (range 4)]
                 ^{:key i} [mdl/layout-nav-link
                            :href    "#"
                            :content "Link"]))]]]]]
    [mdl/layout-drawer
     :children
     [[mdl/layout-title
       :label "Title"]
      [mdl/layout-nav
       :children
       (into []
             (for [i (range 4)]
               ^{:key i} [mdl/layout-nav-link
                          :href    "#"
                          :content "Link"]))]]]
    [mdl/layout-content]]])

(defn navigation-demo-fixed-drawer
  "This is a navigation layout with no header and a fixed drawer."
  []
  [mdl/layout
   :fixed-drawer? true
   :children
   [[mdl/layout-drawer
     :children
     [[mdl/layout-title
       :label "Title"]
      [mdl/layout-nav
       :children
       (into []
             (for [i (range 4)]
               ^{:key i} [mdl/layout-nav-link
                          :href    "#"
                          :content "Link"]))]]]
    [mdl/layout-content]]])

(defn navigation-demo
  []
  [:div.navigation-demo
   [:h6 "Navigation Layout"]
   [:p "Header and side drawers used for navigating a site."]
   [demo-doc-component
    [[navigation-demo-transparent]]
    [#(source navigation-demo-transparent)]]
   [demo-doc-component
    [[navigation-demo-fixed-drawer]]
    [#(source navigation-demo-fixed-drawer)]]
   [demo-options]
   [demo-reference "layout" "layout"]])

(defn tab-demo-content
  "This is a tab that has content."
  []
  [mdl/tabs
   :ripple-effect? true
   :children
   [[mdl/tab-bar
     :children
     [[mdl/tab
       :href       "#starks-panel"
       :is-active? true
       :child     "Starks"]
      [mdl/tab
       :href  "#lannisters-panel"
       :child "Lannisters"]
      [mdl/tab
       :href  "#targaryens-panel"
       :child "Targaryens"]]]
    [mdl/tabs-panel
     :is-active? true
     :id "starks-panel"
     :children
     [[:ul
       [:li "Eddard"]
       [:li "Catelyn"]
       [:li "Robb"]
       [:li "Sansa"]
       [:li "Brandon"]
       [:li "Arya"]
       [:li "Rickon"]]]]
    [mdl/tabs-panel
     :id "lannisters-panel"
     :children
     [[:ul
       [:li "Tywin"]
       [:li "Cersei"]
       [:li "Jamie"]
       [:li "Tyrion"]]]]
    [mdl/tabs-panel
     :id "targaryens-panel"
     :children
     [[:ul
       [:li "Viserys"]
       [:li "Danerys"]]]]]])

(defn tab-demo []
  [:div.tab-demo
   [:h6 "Tabs"]
   [:p "Collections of categorized content."]
   [demo-doc-component
    [[tab-demo-content]]
    [#(source tab-demo-content)]]
   [demo-options
    {:title       "tabs"
     :description "Outer container for the tabs."
     :rows
     [[":ripple-effect?" "Applies ripple click effect to the tab links" "Optional"]]}]
   [demo-options
    {:title       "tab-bar"
     :description "This is the container for the tab link bar."}]
   [demo-options
    {:title "tabs-panel"
     :description "This is a container that has the tab content. Link the :tab-bar anchors to the id of the :tabs-panel."
     :rows
     [[":el"         "The element type for the container"                "Optional; Defaults to :div"]
      [":is-active?" "This defines this container to be default display" "Optional"]]}]
   [demo-options
    {:title       "tab"
     :description "Defines the container for the anchor link of a tab."
     :rows
     [[":href"       "Anchor that matches :id of the :tabs-panel"          "String"]
      [":child"      "Content for the tab anchor"                          "String or hiccup"]
      [":is-active?" "This defines this tab as the default tab to display" "Optional"]]}]
   [demo-reference "layout" "tabs"]])

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
   :fixed-drawer? true
   :children
   [[mdl/layout-header
     :children
     [[mdl/layout-header-row
       :children
       [[mdl/layout-title
         :label (clojure.string/capitalize (name @current-demo-ra))]
        [mdl/layout-spacer]
        [mdl/layout-nav
         :children
         [[mdl/layout-nav-link
           :href    "https://github.com/yetanalytics/re-mdl"
           :content [:span [:i.material-icons "link"] " GitHub"]]]]]]]]
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

(defn loading-progress-demo-progress
  "This is a loading progress bar with some progress."
  []
  (let [progress-model (r/atom 44)]
    (fn []
      [:div
       [mdl/loading-progress
        :model @progress-model
        :attr  {:style {:width "250px"}}]
       [:p ":model " @progress-model]
       [mdl/slider
        :model      progress-model
        :handler-fn #(reset! progress-model (js/Number %))]])))

(defn loading-progress-demo-indeterminate
  "This is an indeterminate loading progress bar."
  []
  [mdl/loading-progress
   :indeterminate? true
   :attr           {:style {:width "250px"}}])

(defn loading-progress-demo-buffer
  "This is a loading progress bar with a buffer."
  []
  (let [progress-model  (r/atom 33)
        progress-buffer (r/atom 87)]
    (fn []
      [:div
       [mdl/loading-progress
        :model  progress-model
        :buffer progress-buffer
        :attr   {:style {:width "250px"}}]
       [:p ":model " @progress-model]
       [mdl/slider
        :model      progress-model
        :handler-fn #(reset! progress-model (js/Number %))]
       [:p ":buffer " @progress-buffer]
       [mdl/slider
        :model      progress-buffer
        :handler-fn #(reset! progress-buffer (js/Number %))]])))

(defn loading-progress-demo
  []
  [:div.loading-progress-demo
   [:h6 "Loading Progress Bar"]
   [:p "This is a loading progress bar for showing load times."]
   [demo-doc-component
    [[loading-progress-demo-progress]]
    [#(source loading-progress-demo-progress)]]
   [demo-doc-component
    [[loading-progress-demo-indeterminate]]
    [#(source loading-progress-demo-indeterminate)]]
   [demo-doc-component
    [[loading-progress-demo-buffer]]
    [#(source loading-progress-demo-buffer)]]
   [demo-options
    {:description "These are the properties that can change how the progress bar renders."
     :rows
     [[":model"          "This defines how much progress has been made"                    "Value | Atom"]
      [":buffer"         "This defines how much buffered data there is"                    "Value | Atom"]
      [":indeterminate?" "This applies an animation effect. :model and :buffer don't work" "Optional; Boolean"]]}]
   [demo-reference "loading" "progress"]])

(defn loading-spinner-demo-default
  "This is a default spinner."
  []
  [mdl/loading-spinner
   :is-active? true])

(defn loading-spinner-demo-single-color
  "This is a single colored spinner."
  []
  [mdl/loading-spinner
   :is-active?    true
   :single-color? true])

(defn loading-spinner-demo []
  [:div.loading-spinner-demo
   [:h6 "Loading Spinners"]
   [:p "This is a loading spinner for indeterminate load times."]
   [demo-doc-component
    [[loading-spinner-demo-default]
     [loading-spinner-demo-single-color]]
    [#(source loading-spinner-demo-default)
     #(source loading-spinner-demo-single-color)]]
   [demo-options
    {:description "These are the options for the loading spinner."
     :rows
     [[":el"            "Container element type for the spinner" "Optional; Defaults to :div"]
      [":is-active?"    "Makes the spinner visible and animate"  "Optional; Boolean"]
      [":single-color?" "Uses a single primary pallete"          "Optional; Boolean"]]}]
   [demo-reference "loading" "spinner"]])

(defn menu-demo-lower-left
  "This is a left-aligned menu that expands down."
  []
  [:div.mdl-demo-menu-container
   [:span
    [mdl/button
     :id    "menu-lower-left"
     :icon? true
     :child [:i.material-icons "more_vert"]]
    [mdl/menu
     :for            "menu-lower-left"
     :bottom-left?   true
     :ripple-effect? true
     :children
     [[mdl/menu-item
       :label "Some Action"]
      [mdl/menu-item
       :full-bleed-divider? true
       :label               "Another Action"]
      [mdl/menu-item
       :disabled? true
       :label     "Disabled Action"]
      [mdl/menu-item
       :label "Yet Another Action"]]]]])

(defn menu-demo-lower-right
  "This is a right-aligned menu that expands down."
  []
  [:div.mdl-demo-menu-container.mdl-shadow--2dp
   [:span.bar
    [mdl/button
     :id    "menu-lower-right"
     :icon? true
     :child [:i.material-icons "more_vert"]]
    [mdl/menu
     :for            "menu-lower-right"
     :bottom-right?  true
     :ripple-effect? true
     :children
     [[mdl/menu-item
       :label "Some Action"]
      [mdl/menu-item
       :label "Another Action"]
      [mdl/menu-item
       :disabled? true
       :label     "Disabled Action"]
      [mdl/menu-item
       :label "Yet Another Action"]]]]])

(defn menu-demo-top-left
  "This is a left aligned menu that expands up."
  []
  [:div.mdl-demo-menu-container
   [:span
    [mdl/button
     :id    "menu-top-left"
     :icon? true
     :child [:i.material-icons "more_vert"]]
    [mdl/menu
     :for            "menu-top-left"
     :top-left?      true
     :ripple-effect? true
     :children
     [[mdl/menu-item
       :label "Some Action"]
      [mdl/menu-item
       :full-bleed-divider? true
       :label               "Another Action"]
      [mdl/menu-item
       :disabled? true
       :label     "Disabled Action"]
      [mdl/menu-item
       :label "Yet Another Action"]]]]])

(defn menu-demo-top-right
  "This is a right aligned menu that expands up."
  []
  [:div.mdl-demo-menu-container
   [:span
    [mdl/button
     :id    "menu-top-right"
     :icon? true
     :child [:i.material-icons "more_vert"]]
    [mdl/menu
     :for            "menu-top-right"
     :top-right?     true
     :ripple-effect? true
     :children
     [[mdl/menu-item
       :label "Some Action"]
      [mdl/menu-item
       :full-bleed-divider? true
       :label               "Another Action"]
      [mdl/menu-item
       :disabled? true
       :label     "Disabled Action"]
      [mdl/menu-item
       :label "Yet Another Action"]]]]])

(defn menu-demo []
  [:div.menu-demo
   [:h6 "Menus"]
   [:p "Lists of clickable actions."]
   [demo-doc-component
    [[menu-demo-lower-left]
     [menu-demo-lower-right]]
    [#(source menu-demo-lower-left)
     #(source menu-demo-lower-right)]]
   [demo-doc-component
    [[menu-demo-top-left]
     [menu-demo-top-right]]
    [#(source menu-demo-top-left)
     #(source menu-demo-top-right)]]
   [demo-options]
   [demo-reference "menus"]])

(defn slider-demo-default
  "This is a slider that defaults to 0."
  []
  (let [slider-model 0]
    (fn []
      [mdl/slider
       :id         "slider-default"
       :model      slider-model
       :handler-fn #(print slider-model)
       :min        0
       :max        100])))

(defn slider-demo-starting
  "This is a slider with a starting value and tracks state."
  []
  (let [slider-model (r/atom 25)]
    (fn []
      [:div
       [mdl/slider
        :id         "slider-starting"
        :model      slider-model
        :handler-fn #(reset! slider-model (js/Number %))
        :min        0
        :max        100]
       [:p ":model " @slider-model]
       [mdl/button
        :child    [:i.material-icons "add"]
        :on-click #(swap! slider-model inc)]
       [mdl/button
        :child    [:i.material-icons "remove"]
        :on-click #(swap! slider-model dec)]])))

(defn slider-demo []
  [:div.slider-demo
   [:h6 "SLIDERS"]
   [:p "This allows you to select a value from a range."]
   [demo-doc-component
    [[slider-demo-default]
     [slider-demo-starting]]
    [#(source slider-demo-default)
     #(source slider-demo-starting)]]
   [demo-options
    {:description "Options for a slider."
     :rows
     [[":model"      "This is the current value of the slider"              "Value | Atom"]
      [":min"        "This is the minimum value on the slider"              "Optional; Defaults to 0"]
      [":max"        "This is the maximum value on the slider"              "Optional; Defaults to 100"]
      [":step"       "This is the step amount of the slider"                "Optional; Defaults to 1"]
      [":disabled?"  "This will disabled the slider input"                  "Optional; Boolean"]
      [":handler-fn" "A callback function to fire when the slider is moved" "Optional"]]}]
   [demo-reference "sliders"]])

(defn toggles-demo-checkbox-on
  "This is a checkbox with a r/atom for model. The slider drives its value."
  []
  (let [checked-model (r/atom false)
        slider-model  (r/atom 0)]
    (fn []
      [:div
       [mdl/toggle-checkbox
        :model          checked-model
        :label          "Checkbox"
        :ripple-effect? true
        :handler-fn     #(swap! checked-model not)]
       [mdl/slider
        :model      slider-model
        :max        10
        :handler-fn #(do (reset! slider-model (js/Number %))
                         (reset! checked-model (odd? @slider-model)))]])))

(defn toggles-demo-checkbox-off
  "This is a simple checkbox with no model."
  []
  [mdl/toggle-checkbox
   :label "Checkbox"])

(defn toggles-demo-radio
  "This is a radio with a r/atom for the model. The slider drives the value."
  []
  (let [radio-model  (r/atom :first)
        slider-model (r/atom 0)]
    (fn []
      [:div
       [mdl/toggle-radios
        :name  "radio-demo"
        :model radio-model
        :choices
        [[:first "First"] [:second "Second"] [:third "Third"]]]
       [mdl/slider
        :model      slider-model
        :max        10
        :handler-fn #(do (reset! slider-model (js/Number %))
                         (reset! radio-model (case (mod @slider-model 3)
                                               0 :first
                                               1 :second
                                               2 :third)))]])))

(defn toggles-demo-radio-children
  "This is a radio that is set with :children and not :choices."
  []
  [mdl/toggle-radios
   :name "radio-demo-children"
   :children
   (into []
         (for [[value label] [[:first "First"] [:second "Second"]]]
           [mdl/toggle-radio
            :name  "radio-demo-children"
            :value value
            :label label]))])

(defn toggles-demo-icon-on
  "This is an icon toggle that is initially on."
  []
  (let [checked-model (r/atom false)
        slider-model  (r/atom 0)]
    (fn []
      [:div
       [mdl/toggle-icon-toggle
        :model          checked-model
        :icon           "format_bold"
        :ripple-effect? true
        :handler-fn     #(swap! checked-model not)]
       [mdl/slider
        :model      slider-model
        :max        10
        :handler-fn #(do (reset! slider-model (js/Number %))
                         (reset! checked-model (odd? @slider-model)))]])))

(defn toggles-demo-icon-off
  "This is an icon toggle that is initially off."
  []
  [mdl/toggle-icon-toggle
   :icon "format_italic"])

(defn toggles-demo-switch-on
  "This is a switch that is initially on."
  []
  (let [checked-model (r/atom false)
        slider-model  (r/atom 0)]
    (fn []
      [:div
       [mdl/toggle-switch
        :model          checked-model
        :label          "Switch"
        :ripple-effect? true
        :handler-fn     #(swap! checked-model not)]
       [mdl/slider
        :model      slider-model
        :max        10
        :handler-fn #(do (reset! slider-model (js/Number %))
                         (reset! checked-model (odd? @slider-model)))]])))

(defn toggles-demo-switch-off
  "This is a switch that is initially off."
  []
  [mdl/toggle-switch
   :label "Switch"])

(defn toggles-demo []
  [:div.toggles-demo
   [:h6 "Toggles"]
   [:p "These let you choose between states."]
   [:h6 "Checkbox"]
   [demo-doc-component
    [[toggles-demo-checkbox-on]
     [toggles-demo-checkbox-off]]
    [#(source toggles-demo-checkbox-on)
     #(source toggles-demo-checkbox-off)]]
   [demo-options
    {:title       "toggle-checkbox"
     :description "These are the available props to modify a checkbox."
     :rows
     [[":model"          "Defines the sate of the checkbox"      "Value | Atom"]
      [":label"          "The label attached to the checkbox"    "Optional"]
      [":disabled?"      "Disables user interaction"             "Optional"]
      [":ripple-effect?" "Applies the ripple click effect"       "Optional"]
      [":handler-fn"     "Callback when the checkbox is clicked" "Optional"]]}]
   [demo-reference "toggles" "checkbox"]
   [:h6 "Radio"]
   [demo-doc-component
    [[toggles-demo-radio]
     [toggles-demo-radio-children]]
    [#(source toggles-demo-radio)
     #(source toggles-demo-radio-children)]]
   [demo-options
    {:title       "toggle-radios"
     :description "These are the available props to modify a radio group. You can let the radio group manage the choices for you by passing a vector of key value tuples under :choices. This lets the :radios manage a lot of the work for you. If you add any extra children this will override choices and you will need to manually inject the :radio components."
     :rows
     [[":model"          "Defines the sate of the checkbox"            "Value | Atom"]
      [":label"          "The label attached to the checkbox"          "Optional"]
      [":name"           "Group name for the radio group"              "String"]
      [":disabled"       "Disables user interaction for a given radio" "Optional; Set containing the key of the choice you want disabled. Only valid with :choices"]
      [":choices"        "Vector of key/value tuples for each radio"   "Cannot work with :children"]
      [":ripple-effect?" "Applies the ripple click effect"             "Optional"]
      [":handler-fn"     "Callback when the checkbox is clicked"       "Optional"]]}]
   [demo-options
    {:title       "toggle-radio"
     :description "These are the available props to modify a single radio component. If you set the radio group using :choices instead of :children then you won't need this. If you decide to set them in :children yourself then each element should be :radio with these settings. Using :choices is encouraged instead of managain :children yourself is encouraged."
     :rows
     [[":model"          "Defines the sate of the icon toggle" "Value | Atom"]
      [":name"           "Name of the matching radio group"    "String"]
      [":value"          "The value for this radio button"     "Anything"]
      [":label"          "The icon to render as the checkbox"  "Optional"]
      [":disabled?"      "Disables user interaction"           "Optional"]
      [":ripple-effect?" "Applies the ripple click effect"     "Optional"]
      [":handler-fn"     "Callback when the toggle is clicked" "Optional"]]}]
   [demo-reference "toggles" "radio"]
   [:h6 "Icon"]
   [demo-doc-component
    [[toggles-demo-icon-on]
     [toggles-demo-icon-off]]
    [#(source toggles-demo-icon-on)
     #(source toggles-demo-icon-off)]]
   [demo-options
    {:title       "toggle-icon-toggle"
     :description "These are the available props to modify a icon toggle."
     :rows
     [[":model"          "Defines the sate of the icon toggle" "Value | Atom"]
      [":icon"           "The icon to render as the checkbox"  "Optional"]
      [":disabled?"      "Disables user interaction"           "Optional"]
      [":ripple-effect?" "Applies the ripple click effect"     "Optional"]
      [":handler-fn"     "Callback when the toggle is clicked" "Optional"]]}]
   [demo-reference "toggles" "icon"]
   [:h6 "Switch"]
   [demo-doc-component
    [[toggles-demo-switch-on]
     [toggles-demo-switch-off]]
    [#(source toggles-demo-switch-on)
     #(source toggles-demo-switch-off)]]
   [demo-options
    {:title       "toggle-switch"
     :description "These are the available props to modify a switch toggle."
     :rows
     [[":model"          "Defines the sate of the switch toggle"   "Value | Atom"]
      [":label"          "The label attached to the switch toggle" "Optional"]
      [":disabled?"      "Disables user interaction"               "Optional"]
      [":ripple-effect?" "Applies the ripple click effect"         "Optional"]
      [":handler-fn"     "Callback when the switch is clicked"     "Optional"]]}]
   [demo-reference "toggles" "switch"]])

(defn table-demo-data
  "This is a table."
  []
  [mdl/table
   :selectable? true
   :shadow      2
   :headers
   [["Material" :non-numeric true] ["Quantity"] ["Unit price"]]
   :rows
   [["Acrylic (Transparent)" 25 "$2.90"]
    ["Plywood (Birch)" 50 "$1.25"]
    ["Laminate (Gold on Blue)" 10 "$2.35"]]])

(defn table-demo-data-ascending
  "This is a table where quantity is sorted ascending order."
  []
  [mdl/table
   :selectable? true
   :shadow      2
   :headers
   [["Material" :non-numeric true] ["Quantity" :ascending true] ["Unit price"]]
   :rows
   (into []
         (sort-by second
                  >
                  [["Acrylic (Transparent)" 25 "$2.90"]
                   ["Plywood (Birch)" 50 "$1.25"]
                   ["Laminate (Gold on Blue)" 10 "$2.35"]]))])

(defn table-demo
  []
  [:div.table-demo
   [:h6 "Tables"]
   [:p "Organize data"]
   [demo-doc-component
    [[table-demo-data]
     [table-demo-data-ascending]]
    [#(source table-demo-data)
     #(source table-demo-data-ascending)]]
   [demo-options
    {:description "The table component has a number of properties to manipulate to change the look."
     :rows
     [[":selectable?" "Applies and all/individual checkbox for rows"                  "Optional; Boolean"]
      [":headers"     "Header title for each column"                                  "Vector with header as first item"]
      [":non-numeric" "Sets the column formatting to non-numeric; left aligned"       "Optional; part of :header "]
      [":ascending"   "Sets formatting to header; does NOT actually sort the columns" "Optional; part of :header"]
      [":descending"  "Sets formatting to header; does NOT actually sort the columns" "Optional; part of :header"]
      [":rows"        "Text information matching up to the given headers"             "Vector"]
      [":shadow"      "Shadow effect on the table border."                            "Optional; Only [2 3 4 6 8 16] are valid"]]}]
   [demo-reference "tables"]])

(defn text-field-demo-text
  []
  [mdl/textfield
   :label       "Text..."])

(defn text-field-demo-numeric
  []
  (let [text-field-model (r/atom 5)]
    (fn []
      [:div
       [mdl/textfield
        :model           text-field-model
        :label           "Number..."
        :pattern         "-?[0-9]*(\\.[0-9]+)?"
        :invalid-message "Input is not a number!"
        :handler-fn      #(reset! text-field-model (js/Number %))]
       [mdl/slider
        :model      text-field-model
        :handler-fn #(reset! text-field-model (js/Number %))]])))

(defn text-field-demo-floating-text
  []
  [mdl/textfield
   :floating-label? true
   :label           "Text..."])

(defn text-field-demo-floating-numeric
  []
  [mdl/textfield
   :floating-label? true
   :label           "Number..."
   :pattern         "-?[0-9]*(\\.[0-9]+)?"
   :invalid-message "Input is not a number!"])

(defn text-field-demo-multiple-line
  []
  (let [text-field-model (r/atom 5)]
    (fn []
      [:div
       [mdl/textfield
        :type       :textarea
        :model      text-field-model
        :label      "Text lines..."
        :rows       3
        :handler-fn #(reset! text-field-model (js/Number %))]
       [mdl/slider
        :model      text-field-model
        :handler-fn #(reset! text-field-model (js/Number %))]])))

(defn text-field-demo-expanding
  []
  [mdl/textfield
   :label       "Expandable"
   :id          "text-field-demo-expandable"
   :expandable? true
   :expand-icon "search"])

(defn text-field-demo []
  [:div.text-field-demo
   [:h6 "Text Fields"]
   [:p "Textual input components"]
   [demo-doc-component
    [[text-field-demo-text]
     [text-field-demo-numeric]]
    [#(source text-field-demo-text)
     #(source text-field-demo-numeric)]]
   [demo-doc-component
    [[text-field-demo-floating-text]
     [text-field-demo-floating-numeric]]
    [#(source text-field-demo-floating-text)
     #(source text-field-demo-floating-numeric)]]
   [demo-doc-component
    [[text-field-demo-multiple-line]
     [text-field-demo-expanding]]
    [#(source text-field-demo-multiple-line)
     #(source text-field-demo-expanding)]]
   [demo-options]
   [demo-reference "textfields"]])

(defn tooltip-demo-simple
  "This is a simple tooltip."
  []
  [:span
   [:div#tooltip-simple.material-icons
    "add"]
   [mdl/tooltip
    :for      "tooltip-simple"
    :children ["Follow"]]])

(defn tooltip-demo-large
  "This is a large tooltip."
  []
  [:span
   [:div#tooltip-large.material-icons
    "print"]
   [mdl/tooltip
    :for      "tooltip-large"
    :large?   true
    :children ["Print"]]])

(defn tooltip-demo-rich
  "This is a rich tooltip."
  []
  [:span
   [:div#tooltip-rich.material-icons
    "cloud_upload"]
   [mdl/tooltip
    :for      "tooltip-rich"
    :children ["Upload "
               [:b "file.zip"]]]])

(defn tooltip-demo-multiline
  "This is a multilined tooltip."
  []
  [:span
   [:div#tooltip-multiline.material-icons
    "share"]
   [mdl/tooltip
    :for      "tooltip-multiline"
    :children ["Share your content"
               [:br]
               "via social media"]]])

(defn tooltip-demo-right
  "This is a right aligned tooltip."
  []
  [:span
   [:div#tooltip-right.material-icons
    "arrow_forward"]
   [mdl/tooltip
    :for      "tooltip-right"
    :right?   true
    :children ["Follow"]]])

(defn tooltip-demo-left
  "This is a left aligned tooltip."
  []
  [:span
   [:div#tooltip-left.material-icons
    "arrow_back"]
   [mdl/tooltip
    :for      "tooltip-left"
    :left?    true
    :children ["Follow"]]])

(defn tooltip-demo-top
  "This is a top aligned tooltip."
  []
  [:span
   [:div#tooltip-top.material-icons
    "arrow_upward"]
   [mdl/tooltip
    :for      "tooltip-top"
    :top?     true
    :children ["Follow"]]])

(defn tooltip-demo-bottom
  "This is a bottom aligned tooltip."
  []
  [:span
   [:div#tooltip-bottom.material-icons
    "arrow_downward"]
   [mdl/tooltip
    :for      "tooltip-bottom"
    :bottom?  true
    :children ["Follow"]]])

(defn tooltip-demo []
  [:div.tooltip-demo
   [:h6 "TOOLTIPS"]
   [:p "Useful information on hover-over."]
   [demo-doc-component
    [[tooltip-demo-simple]
     [tooltip-demo-large]]
    [#(source tooltip-demo-simple)
     #(source tooltip-demo-large)]]
   [demo-doc-component
    [[tooltip-demo-rich]
     [tooltip-demo-multiline]]
    [#(source tooltip-demo-rich)
     #(source tooltip-demo-multiline)]]
   [demo-doc-component
    [[tooltip-demo-right]
     [tooltip-demo-left]
     [tooltip-demo-top]
     [tooltip-demo-bottom]]
    [#(source tooltip-demo-right)
     #(source tooltip-demo-left)
     #(source tooltip-demo-top)
     #(source tooltip-demo-bottom)]]
   [demo-options
    {:description "These are the options that can be applied to change the tooltip appearance."
     :rows
     [[":el"      "Container element for tooltip"       "Optional; Defaults to :span"]
      [":for"     "ID of target element to hover over"  "Required"]
      [":large?"  "Defines a larget tooltip"            "Optional; Boolean"]
      [":left?"   "Specifies a left aligned tooltip"    "Optional; Boolean"]
      [":right?"  "Specifies a right aligned tooltip"   "Optional; Boolean"]
      [":top?"    "Specifies a top aligned tooltip"     "Optional; Boolean"]
      [":bottom?" "Specifies a bottom aligned tooltip"  "Optional; Boolean"]]}]
   [demo-reference "tooltips"]])

(defn snackbar-demo-color
  "This is a snackbar that changes the button color."
  []
  (let [model-color (r/atom "")]
    (fn []
      [:span
       [mdl/button
        :raised?  true
        :child    "show"
        :attr     {:style {:background-color @model-color}}
        :on-click (fn []
                    (reset! model-color (str "#" (.toString
                                                  (.floor js/Math
                                                          (* (rand) 0xFFFFFF))
                                                  16)))
                    (mdl/snackbar! :message        "Button color changed"
                                   :timeout        2000
                                   :action-handler #(reset! model-color "")
                                   :action-text    "Undo"))]
       [mdl/snackbar-target]])))

(defn snackbar-demo-toast
  "This is a snackbar that updates a counter."
  []
  (let [model-count (r/atom 0)]
    (fn []
      [:span
       [mdl/button
        :raised?  true
        :child    "show"
        :on-click (fn []
                    (swap! model-count inc)
                    (mdl/snackbar! :message (str "Example Message #" @model-count)))]
       [mdl/snackbar-target]])))

(defn snackbar-demo
  []
  [:div.snackbar-demo
   [:h6 "SNACKBAR"]
   [:p "Transient popup notifications"]
   [demo-doc-component
    [[snackbar-demo-color]]
    [#(source snackbar-demo-color)]]
   [demo-doc-component
    [[snackbar-demo-toast]]
    [#(source snackbar-demo-toast)]]
   [demo-options
    {:title       "snackbar"
     :description "These are the options for the snackbar."
     :rows
     [[":message"        "The text message to display"                   "String"]
      [":timeout"        "The amount of time in ms to show the snackbar" "Optional; Defaults to 2750"]
      [":action-handler" "The callback for when action is clicked"       "Optional"]
      [":action-text"    "The text to display for the action button"     "Required if :action-handler is provided"]]}]
   [demo-reference "snackbar"]])

(defn dialog-demo-simple
  "This is a dialog demo."
  []
  (let [open? (r/atom false)]
    (fn []
      [:span
       [mdl/button
        :child    "Show Dialog"
        :raised?  true
        :on-click #(reset! open? true)]
       (when @open?
         [mdl/dialog
          :children
          [[mdl/dialog-title
            :child "MDL Dialog"]
           [mdl/dialog-content
            :children
            [[:p "This is an example of the Material Design Lite
                        Lite dialog component. Please use responsibly."]]]
           [mdl/dialog-actions
            :children
            [[mdl/button
              :child    "Close"
              :on-click #(reset! open? false)]
             [mdl/button
              :child     "Disabled Action"
              :disabled? true]]]]])])))

(defn dialog-demo
  []
  [:div.dialog-demo
   [:h6 "Dialogs"]
   [:p "Modal windows for dedicated user input."]
   [demo-doc-component
    [[dialog-demo-simple]]
    [#(source dialog-demo-simple)]]
   [demo-options
    {:title       "dialog"
     :description "This is the outer-most container for the dialog box."}]
   [demo-options
    {:title       "dialog-actions"
     :description "This is the actions container for the dialog box."
     :rows
     [[":el"          "Container element for the actions"                                "Optional; Defaults to :div"]
      [":full-width?" "Sets the actions to be on their own line and take the full width" "Optional; Boolean"]]}]
   [demo-options
    {:title       "dialog-content"
     :description "This is the content container for the dialog box."
     :rows
     [[":el" "Container for the content" "Optional; Defaults to :div"]]}]
   [demo-options
    {:title       "dialog-title"
     :description "This is the title container of the dialog box."
     :rows
     [[":el"    "Container type for the content" "Optional; Defaults to :h3"]
      [":child" "Content of the title"           "String or hiccup"]]}]
   [demo-reference "dialog"]])

(defn list-demo-simple
  "This is a simple list."
  []
  [mdl/list-coll
   :class "mdl-demo-list"
   :children
   (into []
         (for [s ["Bryan Cranston"
                  "Aaron Paul"
                  "Bob Odenkirk"]]
           ^{:key s} [mdl/list-item
                      :children
                      [[mdl/list-item-primary-content
                        :child s]]]))])

(defn list-demo-icon
  "This is a list that has icons."
  []
  [mdl/list-coll
   :class "mdl-demo-list"
   :children
   (into []
         (for [[i s] [["person" "Bryan Cranston"]
                      ["person" "Aaron Paul"]
                      ["person" "Bob Odenkirk"]]]
           ^{:key s} [mdl/list-item
                      :children
                      [[mdl/list-item-primary-content
                        :icon  i
                        :child s]]]))])

(defn list-demo-avatar-and-action
  "This is a list that has avatars and actions."
  []
  [mdl/list-coll
   :class "mdl-demo-list"
   :children
   (into []
         (for [[a s] [["person" "Bryan Cranston"]
                      ["person" "Aaron Paul"]
                      ["person" "Bob Odenkirk"]]]
           ^{:key s} [mdl/list-item
                      :children
                      [[mdl/list-item-primary-content
                        :avatar a
                        :child  s]
                       [mdl/list-item-secondary-content
                        :children
                        [[mdl/list-item-secondary-action
                          :href  "#"
                          :el    :a
                          :child [:i.material-icons "star"]]]]]]))])

(defn list-demo-avatar-and-controls
  "This is a list that has avatars and controls."
  []
  [mdl/list-coll
   :class "mdl-demo-list"
   :children
   (into []
         (for [[a s c] [["person" "Bryan Cranston" [mdl/toggle-checkbox
                                                    :ripple-effect? true
                                                    :checked?       true
                                                    :handler-fn     #(print "hmm")]]
                        ["person" "Aaron Paul"     [mdl/toggle-radios
                                                    :ripple-effect? true
                                                    :handler-fn     #(print "why")
                                                    :choices
                                                    [[:test ""]]]]
                        ["person" "Bob Odenkirk"   [mdl/toggle-switch
                                                    :ripple-effect? true
                                                    :handler-fn     #(print "ugh")]]]]
           ^{:key s} [mdl/list-item
                      :children
                      [[mdl/list-item-primary-content
                        :avatar a
                        :child  s]
                       [mdl/list-item-secondary-content
                        :children
                        [[mdl/list-item-secondary-action
                          :el    :span
                          :child c]]]]]))])

(defn list-demo-subtitle-with-secondary-info-and-action
  "This is a list that has subtitles and secondary info with an action."
  []
  [mdl/list-coll
   :class "mdl-demo-list"
   :children
   (into []
         (for [[a s t i] [["person" "Bryan Cranston" "62 Episodes" "Actor"]
                          ["person" "Aaron Paul"     "62 Episodes"]
                          ["person" "Bob Odenkirk"   "62 Episodes"]]]
           ^{:key s} [mdl/list-item
                         :item-type :two-line
                         :children
                         [[mdl/list-item-primary-content
                           :avatar a
                           :child  s
                           :children
                           [[mdl/list-item-sub-title
                             :child t]]]
                          [mdl/list-item-secondary-content
                           :children
                           [[mdl/list-item-secondary-info
                             :child i]
                            [mdl/list-item-secondary-action
                             :href  "#"
                             :el    :a
                             :child [:i.material-icons "star"]]]]]]))])

(defn list-demo-three-line
  "This is a list that has a three line subtitle."
  []
  [mdl/list-coll
   :class "mdl-demo-list-three"
   :children
   (into []
         (for [[a s t i] [["person" "Bryan Cranston"
                           "Bryan Cranston played the role of Walter in Breaking Bad. He is also know for playing Hal in Malcolm in the Middle."]
                          ["person" "Aaron Paul"
                           "Aaron Paul played the role of Jesse in Breaking Bad. He also featured in the \"Need for Speed\" Movie."]
                          ["person" "Bob Odenkirk"
                           "Bob Odenkirk played the role of Saul in Breaking Bad. Due to public fondness for the character, Bob stars in his own show now, called \"Better Call Saul\"."]]]
           ^{:key s} [mdl/list-item
                         :item-type :three-line
                         :children
                         [[mdl/list-item-primary-content
                           :avatar a
                           :child  s
                           :children
                           [[mdl/list-item-text-body
                             :child t]]]
                          [mdl/list-item-secondary-content
                           :children
                           [[mdl/list-item-secondary-action
                             :href  "#"
                             :el    :a
                             :child [:i.material-icons "star"]]]]]]))])

(defn list-demo []
  [:div.list-demo
   [:h6 "LISTS"]
   [:p "Customizable scrollable lists."]
   [demo-doc-component
    [[list-demo-simple]]
    [#(source list-demo-simple)]]
   [demo-doc-component
    [[list-demo-icon]]
    [#(source list-demo-icon)]]
   [demo-doc-component
    [[list-demo-avatar-and-action]]
    [#(source list-demo-avatar-and-action)]]
   [demo-doc-component
    [[list-demo-avatar-and-controls]]
    [#(source list-demo-avatar-and-controls)]]
   [demo-doc-component
    [[list-demo-subtitle-with-secondary-info-and-action]]
    [#(source list-demo-subtitle-with-secondary-info-and-action)]]
   [demo-doc-component
    [[list-demo-three-line]]
    [#(source list-demo-three-line)]]
   [demo-options
    {:title       "list-coll"
     :description "This is the parent :ul container for the list."}]
   [demo-options
    {:title       "list-item"
     :description "This is the individual :li item for a list."
     :rows
     [[":item-type" "Defines how many lines the item should be." "Options: :one-line, :two-line, :three-line; defaults to :one-line"]]}]
   [demo-options
    {:title       "list-item-primary-content"
     :description "This containing element will house the primary portion of the list item."
     :rows
     [[":el"     "The container element type for the content"             "Optional; Defaults to :span"]
      [":icon"   "This is the icon that is left aligned to the content"   "Optional; Cannot use with :avatar"]
      [":avatar" "This is the avatar that is left aligned to the content" "Optional; Cannot use with :icon"]
      [":child"  "This is the main content of the list item"              "String or hiccup"]]}]
   [demo-options
    {:title       "list-item-sub-title"
     :description "This is the subtitle that will render within the primary content. Requires :item-type to be :two-line."
     :rows
     [[":el"    "The container element type for the content" "Optional; Defaults to :span"]
      [":child" "This is the content inside the container"   "String or hiccup"]]}]
   [demo-options
    {:title       "list-item-text-body"
     :description "This will render a two lined subtitle. Requires :item-type to be :three-line."
     :rows
     [[":el"    "The container element type for the content" "Optional; Defaults to :span"]
      [":child" "This is the content inside the container"   "String or hiccup"]]}]
   [demo-options
    {:title       "list-item-secondary-content"
     :description "This is the container for the secondary content sub-division. Requires :two-lines or :three-lines set on the :list-item component."
     :rows
     [[":el"    "The container element type for the content" "Optional; Defaults to :span"]
      [":child" "This is the content inside the container"   "String or hiccup"]]}]
   [demo-options
    {:title       "list-item-secondary-info"
     :description "This defines the information sub-division. Requires :two-lines or :three-lines set on the :list-item component."
     :rows
     [[":el"    "The container element type for the content" "Optional; Defaults to :span"]
      [":child" "This is the content inside the container"   "String or hiccup"]]}]
   [demo-options
    {:title       "list-item-secondary-action"
     :description "This defines the action sub-division. Requires :two-lines or :three-lines set on the :list-item component."
     :rows
     [[":el"    "The container element type for the content" "Optional; Defaults to :span"]
      [":child" "This is the content inside the container"   "String or hiccup"]
      [":href"  "Address for link to point to."              "Optional; requires :el to be set to :a"]]}]
   [demo-reference "lists"]])

(defn grid-demo-small
  "This is a responsive grid demo of small cells."
  []
  (let [style {:box-sizing       "border-box"
               :background-color "#BDBDBD"
               :height           "50px"
               :padding-left     "8px"
               :padding-top      "4px"
               :color            "white"}]
    (mdl/grid
     :children
     (into []
           (for [i (range 12)]
             ^{:key i} [mdl/cell
                        :attr     {:style style}
                        :col      1
                        :children ["1"]])))))

(defn grid-demo-medium
  "This is a responsive grid demo of medium cells."
  []
  (let [style {:box-sizing       "border-box"
               :background-color "#BDBDBD"
               :height           "200px"
               :padding-left     "8px"
               :padding-top      "4px"
               :color            "white"}]
    (mdl/grid
     :children
     (into []
           (for [i (range 3)]
             ^{:key i} [mdl/cell
                        :attr     {:style style}
                        :col      4
                        :children ["4"]])))))

(defn grid-demo-mixed
  "This is a responsive grid demo with mixed sized cells."
  []
  (let [style {:box-sizing       "border-box"
               :background-color "#BDBDBD"
               :height           "200px"
               :padding-left     "8px"
               :padding-top      "4px"
               :color            "white"}]
    [mdl/grid
     :children
     [[mdl/cell
       :attr     {:style style}
       :col      6
       :children ["6"]]
      [mdl/cell
       :attr     {:style style}
       :col      4
       :children ["4"]]
      [mdl/cell
       :attr     {:style style}
       :col      2
       :children ["2"]]]]))

(defn grid-demo-device
  "This is a responsive grid demo for mobile device modes."
  []
  (let [style {:box-sizing       "border-box"
               :background-color "#BDBDBD"
               :height           "200px"
               :padding-left     "8px"
               :padding-top      "4px"
               :color            "white"}]
    [mdl/grid
     :children
     [[mdl/cell
       :attr     {:style style}
       :col      6
       :tablet   {:col 8}
       :children ["6 (8 tablet)"]]
      [mdl/cell
       :attr     {:style style}
       :col      4
       :tablet   {:col 6}
       :children ["4 (6 tablet)"]]
      [mdl/cell
       :attr     {:style style}
       :col      2
       :phone    {:col 4}
       :children ["2 (4 phone)"]]]]))

(defn grid-spacing-demo
  []
  [:div.grid-demo
   [:h6 "Grids"]
   [:p "Responsive grid layouts."]
   [demo-doc-component
    [[grid-demo-small]
     [grid-demo-medium]
     [grid-demo-mixed]
     [grid-demo-device]]
    [#(source grid-demo-small)
     #(source grid-demo-medium)
     #(source grid-demo-mixed)
     #(source grid-demo-device)]]
   [demo-options
    {:title       "grid"
     :description "This is the container for the grid component."
     :rows
     [[":no-spacing?" "This will modify the cells so there is no space" "Optional"]]}]
   [demo-options
    {:title       "cell"
     :description "These are containers for individual cells."
     :rows
     [[":align"    "This will align the cell to this position of the parent" "Valid options [:top :middle :bottom]"]
      [":col"      "This sets the column size" ""]
      [":offset"   "" ""]
      [":order"    "" ""]
      [":stretch?" "" ""]
      [":desktop"  "" ""]
      [":tablet"   "" ""]
      [":phone"    "" ""]]}]
   [demo-reference "layout" "grid"]])

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
   :list             list-demo
   :footer           footer-demo
   :navigation       navigation-demo))

(defn app-view []
  (let [current-demo (r/atom :intro)]
    (fn []
      [:div ;; extra wrapper div so mdl doesn't clobber the root
       [demo-layout
        :demo-map demo-map
        :current-demo-ra current-demo
        :children
        [[grid-demo
          [(@current-demo demo-map)]]]]])))

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
