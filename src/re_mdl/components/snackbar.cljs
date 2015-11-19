(ns re-mdl.components.snackbar)

(defn toast! [& {:keys [message action-handler action-text timeout] :as args}]
  (-> (.querySelector js/document ".mdl-js-snackbar")
      .-MaterialSnackbar
      (.showSnackbar (clj->js args))))
