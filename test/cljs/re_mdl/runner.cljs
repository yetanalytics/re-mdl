(ns re-mdl.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [re-mdl.components.badge-test]
              [re-mdl.components.button-test]))

(doo-tests 're-mdl.components.badge-test
           're-mdl.components.button-test)
