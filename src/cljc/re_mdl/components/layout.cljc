(ns re-mdl.components.layout
  (:require [re-mdl.util :refer [wrap-mdl]]))

(defn layout* [& {:keys [fixed-drawer? fixed-header? fixed-tabs?
                         no-drawer-button? no-desktop-drawer-button?
                         children
                         id class attr]
                 :as   args}]
  (into
   [:div
    (merge
     {:id    id
      :class (cond-> "mdl-layout mdl-js-layout"
               class                     (str " " class)
               fixed-drawer?             (str " mdl-layout--fixed-drawer")
               fixed-header?             (str " mdl-layout--fixed-header")
               fixed-tabs?               (str " mdl-layout--fixed-tabs")
               no-drawer-button?         (str " mdl-layout--no-drawer-button")
               no-desktop-drawer-button? (str " mdl-layout--no-desktop-drawer-button"))}
     attr)]
   children))

(def layout (wrap-mdl layout*))

(defn title [& {:keys [large-screen-only? small-screen-only?
                       label
                       children
                       id class attr]
                :as   args}]
  (into
   [:span
    (merge
     {:id    id
      :class (cond-> "mdl-layout-title"
               class              (str " " class)
               large-screen-only? (str " mdl-layout--large-screen-only")
               small-screen-only? (str " mdl-layout--small-screen-only"))}
     attr)
    label]
   children))

(defn spacer [& {:keys [large-screen-only? small-screen-only?
                        children
                        id class attr]
                 :as   args}]
  (into
   [:div
    (merge
     {:id id
      :class (cond-> "mdl-layout-spacer"
               class (str " " class)
               large-screen-only? (str " mdl-layout--large-screen-only")
               small-screen-only? (str " mdl-layout--small-screen-only"))}
     attr)]
   children))

(defn header [& {:keys [large-screen-only? small-screen-only? waterfall?
                        transparent? seamed? scroll?
                        waterfall-hide-top?
                        children
                        id class attr]
                 :as   args}]
  (into
   [:header
    (merge
     {:id id
      :class (cond-> "mdl-layout__header"
               class (str " " class)
               large-screen-only?  (str " mdl-layout--large-screen-only")
               small-screen-only?  (str " mdl-layout--small-screen-only")
               waterfall?          (str " mdl-layout__header--waterfall")
               waterfall-hide-top? (str " mdl-layout__header--waterfall-hide-top")
               transparent?        (str " mdl-layout__header--transparent")
               seamed?             (str " mdl-layout__header--seamed")
               scroll?             (str " mdl-layout__header--scroll"))}
     attr)]
   children))

(defn icon [& {:keys [large-screen-only? small-screen-only?
                      id class attr]
                :as   args}]
  [:img
   (merge
    {:id    id
     :class (cond-> "mdl-layout-icon"
              class (str " " class)
              large-screen-only? (str " mdl-layout--large-screen-only")
              small-screen-only? (str " mdl-layout--small-screen-only"))}
    attr)])

(defn header-row [& {:keys [large-screen-only? small-screen-only?
                            children
                            id class attr]
                     :as   args}]
  (into
   [:div
    (merge
     {:id    id
      :class (cond-> "mdl-layout__header-row"
               class (str " " class)
               large-screen-only? (str " mdl-layout--large-screen-only")
               small-screen-only? (str " mdl-layout--small-screen-only"))}
     attr)]
   children))

(defn drawer [& {:keys [large-screen-only? small-screen-only?
                        children
                        id class attr]
                 :as   args}]
  (into
   [:div
    (merge
     {:id    id
      :class (cond-> "mdl-layout__drawer"
               class (str " " class)
               large-screen-only? (str " mdl-layout--large-screen-only")
               small-screen-only? (str " mdl-layout--small-screen-only"))}
     attr)]
   children))

(defn content [& {:keys [large-screen-only? small-screen-only?
                         children
                         id class attr]
                  :as   args}]
  (into
   [:main
    (merge
     {:id id
      :class (cond-> "mdl-layout__content"
               class (str " " class)
               large-screen-only? (str " mdl-layout--large-screen-only")
               small-screen-only? (str " mdl-layout--small-screen-only"))}
     attr)]
   children))

(defn nav [& {:keys [large-screen-only? small-screen-only?
                     children
                     id class attr]
              :as   args}]
  (into [:nav
         (merge
          {:id id
           :class (cond-> "mdl-navigation"
                    class (str " " class)
                    large-screen-only? (str " mdl-layout--large-screen-only")
                    small-screen-only? (str " mdl-layout--small-screen-only"))}
          attr)]
        children))

(defn nav-link [& {:keys [large-screen-only? small-screen-only?
                          href content on-click
                          id class attr]
              :as   args}]
  [:a
   (merge
    (cond->
        {:id id
         :href href
         :class (cond-> "mdl-navigation__link"
                  class (str " " class)
                  large-screen-only? (str " mdl-layout--large-screen-only")
                  small-screen-only? (str " mdl-layout--small-screen-only"))}
      on-click (assoc :on-click on-click))
    attr)
   content])

;; navigation tabs

(defn layout-tab-bar [& {:keys [large-screen-only? small-screen-only? ripple-effect?
                                tab-manual-switch?
                                children
                                id class attr]
                         :as   args}]
  (into [:div
         (merge
          {:id id
           :class (cond-> "mdl-layout__tab-bar"
                    class              (str " " class)
                    ripple-effect?     (str " mdl-js-ripple-effect")
                    large-screen-only? (str " mdl-layout--large-screen-only")
                    small-screen-only? (str " mdl-layout--small-screen-only")
                    tab-manual-switch? (str " mdl-layout__tab-manual-switch"))}
          attr)] children))

(defn layout-tab [& {:keys [large-screen-only? small-screen-only? is-active?
                            href content
                            id class attr]
                     :as   args}]
  [:a
   (merge
    {:id id
     :href href
     :class (cond-> "mdl-layout__tab"
              class (str " " class)
              large-screen-only? (str " mdl-layout--large-screen-only")
              small-screen-only? (str " mdl-layout--small-screen-only")
              is-active? (str " is-active"))}
    attr)
   content])

(defn layout-tab-panel [& {:keys [large-screen-only? small-screen-only?
                                  is-active? ripple-effect?
                                  children
                                  id class attr]
                           :as   args}]
  (into
   [:section
    (merge
     {:id    id
      :class (cond-> "mdl-layout__tab-panel"
               class              (str " " class)
               large-screen-only? (str " mdl-layout--large-screen-only")
               small-screen-only? (str " mdl-layout--small-screen-only")
               is-active?         (str " is-active")
               ripple-effect?     (str " mdl-js-ripple-effect"))}
     attr)]
   children))

;; TODO: ad-hoc tabs

(defn tab-bar [& {:keys [children
                         id class attr]
                  :as   args}]
  (into
   [:div
    (merge
     {:id    id
      :class (cond-> "mdl-tabs__tab-bar"
               class (str " " class))}
     attr)]
   children))

(defn tabs* [& {:keys [ripple-effect?
                       children
                       id class attr]
                :as   args}]
  (into
   [:div
    (merge
     {:id    id
      :class (cond-> "mdl-tabs mdl-js-tabs"
               class          (str " " class)
               ripple-effect? (str " mdl-js-ripple-effect"))}
     attr)]
   children))

(def tabs (wrap-mdl tabs*))

(defn tab [& {:keys [is-active?
                     href child
                     children
                     id class attr]
              :as   args}]
  (into
   [:a
    (merge
     {:id    id
      :href  href
      :class (cond-> "mdl-tabs__tab"
               class          (str " " class)
               is-active?     (str " is-active"))}
     attr)
    child]
   children))

(defn tabs-panel [& {:keys [el is-active?
                            children
                            id class attr]
                     :or   {el :div}
                     :as   args}]
  (into
   [el
    (merge
     {:id    id
      :class (cond-> "mdl-tabs__panel"
               class      (str " " class)
               is-active? (str " is-active"))}
     attr)]
   children))


;; Footers

(defn mega-social-btn [& {:keys [child
                                 children
                                 id class attr]
                          :as   args}]
  (into
   [:button
    (merge
     {:id    id
      :class (cond-> "mdl-mega-footer__social-btn"
               class (str " " class))}
     attr)
    child]
   children))

(defn mega-link-list [& {:keys [children
                                id class attr]
                         :as   args}]
  (into [:ul
         (merge
          {:id id
           :class (cond-> "mdl-mega-footer__link-list"
                    class (str " " class))}
          attr)]
        (for [child children]
          ^{:key child} [:li child])))

(defn mega-drop-down [& {:keys [heading
                                children
                                id class attr]
                         :as   args}]
  [:div
   (merge
    {:id id
     :class (cond-> "mdl-mega-footer__drop-down-section"
              class (str " " class))}
    attr)
   [:h1.mdl-mega-footer__heading
    heading]
   [mega-link-list
    :children children]])

(defn mega-footer [& {:keys [top left middle right bottom logo
                             children
                             id class attr]
                      :as   args}]
  (into
   [:footer
    (merge
     {:id    id
      :class (cond-> "mdl-mega-footer"
               class (str " " class))}
     attr)
    (when top
      (into [:div.mdl-mega-footer__top-section] top))
    (when left
      (into [:div.mdl-mega-footer__left-section] left))
    (when middle
      (into [:div.mdl-mega-footer__middle-section] middle))
    (when right
      (into [:div.mdl-mega-footer__right-section] right))
    (when bottom
      (into [:div.mdl-mega-footer__bottom-section
             [:div.mdl-logo (or logo "")]] bottom))]
   children))

(defn mini-social-btn [& {:keys [child
                                 children
                                 id class attr]
                          :as   args}]
  (into
   [:button
    (merge
     {:id    id
      :class (cond-> "mdl-mega-footer__social-btn"
               class (str " " class))}
     attr)
    child]
   children))

(defn mini-link-list [& {:keys [children
                                id class attr]
                         :as   args}]
  (into
   [:ul
    (merge
     {:id id
      :class (cond-> "mdl-mini-footer__link-list"
               class (str " " class))}
     attr)]
   (for [child children]
     ^{:key child} [:li child])))

(defn mini-footer [& {:keys [left right logo
                             children
                             id class attr]
                      :as   args}]
  (into
   [:footer
    (merge
     {:id id
      :class (cond-> "mdl-mini-footer"
               class (str " " class))}
     attr)
    (when left
      (into [:div.mdl-mini-footer__left-section
             [:div.mdl-logo (or logo "")]] left))
    (when right
      (into [:div.mdl-mini-footer__right-section] right))]
   children))
