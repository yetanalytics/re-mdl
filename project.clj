(defproject com.yetanalytics/re-mdl "0.1.1-SNAPSHOT"
  :description "Yet another library of reusable UI components for Reagent"
  :url "https://github.com/yetanalytics/re-mdl.git"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [cljsjs/material "1.0.6-0"]
                 [reagent "0.5.1" :exclusions [cljsjs/react]]
                 [cljsjs/react-with-addons "0.13.3-0"]
                 [cljs-react-test "0.1.3-SNAPSHOT"]
                 [prismatic/dommy "1.1.0"]]

  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-2"]
            [lein-doo "0.1.6"]]

  :source-paths ["src/cljc"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src/cljc" "src-dev"]
              :figwheel { :on-jsload "re-mdl.demo/on-js-reload" }
              :compiler {:main re-mdl.demo
                         :asset-path "js/compiled/out"
                         :output-to "resources/public/js/compiled/re_mdl.js"
                         :output-dir "resources/public/js/compiled/out"
                         :source-map-timestamp true }}

             {:id "min"
              :source-paths ["src/cljc"]
              :compiler {:output-to "resources/public/js/compiled/re_mdl.js"
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
