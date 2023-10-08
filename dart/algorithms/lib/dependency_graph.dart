main() {
  print("test");

  // nodes
  var a = new Node('a');
  var b = new Node('b');
  var c = new Node('c');
  var d = new Node('d');
  var e = new Node('e');

  // edges
  a.edges.add(b);    // a depends on b
  a.edges.add(d);    // a depends on d
  b.edges.add(c);    // b depends on c
  b.edges.add(e);    // b depends on e
  c.edges.add(d);    // c depends on d
  c.edges.add(e);    // c depends on e

  //c.edges.add(a);    // Circular dependency c depends on a

  // dependency resolve
  var resolved = <Node>[];
  var unresolved = <Node>[];
  dep_resolve(a, resolved, unresolved);
  print("resolved dependencies $resolved");
}


/**
 * Dependency resolution
 *
 * A software package can be installed when all of its dependencies have
 * been installed, or when it doesn’t have any dependencies at all.
 */
void dep_resolve(Node node, List<Node> resolved, List<Node> unresolved) {
    unresolved.add(node);
    node.edges.forEach((edge) {
      // only visit node once
      if(!resolved.contains(edge)) {
        // circular reference is occuring when we see a software package more
        // than once, unless that software package has all its dependencies
        // resolved.
        if(unresolved.contains(edge)) {
          throw "Circular reference detected: circular dependency [${node.name} -> ${edge.name}] for resolved $resolved and unresolved $unresolved";
        }
        dep_resolve(edge, resolved, unresolved);
      }
    });
    resolved.add(node);
    unresolved.remove(node);
}

class Node {
  final List<Node> edges = [];
  final String name;

  Node(this.name);

  @override
  String toString() => name;
}
