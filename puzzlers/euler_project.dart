/**
 * TODO how to make link to the Euler tasks
 */
main() {
  test('P01: Find the last element of a list', () {
    dynamic last(List list) => list.last;
    expect(last([1,1,2,3,5,8]), equals(8));
  });
}