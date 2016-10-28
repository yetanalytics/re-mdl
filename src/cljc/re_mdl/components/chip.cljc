(ns re-mdl.components.chip)

(defn contact
  "Implementation of the MDL chip inner contact component."
  [& {:keys [el content
             id class attr]
      :or   {el :span}
      :as   args}]
  [el
   (merge
    {:id    id
     :class (cond-> "mdl-chip__contact"
              class (str " " class))}
    attr)
   content])

(defn text
  "Implementation of the MDL chip inner text component."
  [& {:keys [el content
             id class attr]
      :or   {el :span}
      :as   args}]
  [el
   (merge
    {:id    id
     :class (cond-> "mdl-chip__text"
              class (str " " class))}
    attr)
   content])

(defn action
  "Implementation of the MDL chip inner action component."
  [& {:keys [el content on-click
             button?
             id class attr]
      :or   {el :button}
      :as   args}]
  [el
   (merge
    (cond-> {:id       id
             :on-click on-click
             :class    (cond-> "mdl-chip__action"
                         class (str " " class))}
      button? (assoc :type button?))
    attr)
   content])

(defn chip
  "Implementation of the MDL chip component."
  [& {:keys [el on-click children
             deletable? contact? button?
             id class attr]
      :or   {el :span}
      :as   args}]
  (into [el
         (merge
          (cond-> {:id       id
                   :on-click on-click
                   :class    (cond-> "mdl-chip"
                               class      (str " " class)
                               contact?   (str " mdl-chip--contact")
                               deletable? (str " mdl-chip--deletable"))}
            button? (assoc :type button?))
          attr)]
        children))
