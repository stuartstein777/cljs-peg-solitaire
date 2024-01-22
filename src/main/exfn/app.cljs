(ns exfn.app
  (:require [reagent.dom :as dom]
            [re-frame.core :as rf]
            [exfn.subscriptions]
            [exfn.events]
            [exfn.logic :as lgc]
            [goog.string.format]))



;; -- App -------------------------------------------------------------------------
(defn app []
  (let [board @(rf/subscribe [:board])]
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
        [:div.inactive
         ]
        [:div.inactive]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 1)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 2)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 3)
            [:div.marble])]]
        [:div.inactive]
        [:div.inactive]
        [:div.inactive]
        [:div.inactive]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 4)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 5)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 6)
            [:div.marble])]]
        [:div.inactive]
        [:div.inactive]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 7)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 8)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 9)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 10)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 11)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 12)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 13)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 14)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 15)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 16)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 17)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 18)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 19)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 20)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 21)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 22)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 23)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 24)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 25)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 26)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 27)
            [:div.marble])]]
        [:div.inactive]
        [:div.inactive]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 28)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 29)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 30)
            [:div.marble])]]
        [:div.inactive]
        [:div.inactive]
        [:div.inactive]
        [:div.inactive]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 31)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 32)
            [:div.marble])]]
        [:div.board-cell
         [:div.concave
          (when (lgc/has-marble? board 33)
            [:div.marble])]]
        [:div.inactive]
        [:div.inactive]]]]]))

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