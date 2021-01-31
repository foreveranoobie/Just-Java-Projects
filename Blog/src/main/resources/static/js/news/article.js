var tags = new Map();
var previewCloseButton;
var clipboardImageCount = 0;

window.onload = function() {
    this.tags.set("header", "<header>Text</header>");
    this.tags.set("video", "<video>url</video>");
    this.tags.set("image", "<img>url</img>");
    $('#text').each(function () {
      this.style.height = '400px';
      this.style.minHeight = '400px';
      this.style.overflowY = 'hidden';
    }).on('input', function () {
      /*if(this.scrollHeight > 400){
        this.style.height = 'auto';
        this.style.height = this.scrollHeight + 'px';
      }*/
      correctDivHeight(this);
    });
   previewCloseButton = document.createElement("p");
   previewCloseButton.innerHTML = "X";
   previewCloseButton.style.display = "none";
   previewCloseButton.style.marginLeft = "95%";
   previewCloseButton.addEventListener("click", closePreview);
}

function correctDivHeight(divElement){
  if(divElement.scrollHeight > 400){
    divElement.style.height = 'auto';
    divElement.style.height = divElement.scrollHeight + 'px';
  }
}

function previewArticle(){
  alert($('#text').val());
  $.post("/article/previewArticle",
  {
    articleName: $('textarea[name ="articleName"]').val(),
    articleText: $('#text').val()
  },
  function(data, status){
  var previewBody = $("#previewFrame").contents().find("body");
  previewBody.text("");
  previewCloseButton.style.display = "";
  //$("#previewFrame").attr("srcdoc", data);
  previewBody.append(previewCloseButton);
  previewBody.append(data);
    $("#previewFrame").show();
  });
  $('body').css('background-color', 'gray');
}

function closePreview(){
    previewCloseButton.style.display = "none";
    $("#previewFrame").contents().find("body").text("");
    $("#previewFrame").hide();
    $('body').css('background-color', 'white');
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
  var cursorPosition = textDiv.prop('selectionStart');
  var additionalSymbol = "";
  if(textDiv.val() != ""){
    additionalSymbol = "\n";
  }
  var newTextVal = textDiv.val();
  if(cursorPosition === 0){
    newTextVal = newTextVal.insert(cursorPosition, tags.get(element.name) + "\n");
  } else {
    newTextVal = newTextVal.insert(cursorPosition, "\n" + tags.get(element.name));
  }
  textDiv.val(newTextVal);
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
            var cursorPosition = textDiv.prop('selectionStart');
            var additionalSymbol = "";
            if(textDiv.val() != ""){
              additionalSymbol = "\n";
            }
            var newTextVal = textDiv.val();
            var image = imageBlob;
            var fd = new FormData();
            fd.append('image', image);
            fd.append('number', clipboardImageCount++);
                $.ajax({
                    url: '/article/saveImage',
                    type: 'POST',
                    data: fd,
                    cache: false,
                    processData: false,
                    contentType: false,
                    success: function(data){
                      if(cursorPosition === 0){
                        newTextVal = newTextVal.insert(cursorPosition, "<img>" + data + "</img>" + "\n");
                      } else {
                        newTextVal = newTextVal.insert(cursorPosition, "\n" + "<img>" + data + "</img>");
                      }
                      textDiv.val(newTextVal);
                      }
                });
            e.preventDefault();
            }
        }
    });
}, false);

String.prototype.insert = function(index, string) {
  if (index > 0) {
    return this.substring(0, index) + string + this.substr(index);
  }
  return string + this;
};