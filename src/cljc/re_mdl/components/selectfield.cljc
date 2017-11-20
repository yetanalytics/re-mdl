(ns re-mdl.components.selectfield
  (:require [re-mdl.util :refer [wrap-mdl
                                 mdl-get-value]]
            #?(:clj [re-mdl.macros :refer [build-class]]))
  #?(:cljs (:require-macros [re-mdl.macros :refer [build-class]])))

(defn option
  "A single option component. Has a value and is optionally disabled."
  [& {:keys [value disabled? content
             children
             id class attr]}]
  ^{:key value}
  (into
   [:option (merge
             (cond-> {:id    id
                      :class class}
               value     (assoc :value value)
               disabled? (assoc :disabled true))
             attr)
    content]
   children))

(defn selectfield*
  "Selectfield models from the module
   https://github.com/MEYVN-digital/mdl-selectfield

   Built out to follow other MDL conventions."
  [& {:keys [label options
             model on-change
             required? error-msg
             floating-label?
             children
             id class attr]
      :or   {;; unique id needed to bind select to label
             id        #?(:clj  (str (java.util.UUID/randomUUID))
                          :cljs (str (random-uuid)))
             on-change (constantly nil)}}]
  (let [_ (mdl-get-value model)]
    (cond-> [:div
             {:class (build-class "mdl-selectfield mdl-js-selectfield"
                                  floating-label? " mdl-selectfield--floating-label")}
             (cond->
                 [:select
                  (merge
                   (cond-> {:id    id
                            :name  (str id "--name")
                            :class (build-class "mdl-selectfield__select"
                                                class (str " " class))
                            #?@(:cljs
                                [:on-change
                                 #(on-change (.. % -target -value))])}
                     required? (assoc :required true)
                     model     (assoc :value model))
                   attr)]
               ;; user passed in children with custom props
               children (-> (concat children)
                            vec)
               ;; quicker option, construct component from map
               options  (into (for [opts options]
                                [option
                                 :value     (:value opts)
                                 :disabled? (:disabled? opts)
                                 :content   (:content opts)])))
             [:label.mdl-selectfield__label
              {:for id}
              label]]
      ;; optional error message
      error-msg (conj [:span.mdl-selectfield__error error-msg]))))

(def selectfield (wrap-mdl selectfield*))
