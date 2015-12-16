(ns re-mdl.components.badge-test
  (:require [cljs.test :refer-macros [deftest is use-fixtures]]
            [re-mdl.components.badge :as badge]
            [cljs-react-test.simulate :as sim]
            [cljs-react-test.utils :as tu]
            [dommy.core :as dommy :refer-macros [sel1]]
            [reagent.core :as r]
            [clojure.string :as string]))


;; util

(def ^:dynamic c)

(use-fixtures
  :each (fn [test-fn]
          (binding [c (tu/new-container!)]
            (test-fn)
            (tu/unmount! c))))

(defn check-class [comp class]
  (is (= class (.-className comp))))

(defn check-label [comp label]
  (is (= label (.getAttribute comp "data-badge"))))

(defn check-text [comp text]
  (is (= text (.-innerHTML comp))))

(defn check-component [comp {:keys [class label text el]
                             :or {el :span}}]
  (r/render comp c)
  (let [node (sel1 c [el])]
    (doto node
      (check-class class)
      (check-label label)
      (check-text text))))


;; tests

(deftest base
  (let [badge [badge/badge]]
    (check-component badge {:label nil
                            :class "mdl-badge"
                            :text ""})))

(deftest id
  (let [badge [badge/badge
               :el :div]]
    (check-component badge {:label nil
                            :class "mdl-badge"
                            :text ""
                            :el :div})))

(deftest badge-label
  (let [badge [badge/badge
               :badge-label "3"]]
    (check-component badge {:label "3"
                            :class "mdl-badge"
                            :text ""})))

(deftest child
  (let [badge [badge/badge
               :child "foobar"]]
    (check-component badge {:label nil
                            :class "mdl-badge"
                            :text "foobar"})))

(deftest no-background?
  (let [badge [badge/badge
               :no-background? true]]
    (check-component badge {:label nil
                            :class "mdl-badge mdl-badge--no-background"
                            :text ""})))

(deftest overlap?
  (let [badge [badge/badge
               :overlap? true]]
    (check-component badge {:label nil
                            :class "mdl-badge mdl-badge--overlap"
                            :text ""})))

(deftest icon?
  (let [badge [badge/badge
               :icon? true]]
    (check-component badge {:label nil
                            :class "mdl-badge material-icons"
                            :text ""})))

(deftest classes
  (let [badge [badge/badge
               :no-background? true
               :overlap? true
               :icon? true]]
    (check-component badge {:label nil
                            :class (string/join " "
                                                ["mdl-badge"
                                                 "mdl-badge--no-background"
                                                 "mdl-badge--overlap"
                                                 "material-icons"])
                            :text ""})))

(deftest attr
  (let [badge [badge/badge
               :attr {:class "overwritten"}]]
    (check-component badge {:label nil
                            :class "overwritten"
                            :text ""})))
