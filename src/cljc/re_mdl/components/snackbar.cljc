(ns re-mdl.components.snackbar
  (:require [re-mdl.util :refer [wrap-mdl]]))


(defn snackbar-target* []
  [:div
   {:class "mdl-js-snackbar mdl-snackbar"
    :aria-live "assertive"
    :aria-atomic true
    :aria-relevant "text"}

   [:div.mdl-snackbar__text]
   [:button.mdl-snackbar__action]])


(def snackbar-target
  (wrap-mdl snackbar-target*))


(defn snackbar! [& {:keys [message actionHandler actionText timeout] :as args}]
  #?(:cljs
     (-> (.querySelector js/document ".mdl-js-snackbar")
         .-MaterialSnackbar
         (.showSnackbar (clj->js args)))
     :clj (throw (Exception. "Snackbar doesn't do anything in clj"))))
