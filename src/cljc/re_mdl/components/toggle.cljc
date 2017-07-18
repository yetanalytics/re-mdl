(ns re-mdl.components.toggle
  (:require #?(:cljs [reagent.core :as r])
            [re-mdl.util :refer [wrap-mdl
                                 mdl-init-mount
                                 mdl-get-value
                                 mdl-get-props]]))

(defn checkbox* [this]
  (let [{:keys [disabled? ripple-effect? model
                label handler-fn
                children
                id class attr]
         :or   #?(:cljs {handler-fn (constantly nil)}
                  :clj  {})
         :as   args}
        #?(:cljs (-> this r/argv mdl-get-props)
           :clj  this)
        _ (mdl-get-value model)]
    (println "CHECKBOX*: " args handler-fn)
    #?(:clj (when handler-fn (throw (Exception. "No handler function allowed in clj"))))
    (into
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
             :defaultChecked (mdl-get-value model)
             #?@(:cljs [:on-change
                        #(handler-fn (.. % -target -checked))])}
          disabled? (assoc :disabled true)
          #?@(:clj [model (assoc :checked true)]))
        attr)]
      (when label
        [:span.mdl-checkbox__label label])]
     children)))

(defn checkbox [& {:keys [model]
                   :as   args}]
  #?(:cljs
     (r/create-class
      {:component-did-mount
       mdl-init-mount
       :component-did-update
       (fn [this _]
         (let [elem (-> (r/dom-node this) .-MaterialCheckbox)]
           (if (-> (r/argv this)
                   mdl-get-props
                   :model
                   mdl-get-value)
             (.check elem)
             (.uncheck elem))))
       :render
       checkbox*})
     :clj (do
            (println "CHECKBOX: " args)
            (checkbox* args))))

(defn radio* [& {:keys [handler-fn disabled? ripple-effect? model
                        value name label
                        children
                        id class attr]
                 #?@(:cljs [:or {handler-fn (constantly nil)}])
                 :as   args}]
  #?(:clj (when handler-fn (throw (Exception. "No handler function allowed in clj"))))
  (let [_ (mdl-get-value model)]
    (into
     [:label
      (merge
       {:for id
        :class (cond-> "mdl-radio mdl-js-radio"
                 class          (str " " class)
                 ripple-effect? (str " mdl-js-ripple-effect"))}
       attr)
      [:input.mdl-radio__button
       (cond->
           {:type           "radio"
            :id             id
            :name           name
            :value          value
            :defaultChecked (mdl-get-value model)
            #?@(:cljs
                [:on-change #(handler-fn (.. % -target -value))])}
         disabled? (assoc :disabled true)
         #?@(:clj [model (assoc :checked true)]))]
      (when label
        [:span.mdl-radio__label label])]
     children)))

(defn radio [& {:keys [model]}]
  #?(:cljs (r/create-class
            {:component-did-mount
             mdl-init-mount
             :component-did-update
             (fn [this _]
               (let [elem (-> (r/dom-node this) .-MaterialRadio)]
                 (if (-> (r/argv this)
                         mdl-get-props
                         :model
                         mdl-get-value)
                   (.check elem)
                   (.uncheck elem))))
             :reagent-render
             radio*})
     :clj radio*))

(defn radios [& {:keys [handler-fn ripple-effect? model
                        choices disabled name
                        children
                        id class attr]
                 :or   {disabled   #{}
                        handler-fn (constantly nil)}
                 :as   args}]
  (into
   [:div
    (merge
     {:for   id
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
        :model          (= val (mdl-get-value model))
        :ripple-effect? ripple-effect?])
     choices))))

(defn icon-toggle* [& {:keys [handler-fn icon
                              disabled? ripple-effect? model
                              children
                              id class attr]
                       #?@(:cljs [:or {handler-fn (constantly nil)}])
                       :as   args}]
  #?(:clj (when handler-fn (throw (Exception. "No handler function allowed in clj"))))
  (let [_ (mdl-get-value model)]
    (into
     [:label
      (merge
       {:for   id
        :class (cond-> "mdl-icon-toggle mdl-js-icon-toggle"
                 class          (str " " class)
                 ripple-effect? (str " mdl-js-ripple-effect"))}
       attr)
      [:input.mdl-icon-toggle__input
       (merge
        (cond->
            {:type           "checkbox"
             :id             id
             :defaultChecked (mdl-get-value model)
             #?@(:cljs
                 [:on-change #(handler-fn (.. % -target -checked))])}
          disabled? (assoc :disabled true)
          #?@(:clj [model (assoc :checked true)]))
        attr)]
      [:i.mdl-icon-toggle__label.material-icons
       icon]]
     children)))

(defn icon-toggle [& {:keys [model]}]
  #?(:cljs
     (r/create-class
      {:component-did-mount
       mdl-init-mount
       :component-did-update
       (fn [this _]
         (let [elem (-> (r/dom-node this) .-MaterialIconToggle)]
           (if (-> (r/argv this)
                   mdl-get-props
                   :model
                   mdl-get-value)
             (.check elem)
             (.uncheck elem))))
       :reagent-render
       icon-toggle*})
     :clj icon-toggle*))

(defn switch* [& {:keys [handler-fn model
                         disabled? ripple-effect?
                         label
                         children
                         id class attr]
      #?@(:cljs [:or {handler-fn (constantly nil)}])
      :as   args}]
  #?(:clj (when handler-fn (throw (Exception. "No handler function allowed in clj"))))
  (let [_ (mdl-get-value model)]
    (into
     [:label
      (merge
       {:for   id
        :class (cond-> "mdl-switch mdl-js-switch"
                 class          (str " " class)
                 ripple-effect? (str " mdl-js-ripple-effect"))}
       attr)
      [:input.mdl-switch__input
       (merge
        (cond->
            {:type "checkbox"
             :id   id
             :defaultChecked (mdl-get-value model)
             #?@(:cljs [:on-change #(handler-fn (.. % -target -checked))])}
          disabled? (assoc :disabled true)
          #?@(:clj [model (assoc :checked true)]))
        attr)]
      (when label
        [:span.mdl-switch__label label])]
     children)))

(defn switch [& {:keys [model]}]
  #?(:cljs
     (r/create-class
      {:component-did-mount
       mdl-init-mount
       :component-did-update
       (fn [this _]
         (let [elem (-> (r/dom-node this) .-MaterialSwitch)]
           (if (-> (r/argv this)
                   mdl-get-props
                   :model
                   mdl-get-value)
             (.on elem)
             (.off elem))))
       :reagent-render
       switch*})
     :clj switch*))
