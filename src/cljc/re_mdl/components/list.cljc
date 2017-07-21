(ns re-mdl.components.list
  (#?(:clj :require
      :cljs :require-macros) [re-mdl.macros :refer [build-class]]))

(defn item-primary-content [& {:keys [el icon avatar child
                                      children
                                      id class attr]
                               :or   {el :span}
                               :as   args}]
  (assert (not (and icon avatar)) "Can't have both icon and avatar")
  (into
   [el
    (merge
     {:id    id
      :class (build-class "mdl-list__item-primary-content"
                          class (str " " class))}
     attr)
    (when-let [icon-name (or icon avatar)]
      [:i.material-icons
       {:class (if icon
                 "mdl-list__item-icon"
                 "mdl-list__item-avatar")}
       icon-name])
    child]
   children))

(defn item-secondary-content [& {:keys [el child
                                        children
                                        id class attr]
                                 :or   {el :span}
                                 :as   args}]
  (into
   [el
    (merge
     {:id    id
      :class (build-class "mdl-list__item-secondary-content"
                          class (str " " class))}
     attr)
    child]
   children))

(defn item-secondary-action [& {:keys [child el href
                                       children
                                       id class attr]
                                :or   {el :span}
                                :as   args}]
  (assert (not (when href
                 (not= el :a)))
          "href only valid on <a>")
  (into
   [el
    (merge
     (cond-> {:id    id
              :class (build-class "mdl-list__item-secondary-action"
                                  class (str " " class))}
       href (assoc :href href))
     attr)
    child]
   children))

(defn item-secondary-info [& {:keys [el child
                                     children
                                     id class attr]
                              :or   {el :span}
                              :as   args}]
  (into
   [el
    (merge
     {:id    id
      :class (build-class "mdl-list__item-secondary-info"
                          class (str " " class))}
     attr)
    child]
   children))

(defn item-text-body [& {:keys [el child
                                children
                                id class attr]
                         :or   {el :span}
                         :as   args}]
  (into
   [el
    (merge
     {:id    id
      :class (build-class "mdl-list__item-text-body"
                          class (str " " class))}
     attr)
    child]
   children))

(defn item-sub-title [& {:keys [el child
                                children
                                id class attr]
                         :or   {el :span}
                         :as   args}]
  (into
   [el
    (merge {:id    id
            :class (build-class "mdl-list__item-sub-title"
                                class (str " " class))}
           attr)
    child]
   children))

(defn item [& {:keys [item-type
                      children
                      id class attr]
               :or   {item-type :one-line}
               :as   args}]
  (assert (#{:one-line
             :two-line
             :three-line} item-type) (str "Invalid item-type :" item-type))

  (into
   [:li
    (merge
     {:id    id
      :class (build-class (str "mdl-list__item mdl-list__item"
                               (case item-type
                                 :two-line   "--two-line"
                                 :three-line "--three-line"
                                 nil))
                          class (str " " class))}
     attr)]
   children))

(defn list-coll [& {:keys [children
                           id class attr]
                    :as   args}]
  (into
   [:ul.mdl-list
    (merge {:id    id
            :class class}
           attr)]
   children))
