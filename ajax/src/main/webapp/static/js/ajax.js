var ajax = {};
ajax.sendPost = function (obj) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("post",obj.url);
    xmlHttp.onreadystatechange = function () {
      if(xmlHttp.readyState == 4){
          if(xmlHttp.status == 200){
              var result = xmlHttp.responseText;
              obj.success(result);
          }else{
             obj.error(xmlHttp.status);
          }
      }
    };
    xmlHttp.send(obj.data);

};