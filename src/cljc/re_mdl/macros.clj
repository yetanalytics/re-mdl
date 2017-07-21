(ns re-mdl.macros)

(defmacro build-class [^String base & clauses]
  (assert (even? (count clauses)) "Please provide an even number of clauses.")
  `(str ~base
        ~@(for [[test# build-string#] (partition 2 clauses)]
            `(and ~test# ~build-string#))))
