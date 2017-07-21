(ns re-mdl.components.card
  (#?(:clj :require
      :cljs :require-macros) [re-mdl.macros :refer [build-class]]))

(defn subtitle [& {:keys [child el
                          children
                          id class attr]
                   :or   {el :div}}]
  (into
   [el
    (merge
     {:id    id
      :class (build-class "mdl-card__subtitle-text"
                          class (str " " class))}
     attr)
    child]
   children))

(defn title [& {:keys [child el header
                       border? expand?
                       children
                       id class attr]
                :or   {el     :div
                       header :h1}
                :as   args}]
  (into
   [el
    (merge
     {:id    id
      :class (build-class "mdl-card__title"
                          class   (str " " class)
                          border? " mdl-card--border"
                          expand? " mdl-card--expand")}
     attr)
    (when child
      [header {:class "mdl-card__title-text"}
       child])]
   children))

(defn media [& {:keys [border? el
                       children
                       id class attr]
                :or   {el :div}
                :as   args}]
  (into
   [el
    (merge
     {:id id
      :class (build-class "mdl-card__media"
                          class (str " " class)
                          border? " mdl-card--border")}
     attr)]
   children))

(defn supporting-text [& {:keys [border? el
                                 children
                                 id class attr]
                          :or   {el :div}
                          :as   args}]
  (into
   [el
    (merge
     {:id id
      :class (build-class "mdl-card__supporting-text"
                          class (str " " class)
                          border? " mdl-card--border")}
     attr)]
   children))

(defn actions [& {:keys [el border?
                         children
                         id class attr]
                  :or   {el :div}
                  :as   args}]
  (into
   [el
    (merge
     {:id id
      :class (build-class "mdl-card__actions"
                          class   (str " " class)
                          border? " mdl-card--border")}
     attr)]
   children))

(defn menu [& {:keys [el
                      children
                      id class attr]
               :or   {el :div}
               :as   args}]
  (into
   [el
    (merge
     {:id    id
      :class (build-class "mdl-card__menu"
                          class (str " " class))}
     attr)]
   children))

(def valid-shadows #{2 3 4 6 8 16})

(defn card
  [& {:keys [el shadow
             children
             id class attr]
      :or   {el :div}
      :as   args}]

  (when shadow (assert (valid-shadows shadow)))

  (into
   [el
    (merge
     {:id id
      :class (build-class "mdl-card"
                          class  (str " " class)
                          shadow (str " mdl-shadow--" shadow "dp"))}
     attr)]
   children))
