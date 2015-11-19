(defproject com.yetanalytics/re-mdl "0.1.0-SNAPSHOT"
  :description "Yet another library of reusable UI components for Reagent"
  :url "https://github.com/yetanalytics/re-mdl.git"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.122"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 ;; [cljsjs/material "1.0.4-0"]
                 [reagent "0.5.1"]]

  :plugins [[lein-cljsbuild "1.1.1-SNAPSHOT"]
            [lein-figwheel "0.4.1"]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src/cljs" "src/cljc" "src-dev"]

              :figwheel { :on-jsload "re-mdl.demo/on-js-reload" }

              :compiler {:main re-mdl.demo
                         :foreign-libs [{:file "material/material.js"
                                         :file-min "material/material.min.js"
                                         :provides ["material"]}]
                         :asset-path "js/compiled/out"
                         :output-to "resources/public/js/compiled/re_mdl.js"
                         :output-dir "resources/public/js/compiled/out"
                         :source-map-timestamp true }}
             {:id "min"
              :source-paths ["src/cljs" "src/cljc"]
              :compiler {:output-to "resources/public/js/compiled/re_mdl.js"
                         :main re-mdl.core
                         :externs ["material/material.ext.js"]
                         :foreign-libs [{:file "material/material.js"
                                         :file-min "material/material.min.js"
                                         :provides ["material"]}]
                         :optimizations :advanced
                         :pretty-print false}}]}

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
