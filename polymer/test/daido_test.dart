import 'package:unittest/unittest.dart';
import 'package:daido/daido_lib.dart';

main() {
  group("dysfunctional attitude scale -", () {
    var das = new DasQuestionnaire();
    
    test("has correct groups", () {
      expect(das.groups, hasLength(7));
    });
    
    test("each group has correct questions", () {
      das.groups.forEach((group) {
        expect(group.description, isNot(isEmpty), reason:"group with id ${group.groupId} must have description");
        expect(group.questions, hasLength(5));
        group.questions.forEach((question) {
          expect(question.question, isNot(isEmpty), reason:"question with id ${question.questionId} must have question text");
        });
      });
    });
  });
}