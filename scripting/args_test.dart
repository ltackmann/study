class ArgsParser {
  var _help = const {
     "install": "Install a package",
     "link": "Symlink a package folder",
     "list": "List installed packages",
     "test": "Test a package"
  };
  
  // TODO print commands in help text from above
  // TODO add svn support to get the google guys on
  // TODO rename modules to packages
  ArgsParser() {
    // setup handlers
    // TODO typedef
    Map<String, Function> _handlers = {
      "help": _showHelp
    };
    
    var opts = new Options();
    List<String> arguments = opts.arguments;
    final int numArgs = arguments.length;
    
    if(numArgs < 1 || !_handlers.containsKey(arguments[0])) {
      _showHelp();
    } else  {
      List<String> tail = (numArgs > 1) ? arguments.getRange(1, numArgs) : [];
      _handlers[arguments[0]](tail);
    } 
  }
  
  _showHelp([tail]) {
    print('''

Usage: dpm <command>

where <command> is one of:
    help install link list test version 

npm help <command>: quick help on <command>
    ''');
  }
}
