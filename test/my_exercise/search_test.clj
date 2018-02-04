(ns my-exercise.search-test
  (:require [clojure.test :refer :all]
            [my-exercise.search :refer :all]))

(deftest ocd-id-test
  (testing "ocd-id string for empty input form"
    (is (= (get-ocd-id {:state "" :place ""}) "ocd-division/country:us")))
  (testing "ocd-id string for only state input"
    (is (= (get-ocd-id {:state "NY" :place ""}) "ocd-division/country:us,ocd-division/country:us/state:ny")))
  (testing "ocd-id string for state and city input"
    (is (= (get-ocd-id {:state "NY" :place "New York"}) "ocd-division/country:us,ocd-division/country:us/state:ny,ocd-division/country:us/state:ny/place:new_york")))
  (testing "ocd-id string for city input"
    (is (= (get-ocd-id {:state "" :place "New York"}) "ocd-division/country:us")))
  (testing "get params with form params"
    (is (= (get-params {:state "NY" :city "New York"}) {:state "NY" :place "New York"})))
  (testing "get params with empty form params"
    (is (= (get-params {:state "" :city ""}) {:state "" :place ""}))))
