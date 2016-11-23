(ns re-mdl.components.chip)

(defn contact
  "Implementation of the MDL chip inner contact component."
  [& {:keys [el child
             id class attr]
      :or   {el :span}
      :as   args}]
  [el
   (merge
    {:id    id
     :class (cond-> "mdl-chip__contact"
              class (str " " class))}
    attr)
   child])

(defn text
  "Implementation of the MDL chip inner text component."
  [& {:keys [el child
             id class attr]
      :or   {el :span}
      :as   args}]
  [el
   (merge
    {:id    id
     :class (cond-> "mdl-chip__text"
              class (str " " class))}
    attr)
   child])

(defn action
  "Implementation of the MDL chip inner action component."
  [& {:keys [el child on-click
             type
             id class attr]
      :or   {el :button}
      :as   args}]
  [el
   (merge
    (cond-> {:id       id
             :on-click on-click
             :class    (cond-> "mdl-chip__action"
                         class (str " " class))}
      type (assoc :type type))
    attr)
   child])

(defn chip
  "Implementation of the MDL chip component."
  [& {:keys [el on-click children
             deletable? contact? button?
             id class attr]
      :or   {el :span}
      :as   args}]
  (into [(if button? :button el)
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
