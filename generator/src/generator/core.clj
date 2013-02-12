(ns generator.core
  (:import org.pegdown.PegDownProcessor java.io.File)
  (:use hiccup.element hiccup.page)
  (:gen-class))

(def prcssr (PegDownProcessor.))

(defn md->html
  "Parses md"
  [md]
  (. prcssr markdownToHtml md))

(defn generate-site
  "Generates a website"
  ([file]
     (generate-site
      (.replaceFirst (.getName file) "[.][^.]+$" "")
      (slurp (.getAbsolutePath file))))              
  ([name content]
     (html5
      [:head
       [:title "earthcraftsurvival.com"]
       (include-css "./css/style.css")
       (include-js "./js/jquery-1.7.2.min.js")
       (include-js "./js/script.js")]
      [:body
       [:div#header.shadow ; header
        (link-to {:class "headeritem" :id "earthcraft"} "index.html" "home")
        (link-to {:class "headeritem"} "http://www.planetminecraft.com/server/playblockstormcom/" "planetminecraft")]
       [:div#logo-container ; logo
        [:div#logo "earthcraftsurvival.com"]
        [:div#description "play.earthcraftsurvival.com - Description"]]
       [:div#menu.shadow.gradient ; menu
        [:a#home {:href "index.html"}]
        (link-to {:class "menuitem"} "faq.html" "FAQ")
        (link-to {:class "menuitem"} "donate.html" "Donate")
        (link-to {:class "menuitem"} "vote.html" "Vote")
        (link-to {:class "menuitem"} "rules.html" "Rules")
        (link-to {:class "menuitem"} "ranks.html" "Ranks")
        [:div#ip "play.earthcraftsurvival.com"]]
       (if (.equalsIgnoreCase name "index")
         [:div#slideshow ; slideshow
          (image {:id "current-image"} "./img/slideshow01.png" "Slideshow")])
       [:div#content.shadow ; content
        [:div#content-container content]]])))

(defn generate
  "Generates websites for all files in pages/ dir"
  []
  (str "Generated "
       (count
        (map (fn [file]
               (let [name (.replaceFirst (.getName file) "[.][^.]+$" "")]
                 (spit (str "../" name ".html")
                       (generate-site name (md->html (slurp (.getAbsolutePath file)))))
                 name))
             (.listFiles (File. "../pages")))) " files"))

(defn -main
  "I don't do a whole lot."
  [& args]
  (println "You should run this in a REPL"))
