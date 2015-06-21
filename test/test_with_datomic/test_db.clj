(ns test-with-datomic.test-db
  (:require [datomic.api :only [q db] :as d]))


(def uri "datomic:mem://in-memory-unit-test-db")

(defn connect! []
  "Create database and/or establish connection."
  (if (d/create-database uri)
    (let [schema-tx (read-string (slurp "schema.edn"))
          connection (d/connect uri)]
      @(d/transact connection schema-tx)
      connection)
    (d/connect uri)))
