function changeLanguage(language){
   $(document).ready(function() {
        if (language != ''){
            window.location.replace('?lang=' + language);
        }
   });
}