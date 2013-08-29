import 'dart:html';
import 'package:route/client.dart';

class WebApp {
  WebApp() {
    print("in webapp");
    _router.addHandler(_burnsQuestionnaireUrl, (path) {
      query('#questionnaire').xtag.model.showBurnsQuestionnaire(); 
      print('burns');
    });
    _router.addHandler(_dasQuestionnaireUrl, (path) {
      query('#questionnaire').xtag.model.showDasQuestionnaire();
      print('das');
    });
  }
  
  go() {
    _router.listen();
    _router.gotoUrl(_burnsQuestionnaireUrl, [r'index.html#'], 'home');
  }
  
  final _dasQuestionnaireUrl = new UrlPattern(r'(.*)questionnaire/das');
  final _burnsQuestionnaireUrl = new UrlPattern(r'(.*)questionnaire/burns');
  final _router = new Router();
}

main() {
  // TODO add using custom element https://github.com/sethladd/dart-polymer-dart-examples/blob/master/web/manually_add_custom_element_to_dom/index.dart
  var webApp = new WebApp();
  webApp.go();
  
  print('app started');
}


