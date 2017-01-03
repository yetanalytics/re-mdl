(ns re-mdl.core
  (:require
   #?@(:cljs [cljsjs.material
              cljsjs.dialog-polyfill])
   [re-mdl.components.badge :as badge]
   [re-mdl.components.button :as button]
   [re-mdl.components.card :as card]
   [re-mdl.components.chip :as chip]
   [re-mdl.components.grid :as grid]
   [re-mdl.components.layout :as layout]
   [re-mdl.components.loading :as loading]
   [re-mdl.components.menu :as menu]
   [re-mdl.components.slider :as slider]
   [re-mdl.components.toggle :as toggle]
   [re-mdl.components.table :as table]
   [re-mdl.components.textfield :as textfield]
   [re-mdl.components.tooltip :as tooltip]
   [re-mdl.components.snackbar :as snackbar]
   [re-mdl.components.dialog :as dialog]
   [re-mdl.components.list :as list]))

(def badge badge/badge)

(def button button/button)

;; card
(def card-title card/title)

(def card-subtitle card/subtitle)

(def card-media card/media)

(def card-supporting-text card/supporting-text)

(def card-actions card/actions)

(def card-menu card/menu)

(def card card/card)

;; chip
(def chip-contact chip/contact)

(def chip-text chip/text)

(def chip-action chip/action)

(def chip chip/chip)


;; grid

(def grid grid/grid)

(def cell grid/cell)


;; layout

(def layout layout/layout)

(def layout-title layout/title)

(def layout-spacer layout/spacer)

(def layout-header layout/header)

(def layout-icon layout/icon)

(def layout-header-row layout/header-row)

(def layout-drawer layout/drawer)

(def layout-content layout/content)

(def layout-nav layout/nav)

(def layout-nav-link layout/nav-link)

(def layout-tab-bar layout/layout-tab-bar)

(def layout-tab layout/layout-tab)

(def layout-tab-panel layout/layout-tab-panel)

;; tabs (part of layout, but whatever)

(def tab-bar layout/tab-bar)

(def tabs layout/tabs)

(def tab layout/tab)

(def tabs-panel layout/tabs-panel)

;; footers

(def mega-footer-social-btn layout/mega-social-btn)

(def mega-footer-link-list layout/mega-link-list)

(def mega-footer-dropdown layout/mega-drop-down)

(def mega-footer layout/mega-footer)


(def mini-footer-social-btn layout/mini-social-btn)

(def mini-footer-link-list layout/mini-link-list)

(def mini-footer layout/mini-footer)


;; loading

(def loading-progress loading/progress)

(def loading-spinner loading/spinner)


;; Menu

(def menu-item menu/item)

(def menu menu/menu)


;; slider

(def slider slider/slider)


;; table

(def table table/table)


;; textfield

(def textfield textfield/textfield)


;; toggle

(def toggle-checkbox toggle/checkbox)

(def toggle-radio toggle/radio)

(def toggle-radios toggle/radios)

(def toggle-icon-toggle toggle/icon-toggle)

(def toggle-switch toggle/switch)


;; tooltip

(def tooltip tooltip/tooltip)


;; snackbar
(def snackbar-target snackbar/snackbar-target)
(def snackbar! snackbar/snackbar!)

;; dialog

(def dialog dialog/dialog)

(def dialog-title dialog/title)

(def dialog-content dialog/content)

(def dialog-actions dialog/actions)

(def list-item list/item)
(def list-coll list/list-coll)

(def list-item-primary-content list/item-primary-content)
(def list-item-secondary-content list/item-secondary-content)
(def list-item-secondary-action list/item-secondary-action)
(def list-item-secondary-info list/item-secondary-info)
(def list-item-text-body list/item-text-body)
(def list-item-sub-title list/item-sub-title)
