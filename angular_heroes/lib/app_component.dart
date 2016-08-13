import 'dart:async';

import 'package:angular2/core.dart';

import 'hero.dart';
import 'hero_detail_component.dart';
import 'hero_service.dart';

@Component(
  selector: 'my-app',
  templateUrl: 'app_component.html',
  directives: const [HeroDetailComponent],
  providers: const [HeroService])
  class AppComponent implements OnInit {
    String title = 'Tour of Heroes';
    List<Hero> heroes;
    Hero selectedHero;
    final HeroService _heroService;
    AppComponent(this._heroService);

    Future<Null> getHeroes() async {
      heroes = await _heroService.getHeroes();
    }

    void ngOnInit() {
      getHeroes();
    }

    void onSelect(Hero hero) {
      selectedHero = hero;
    }
  }
