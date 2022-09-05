package exceptions

class NoteNotFoundException(message: String = "Заметка не найдена!"): RuntimeException(message) {
}