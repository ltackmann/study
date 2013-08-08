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
      
      assertSort(selectionSort);
    });
    
    test("insertion sort", () {
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
      
      assertSort(insertionSort);
    });
    
    test("merge sort", () {
      // merging the sorted sublists list[start..pivot] and list[pivot..end] 
      // into one sorted list list[start..end] 
      merge(List<Comparable> list, int start, int pivot, int end) {
        List<Comparable> tmp1 = list.getRange(start, pivot+1).toList(); 
        List<Comparable> tmp2 = list.getRange(pivot+1, end+1).toList(); 
        int i = 0, j = 0;
        for(int k=start; k<=end; k++) {
          if(i < tmp1.length && j >= tmp2.length) {
            list[k] = tmp1[i];
            i++;
          } else if(i >= tmp1.length && j < tmp2.length) {
            list[k] = tmp2[j];
            j++;
          } else if(tmp1[i].compareTo(tmp2[j]) <= 0) {
            list[k] = tmp1[i];
            i++;
          } else {
            list[k] = tmp2[j];
            j++;
          }
        }
      }
      
      mergeSortRec(List<Comparable> list, int start, int end) {
        if(start >= end) {
          return;
        }
        var pivot = (start + end) ~/ 2;
        mergeSortRec(list, start, pivot);
        mergeSortRec(list, pivot + 1, end);
        merge(list, start, pivot, end);
      }
      
      List<Comparable> mergeSort(List<Comparable> list) {
        mergeSortRec(list, 0, list.length - 1);
        return list;
      }
      
      assertSort(mergeSort);
    });
    
    solo_test("quick sort", () {
      // rearranges list[start..end] so that every element in 
      // list[start..pivot] is less than list[pivot] and every 
      // element in list[pivot..end] is greater than list[pivot]
      int partition(List<Comparable> list, int start, int end) {
        int pivot = start;
        for(int i=start; i<end; i++) {
          if(list[i].compareTo(list[end]) <= 0) {
            swap(list, pivot, i);
            pivot++;
          }
        }
        swap(list, pivot, end);
        return pivot;
      }
      
      quickSortRec(List<Comparable> list, int start, int end) {
        if(start >= end) {
          return;
        }
        int pivot = partition(list, start, end);
        quickSortRec(list, start, pivot-1);
        quickSortRec(list, pivot+1, end);
      }
      
      List<Comparable> quickSort(List<Comparable> list) {
        quickSortRec(list, 0, list.length - 1);
        return list;
      }
      
      assertSort(quickSort);
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

assertSort(List<Comparable> sorter(List<Comparable> list)) {
  expect(sorter([4,3,1,2]), equals([1,2,3,4]));
  expect(sorter([3,1,2,5,4]), equals([1,2,3,4,5]));
  expect(sorter([6,3,1,4,2,5]), equals([1,2,3,4,5,6]));
}