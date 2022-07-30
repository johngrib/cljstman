(ns example.login-token
  (:require [http.cljstman :as http :refer [def-requests POST]]))

(def token (atom nil))

(defn login
  ([]
   (login "johngrib@xxxx.com" "p@ssword"))
  ([id password]
   (let [result (POST "http://localhost:80/user/token"
                  {} ; body
                  {:userid     id
                   :password   password
                   :grant-type "password"}
                  {:content-type :application/x-www-form-urlencoded})
         login-token (get-in result [:body :token])]
     (println result)
     (reset! token login-token)
     login-token)))

(def-requests
  "로그인 요청을 보내고 받은 토큰을 token에 등록한다."

  (login)
  ;;
  )


