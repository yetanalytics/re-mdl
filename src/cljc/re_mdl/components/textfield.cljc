(ns re-mdl.components.textfield
  (:require #?(:cljs [reagent.core :as r]
               :clj [re-mdl.macros :refer [build-class]])
            [re-mdl.util :refer [mdl-init!
                                 mdl-get-value
                                 mdl-get-props]]
            [re-mdl.components.button :refer [button]])
  #?(:cljs (:require-macros [re-mdl.macros :refer [build-class]])))

(defn textfield* [this]
  (let [{:keys [type input-type rows model
                floating-label? expandable? expand-icon
                label pattern invalid-message
                handler-fn disabled?
                input-attr
                children
                id class attr]
         :or {type       :text
              input-type "text"
              handler-fn (constantly nil)}
         :as args}
        #?(:cljs (-> this r/argv mdl-get-props)
           :clj  this)]
    (when type
      (assert
       (#{:text :textarea} type) "Invalid type, must be :text or :textarea"))

    (when (= :textarea type)
      (assert rows "Text area must have :rows option"))

    (when expandable?
      (assert expand-icon "No :expand-icon set!"))

    (let [_ (mdl-get-value model)
          input-el [(case type
                      :text     :input
                      :textarea :textarea)
                    (merge
                     (cond->
                         {:class "mdl-textfield__input"
                          :type  input-type
                          :id    id
                          #?@(:cljs
                              [:on-change
                               #(do
                                  ;; prevent component update if change
                                  ;; comes from here
                                  #?(:cljs (r/set-state this {:update false}))
                                  (handler-fn (.. % -target -value)))])}
                       (= type :textarea) (assoc :rows rows)
                       pattern            (assoc :pattern pattern)
                       disabled?          (assoc :disabled true))
                     input-attr)]
          label-el [:label.mdl-textfield__label
                    {:for id}
                    label]
          error-el (when invalid-message
                     [:span.mdl-textfield__error
                      invalid-message])
          body     (if expandable?
                     [[button
                       :el    :label
                       :icon? true
                       :for   id
                       :child [:i.material-icons expand-icon]]
                      [:div.mdl-textfield__expandable-holder
                       input-el
                       label-el
                       error-el]]
                     [input-el
                      label-el
                      error-el])]
      (into
       [:div
        (merge
         {:class (build-class "mdl-textfield mdl-js-textfield"
                              class           (str " " class)
                              floating-label? " mdl-textfield--floating-label"
                              expandable?     " mdl-textfield--expandable")}
         attr)]
       body))))

(defn textfield [& {:keys [model]
                    :as args}]
  #?(:cljs
     (r/create-class
      {:get-initial-state
       ;; use this state to control when MaterialTextField should change
       (fn [this]
         {:update true})
       :component-did-mount
       (fn [this]
         (let [node (r/dom-node this)]
           (doto node
             mdl-init!
             (-> .-MaterialTextfield (.change (mdl-get-value model))))))
       :component-did-update
       (fn [this _]
         (if (-> this r/state :update)
           (doto (r/dom-node this)
             (-> .-MaterialTextfield (.change (-> (r/argv this)
                                                  mdl-get-props
                                                  :model
                                                  mdl-get-value))))
           (r/set-state this {:update true})))
       ;; use render instead of reagent-render so we can get access to this
       :render
       textfield*})
     :clj (textfield* args)))
