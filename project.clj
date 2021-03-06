(defproject diegoscheduler "0.2.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0-RC1"]
                 [org.danielsz/system "0.1.4"]
                 [environ "1.0.0"]
                 [http-kit "2.1.18"]
                 [clj-http "1.1.2" :exclusions [org.clojure/tools.reader]]
                 [compojure "1.3.4"]
                 [com.cognitect/transit-cljs "0.8.220"]
                 [jarohen/chord "0.6.0" :exclusions [org.clojure/tools.reader
                                                     com.cognitect/transit-cljs]]
                 [org.clojure/clojurescript "0.0-3308"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [reagent "0.5.0"]
                 [hiccup "1.0.5"]
                 [leiningen "2.5.1"]
                 [org.clojure/tools.logging "0.3.1"]]

  :plugins [[lein-environ "1.0.0"]]

  :source-paths ["src"]
  :test-paths ["test"]
  :main diegoscheduler.systems

  :profiles {:dev {:dependencies [[org.clojure/tools.nrepl "0.2.10"]
                                  [org.clojure/tools.namespace "0.2.10"]
                                  [com.cemerick/piggieback "0.2.1"]
                                  [com.cemerick/clojurescript.test "0.3.3"]]
                   :repl-options {:init-ns user
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :source-paths ["dev_src"]
                   :plugins [[cider/cider-nrepl "0.9.0"]]
                   :env {:port 8081
                         :ws-url "ws://localhost:8081/ws"}}
             :uberjar {:main diegoscheduler.systems
                       :aot [diegoscheduler.diego
                             diegoscheduler.http
                             diegoscheduler.pages
                             diegoscheduler.systems
                             diegoscheduler.web]}}

  :uberjar-exclusions [#"^goog/.*$"
                       #"^com/google/javascript/.*"
                       #"^public/js/chord/.*"
                       #"^public/js/cljs/.*"
                       #"^public/js/clojure/.*"])
