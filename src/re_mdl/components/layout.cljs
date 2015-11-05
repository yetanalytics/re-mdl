(ns re-mdl.components.layout
  (:require [reagent.core :as r]
            [re-mdl.util :refer [wrap-mdl]]))

(defn layout* [& {:keys [fixed-drawer? fixed-header? fixed-tabs?
                        children
                        class attr]
                 :as   args}]
  (into [:div
         (r/merge-props
          {:class (cond-> "mdl-layout mdl-js-layout"
                    class (str " " class)
                    fixed-drawer? (str " mdl-layout--fixed-drawer")
                    fixed-header? (str " mdl-layout--fixed-header")
                    fixed-tabs? (str " mdl-layout--fixed-tabs"))}
          attr)] children))

(def layout (wrap-mdl layout*))


(defn title [& {:keys [large-screen-only? small-screen-only?
                       label
                       class attr]
                :as   args}]
  [:span
   (r/merge-props
    {:class (cond-> "mdl-layout-title"
              class (str " " class)
              large-screen-only? (str " mdl-layout--large-screen-only")
              small-screen-only? (str " mdl-layout--small-screen-only"))}
    attr)
   label])

(defn spacer [& {:keys [large-screen-only? small-screen-only?
                        class attr]
                :as   args}]
  [:div
   (r/merge-props
    {:class (cond-> "mdl-layout-spacer"
              class (str " " class)
              large-screen-only? (str " mdl-layout--large-screen-only")
              small-screen-only? (str " mdl-layout--small-screen-only"))}
    attr)])

(defn header [& {:keys [large-screen-only? small-screen-only? waterfall? transparent? seamed?
                        children
                        class attr]
                 :as   args}]
  (into [:header
         (r/merge-props
          {:class (cond-> "mdl-layout__header"
                    class (str " " class)
                    large-screen-only? (str " mdl-layout--large-screen-only")
                    small-screen-only? (str " mdl-layout--small-screen-only")
                    waterfall? (str " mdl-layout__header--waterfall")
                    transparent? (str " mdl-layout__header--transparent")
                    seamed? (str " mdl-layout__header--seamed"))}
          attr)] children))

(defn icon [& {:keys [large-screen-only? small-screen-only?
                       class attr]
                :as   args}]
  [:img
   (r/merge-props
    {:class (cond-> "mdl-layout-icon"
              class (str " " class)
              large-screen-only? (str " mdl-layout--large-screen-only")
              small-screen-only? (str " mdl-layout--small-screen-only"))}
    attr)])

(defn header-row [& {:keys [large-screen-only? small-screen-only?
                            children
                            class attr]
                     :as   args}]
  (into [:div
         (r/merge-props
          {:class (cond-> "mdl-layout__header-row"
                    class (str " " class)
                    large-screen-only? (str " mdl-layout--large-screen-only")
                    small-screen-only? (str " mdl-layout--small-screen-only"))}
          attr)] children))

(defn drawer [& {:keys [large-screen-only? small-screen-only?
                        children
                        class attr]
                 :as   args}]
  (into [:div
         (r/merge-props
          {:class (cond-> "mdl-layout__drawer"
                    class (str " " class)
                    large-screen-only? (str " mdl-layout--large-screen-only")
                    small-screen-only? (str " mdl-layout--small-screen-only"))}
          attr)] children))

(defn content [& {:keys [large-screen-only? small-screen-only?
                        children
                        class attr]
                 :as   args}]
  [:main
   (r/merge-props
    {:class (cond-> "mdl-layout__content"
              class (str " " class)
              large-screen-only? (str " mdl-layout--large-screen-only")
              small-screen-only? (str " mdl-layout--small-screen-only"))}
    attr)
   (into [:div.page-content] children)])

(defn nav [& {:keys [large-screen-only? small-screen-only?
                     children
                     class attr]
              :as   args}]
  (into [:nav
         (r/merge-props
          {:class (cond-> "mdl-navigation"
                    class (str " " class)
                    large-screen-only? (str " mdl-layout--large-screen-only")
                    small-screen-only? (str " mdl-layout--small-screen-only"))}
          attr)]
        children))

(defn nav-link [& {:keys [large-screen-only? small-screen-only?
                          href content
                          class attr]
              :as   args}]
  [:a
   (r/merge-props
    {:href href
     :class (cond-> "mdl-navigation__link"
              class (str " " class)
              large-screen-only? (str " mdl-layout--large-screen-only")
              small-screen-only? (str " mdl-layout--small-screen-only"))}
    attr)
   content])


;; tabbbbs

(defn layout-tab-bar [& {:keys [large-screen-only? small-screen-only?
                         children
                         class attr]
                 :as   args}]
  (into [:div
         (r/merge-props
          {:class (cond-> "mdl-layout__tab-bar"
                    class (str " " class)
                    large-screen-only? (str " mdl-layout--large-screen-only")
                    small-screen-only? (str " mdl-layout--small-screen-only"))}
          attr)] children))

(defn layout-tab [& {:keys [large-screen-only? small-screen-only? is-active?
                     href content
                     class attr]
              :as   args}]
  [:a
   (r/merge-props
    {:href href
     :class (cond-> "mdl-layout__tab"
              class (str " " class)
              large-screen-only? (str " mdl-layout--large-screen-only")
              small-screen-only? (str " mdl-layout--small-screen-only")
              is-active? (str " is-active"))}
    attr)
   content])

(defn layout-tab-panel [& {:keys [large-screen-only? small-screen-only? is-active? ripple-effect?
                           children
                           id class attr]
                    :as   args}]
  (into [:section
         (r/merge-props
          {:id id
           :class (cond-> "mdl-layout__tab-panel"
                    class (str " " class)
                    large-screen-only? (str " mdl-layout--large-screen-only")
                    small-screen-only? (str " mdl-layout--small-screen-only")
                    is-active? (str " is-active")
                    ripple-effect? (str " mdl-js-ripple-effect"))}
          attr)] children))

;; TODO: ad-hoc tabs

(defn tab-bar [& {:keys [
                         children
                         class attr]
                  :as   args}]
  (into [:div
         (r/merge-props
          {:class (cond-> "mdl-tabs__tab-bar"
                    class (str " " class))}
          attr)] children))

(defn tabs* [& {:keys [ripple-effect?
                      children
                      class attr]
               :as   args}]
  (into [:div
         (r/merge-props
          {:class (cond-> "mdl-tabs mdl-js-tabs"
                    class (str " " class)
                    ripple-effect? (str " mdl-js-ripple-effect"))}
          attr)] children))

(def tabs (wrap-mdl tabs*))

(defn tab [& {:keys [is-active?
                     href content
                     class attr]
                     :as   args}]
  [:a
   (r/merge-props
    {:href href
     :class (cond-> "mdl-tabs__tab"
              class (str " " class)
              is-active? (str " is-active"))}
    attr)
   content])

(defn tabs-panel [& {:keys [is-active?
                            children
                            el id class attr]
                     :or {el :div}
                     :as   args}]
  (into [el
         (r/merge-props
          {:id id
           :class (cond-> "mdl-tabs__panel"
                    class (str " " class)
                    is-active? (str " is-active")
                     )}
          attr)] children))

;; TODO: footers





;;; DEMO

(defn tab-demo []
  [tabs
   :children
   [[tab-bar
     :children
     [[tab
      :href "#starks-panel"
       :is-active? true
       :content "Starks"]
      [tab
       :href "#lannisters-panel"
       :is-active? true
       :content "Lannisters"]
      [tab
       :href "#targaryens-panel"
       :is-active? true
       :content "Targaryens"]]]
    [tabs-panel
     :is-active? true
     :id "starks-panel"
     :children
     [[:ul
       [:li "Eddard"]
       [:li "Catelyn"]
       [:li "Rob"]]]]
    [tabs-panel
     :id "lannisters-panel"
     :children
     [[:ul
       [:li "Tywin"]
       [:li "Cersei"]
       [:li "Jamie"]]]]
    [tabs-panel
     :id "targaryens-panel"
     :children
     [[:ul
       [:li "Viserys"]
       [:li "Danerys"]]]]]])

(defn demo-layout []
  (let [kids (r/children (r/current-component))]
    [:div.mdl-wrapper
     [layout
      :fixed-header? true
      :children
      [[header
        :children
        [[header-row
          :children
          [[title :label "Material Design Lite"]
           [spacer]
           [nav
            :children
            [[nav-link :href "/#/" :content "Home"]
             [nav-link :href "/#/about" :content "About"]]]]]]]
       [drawer
        :children
        [[title :label "Material Design Lite"]
         [nav
          :children
          [[nav-link :href "/#/" :content "Home"]
           [nav-link :href "/#/about" :content "About"]]]]]
       [content
        :children kids]]]]))
