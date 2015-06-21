(ns test-with-datomic.user
  (:require [datomic.api :only [q db] :as d]))

(defn get-user [conn id]
  (let [player-data (d/q '[:find ?e ?n
                           :in $ ?id
                           :where [?e :player/username ?n]]
                      (d/db conn) id)
        [user-id username] (first (vec player-data))]
    {:player-id user-id :player/username username}))

(defn create-user! [conn username]
  (let [user {:db/id (d/tempid :player)
              :player/username username}]
    @(d/transact conn [user])))
