(ns re-mdl.components.list)

(defn item-primary-content
  [& {:keys [el icon avatar child
             children
             id class attr]
      :or   {el :span}
      :as   args}]
  (assert (not (and icon avatar)) "Can't have both icon and avatar")
  (cond-> [el
           (merge {:id id
                   :class (cond-> "mdl-list__item-primary-content"
                            class (str " " class))}
                  attr)

           (when-let [icon-name (or icon avatar)]
             [:i.material-icons
              {:class (if icon
                        "mdl-list__item-icon"
                        "mdl-list__item-avatar")}
              icon-name])
           child]
    children (into children)))

(defn item-secondary-content
  [& {:keys [el child children
             id class attr]
      :or   {el :span}
      :as   args}]
  (into [el
         (merge {:id id
                 :class (cond-> "mdl-list__item-secondary-content"
                          class (str " " class))}
                attr)
         child]
        children))

(defn item-secondary-action
  [& {:keys [child children
             el href icon
             id class attr]
      :as   args
      :or {el :span}}]
  (assert (not (and (= el :span)
                    (or href icon)))
          "href and icon only valid on <a>")
  (into [el
         (merge (cond-> {:id id
                         :class (cond-> "mdl-list__item-secondary-action"
                                  class (str " " class))}
                  href (assoc :href href))
                attr)

         (or child
             [:i.material-icons icon])]
        children))

(defn item-secondary-info
  [& {:keys [el child children
             id class attr]
      :or   {el :span}
      :as   args}]
  (into [el
         (merge {:id id
                 :class (cond-> "mdl-list__item-secondary-info"
                          class (str " " class))}
                attr)

         child]
        children))

(defn item-text-body
  [& {:keys [el child children
             id class attr]
      :or   {el :span}
      :as   args}]
  (into [el
         (merge {:id id
                 :class (cond-> "mdl-list__item-text-body"
                          class (str " " class))}
                attr)
         child]
        children))

(defn item-sub-title
  [& {:keys [el child children
             id class attr]
      :or   {el :span}
      :as   args}]
  (into [el
         (merge {:id id
                 :class (cond-> "mdl-list__item-sub-title"
                          class (str " " class))}
                attr)
         child]
        children))


(defn item
  [& {:keys [children
             item-type
             id class attr]
      :as   args
      :or {item-type :one-line}}]

  (assert (#{:one-line
             :two-line
             :three-line} item-type) (str "Invalid item-type :" item-type))

  (into [:li (merge
              {:id id
               :class (cond-> (str "mdl-list__item mdl-list__item"
                                   (case item-type
                                     :two-line "--two-line"
                                     :three-line "--three-line"
                                     nil))
                        class (str " " class))}
              attr)]
        children))

(defn list-coll
  [& {:keys [children
             id class attr]
      :as   args}]
  (into [:ul.mdl-list
         (merge {:id id
                 :class class}
                attr)]
        children))
