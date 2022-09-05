data class Note(
    val id: Int,
    var title: String,
    var text: String,
    val date: Int,
    val user: User,
    val ownerId: Int = user.id,
    var comments: MutableList<Comment> = mutableListOf<Comment>()
) {
    override fun toString(): String {
        if (comments.size < 1)
            return "$id Заметка: $title, $text, Автор: $user, $date"
        else
            return "$id Заметка: $title, $text, Автор: $user, $date " + commentsToString()
    }

    fun findComment(id: Int): Comment? {
        var commentToReturn: Comment? = null
        for (index in comments.indices) {
            if (id == comments[index].id) {
                commentToReturn = comments[index]
            }

        }
        return commentToReturn
    }

    fun commentsToString(): String {
        var commentToString: String
        var result: String = ""
        for (index in comments.indices) {

            result += "\n" + comments[index].toString()
        }
        return result
    }


}