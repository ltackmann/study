import 'dart:async';
import 'dart:html';
import 'package:daido/daido_lib.dart';
import 'package:mdv/mdv.dart' as mdv;
import 'package:polymer/polymer.dart';
import 'package:route/client.dart';

class WebApp {
  WebApp() {
    _router.addHandler(_burnsQuestionnaireUrl, (path) {
      //query('#questionnaire').model.showBurnsQuestionnaire();
    });
    _router.addHandler(_dasQuestionnaireUrl, (path) {
      //query('#questionnaire').model.showDasQuestionnaire();
    });
  }
  
  go() {
    _router.listen();
    _router.gotoUrl(_burnsQuestionnaireUrl, [r"daido.html#"], "home");
  }
  
  final _dasQuestionnaireUrl = new UrlPattern(r'(.*)questionnaire/das');
  final _burnsQuestionnaireUrl = new UrlPattern(r'(.*)questionnaire/burns');
  final _router = new Router();
}

main() {
  mdv.initialize();
  
  var webApp = new WebApp();
  webApp.go();
  
  print("app started");
}


