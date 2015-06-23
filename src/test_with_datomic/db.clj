(ns test-with-datomic.db
  (:require [datomic.api :only [q db] :as d]))

(defn connect! [uri]
  "Create database and/or establish connection."
  (if (d/create-database uri)
    (let [schema-tx (read-string (slurp "schema.edn"))
          connection (d/connect uri)]
      @(d/transact connection schema-tx)
      connection)
    (d/connect uri)))
