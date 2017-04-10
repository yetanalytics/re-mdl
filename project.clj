(defproject com.yetanalytics/re-mdl "0.1.5"
  :description "Yet another library of reusable UI components for Reagent"
  :url "https://github.com/yetanalytics/re-mdl.git"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[cljsjs/material "1.3.0-0"]
                 [cljsjs/dialog-polyfill "0.4.3-0"]
                 [reagent "0.6.0-rc" :exclusions [cljsjs/react]]
                 [cljsjs/react-with-addons "15.2.1-1"]
                 [prismatic/dommy "1.1.0"]]

  :plugins [[lein-cljsbuild "1.1.3"]
            [lein-figwheel "0.5.4-7"]
            [lein-doo "0.1.7"]]

  :profiles {:dev {:dependencies [[cljs-react-test "0.1.4-SNAPSHOT"
                                   :exclusions [cljsjs/react-with-addons
                                                org.clojure/core.async
                                                org.clojure/tools.analyzer.jvm
                                                ]]
                                  [cljsjs/react-dom "15.2.1-1" :exclusions [cljsjs/react]]
                                  [org.clojure/clojure "1.8.0"]
                                  [org.clojure/clojurescript "1.9.93"]
                                  [figwheel-sidecar "0.5.4-7"]]}}

  :source-paths ["src/cljc"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :aliases {"ci" ["do"
                  ["doo" "phantom" "test" "once"]
                  ["test"]]}

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src/cljc" "src-dev"]
              :figwheel { :on-jsload "re-mdl.demo/on-js-reload" }
              :compiler {:main re-mdl.demo
                         :asset-path "js/compiled/out"
                         :output-to "resources/public/js/compiled/re_mdl.js"
                         :output-dir "resources/public/js/compiled/out"
                         :source-map-timestamp true}}
             {:id "demo"
              :source-paths ["src/cljc" "src-dev"]
              :compiler {:main re-mdl.demo
                         :output-to "resources/public/js/compiled/re_mdl.js"
                         :optimizations :advanced
                         :pretty-print false}}

             {:id "min"
              :source-paths ["src/cljc"]
              :compiler {:output-to "target/js/re_mdl.js"
                         :main re-mdl.core
                         :optimizations :advanced
                         :pretty-print false}}

             {:id "test"
              :source-paths ["src/cljc" "src-dev" "test/cljs"]
              :compiler {:output-to "resources/public/js/compiled/test.js"
                         :main re-mdl.runner
                         :optimizations :none}}]}

  :figwheel {
             ;; :http-server-root "public" ;; default and assumes "resources"
             ;; :server-port 3449 ;; default
             ;; :server-ip "127.0.0.1"

             :css-dirs ["resources/public/css"] ;; watch and update CSS

             ;; Start an nREPL server into the running figwheel process
             ;; :nrepl-port 7888

             ;; Server Ring Handler (optional)
             ;; if you want to embed a ring handler into the figwheel http-kit
             ;; server, this is for simple ring servers, if this
             ;; doesn't work for you just run your own server :)
             ;; :ring-handler hello_world.server/handler

             ;; To be able to open files in your editor from the heads up display
             ;; you will need to put a script on your path.
             ;; that script will have to take a file path and a line number
             ;; ie. in  ~/bin/myfile-opener
             ;; #! /bin/sh
             ;; emacsclient -n +$2 $1
             ;;
             ;; :open-file-command "myfile-opener"

             ;; if you want to disable the REPL
             ;; :repl false

             ;; to configure a different figwheel logfile path
             ;; :server-logfile "tmp/logs/figwheel-logfile.log"
             })
