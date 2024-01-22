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
