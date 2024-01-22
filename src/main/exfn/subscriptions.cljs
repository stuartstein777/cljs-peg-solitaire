(ns exfn.subscriptions
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :board
 (fn [db _]
   (:board db)))

(rf/reg-sub
 :selected-cell
 (fn [db _]
   (:selected-cell db)))

(rf/reg-sub
 :targets
 (fn [db _]
   (:targets db)))

(rf/reg-sub
 :remaining-pegs
 (fn [db _]
   (:remaining-pegs db)))

(rf/reg-sub
 :game-over?
 (fn [db _]
   (:game-over? db)))

(rf/reg-sub
 :game-win?
 (fn [db _]
   (:game-win? db)))