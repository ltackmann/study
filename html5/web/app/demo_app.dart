import 'package:route/client.dart';
import 'package:polymer/polymer.dart';

@CustomTag('demo-app')
class DemoApp extends PolymerElement {
  DemoApp.created(): super.created() {
    print("app created");
    /*
     * TODO Set up routing whith named events to centralize URL handling, but on the same
     * time make sure that URLs are actually bookmarkable 
     * - Url getUrl(navigationEvent)
     * - NavigationEvent getEvent(url)
     * 
     * TODO use dice to inject eventbus into each component
     */
    _router.addHandler(_effectsDemoUrl, (path) => print(path));
    _router.addHandler(_inputDemoUrl, (path) => print(path));
    _router.addHandler(_layoutDemoUrl, (path) => print(path));
    _router.addHandler(_notificationDemoUrl, (path) => print(path));
    _router.addHandler(_storageDemoUrl, (path) => print(path));
    _router.addHandler(_svgDemoUrl, (path) => print(path));
  }
  
  @initMethod
  init() {
    print("app initialized");
    _router.listen();
    _router.gotoUrl(_inputDemoUrl, [r'index.html#'], 'home');
  }
  
  final _effectsDemoUrl = new UrlPattern(r'(.*)demo/effects');
  final _inputDemoUrl = new UrlPattern(r'(.*)demo/input');
  final _layoutDemoUrl = new UrlPattern(r'(.*)demo/layout');
  final _notificationDemoUrl = new UrlPattern(r'(.*)demo/notification');
  final _storageDemoUrl = new UrlPattern(r'(.*)demo/storage');
  final _svgDemoUrl = new UrlPattern(r'(.*)demo/svg');
  final _router = new Router();
}