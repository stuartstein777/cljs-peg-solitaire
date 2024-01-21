(ns exfn.app
  (:require [reagent.dom :as dom]
            [re-frame.core :as rf]
            [exfn.subscriptions]
            [exfn.events]
            [exfn.logic :as ms]
            [goog.string.format]))

(defn pad-zero [n]
  (if (< n 10)
    (str "0" n)
    (str n)))



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
      " other projects"]]]])

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