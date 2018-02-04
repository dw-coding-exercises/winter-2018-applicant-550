(ns my-exercise.search
  (:require [hiccup.page :refer [html5]]
            [my-exercise.home :as home]))

(defn getParams
  "Function to get incoming form params"
  [{city :city state :state}]
  {:state state :place city})

(def country-ocd-id
  "ocd-division/country:us")

(defn state-ocd-id
  "Function to create ocd-id state string"
  [state]
  (if (empty? state)
    nil
    (str "ocd-division/country:us/state:" state)))

(defn place-ocd-id
  "Function to create ocd-id place string"
  [state, place]
  (if (or (empty? state) (empty? place))
    nil
    (str "ocd-division/country:us/state:" state "/place:" place)))

(defn create-ocd-id-list
  "Function to create list of ocd-ids and remove nil elements"
  [{state :state place :place}]
  (let [string-state (clojure.string/lower-case state) string-place (clojure.string/replace (clojure.string/lower-case place) " " "_")]
    (clojure.core/remove nil? [country-ocd-id (state-ocd-id string-state) (place-ocd-id string-state string-place)])))

(defn get-ocd-id
  "Function to create ocd-id string"
  [params]
  (clojure.string/join "," (create-ocd-id-list params)))

(defn search-results
  "Function to render search results"
  [request]
  (let [search-params (getParams (:params request))]
    (println (get-ocd-id search-params))
    [:div {:class "search-results"}]
    [:h1 "Search Results"]))

(defn page
  "Function to render search page"
  [request]
  (html5
   (home/header request)
   (search-results request)))
