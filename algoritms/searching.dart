import 'package:unittest/unittest.dart';

main() {
  group("searching algorithms -", () {
    test("binary search iterative", () {
      int binarySearch(List<Comparable> list, Comparable target) {
        var start = 0;
        var end = list.length - 1;
        while(start <= end) {
          var pivot = (start + end) ~/ 2;
          var comp = list[pivot].compareTo(target);
          if(comp == 0) {
            return pivot;
          } else if(comp > 0) {
            end = pivot - 1;
          } else if(comp < 0) {
            start = pivot + 1;
          }
        }
        return -1;
      }
      
      expect(binarySearch([], 1), equals(-1));
      expect(binarySearch([1], 1), equals(0));
      expect(binarySearch([-1], 1), equals(-1));
      expect(binarySearch([1,2], 2), equals(1));
      expect(binarySearch([1,2], 3), equals(-1));
      expect(binarySearch([1,2,3], 3), equals(2));
      expect(binarySearch([1,2,3], 4), equals(-1));
      expect(binarySearch([1,2,3,4], 2), equals(1));
      expect(binarySearch([1,2,3,4], 5), equals(-1));
    });
    
    test("binary search recursive", (){
      int binarySearchRecursive(List<Comparable> list, int start, int end, Comparable target) {
        if(start > end) {
          return -1;
        } else {
          var pivot = (start + end) ~/ 2;
          var comp = list[pivot].compareTo(target);
          if(comp == 0) {
            return pivot;
          } else if(comp > 0) {
            return binarySearchRecursive(list, start, pivot - 1, target);
          } else if(comp < 0) {
            return binarySearchRecursive(list, pivot + 1, end, target);
          }
        }
      }
      int binarySearch(List<Comparable> list, Comparable target) => binarySearchRecursive(list, 0, list.length - 1, target);
      
      expect(binarySearch([], 1), equals(-1));
      expect(binarySearch([1], 1), equals(0));
      expect(binarySearch([-1], 1), equals(-1));
      expect(binarySearch([1,2], 2), equals(1));
      expect(binarySearch([1,2], 3), equals(-1));
      expect(binarySearch([1,2,3], 3), equals(2));
      expect(binarySearch([1,2,3], 4), equals(-1));
      expect(binarySearch([1,2,3,4], 2), equals(1));
      expect(binarySearch([1,2,3,4], 5), equals(-1));
    });
  });
}