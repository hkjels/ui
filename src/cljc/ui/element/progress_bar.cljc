(ns ui.element.progress-bar
  (:require [garden.units :as unit]))


(defn style
  [{:keys [primary]}]
  [[:.Progress-bar {:position :fixed
                    :top      0
                    :left     0
                    :width    (unit/percent 100)
                    :height   (unit/rem 0.25)
                    :z-index  100}
    [:.Progress {:background primary
                 :height     (unit/percent 100)
                 :transition [[:200ms :ease]]
                 :transform  "translateZ(0)"
                 :width      0}]]])


(defn progress-bar
  [{:keys [progress]}]
  [:div.Progress-bar
   [:div.Progress {:style {:width (str progress "%")}}]])
