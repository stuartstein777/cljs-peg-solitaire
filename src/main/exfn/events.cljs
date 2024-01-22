(ns exfn.events
  (:require [re-frame.core :as rf]
            [exfn.logic :as bf]
            [clojure.set :as set]))


(rf/reg-event-db
 :initialize
 (fn [_ _]
   {:board (bf/generate-board)
    :selected-cell nil
    :valid-targets []}))

(rf/reg-event-db
 :select-cell
 (fn [db [_ cell-id]]
   (let [board (:board db)]
     (if (bf/has-marble? board cell-id)
       (if (= (db :selected-cell) cell-id)
         (-> db
             (dissoc :selected-cell)
             (assoc :targets [])
             )
         (-> db 
             (assoc :selected-cell cell-id)
             (assoc :targets (set (bf/get-potential-jumps board cell-id)))))
       db))))
