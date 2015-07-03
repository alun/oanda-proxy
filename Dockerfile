FROM clojure
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY project.clj /usr/src/app/
RUN lein deps
COPY . /usr/src/app
RUN lein uberjar
CMD ["java", "-cp", "target/oanda-proxy.jar", "clojure.main", "-m", "oanda_proxy.web"]
