(ns re-mdl.components.textfield
  (:require #?(:cljs [reagent.core :as r])
            [re-mdl.util :refer [mdl-init!
                                 mdl-get-value
                                 mdl-get-props]]
            [re-mdl.components.button :refer [button]]))

(defn textfield* [& {:keys [type input-type rows model
                            floating-label? expandable? expand-icon
                            label pattern invalid-message
                            handler-fn disabled?
                            input-attr
                            children
                            id class attr]
                     :or {type       :text
                          input-type "text"
                          handler-fn (constantly nil)}
                     :as args}]
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
                             #(handler-fn (.. % -target -value))])}
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
       {:class (cond-> "mdl-textfield mdl-js-textfield"
                 class           (str " " class)
                 floating-label? (str " mdl-textfield--floating-label")
                 expandable?     (str " mdl-textfield--expandable"))}
       attr)]
     body)))

(defn textfield [& {:keys [model]
                    :as args}]
  #?(:cljs
     (r/create-class
      {:component-did-mount
       (fn [this]
         (let [node (r/dom-node this)]
           (doto node
             mdl-init!
             (-> .-MaterialTextfield (.change (mdl-get-value model))))))
       :component-did-update
       (fn [this _]
         (doto (r/dom-node this)
           (-> .-MaterialTextfield (.change (-> (r/argv this)
                                                mdl-get-props
                                                :model
                                                mdl-get-value)))))
       :reagent-render
       textfield*})
     :clj (apply textfield* (flatten args))))
