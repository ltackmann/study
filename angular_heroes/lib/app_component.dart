import 'package:angular2/core.dart';
import 'hero.dart';
import 'hero_detail_component.dart';

@Component(
  selector: 'my-app',
  templateUrl: 'app_component.html',
  directives: const [HeroDetailComponent])
class AppComponent {
  Hero selectedHero;
  String title = 'Tour of Heroes';
  final List<Hero> heroes = mockHeroes;

  Hero hero = new Hero(1, 'Windstorm');

  onSelect(Hero hero) {
    selectedHero = hero;
  }
}

final List<Hero> mockHeroes = [
  new Hero(11, 'Mr. Nice'),
  new Hero(12, 'Narco'),
  new Hero(13, 'Bombasto'),
  new Hero(14, 'Celeritas'),
  new Hero(15, 'Magneta'),
  new Hero(16, 'RubberMan'),
  new Hero(17, 'Dynama'),
  new Hero(18, 'Dr IQ'),
  new Hero(19, 'Magma'),
  new Hero(20, 'Tornado')
];
