(ns example.httpbin
  (:require [http.cljstman :as http :refer [def-requests POST]]))

(def-requests
  ; 요청을 보내고 응답을 본다.
  (-> "https://httpbin.org/status/200"
      POST)

  ; 요청을 보내고 응답에서 상태 코드만 본다.
  (-> "https://httpbin.org/status/200"
      POST
      :status)
  ;;
  )

