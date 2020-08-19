var tags = new Map();

window.onload = function() {
    this.tags.set("header", "<header>Text</header>");
    this.tags.set("video", "<video>url</video>");
    this.tags.set("image", "<img>url</img>");
    $('textarea').each(function () {
      this.style.height = this.scrollHeight;
      this.style.overflowY = 'hidden';
    }).on('input', function () {
      this.style.height = 'auto';
      this.style.height = (this.scrollHeight) + 'px';
    });
}


function insertTag(element){
  var textarea = $('#text');
  textarea.val(textarea.val()+tags.get(element.name)); 
}