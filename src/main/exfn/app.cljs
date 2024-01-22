(ns exfn.app
  (:require [reagent.dom :as dom]
            [re-frame.core :as rf]
            [exfn.subscriptions]
            [exfn.events]
            [exfn.logic :as ms]
            [goog.string.format]))



;; -- App -------------------------------------------------------------------------
(defn app []
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
      [:div.board-cell.inactive]
      [:div.board-cell.inactive]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell.inactive]
      [:div.board-cell.inactive]
      [:div.board-cell.inactive]
      [:div.board-cell.inactive]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell.inactive]
      [:div.board-cell.inactive]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell
       [:div.marble]]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell.inactive]
      [:div.board-cell.inactive]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell.inactive]
      [:div.board-cell.inactive]
      [:div.board-cell.inactive]
      [:div.board-cell.inactive]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell]
      [:div.board-cell.inactive]
      [:div.board-cell.inactive]]]]])

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