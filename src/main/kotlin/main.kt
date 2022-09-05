fun main() {
    var user = User(1, "Vasya")
    var user2 = User(2, "Petya")
    var note = Note(1, "Title1", "text1", 12345, user)
    var comment = Comment(1, user, "Hi", 1)
    var comment2 = Comment(1, user2, "Hello", 1)
    var comment3 = Comment(1, user, "Comment3", 1)
    var note2 = Note(1, "Title2", "text2", 12347, user)
    var comment4 = Comment(1, user, "Hi2", 2)
    var comment5 = Comment(1, user, "Hello2", 2)
    var noteService = NoteService
    noteService.createNote(note)
    noteService.createComment(comment)
    noteService.createComment(comment2)
    noteService.createComment(comment3)
    noteService.createNote(note2)
    noteService.createComment(comment4)
    noteService.createComment(comment5)
    noteService.printNotes()
    println()
    println()
    noteService.deleteComment(2, note)
    noteService.printNotes()
    println()
    println()
    noteService.restoreComment(2, note)
    noteService.printNotes()

/*    println(note)
    println(comment)*/

}