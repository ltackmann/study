library questionnaire_lib;

import 'package:meta/meta.dart';

part 'src/burns_questionnaire.dart';
part 'src/das_questionnaire.dart';

abstract class Questionnaire<G extends QuestionGroup> {
  Questionnaire(this.questionnaireId, this.questionnaireName);
  
  final String questionnaireName;
  final List<G> groups = [];
  final int questionnaireId;
}

abstract class QuestionGroup<Q extends Question> {
  QuestionGroup(this.groupId);
  
  bool get canProcede;
  
  final List<Q> questions = [];
  final int groupId;
}

class Question {
  Question(this.questionId);

  final int questionId;
}


