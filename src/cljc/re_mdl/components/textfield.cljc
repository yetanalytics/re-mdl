(ns re-mdl.components.textfield
  (:require #?(:cljs [reagent.core :as r])
            [re-mdl.util :refer [mdl-init!]]
            [re-mdl.components.button :refer [button]]))

(defn textfield*
  [& {:keys [type input-type rows maxrows floating-label? expandable? expand-icon
             label pattern invalid-message handler-fn
             id class attr input-attr]
      :or {type :text
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

      (let [input-el [(case type
                        :text :input
                        :textarea :textarea)
                      (merge
                       (cond->
                           {:class "mdl-textfield__input"
                            :type input-type
                            :id id
                            #?@(:cljs
                                [:on-change (fn [e]
                                              (-> e
                                                  .-target
                                                  .-value
                                                  handler-fn))])}
                         (= type :textarea) (assoc :rows rows)
                         pattern (assoc :pattern pattern)
                         maxrows (assoc :maxrows maxrows))
                       input-attr)]
            label-el [:label.mdl-textfield__label
                      {:for id}
                      label]
            error-el (when invalid-message
                       [:span.mdl-textfield__error
                        invalid-message])
            body (if expandable?
                   [[button
                     :el :label
                     :for id
                     :label [:i.material-icons expand-icon]]
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
                     class (str " " class)
                     floating-label? (str " mdl-textfield--floating-label")
                     expandable? (str " mdl-textfield--expandable"))}
           attr)]
         body)))

(defn textfield [& {:keys [init-val]
                    :as args}]
  #?(:cljs
     (r/create-class
      {:component-did-mount
       (fn [this]
         (let [node (r/dom-node this)]
           (doto node
             mdl-init!
             (-> .-MaterialTextfield (.change init-val)))))
       :reagent-render
       textfield*})
     :clj (apply textfield* (flatten args))))
