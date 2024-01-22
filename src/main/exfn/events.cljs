(ns exfn.events
  (:require [re-frame.core :as rf]
            [exfn.logic :as bf]
            [clojure.set :as set]))

(rf/reg-event-db
 :initialize
 (fn [_ _]
   {:board (bf/generate-board)
    :selected-cell nil
    :remaining-pegs 32
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

(defn translate-direction [direction]
  (case direction
    :north 0
    :east 1
    :west 2
    :south 3))

(rf/reg-event-db
 :jump
 (fn [db [_ target-cell]]
   (let [selected-cell (db :selected-cell)
         direction (->> db
                        :targets
                        (filter #(= target-cell (:cell %)))
                        first
                        :direction
                        translate-direction)
         new-board (bf/jump (:board db) selected-cell target-cell direction)
         remaining-pegs (bf/pegs-remaining new-board)
         game-over? (not (bf/any-jumps-remaining new-board))]
     (-> db
         (assoc :board new-board)
         (assoc :selected-cell nil)
         (assoc :remaining-pegs remaining-pegs)
         (assoc :targets #{})
         (assoc :game-over? game-over?)))))

(comment
  (let [targets #{{:direction :north, :cell nil} {:direction :east, :cell 17} {:direction :west, :cell nil} {:direction :south, :cell nil}}
        target-cell 17]
    
    (->> targets
         (filter #(= target-cell (:cell %)))
         (first)
         :direction
         translate-direction)
    )
  )