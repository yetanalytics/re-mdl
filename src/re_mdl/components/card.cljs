(ns re-mdl.components.card
  (:require [reagent.core :as r]))

(defn title [& {:keys [text subtitle-text header
                       border?
                       expand?
                       id class attr]
                 :or   {header :h1}
                 :as   args}]

  [:div
   (merge
    {:id id
     :class (cond-> "mdl-card__title"
              class (str " " class)
              border? (str " mdl-card--border")
              expand? (str " mdl-card--expand"))}
    attr)
   (when text [header {:class "mdl-card__title-text"}
                text])
   (when subtitle-text
     [:div.mdl-card__subtitle-text
      subtitle-text])])

(defn media [& {:keys [children
                       border?
                       id class attr]
                :as   args}]
  (into [:div
         (merge
          {:id id
           :class (cond-> "mdl-card__media"
                    class (str " " class)
                    border? (str " mdl-card--border"))}
          attr)] children))



(defn supporting-text [& {:keys [text
                                 border?
                                 id class attr]
                           :as   args}]
  [:div
   (merge
    {:id id
     :class (cond-> "mdl-card__supporting-text"
              class (str " " class)
              border? (str " mdl-card--border"))}
    attr)
   text])




(defn actions [& {:keys [children
                          border?
                          id class attr]
                   :as   args}]
  (into
   [:div
    (merge
     {:id id
      :class (cond-> "mdl-card__actions"
               class (str " " class)
               border? (str " mdl-card--border"))}
     attr)]
   children))



(def valid-shadows #{2 3 4 6 8 16})

(defn card
  [& {:keys [children shadow
             id class attr]
      :as   args}]

  (when shadow (assert (valid-shadows shadow)))

  (into [:div
         (merge
          {:id id
           :class (cond-> "mdl-card"
                    class (str " " class)
                    shadow (str " mdl-shadow--" shadow "dp"))}
          attr)]
        children))
