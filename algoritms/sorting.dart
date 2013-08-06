import 'package:unittest/unittest.dart';

main() {
  group("sorting algorithms -", () {
    solo_test("selection sort", () {
      selectionSort(List<Comparable> list) {
        final length = list.length;
        for(int i=0; i<length-1; i++) {
          var smallest = i;
          for(int j=i+1; j<length; j++) {
            if(list[j].compareTo(list[smallest]) < 0) {
              smallest = j;
            }
          }
          swap(list, smallest, i);
        }
        return list;
      }
      expect(selectionSort([6,3,1,4,2,5]), equals([1,2,3,4,5,6]));
    });
    
    test("insertion sort", () {
      
    });
    
    test("merge sort", () {
      
    });
    
    test("quick sort", () {
      
    });
  });
}

swap(List list, int i, int j) {
  var tmp = list[i];
  list[i] = list[j];
  list[j] = tmp;
}