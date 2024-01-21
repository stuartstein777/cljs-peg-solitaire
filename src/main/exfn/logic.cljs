(ns exfn.logic
  (:require [clojure.set :as set]))


;;

(defn generate-board []
  {1  {:id 1
       :has-marble true
       :neighbors  [nil 2 nil 4]}
   2  {:id 2
       :has-marble true
       :neighbors  [nil 3 1 5]}
   3  {:id 3
       :has-marble true
       :neighbors  [nil nil 2 6]}
   4  {:id 4
       :has-marble true
       :neighbors  [1 5 nil 9]}
   5  {:id 5
       :has-marble true
       :neighbors  [2 6 4 10]}
   6  {:id 6
       :has-marble true
       :neighbors  [3 nil 5 11]}
   7  {:id 7
       :has-marble true
       :neighbors  [nil 8 nil 14]}
   8  {:id 8
       :has-marble true
       :neighbors  [nil 9 7 15]}
   9  {:id 9
       :has-marble true
       :neighbors  [4 10 8 16]}
   10 {:id 10
       :has-marble true
       :neighbors  [5 11 9 17]}
   11 {:id 11
       :has-marble true
       :neighbors  [6 12 10 18]}
   12 {:id 12
       :has-marble true
       :neighbors  [nil 13 11 19]}
   13 {:id 13
       :has-marble true
       :neighbors  [nil nil 12 20]}
   14 {:id 14
       :has-marble true
       :neighbors  [7 15 nil 21]}
   15 {:id 15
       :has-marble true
       :neighbors  [8 16 14 22]}
   16 {:id 16
       :has-marble true
       :neighbors  [9 17 15 23]}
   17 {:id 17
       :has-marble false
       :neighbors  [10 18 16 24]}
   18 {:id 18
       :has-marble true
       :neighbors  [11 19 17 25]}
   19 {:id 19
       :has-marble true
       :neighbors  [12 20 18 26]}
   20 {:id 20
       :has-marble true
       :neighbors  [13 nil 19 27]}
   21 {:id 21
       :has-marble true
       :neighbors  [14 22 nil nil]}
   22 {:id 22
       :has-marble true
       :neighbors  [15 23 21 nil]}
   23 {:id 23
       :has-marble true
       :neighbors  [16 24 22 28]}
   24 {:id 24
       :has-marble true
       :neighbors  [17 25 23 29]}
   25 {:id 25
       :has-marble true
       :neighbors  [18 26 24 30]}
   26 {:id 26
       :has-marble true
       :neighbors  [19 27 25 nil]}
   27 {:id 27
       :has-marble true
       :neighbors  [20 nil 26 nil]}
   28 {:id 28
       :has-marble true
       :neighbors  [23 29 nil 31]}
   29 {:id 29
       :has-marble true
       :neighbors  [24 30 28 32]}
   30 {:id 30
       :has-marble true
       :neighbors  [25 nil 29 33]}
   31 {:id 31
       :has-marble true
       :neighbors  [28 32 nil nil]}
   32 {:id 32
       :has-marble true
       :neighbors  [29 33 31 nil]}
   33 {:id 33
       :has-marble true
       :neighbors  [30 nil 32 nil]}})

;; neighbour has to have a marble, that neighbour in same direction
;; has to be empty
;; e.g. to jump east 
;; o o _ o o
;; ^
;; |_ can jump here. since east has marble, and east-east has no marble
;; from is cell id
;; direction is 0, 1, 2, 3
;; 0 is north, 1 is east, 2 is west, 3 is south
(defn can-jump [board from direction]
  (let [direct-neighbour (get-in board [from :neighbors direction])
        direct-neighbour-neighbour (get-in board [direct-neighbour :neighbors direction] nil)
        direct-neighbour-has-marble? (true? (get-in board [direct-neighbour :has-marble]))
        direct-neighbour-neighbour-has-no-marble? (false? (get-in board [direct-neighbour-neighbour :has-marble]))]
    (and direct-neighbour-has-marble? direct-neighbour-neighbour-has-no-marble?)))


(defn get-potential-jumps [board from]
  (let [neighbors (get-in board [from :neighbors])
        can-jump-north (can-jump board from 0)
        can-jump-east (can-jump board from 1)
        can-jump-west (can-jump board from 2)
        can-jump-south (can-jump board from 3)]
    
    [(when can-jump-north (get-in board [(neighbors 0) :neighbors 0])) 
     (when can-jump-east (get-in board [(neighbors 1) :neighbors 1]))
     (when can-jump-west (get-in board [(neighbors 2) :neighbors 2]))
     (when can-jump-south (get-in board [(neighbors 3) :neighbors 3]))]))


(comment
  
  (get-potential-jumps (generate-board) 1)
  
  )


  