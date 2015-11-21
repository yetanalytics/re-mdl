(ns re-mdl.components.snackbar)

(defn toast! [& {:keys [message action-handler action-text timeout] :as args}]
  #?(:cljs
     (-> (.querySelector js/document ".mdl-js-snackbar")
         .-MaterialSnackbar
         (.showSnackbar (clj->js args)))
     :clj (throw (Exception. "Toast doesn't do anything in clj"))))
