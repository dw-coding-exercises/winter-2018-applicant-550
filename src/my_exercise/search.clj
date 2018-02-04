(ns my-exercise.search
  (:require [hiccup.page :refer [html5]]
            [my-exercise.home :as home]
            [clj-http.client :as client]))

(defn get-params
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

(def election-api-url
  "https://api.turbovote.org/elections/upcoming?district-divisions=")

(defn get-upcoming-elections
  "Function to take in form params and return result from api call"
  [{params :params}]
  (let [search-params (get-params params)]
    (let [ocd-id-string (get-ocd-id search-params)]
      (client/get (str election-api-url ocd-id-string)))))

(defn search-results
  "Function to render search results"
  [request]
  (let [results (get-upcoming-elections request)]
    [:div {:class "search-results"}
     [:h1 "Search Results"]
     [:div (str results)]]))

(defn page
  "Function to render search page"
  [request]
  (html5
   (home/header request)
   (search-results request)))
