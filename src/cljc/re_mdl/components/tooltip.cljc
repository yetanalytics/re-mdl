(ns re-mdl.components.tooltip
  (:require [re-mdl.util :refer [wrap-mdl]]
            #?(:clj [re-mdl.macros :refer [build-class]]))
  #?(:cljs (:require-macros [re-mdl.macros :refer [build-class]])))

(defn tooltip* [& {:keys [el for large? left? right? top? bottom?
                          children
                          id class attr]
                   :or   {el :span}
                   :as   args}]
  (into
   [el
    (merge
     {:id    id
      :for   for
      :class (build-class "mdl-tooltip"
                          class   (str " " class)
                          large?  " mdl-tooltip--large"
                          left?   " mdl-tooltip--left"
                          right?  " mdl-tooltip--right"
                          top?    " mdl-tooltip--top"
                          bottom? " mdl-tooltip--bottom")}
     attr)]
   children))

(def tooltip (wrap-mdl tooltip*))
