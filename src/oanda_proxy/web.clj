(ns oanda_proxy.web
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [clj-http.client :as client]
            [environ.core :refer [env]]))

(def base-url (:base-url env))
(def token (:access-token env))

(defroutes app
  (GET "*" req
       (print (:params req))
       (client/get (str base-url (:uri req))
                          {:headers {:Authorization (str "Bearer " token)}
                           :query-params (-> req :params (dissoc :*))})))

(defn -main [& args]
  (let [port (Integer. (or (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))

;; For interactive development:
;; (.stop server)
;; (def server (-main))
