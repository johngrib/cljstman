(ns example.jsonplaceholder
  (:require [http.cljstman :as http :refer [def-requests GET POST]]
            [cheshire.core :as json]))

(def host "https://jsonplaceholder.typicode.com")

(def-requests
  "jsonplaceholder에 GET 요청을 보내는 예제"

  ; todo 목록을 받아온다. 결과에 http 헤더, status, length까지 모두 표시된다.
  (->> "https://jsonplaceholder.typicode.com/todos"
       GET)

  ; todo 목록을 받아온다. 결과에서 body 만 본다.
  (-> "https://jsonplaceholder.typicode.com/todos"
      GET
      :body)

  ; todo 목록을 받아온다. todo 목록의 3번 인덱스 항목만 본다.
  ;     host 주소를 일일이 입력하지 않고 host를 사용할 수 있다.
  (-> (str host "/todos")
      GET
      :body
      (get 3))

  ; todo 목록의 3번 인덱스 항목을 json String으로 변환한 결과를 본다.
  (-> (str host "/todos")
      GET
      :body
      (get 3)
      json/encode)
  ;;
  )

(def-requests
  "jsonplaceholder에 POST 요청을 보내는 예제"

  ; 새로운 포스트를 등록하고 결과를 받는다.
  (-> "https://jsonplaceholder.typicode.com/posts"
      (POST {:title   "포스트 제목"
             :body    "포스트 내용"
             :user-id 1}))

  ; 새로운 포스트를 등록하고, 응답에서 status 와 body 만 본다.
  (-> (str host "/posts")
      (POST {:title   "포스트 제목"
             :body    "포스트 내용"
             :user-id 1})
      (select-keys [:status :body]))
  ;;
  )
