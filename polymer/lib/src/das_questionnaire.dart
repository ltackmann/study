part of questionnaire_lib;

/** [Dysfunctional Attitude Scale](http://www.ncbi.nlm.nih.gov/pmc/articles/PMC2712063) */
class DasQuestionnaire extends Questionnaire<DasQuestionGroup> {
  DasQuestionnaire(): super(0, "Dysfunctional Attitude Scale") {
    () {
      var group = new DasQuestionGroup(1, "Approval");
      group.questions.add(new DasQuestion(1, "Criticism will obviouslu upset the person who receives the criticism"));
      group.questions.add(new DasQuestion(2, "It is best to give up my own interests in order to please other people"));
      group.questions.add(new DasQuestion(3, "I need other people's approval in order to be happy"));
      group.questions.add(new DasQuestion(4, "If someone important to me expects me to do something then I reassy should do it"));
      group.questions.add(new DasQuestion(5, "My value as a person depends greatly on what others think of me"));
      groups.add(group);
    }();
    
    () {
      var group = new DasQuestionGroup(2, "Love");
      group.questions.add(new DasQuestion(6, "I cannot find happiness without being loved by another person"));
      group.questions.add(new DasQuestion(7, "If other dislike you, you are bound to be less happy"));
      group.questions.add(new DasQuestion(8, "If people ehom I care about reject me, it means there is something wrong with me"));
      group.questions.add(new DasQuestion(9, "If a person I love does not love me, it means I am unlovable"));
      group.questions.add(new DasQuestion(10, "Being isolated from others is bound to lead to unhappiness"));
      groups.add(group);
    }();
    
    () {
      var group = new DasQuestionGroup(3, "Achievement");
      group.questions.add(new DasQuestion(11, "If I am to be a worthwhile person, I must be truly outstanding in at least one major respect"));
      group.questions.add(new DasQuestion(12, "I must be a useful. productive, creative person or life has no purpose"));
      group.questions.add(new DasQuestion(13, "People who have good ideas are more worthy than those who do not"));
      group.questions.add(new DasQuestion(14, "If I do not do as well as otherpeople, it means I am interior"));
      group.questions.add(new DasQuestion(15, "If I fail at my work, then I am a failure as a person"));
      groups.add(group);
    }();
    
    () {
      var group = new DasQuestionGroup(3, "Perfectionism");
      group.questions.add(new DasQuestion(16, "If you cannot do something well, there is little point in doing it at all"));
      group.questions.add(new DasQuestion(17, "It is shameful for a person to display weakness"));
      group.questions.add(new DasQuestion(18, "A person should try to be the best at everything he undertakes"));
      group.questions.add(new DasQuestion(19, "I should be upset if I make a mistake"));
      group.questions.add(new DasQuestion(20, "If I don't set the highest standard for myself, I am likely to end up a second-rate person"));
      groups.add(group);
    }();
    
    () {
      var group = new DasQuestionGroup(4, "Entitlement");
      group.questions.add(new DasQuestion(21, "If I strongly believe I deserve something, I have reason to expect that I should get it"));
      group.questions.add(new DasQuestion(22, "It is necessary to become frustrated if you find obstacles to getting what you want"));
      group.questions.add(new DasQuestion(23, "If I put other people's needs before my own, they should help me when I need something from them"));
      group.questions.add(new DasQuestion(24, "If I am a good husband (or wife), then my spouse is bound to love me"));
      group.questions.add(new DasQuestion(25, "If I do nice things for someone, I can anticipate that they will respect me and me just as well as I treat them"));
      groups.add(group);
    }();
    
    () {
      var group = new DasQuestionGroup(5, "Omnipotence");
      group.questions.add(new DasQuestion(26, "I should assume responsibility for how people feel and behave if they are close to me"));
      group.questions.add(new DasQuestion(27, "If I criticize the way someone does something and they become angry or depressed, this means I have upset them"));
      group.questions.add(new DasQuestion(28, "To be a good, worthwhile, moral perso, I must try to help everyone who needs it"));
      group.questions.add(new DasQuestion(29, "If a child is having emotional or behvioral difficulties, this shows that the child's parents have failed in some important respect"));
      group.questions.add(new DasQuestion(30, "I should be able to please everybody"));
      groups.add(group);
    }();
    
    () {
      var group = new DasQuestionGroup(6, "Autonomy");
      group.questions.add(new DasQuestion(31, "I cannot expect to control how I feel when something bad happens"));
      group.questions.add(new DasQuestion(32, "There is no point in trying to change upsetting emotions because they are valid and invitable part of daily living"));
      group.questions.add(new DasQuestion(33, "My moods are primarky created by factors that are largely beyond my control, such as the past, or body chemistry, or hormone cycles, or bioruthms or chance or fate"));
      group.questions.add(new DasQuestion(34, "My happiness is largely dependent on what happens to me"));
      group.questions.add(new DasQuestion(35, "People who have the marks of success (good looks, social status, wealth, or fame) are bound to be more happy than those who do not"));
      groups.add(group);
    }();
  }
}

class DasQuestionGroup extends QuestionGroup<DasQuestion> {
  DasQuestionGroup(int id, this.description): super(id);
  
  @override
  bool get canProcede => questions.every((q) => q.score != null);
  
  int score;
  final String description;
}

class DasQuestion extends Question {
  DasQuestion(int id, this.question): super(id);
  
  List get options => _options.keys.toList();
  
  int get score => _score;
      set score(var value) => _score = _options[value];
     
  final String question;    
  int _score;
  static final Map<String, int> _options = const {
    "Strongly agree": -2,
    "Slightly agree": -1,
    "Neuteral": 0,
    "Slightly disagree":1,
    "Strongly disagree":2
  };
}
