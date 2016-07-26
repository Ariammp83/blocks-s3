(defproject mvxcvi/blocks-s3 "0.3.0-SNAPSHOT"
  :description "Content-addressable S3 block store."
  :url "https://github.com/greglook/blocks-s3"
  :license {:name "Public Domain"
            :url "http://unlicense.org/"}

  :aliases
  {"docs" ["do" ["codox"] ["doc-lit"]]
   "doc-lit" ["marg" "--dir" "doc/marginalia"]
   "coverage" ["with-profile" "+test,+coverage" "cloverage"]}

  :deploy-branches ["master"]
  :pedantic? :abort

  :dependencies
  [[org.clojure/clojure "1.8.0"]
   [org.clojure/tools.logging "0.3.1"]
   [com.amazonaws/aws-java-sdk-s3 "1.11.20"]
   [commons-logging "1.2"]
   [mvxcvi/blocks "0.7.1"]
   [mvxcvi/multihash "2.0.1"]]

  :test-selectors
  {:unit (complement :integration)
   :integration :integration}

  :codox
  {:metadata {:doc/format :markdown}
   :source-uri "https://github.com/greglook/blocks-s3/blob/master/{filepath}#L{line}"
   :doc-paths ["doc/extra"]
   :output-path "doc/api"}

  :whidbey
  {:tag-types {'multihash.core.Multihash {'data/hash 'multihash.core/base58}
               'blocks.data.Block {'blocks.data.Block (partial into {})}}}

  :profiles
  {:repl
   {:source-paths ["dev"]
    :dependencies [[org.clojure/tools.namespace "0.2.11"]]}

   :test
   {:jvm-opts ["-Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.NoOpLog"]}

   :coverage
   {:plugins [[rfkm/lein-cloverage "1.0.8"]]
    :dependencies [[com.fasterxml.jackson.dataformat/jackson-dataformat-cbor "2.6.6"]]
    :jvm-opts ["-Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog"
               "-Dorg.apache.commons.logging.simplelog.defaultlog=trace"]}})
