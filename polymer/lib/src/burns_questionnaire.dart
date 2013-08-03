part of daido_lib;

/** [Burns Depression Scale](http://www.hopeenrichmentcenter.com/Burns-Depression-scale.html) */
class BurnsQuestionnaire extends Questionnaire<BurnsQuestionGroup> {
  BurnsQuestionnaire(): super(0, "Burns Depression Scale") {
    () {
      var group = new BurnsQuestionGroup(1, "Questions");
      
      group.questions.add(new BurnsQuestion(1, "Feeling sad or down in the dumps."));
      group.questions.add(new BurnsQuestion(1, "Feeling unhappy or blue."));
      group.questions.add(new BurnsQuestion(1, "Crying spells or tearfulness."));
      group.questions.add(new BurnsQuestion(1, "Feeling discouraged."));
      group.questions.add(new BurnsQuestion(1, "Feeling hopeless."));
      group.questions.add(new BurnsQuestion(1, "Low self-esteem."));
      group.questions.add(new BurnsQuestion(1, " Feeling worthless or inadequate."));
      group.questions.add(new BurnsQuestion(1, "Guilt or shame."));
      group.questions.add(new BurnsQuestion(1, "Criticizing yourself or blaming others."));
      group.questions.add(new BurnsQuestion(1, " Difficulty making decisions."));
      group.questions.add(new BurnsQuestion(1, "Loss of interest in family, friends, or colleagues."));
      group.questions.add(new BurnsQuestion(1, "Loneliness."));
      group.questions.add(new BurnsQuestion(1, "Spending less time with family or friends."));
      group.questions.add(new BurnsQuestion(1, "Loss of motivation."));
      group.questions.add(new BurnsQuestion(1, "Loss of interest in work or others activities."));
      group.questions.add(new BurnsQuestion(1, "Avoiding work or other activities."));
      group.questions.add(new BurnsQuestion(1, "Loss of pleasure or satisfaction in life."));
      group.questions.add(new BurnsQuestion(1, "Feeling tired."));
      group.questions.add(new BurnsQuestion(1, "Difficulty sleeping or sleeping too much.")); 
      group.questions.add(new BurnsQuestion(1, "Decreased or increased appetite."));
      group.questions.add(new BurnsQuestion(1, "Loss of interest in sex."));
      group.questions.add(new BurnsQuestion(1, "Worrying about your health."));
      group.questions.add(new BurnsQuestion(1, "Do you have any suicidal thoughts?."));
      group.questions.add(new BurnsQuestion(1, "Would you like to end your life?."));
      group.questions.add(new BurnsQuestion(1, "Do you have a plan for harming yourself?."));
      groups.add(group);
    }();
  }
}

class BurnsQuestionGroup extends QuestionGroup<BurnsQuestion> {
  BurnsQuestionGroup(int id, this.description): super(id);
  
  @override
  bool get canProcede => false;
  
  int get score => questions.fold(0,(a,b) => a+b);
  final String description;
}

class BurnsQuestion extends Question {
  static final Map<String, int> _options = const {
    "Not at all": 0,
    "Somewhat": 1,
    "Moderate": 2,
    "A lot":3,
    "Extremely":4
  };
  
  BurnsQuestion(int id, this.question): super(id);
  
  List get options => _options.keys.toList();
  
  int get score => _score;
      set score(var value) => _score = _options[value];
      
  int _score = 0;
  final String question;
}
