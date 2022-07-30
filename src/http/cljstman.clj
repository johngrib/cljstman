(ns http.cljstman
  "HTTP CLIENT common basic."
  (:require [clj-http.client :as client]))

(defmacro def-requests
  "comment와 똑같지만 다른 이름을 써서 comment와 구별할 수 있게 한다."
  [& body])

(defn create-requestor
  "http 메소드별 리퀘스터를 생산한다."
  ([f url]
   (create-requestor f url {} {}))
  ([f url body]
   (create-requestor f url {} body))
  ([f url header body]
   (create-requestor f url header body {}))
  ([f url header body custom-options]
   (let [request {:content-type     :application/json
                  :headers          header
                  :form-params      body
                  :as               :json
                  :coerce           :always
                  :throw-exceptions false}]
     (f url (merge request custom-options)))))

(def POST (partial create-requestor client/post))
(def GET (partial create-requestor client/get))
(def PUT (partial create-requestor client/put))
(def HEAD (partial create-requestor client/head))
(def DELETE (partial create-requestor client/delete))
(def PATCH (partial create-requestor client/patch))
(def OPTIONS (partial create-requestor client/options))

