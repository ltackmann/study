import 'dart:html';

main () {
  var audioSource = new SourceElement();
  audioSource.src = "http://slides.html5rocks.com/src/rushus-modal_blues.mp3";
  audioSource.type = "audio/mp3";
  
  var audio = new AudioElement();
  audio.attributes["controls"] = "controls";
  audio.nodes.add(audioSource);
  window.document.body.nodes.add(audio);
}
