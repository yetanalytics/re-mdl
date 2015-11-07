(ns re-mdl.components.snackbar
  (:require [reagent.core :as r]
            [re-mdl.util :refer [wrap-mdl]]))

(defn toast! [& {:keys [message action-handler action-text timeout] :as args}]
  (print args)
  (-> (.querySelector js/document ".mdl-js-snackbar")
      .-MaterialSnackbar
      (.showSnackbar (clj->js args))))
