import 'dart:async';

import 'package:angular2/core.dart';
import 'package:angular2/router.dart';

import 'hero.dart';
import 'hero_service.dart';

@Component(
    selector: 'my-dashboard',
    styleUrls: const ['dashboard_component.css'],
    templateUrl: 'dashboard_component.html'
)
class DashboardComponent implements OnInit {
  final HeroService _heroService;
  final Router _router;
  List<Hero> heroes;

  DashboardComponent(this._heroService, this._router);

  @override
  Future<Null> ngOnInit() async {
    heroes = (await _heroService.getHeroes()).skip(1).take(4).toList();
  }

  gotoDetail(Hero hero) {
    var link = [
       'HeroDetail',
       {'id': hero.id.toString()}
     ];
     _router.navigate(link);
  }
}
