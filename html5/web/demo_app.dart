import 'package:route/client.dart';

class DemoApp {
  DemoApp() {
    /*
     * TODO Set up routing whith named events to centralize URL handling, but on the same
     * time make sure that URLs are actually bookmarkable 
     */

    print("in demo app");
    _router.addHandler(_effectsDemoUrl, (path) => print(path));
    _router.addHandler(_inputDemoUrl, (path) => print(path));
    _router.addHandler(_layoutDemoUrl, (path) => print(path));
    _router.addHandler(_notificationDemoUrl, (path) => print(path));
    _router.addHandler(_storageDemoUrl, (path) => print(path));
    _router.addHandler(_svgDemoUrl, (path) => print(path));
  }
  
  start() {
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

main() {
  var app = new DemoApp();
  app.start();
}