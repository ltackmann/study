import 'package:web_ui/web_ui.dart';

class InventoryItemDetailsComponent extends WebComponent {
  InventoryItemDetailsComponent(this.name, this.itemCount);
  
  // TODO add some info to the details that stems from another domain object (fx name of user who checked it in)
  String name;
  int itemCount;
}


