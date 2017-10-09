function changeLanguage(language){
  /* $(document).click(function() {
          console.log("test")
          var date = new Date();
          date.setTime(date.getTime() + (365*24*60*60*1000));
          expires = "; expires=" + date.toUTCString();
          var x = document.cookie = "my-locale-cookie" + "=" + language + expires + "; path=/";
          console.log(x);
   });*/

   /*console.log(language)
   var date = new Date();
   date.setTime(date.getTime() + (365*24*60*60*1000));
   expires = "; expires=" + date.toUTCString();
   var x = document.cookie = "my-locale-cookie" + "=" + language + expires + "; path=/";
   console.log(x);*/
$(document).ready(function() {
        if (language != ''){
           var date = new Date();
              date.setTime(date.getTime() + (365*24*60*60*1000));
              expires = "; expires=" + date.toUTCString();
              var x = document.cookie = "my-locale-cookie" + "=" + language + expires + "; path=/";
        }
   });
}




