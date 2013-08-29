import 'package:polymer/polymer.dart';
import 'package:polymer_app/questionnaire_lib.dart';

@CustomTag('questionnaire-widget')
class QuestionnaireWidget extends PolymerElement with ObservableMixin {
  @observable Questionnaire questionnaire; 
  
  showDasQuestionnaire() {
     questionnaire = new DasQuestionnaire();
  }
  
  showBurnsQuestionnaire() {
    questionnaire = new BurnsQuestionnaire();
  }

  showMessage(event) {
    var target = event.target; 
    //var model = target.model;
    print("event ${event.toString()} value ${target.value}");
  }       
}