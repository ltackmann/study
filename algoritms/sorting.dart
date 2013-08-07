import 'package:unittest/unittest.dart';

main() {
  group("sorting algorithms -", () {
    test("selection sort", () {
      List<Comparable> selectionSort(List<Comparable> list) {
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
      assertIntegerSort(selectionSort);
    });
    
    solo_test("insertion sort", () {
      List<Comparable> insertionSort(List<Comparable> list) {
        final length = list.length;
        for(int i=1; i<length; i++) {
          var key = list[i];
          int j = i - 1;
          while(j >= 0 && list[j].compareTo(key) > 0) {
            list[j+1] = list[j];
            j--;
          }
          list[j+1] = key;
        }
        return list;
      }
      assertIntegerSort(insertionSort);
    });
    
    test("merge sort", () {
      
    });
    
    test("quick sort", () {
      
    });
    
    test("count sort", () {
      
    });
    
    test("radix sort", () {
      
    });
  });
}

swap(List list, int i, int j) {
  var tmp = list[i];
  list[i] = list[j];
  list[j] = tmp;
}

assertIntegerSort(List<Comparable> sorter(List<Comparable> list)) {
  expect(sorter([3,1,2,5,4]), equals([1,2,3,4,5]));
  expect(sorter([6,3,1,4,2,5]), equals([1,2,3,4,5,6]));
}