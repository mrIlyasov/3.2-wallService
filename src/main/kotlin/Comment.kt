data class Comment(val id: Int, val user: User, val message: String, val noteId: Int, var editable: Boolean = true) {
    override fun toString(): String {
        return "$user прокомментировал: $message"
    }
}