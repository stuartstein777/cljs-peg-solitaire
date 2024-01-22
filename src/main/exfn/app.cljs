(ns exfn.app
  (:require [reagent.dom :as dom]
            [re-frame.core :as rf]
            [exfn.subscriptions]
            [exfn.events]
            [exfn.logic :as lgc]
            [goog.string.format]))

;; -- App -------------------------------------------------------------------------
(defn app []
  (let [board @(rf/subscribe [:board])
        selected-cell @(rf/subscribe [:selected-cell])]
    [:div.container
     [:div.row
      [:div.col.col-lg-8
       [:h1 "Peg Solitaire"]]
      [:div.col.col-lg-4 {:style {:text-align :right
                                  :padding-right 50}}
       [:i.fab.fa-github]
       [:a {:href "https://github.com/stuartstein777/peg-solitaire"
            :style {:text-decoration :none}}
        " (repo) "]
       "|"
       [:a {:href "https://stuartstein777.github.io/"
            :style {:text-decoration :none}}
        " other projects"]]]
     [:div.row
      [:div.col.col-lg-12
       [:div.board
        (for [c (range 1 49)]
          (let [cell (lgc/get-cell-id c)]
            (if (= cell -1)
              [:div.inactive]
              [:div
               {:on-click #(rf/dispatch-sync [:select-cell cell])
                :class (if (= cell selected-cell)
                         "selected"
                         "board-cell")}
               [:div.concave
                (when (lgc/has-marble? board cell)
                  [:div.marble])]])))]]]]))

;; -- After-Load --------------------------------------------------------------------
;; Do this after the page has loaded.
;; Initialize the initial db state.
(defn ^:dev/after-load start
  []
  (dom/render [app]
              (.getElementById js/document "app")))

(defn ^:export init []
  (start))

; dispatch the event which will create the initial state. 
(defonce initialize (rf/dispatch-sync [:initialize]))

(comment

  "Some dev-test events for reseting board."
  (rf/dispatch-sync [:set-game-over])

  
 

  )