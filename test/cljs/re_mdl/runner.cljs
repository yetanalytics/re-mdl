(ns re-mdl.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [re-mdl.components.badge-test]))

(doo-tests 're-mdl.components.badge-test)
