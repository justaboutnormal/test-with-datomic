(ns test-with-datomic.user-tests
  (:require [clojure.test :refer :all]
            [test-with-datomic.test-db :as test-db]
            [test-with-datomic.user :refer :all]
            [datomic.api :only [q db] :as d]))


(deftest user-db-tests
  (testing "User creation and retrieval"
    (let [conn (test-db/test-connect!)
          username "joe"
          user-tx (create-user! conn username) ;Creating a user is implicitly tested in the get-user test. How could I create its own test?
          ids (:tempids user-tx)
          [temp-id] (keys ids)
          user-id (get ids temp-id)]
      (is (= (get-user conn user-id) {:player-id user-id :player/username username})))))
