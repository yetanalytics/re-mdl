(ns re-mdl.test-helpers
  (:require
   [cljs.test :refer-macros [is]]))

(defn check-id [comp id]
  (is (= id (.-id comp))))

(defn check-class [comp class]
  (is (= class (.-className comp))))

(defn check-attribute [comp key val]
  (is (= val (.getAttribute comp key))))

(defn check-inner-html [comp text]
  (is (= text (.-innerHTML comp))))
