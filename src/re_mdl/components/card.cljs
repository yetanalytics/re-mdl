(ns re-mdl.components.card
  (:require [reagent.core :as r]))

(defn title [& {:keys [text subtitle-text header
                       border?
                       expand?
                       class attr]
                 :or   {header :h1}
                 :as   args}]

  [:div
   (r/merge-props
    {:class (cond-> "mdl-card__title"
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
                        class attr]
                 :as   args}]
  (into [:div
         (r/merge-props
          {:class (cond-> "mdl-card__media"
                    class (str " " class)
                    border? (str " mdl-card--border"))}
          attr)] children))



(defn supporting-text [& {:keys [text
                                 border?
                                 class attr]
                           :as   args}]
  [:div
   (r/merge-props
    {:class (cond-> "mdl-card__supporting-text"
              class (str " " class)
              border? (str " mdl-card--border"))}
    attr)
   text])




(defn actions [& {:keys [children
                          border?
                          class attr]
                   :as   args}]
  (into
   [:div
    (r/merge-props
     {:class (cond-> "mdl-card__actions"
               class (str " " class)
               border? (str " mdl-card--border"))}
     attr)]
   children))



(def valid-shadows #{2 3 4 6 8 16})

(defn card
  [& {:keys [children shadow
             class attr]
      :as   args}]

  (when shadow (assert (valid-shadows shadow)))

  (into [:div
         (r/merge-props
          {:class (cond-> "mdl-card"
                    class (str " " class)
                    shadow (str " mdl-shadow--" shadow "dp"))}
          attr)]
        children))



;; DEMO

(defn card-demo []
  [:div.mdl-cell
   [card
    :shadow 2
    :children
    [[title
      :expand? true
      :header :h4
      :text "Card"]
     [actions
      :border? true
      :children
      [[:a.mdl-button.mdl-button--colored.mdl-js-button.mdl-js-ripple-effect
        "Action"]
       ]]]]])
