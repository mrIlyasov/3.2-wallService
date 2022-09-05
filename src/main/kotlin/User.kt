class User(idParam: Int, nameParam: String) {
    val id: Int = if (idParam<1) 1 else idParam
    var name = nameParam


    override fun toString(): String{
        return "$name"
    }
}