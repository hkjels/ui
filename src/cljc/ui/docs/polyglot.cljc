(ns ui.docs.polyglot
  (:require [ui.elements :as element]
            [ui.layout :as layout]
            [ui.util :as util]
            [ui.wire.polyglot :refer [translate]]))



(defn documentation []
  [element/article
   "## Polyglot
    We depend on [tongue](https://github.com/tonsky/tongue) for our internationalization needs and  
    expose it via `re-frame`'s machinery. This means that you can use  
    that very same machinery for all the internationalization-needs of  
    your application.  
  
    So, first of you'll need to initialize `ui` with your favorite  
    language.  
  
    ```
    (dispatch [:ui/add-language :en {:hello \"hello {1}!\"}])  
    (dispatch [:ui/set-current-language :en])  
    ```
  
    Now, whenever you need something translated, just use the  
    translate-function and your good.  
  
    ```
    (ui.wires.polyglot/translate :hello \"jon\")
    ```  
    "
   [:pre [:code "=> " (translate :hello "jon")]] "  
  
    If a translation is missing, that will become obvious in your  
    `gui`, but you can also register an event-handler named  
    `:missing-translation` that takes one argument which will be the  
    failing keyword. You can do pretty much anything you'd like with 
    that.
    "])