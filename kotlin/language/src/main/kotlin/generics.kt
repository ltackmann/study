
// generic function 
fun <T : Comparable<T>> compareTo(compare : T, to : T) : Int {
    return compare.compareTo(to)
}

// TODO in and out