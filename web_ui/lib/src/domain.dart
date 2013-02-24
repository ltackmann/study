/**
 * A item in our inventory
 */
class InventoryItem {
  InventoryItem(this.name):
    itemCount = 0,
    activated = true;
  
  int itemCount;
  bool activated;
  String name;
}
