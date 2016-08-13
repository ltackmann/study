import 'package:angular2/core.dart';
import 'hero.dart';

@Component(
  selector: 'my-hero-detail',
  templateUrl: 'hero_detail_component.html')
class HeroDetailComponent {
  @Input()
  Hero hero;
}
