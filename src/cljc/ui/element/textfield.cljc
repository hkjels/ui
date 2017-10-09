(ns ui.element.textfield
  (:require [#?(:clj clojure.core :cljs reagent.core) :refer [atom]]
            [clojure.spec :as spec]
            [clojure.test.check.generators :as gen]
            [ui.util :as util]
            [clojure.string :as str]))


(spec/def ::maybe-fn
  (spec/with-gen fn?
    (gen/return (constantly nil))))


;; Events
(spec/def ::on-change ::maybe-fn)
(spec/def ::on-focus ::maybe-fn)
(spec/def ::on-blur ::maybe-fn)
(spec/def ::on-key-up ::maybe-fn)
(spec/def ::on-key-down ::maybe-fn)


;; Parameters
(spec/def ::id (spec/and string? #(re-find #"(?i)(\w+)" %)))
(spec/def ::placeholder #(or (string? %) (nil? %)))
(spec/def ::label string?)
(spec/def ::value string?)
(spec/def ::disabled boolean?)
(spec/def ::read-only boolean?)
(spec/def ::focus boolean?)
(spec/def ::params
  (spec/keys :opt-un [::id
                      ::value
                      ::placeholder
                      ::label
                      ::disabled
                      ::read-only
                      ::focus
                      ::on-change
                      ::on-focus
                      ::on-blur
                      ::on-key-up
                      ::on-key-down]))


(spec/def ::args (spec/cat :params ::params))


(defn textfield
  [{:keys [id]
    :or   {id (util/gen-id)}}]
  (fn [& args]
    (let [{:keys [params]}            (util/conform-or-fail ::args args)
          {:keys [style
                  placeholder
                  label
                  focus
                  value]
           :or   {style       {}
                  placeholder ""}} params
          ui-params                   (util/keys-from-spec ::params)
          class                       (str/join " " [(util/params->classes params)
                                                     (when (or (not (empty? value))
                                                               (not (empty? placeholder))) "dirty")])]
      [:div.Textfield {:key   (str "textfield-" id)
                       :style style
                       :class class}
       [:input (merge
                (dissoc params :class :style :placeholder)
                {:type          :text
                 :placeholder   placeholder
                 :auto-complete "off"})]
       (when-not (empty? label)
         [:label {:for id} label])])))


(spec/fdef textfield
           :args ::args
           :ret vector?)
