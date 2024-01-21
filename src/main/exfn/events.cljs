(ns exfn.events
  (:require [re-frame.core :as rf]
            [exfn.logic :as bf]
            [clojure.set :as set]))


(rf/reg-event-db
 :initialize
 (fn [_ _]
   {
    }))


(rf/reg-event-db
 :reset
 (fn [_ [_ game]]
   game))