var tags = new Map();
var previewCloseButton;

window.onload = function() {
    this.tags.set("header", "<header>Text</header>");
    this.tags.set("video", "<video>url</video>");
    this.tags.set("image", "<img>url</img>");
    $('#text').each(function () {
      this.style.height = this.scrollHeight;
      this.style.overflowY = 'hidden';
    }).on('input', function () {
      this.style.height = 'auto';
      this.style.height = (this.scrollHeight) + 'px';
    });
   previewCloseButton = document.createElement("p");
   previewCloseButton.innerHTML = "X";
   previewCloseButton.style.display = "none";
   previewCloseButton.style.marginLeft = "80%";
   previewCloseButton.addEventListener("click", closePreview);
}

function previewArticle(){
  alert($('#text').text());
  $.post("/article/previewArticle",
  {
    articleName: $('textarea[name ="articleName"]').val(),
    articleText: $('#text').text()
  },
  function(data, status){
  var previewBody = $("#previewFrame").contents().find("body");
  previewBody.text("");
  previewCloseButton.style.display = "";
  previewBody.append(previewCloseButton);
  previewBody.append(data);
    $("#previewFrame").show();
  });
}

function closePreview(){
    previewCloseButton.style.display = "none";
    $("#previewFrame").contents().find("head").text("");
    $("#previewFrame").contents().find("body").text("");
    $("#previewFrame").hide();
}

function submitArticle(){
  alert($('#text').text());
  $.post("/article/saveArticle",
  {
    articleName: $('textarea[name ="articleName"]').val(),
    articleText: $('#text').text()
  },
  function(data, status){
    alert("Data: " + data + "\nStatus: " + status);
  });
}

function insertTag(element){
  var textDiv = $('#text');
  textDiv.text(textDiv.text()+tags.get(element.name));
}

function retrieveImageFromClipboardAsBlob(pasteEvent, callback){
	if(pasteEvent.clipboardData == false){
        if(typeof(callback) == "function"){
            callback(undefined);
        }
    };

    var items = pasteEvent.clipboardData.items;

    if(items == undefined){
        if(typeof(callback) == "function"){
            callback(undefined);
        }
    };

    for (var i = 0; i < items.length; i++) {
        // Skip content if not image
        if (items[i].type.indexOf("image") == -1) continue;
        // Retrieve image on clipboard as blob
        var blob = items[i].getAsFile();

        if(typeof(callback) == "function"){
            callback(blob);
        }
    }
}

window.addEventListener("paste", function(e){

    // Handle the event
    retrieveImageFromClipboardAsBlob(e, function(imageBlob){
        if(e.srcElement.id == "text"){
        // If there's an image, display it in the canvas
            if(imageBlob){
            // Create an image to render the blob on the canvas
            var img = new Image();

            // Crossbrowser support for URL
            var URLObj = window.URL || window.webkitURL;

            // Creates a DOMString containing a URL representing the object given in the parameter
            // namely the original Blob
            img.src = URLObj.createObjectURL(imageBlob);

            var textDiv = $('#text');
            textDiv.text(textDiv.text()+"<img>" + img.src + "<img/>");
            e.preventDefault();
            }
        }
    });
}, false);