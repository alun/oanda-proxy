(ns oanda-proxy.web
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [clj-http.client :as client]
            [environ.core :refer [env]]))

(def practice-base "https://api-fxpractice.oanda.com")
(def token "e3aceb850d4aefc315793b5816e4ae3f-b2087cd73a4951bcfcf1cbea1430ea2f")

(defroutes app
  (GET "*" req
       (print (:params req))
       (client/get (str practice-base (:uri req))
                          {:headers {:Authorization (str "Bearer " token)}
                           :query-params (-> req :params (dissoc :*))})))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))

;; For interactive development:
;; (.stop server)
;; (def server (-main))
