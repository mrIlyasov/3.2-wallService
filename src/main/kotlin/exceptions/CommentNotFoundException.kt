package exceptions

class CommentNotFoundException(message: String= "Комментарий не найден!"): RuntimeException(message) {
}