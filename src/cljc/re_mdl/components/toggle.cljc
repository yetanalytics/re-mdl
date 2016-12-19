(ns re-mdl.components.toggle
  (:require #?(:cljs [reagent.core :as r])
            [re-mdl.util :refer [wrap-mdl
                                 mdl-init-mount
                                 mdl-get-value
                                 mdl-get-props]]))

(defn checkbox*
  [& {:keys [handler-fn disabled? ripple-effect? checked?
             label
             id class attr]
      #?@(:cljs [:or {handler-fn (constantly nil)}])
      :as   args}]
  #?(:clj (when handler-fn (throw (Exception. "No handler function allowed in clj"))))
  (let [_ (mdl-get-value checked?)]
    [:label
     (merge
      {:for   id
       :class (cond-> "mdl-checkbox mdl-js-checkbox"
                class          (str " " class)
                ripple-effect? (str " mdl-js-ripple-effect"))}
      attr)
     [:input.mdl-checkbox__input
      (merge
       (cond->
           {:type "checkbox"
            :id   id
            :defaultChecked (mdl-get-value checked?)
            #?@(:cljs [:on-change
                       #(handler-fn (.. % -target -checked))])}
         disabled? (assoc :disabled true)
         #?@(:clj [checked? (assoc :checked true)]))
       attr)]
     (when label
       [:span.mdl-checkbox__label label])]))

(defn checkbox [& {:keys [checked?]}]
  #?(:cljs
     (r/create-class
      {:component-did-mount
       mdl-init-mount
       :component-did-update
       (fn [this _]
         (let [elem (-> (r/dom-node this) .-MaterialCheckbox)]
           (if (-> (r/argv this)
                   mdl-get-props
                   :checked?
                   mdl-get-value)
             (.check elem)
             (.uncheck elem))))
       :reagent-render
       checkbox*})
     :clj checkbox*))

(defn radio*
  [& {:keys [handler-fn disabled? ripple-effect? checked?
             value name label
             id class attr]
      #?@(:cljs [:or {handler-fn (constantly nil)}])
      :as   args}]
  #?(:clj (when handler-fn (throw (Exception. "No handler function allowed in clj"))))
  (let [_ (mdl-get-value checked?)]
    [:label
     (merge
      {:for id
       :class (cond-> "mdl-radio mdl-js-radio"
                class          (str " " class)
                ripple-effect? (str " mdl-js-ripple-effect"))}
      attr)
     [:input.mdl-radio__button
      (cond->
          {:type  "radio"
           :id    id
           :name  name
           :value value
           :defaultChecked (mdl-get-value checked?)
           #?@(:cljs
               [:on-change #(handler-fn (.. % -target -value))])}
        disabled? (assoc :disabled true)
        #?@(:clj [checked? (assoc :checked true)]))]
     (when label
       [:span.mdl-radio__label label])]))

(defn radio [& {:keys [checked?]}]
  #?(:cljs (r/create-class
            {:component-did-mount
             mdl-init-mount
             :component-did-update
             (fn [this _]
               (let [elem (-> (r/dom-node this) .-MaterialRadio)]
                 (if (-> (r/argv this)
                         mdl-get-props
                         :checked?
                         mdl-get-value)
                   (.check elem)
                   (.uncheck elem))))
             :reagent-render
             radio*})
     :clj radio*))

(defn radios [& {:keys [handler-fn ripple-effect?
                        choices checked disabled name children
                        id class attr]
                 :or   {disabled #{}}
                 :as   args}]
  (into
   [:div
    (merge
     {:for id
      :class (cond-> "re-mdl-radio"
               class (str " " class))}
     attr)]
   (or
    children
    (map-indexed
     (fn [idx [val label :as choice]]
       [radio
        :id             (str name idx)
        :name           name
        :value          val
        :label          label
        :handler-fn     handler-fn
        :disabled?      (disabled val)
        :checked?       (= val (mdl-get-value checked))
        :ripple-effect? ripple-effect?])
     choices))))

(defn icon-toggle*
  [& {:keys [handler-fn icon
             disabled? ripple-effect? checked?
             id class attr]
      #?@(:cljs [:or {handler-fn (constantly nil)}])
      :as   args}]
  #?(:clj (when handler-fn (throw (Exception. "No handler function allowed in clj"))))
  (let [_ (mdl-get-value checked?)]
    [:label
     (merge
      {:for id

       :class (cond-> "mdl-icon-toggle mdl-js-icon-toggle"
                class          (str " " class)
                ripple-effect? (str " mdl-js-ripple-effect"))}
      attr)
     [:input.mdl-icon-toggle__input
      (merge
       (cond->
           {:type "checkbox"
            :id   id
            :defaultChecked (mdl-get-value checked?)
            #?@(:cljs
                [:on-change #(handler-fn (.. % -target -checked))])}
         disabled? (assoc :disabled true)
         #?@(:clj [checked? (assoc :checked true)]))
       attr)]
     [:i.mdl-icon-toggle__label.material-icons icon]]))

(defn icon-toggle [& {:keys [checked?]}]
  #?(:cljs
     (r/create-class
      {:component-did-mount
       mdl-init-mount
       :component-did-update
       (fn [this _]
         (let [elem (-> (r/dom-node this) .-MaterialIconToggle)]
           (if (-> (r/argv this)
                   mdl-get-props
                   :checked?
                   mdl-get-value)
             (.check elem)
             (.uncheck elem))))
       :reagent-render
       icon-toggle*})
     :clj icon-toggle*))

(defn switch*
  [& {:keys [handler-fn checked?
             disabled? ripple-effect?
             label
             id class attr]
      #?@(:cljs [:or {handler-fn (constantly nil)}])
      :as   args}]
  #?(:clj (when handler-fn (throw (Exception. "No handler function allowed in clj"))))
  (let [_ (mdl-get-value checked?)]
    [:label
     (merge
      {:for id

       :class (cond-> "mdl-switch mdl-js-switch"
                class          (str " " class)
                ripple-effect? (str " mdl-js-ripple-effect"))}
      attr)
     [:input.mdl-switch__input
      (merge
       (cond->
           {:type "checkbox"
            :id   id
            :defaultChecked (mdl-get-value checked?)
            #?@(:cljs [:on-change #(handler-fn (.. % -target -checked))])}
         disabled? (assoc :disabled true)
         #?@(:clj [checked? (assoc :checked true)]))
       attr)]
     (when label
       [:span.mdl-switch__label label])]))

(defn switch [& {:keys [checked?]}]
  #?(:cljs
     (r/create-class
      {:component-did-mount
       mdl-init-mount
       :component-did-update
       (fn [this _]
         (let [elem (-> (r/dom-node this) .-MaterialSwitch)]
           (if (-> (r/argv this)
                   mdl-get-props
                   :checked?
                   mdl-get-value)
             (.on elem)
             (.off elem))))
       :reagent-render
       switch*})
     :clj switch*))
