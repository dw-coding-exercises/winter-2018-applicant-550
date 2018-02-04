(ns my-exercise.search
  (:require [hiccup.page :refer [html5]]
            [my-exercise.home :as home]))

(defn search-results [_]
  "Function to render search results"
  [:div {:class "search-results"}
   [:h1 "Search Results"]])

(defn page [request]
  "Function to render search page"
  (html5
   (home/header request)
   (search-results request)))
