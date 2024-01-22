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
         remaining-jumps (bf/remaining-jumps new-board)
         game-over?  (and
                      (> remaining-pegs 1)
                      (= (count remaining-jumps) 0))]
     (-> db
         (assoc :board new-board)
         (assoc :selected-cell nil)
         (assoc :remaining-pegs remaining-pegs)
         (assoc :targets #{})
         (assoc :remaining-jumps remaining-jumps)
         (assoc :game-over? game-over?)
         (assoc :game-win? (= remaining-pegs 1))))))

(comment
  (let [targets #{{:direction :north, :cell nil} {:direction :east, :cell 17} {:direction :west, :cell nil} {:direction :south, :cell nil}}
        target-cell 17]
    
    (->> targets
         (filter #(= target-cell (:cell %)))
         (first)
         :direction
         translate-direction)
    )
  
  (rf/reg-event-db
   :set-board-near-win
   (fn [db _]
     {:board {1  {:id         1
                  :has-marble false
                  :neighbors  [nil 2 nil 4]}
              2  {:id         2
                  :has-marble false
                  :neighbors  [nil 3 1 5]}
              3  {:id         3
                  :has-marble false
                  :neighbors  [nil nil 2 6]}
              4  {:id         4
                  :has-marble false
                  :neighbors  [1 5 nil 9]}
              5  {:id         5
                  :has-marble false
                  :neighbors  [2 6 4 10]}
              6  {:id         6
                  :has-marble false
                  :neighbors  [3 nil 5 11]}
              7  {:id         7
                  :has-marble false
                  :neighbors  [nil 8 nil 14]}
              8  {:id         8
                  :has-marble false
                  :neighbors  [nil 9 7 15]}
              9  {:id         9
                  :has-marble false
                  :neighbors  [4 10 8 16]}
              10 {:id         10
                  :has-marble false
                  :neighbors  [5 11 9 17]}
              11 {:id         11
                  :has-marble false
                  :neighbors  [6 12 10 18]}
              12 {:id         12
                  :has-marble false
                  :neighbors  [nil 13 11 19]}
              13 {:id         13
                  :has-marble false
                  :neighbors  [nil nil 12 20]}
              14 {:id         14
                  :has-marble false
                  :neighbors  [7 15 nil 21]}
              15 {:id         15
                  :has-marble false
                  :neighbors  [8 16 14 22]}
              16 {:id         16
                  :has-marble true
                  :neighbors  [9 17 15 23]}
              17 {:id         17
                  :has-marble false
                  :neighbors  [10 18 16 24]}
              18 {:id         18
                  :has-marble true
                  :neighbors  [11 19 17 25]}
              19 {:id         19
                  :has-marble true
                  :neighbors  [12 20 18 26]}
              20 {:id         20
                  :has-marble false
                  :neighbors  [13 nil 19 27]}
              21 {:id         21
                  :has-marble false
                  :neighbors  [14 22 nil nil]}
              22 {:id         22
                  :has-marble false
                  :neighbors  [15 23 21 nil]}
              23 {:id         23
                  :has-marble false
                  :neighbors  [16 24 22 28]}
              24 {:id         24
                  :has-marble false
                  :neighbors  [17 25 23 29]}
              25 {:id         25
                  :has-marble false
                  :neighbors  [18 26 24 30]}
              26 {:id         26
                  :has-marble false
                  :neighbors  [19 27 25 nil]}
              27 {:id         27
                  :has-marble false
                  :neighbors  [20 nil 26 nil]}
              28 {:id         28
                  :has-marble false
                  :neighbors  [23 29 nil 31]}
              29 {:id         29
                  :has-marble false
                  :neighbors  [24 30 28 32]}
              30 {:id         30
                  :has-marble false
                  :neighbors  [25 nil 29 33]}
              31 {:id         31
                  :has-marble false
                  :neighbors  [28 32 nil nil]}
              32 {:id         32
                  :has-marble false
                  :neighbors  [29 33 31 nil]}
              33 {:id         33
                  :has-marble false
                  :neighbors  [30 nil 32 nil]}}
      :remaining-pegs 3
      :game-win? false
      :game-over? false}))
      
)