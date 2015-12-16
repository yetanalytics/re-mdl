(ns re-mdl.components.button-test
  (:require [cljs.test :refer-macros [deftest is use-fixtures]]
            [re-mdl.components.button :as button]
            [cljsjs.material]
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

(defn check-id [comp id]
  (is (= id (.-id comp))))

(defn check-mdl-upgraded [comp]
  (is (= ",MaterialButton" (.getAttribute comp "data-upgraded"))))

(defn check-label-or-icon [comp label-or-icon]
  (is (or
       (= label-or-icon (.-innerHTML comp))
       (when-let [inner (sel1 comp [:i])]
         (and
          (= (.-className inner) "material-icons")
          (= (.-innerHTML inner) label-or-icon))))))

(defn check-disabled? [comp disabled?]
  (is (= disabled? (.getAttribute comp "disabled"))))

(defn check-for [comp for]
  (is (= for (.getAttribute comp for))))

(defn check-component [comp {:keys [id class label for icon disabled? el]
                             :or {el :button
                                  class "mdl-button mdl-js-button"
                                  id ""}}]
  (r/render comp c)
  (let [node (sel1 c [el])]
    (doto node
      (check-id id)
      (check-class class)
      check-mdl-upgraded
      (check-label-or-icon (or label icon ""))
      (check-disabled? disabled?)
      (check-for for))))


(defn test-mdl-class-args
  "for boolean class args, given a component and map of args to appended
   classes, check em all!"
  [comp args-class-map]
  (doseq [[k c-str] args-class-map
          :let [comp-vec [comp
                          k true]]]
    (check-component comp-vec {:class (str "mdl-button mdl-js-button " c-str)})))

;; tests

(deftest base
  (let [button [button/button]]
    (check-component button {:class "mdl-button mdl-js-button"})))

(deftest label
  (let [button [button/button
               :label "Foo"]]
    (check-component button {:label "Foo"})))


(deftest icon
  (let [button [button/button
                :icon "add"]]
    (check-component button {:icon "add"
                             :class "mdl-button mdl-js-button mdl-button--icon"})))

(deftest disabled?
  (let [button [button/button
                :disabled true]]
    (check-component button {:class "mdl-button mdl-js-button"})))

(deftest for*
  (let [button [button/button
                :for "some_id"]]
    (check-component button {:class "mdl-button mdl-js-button"})))

(deftest classes
  (test-mdl-class-args button/button {:raised? "mdl-button--raised"
                                      :fab? "mdl-button--fab"
                                      :mini-fab? "mdl-button--fab mdl-button--mini-fab"
                                      :colored? "mdl-button--colored"
                                      :primary? "mdl-button--primary"
                                      :accent? "mdl-button--accent"
                                      :ripple-effect? "mdl-js-ripple-effect"}))

(deftest attr
  (let [button [button/button
                :id "foo"
                :attr {:id "overwritten"}]]
    (check-component button {:id "overwritten"})))
