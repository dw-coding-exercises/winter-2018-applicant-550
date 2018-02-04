(ns my-exercise.search
  (:require [hiccup.page :refer [html5]]
            [my-exercise.home :as home]))

(defn getParams
  "Function to get incoming form params"
  [{city :city state :state}]
  {:state state :place city})

(defn search-results
  "Function to render search results"
  [request]
  (let [search-params (getParams (:params request))]
    (println search-params)
    [:div {:class "search-results"}]
    [:h1 "Search Results"]))

(defn page
  "Function to render search page"
  [request]
  (html5
   (home/header request)
   (search-results request)))
