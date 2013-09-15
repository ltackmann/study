// TODO http://html5doctor.com/storing-data-the-simple-html5-way-and-a-few-tricks-you-might-not-have-known/
main() {
  document.query('#content').innerHTML =
    'save text locally (it will still be available after you restart your browser)';
  area = new Element.tag('textarea'); 
  area.style.width = '300px';
  area.style.height = '150px';
  document.query('#content').nodes.addLast(area);
  
  area.value = window.localStorage.getItem('value');
  
  area.on.keyUp.add((event) {
    window.localStorage.setItem('value', area.value);
    window.localStorage.setItem('timestamp', (new Date.now()).toString());
    print(window.localStorage.getItem('timestamp'));
    print(window.localStorage.getItem('value'));

  });
  
  updateLog();
  window.setInterval(updateLog, 5000);
}

// TODO session storage

updateLog() {
  var delta = 0;
    Element log = document.query('#log');
    log.nodes = [];
    for(int i=0; i<window.localStorage.length; i++) {
      var elm = new Element.tag("p");
      var key = window.localStorage.key(i);
      elm.innerHTML = "at index $i with key ${key} and value ${window.localStorage.getItem(key)}"; 
      log.nodes.add(elm);
    }
    document.query('#log').innerHTML = 'last saved: ' + delta.inSeconds + 's ago';
}
