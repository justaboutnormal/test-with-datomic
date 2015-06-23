(ns test-with-datomic.test-db
  (:require [test-with-datomic.db :as d]))


(def uri "datomic:mem://in-memory-unit-test-db")

(defn test-connect! []
  (d/connect! uri))
