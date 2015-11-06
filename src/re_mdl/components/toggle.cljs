(ns re-mdl.components.toggle
  (:require [reagent.core :as r]
            [re-mdl.util :refer [wrap-mdl mdl-init!]]))


(defn checkbox [& {:keys [checked?]}]
  (r/create-class
   {:component-did-mount
    (fn [this]
      (let [node (r/dom-node this)]
        (mdl-init! node)
        (when checked?
          (-> node .-MaterialCheckbox .check))))
    :reagent-render
    (fn [& {:keys [handler-fn disabled? ripple-effect?
                  label
                  id class attr]
           :or {handler-fn (fn [e] (.log
                                   js/console
                                   (str "Unhandled checkbox: "
                                        (-> e
                                            .-target
                                            .-checked))))}
           :as   args}]
      [:label
       (r/merge-props
        {:for id

         :class (cond-> "mdl-checkbox mdl-js-checkbox"
                  class (str " " class)
                  ripple-effect? (str " mdl-js-ripple-effect"))}
        attr)
       [:input.mdl-checkbox__input
       (r/merge-props
        (cond->
            {:type "checkbox"
             :id id
             :on-change (fn [e] (-> e
                                   .-target
                                   .-checked
                                   handler-fn))}
          disabled? (assoc :disabled true))
        attr)]
       (when label
         [:span.mdl-checkbox__label label])])}))

(defn radio [& {:keys [checked?]}]
  (r/create-class
   {:component-did-mount
    (fn [this]
      (let [node (r/dom-node this)]
        (mdl-init! node)
        (when checked?
          (-> node .-MaterialRadio .check))))
    :reagent-render
    (fn [& {:keys [handler-fn disabled? ripple-effect?
                  value name label
                  id class attr]
           :or {handler-fn (fn [e] (.log
                                   js/console
                                   (str "Unhandled radio: "
                                        (-> e
                                            .-target
                                            .-value))))}
           :as   args}]
      [:label
       (r/merge-props
        {:for id
         :class (cond-> "mdl-radio mdl-js-radio"
                  class (str " " class)
                  ripple-effect? (str " mdl-js-ripple-effect"))}
        attr)
       [:input.mdl-radio__button
        (cond->
            {:type "radio"
             :id id
             :name name
             :value value
             :on-change (fn [e] (-> e
                                   .-target
                                   .-value
                                   handler-fn))}
          disabled? (assoc :disabled true))]
       (when label
         [:span.mdl-radio__label label])])}))

(defn radios [& {:keys [handler-fn ripple-effect?
                        choices checked disabled name children
                        id class attr]
                 :or {disabled #{}}
                 :as   args}]
  (into
   [:div
    (r/merge-props
     {:for id
      :class (cond-> "re-mdl-radio"
               class (str " " class))}
     attr)]
   (or
    children
    (map-indexed
     (fn [idx [val label :as choice]]
       [radio
        :id (str name idx)
        :name name
        :value val
        :label label
        :handler-fn handler-fn
        :disabled? (disabled val)
        :checked? (= val checked)
        :ripple-effect? ripple-effect?])
     choices))))


(defn icon-toggle [& {:keys [checked? labels]}]
  (let [toggle-state (r/atom checked?)
        [checked-label unchecked-label] labels]
    (r/create-class
     {:component-did-mount
      (fn [this]
        (let [node (r/dom-node this)]
          (mdl-init! node)
          (when checked?
            (-> node .-MaterialIconToggle .check))))
      :reagent-render
      (fn [& {:keys [handler-fn disabled? ripple-effect?
                    id class attr]
             :or {handler-fn (fn [e] (.log
                                     js/console
                                     (str "Unhandled icon-toggle: "
                                          (-> e
                                              .-target
                                              .-checked))))}
             :as   args}]
        [:label
         (r/merge-props
          {:for id

           :class (cond-> "mdl-icon-toggle mdl-js-icon-toggle"
                    class (str " " class)
                    ripple-effect? (str " mdl-js-ripple-effect"))}
          attr)
         [:input.mdl-icon-toggle__input
          (r/merge-props
           (cond->
               {:type "checkbox"
                :id id
                :on-change (fn [e]
                             (let [val (-> e
                                           .-target
                                           .-checked)]
                               (reset! toggle-state val)
                               (handler-fn val)))}
             disabled? (assoc :disabled true))
           attr)]
         [:i.mdl-icon-toggle__label.material-icons (if @toggle-state
                                                     checked-label
                                                     unchecked-label)]])})))


(defn switch [& {:keys [checked?]}]
  (r/create-class
   {:component-did-mount
    (fn [this]
      (let [node (r/dom-node this)]
        (mdl-init! node)
        (when checked?
          (-> node .-MaterialSwitch .check))))
    :reagent-render
    (fn [& {:keys [handler-fn disabled? ripple-effect?
                  label
                  id class attr]
           :or {handler-fn (fn [e] (.log
                                   js/console
                                   (str "Unhandled switch: "
                                        (-> e
                                            .-target
                                            .-checked))))}
           :as   args}]
      [:label
       (r/merge-props
        {:for id

         :class (cond-> "mdl-switch mdl-js-switch"
                  class (str " " class)
                  ripple-effect? (str " mdl-js-ripple-effect"))}
        attr)
       [:input.mdl-switch__input
       (r/merge-props
        (cond->
            {:type "checkbox"
             :id id
             :on-change (fn [e] (-> e
                                   .-target
                                   .-checked
                                   handler-fn))}
          disabled? (assoc :disabled true))
        attr)]
       (when label
         [:span.mdl-switch__label label])])}))
