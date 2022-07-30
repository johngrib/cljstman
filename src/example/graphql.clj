(ns example.graphql
  (:require [http.cljstman :as http :refer [def-requests POST]]))

(def token (atom nil))

(defn login
  "로그인 요청을 보내고, 결과가 돌아오면 token에 토큰 문자열을 등록한다."
  ([]
   (login "johngrib@xxxx.com" "p@ssword"))
  ([id password]
   (let [result (POST "http://localhost:80/login"
                  {}
                  {:userid     id
                   :password   password
                   :grant-type "password"}
                  {:content-type :application/x-www-form-urlencoded})
         login-token (get-in result [:body :token])]
     (println result)
     (reset! token login-token)
     login-token)))

(def-requests
  "매번 로그인하면서 graphql 요청을 보내는 예제."

  (do
    (login)
    (POST "http://localhost:80/graphql"
      {:Authorization (str "Bearer " @token)}
      {:query "
        query {
          hero {
            name
          }
        }"}))
  ;;
  )

(def-requests
  "로그인 따로 GraphQL 요청 따로 보내는 예제"

  (login)
  (POST "http://localhost:80/graphql"
        {:Authorization (str "Bearer " @token)}
        {:query "
         query {
           hero {
             name
           }
         }"})
  ;;
  )
