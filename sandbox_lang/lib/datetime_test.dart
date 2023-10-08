import 'package:test/test.dart';

main() {
  DateTime now = new DateTime.now();
  DateTime berlinWallFell = new DateTime(1989, 11, 9);
  DateTime moonLanding = DateTime.parse("1969-07-20 20:18:00");  // 8:18pm

  test("isAfter", () {
    expect(now.isAfter(berlinWallFell), isTrue);
    expect(now.isAfter(moonLanding), isTrue);
    expect(berlinWallFell.isAfter(moonLanding), isTrue);
  });

  test("compareTo", () {
    expect(now.compareTo(berlinWallFell), equals(1));
    expect(now.compareTo(moonLanding), equals(1));
    expect(moonLanding.compareTo(now), equals(-1));
    expect(moonLanding.compareTo(berlinWallFell), equals(-1));
    expect(berlinWallFell.compareTo(now), equals(-1));
    expect(berlinWallFell.compareTo(moonLanding), equals(1));
  });

  test("sort ascending", () {
    List<DateTime> dates = [ moonLanding, now, berlinWallFell ];
    dates.sort((d1, d2) => d1.compareTo(d2));
    expect(dates, equals([moonLanding, berlinWallFell, now]));
  });

  test("sort decending", () {
    List<DateTime> dates = [ moonLanding, now, berlinWallFell ];
    dates.sort((d1, d2) => d2.compareTo(d1));
    expect(dates, equals([now, berlinWallFell, moonLanding]));
  });

}
