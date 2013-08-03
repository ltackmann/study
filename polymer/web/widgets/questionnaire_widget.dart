import 'package:polymer/polymer.dart';
import 'package:polymer_app/polymer_lib.dart';

@CustomTag('questionnaire-widget')
class QuestionnaireWidget extends PolymerElement with ObservableMixin {
  @observable Questionnaire questionnaire = new BurnsQuestionnaire();
  
  showDasQuestionnaire() {
     questionnaire = new DasQuestionnaire();
     Observable.dirtyCheck();
  }
  
  showBurnsQuestionnaire() {
    questionnaire = new BurnsQuestionnaire();
    Observable.dirtyCheck();
  }

  showMessage(event) {
    var target = event.target; 
    //var model = target.model;
    print("event ${event.toString()} value ${target.value}");
    Observable.dirtyCheck();
  }       
}