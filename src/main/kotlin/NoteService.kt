import exceptions.NoteNotFoundException

object NoteService {
    var notes = mutableListOf<Note>()
    var deletedComments = mutableListOf<Comment>()

    fun createNote(note: Note) {
        var noteToAdd: Note = if (notes.size < 1) note.copy(id = 1) else note.copy(id = notes.last().id + 1)
        notes += noteToAdd
    }


    fun editNote(note: Note, newText: String, newTitle: String): Note {
        var noteToReturn: Note = note.copy(text = newText, title = newTitle)
        return noteToReturn
    }


    fun getNotesOfUser(user: User): List<Note> {
        var notesOfUser = mutableListOf<Note>()
        for (index in notes.indices) {
            if (user == notes[index].user) {
                notesOfUser.add(notes[index])
            }
        }
        if (notesOfUser.size > 0) {
            for (index in notesOfUser.indices) {
                println(notesOfUser[index])
            }
        }
        return notesOfUser
    }

    fun deleteNote(id: Int) {
        var indexOfNote: Int? = findNoteIndex(id)
        val note: Note? = findNote(id)
        if (indexOfNote != null) {
            for (index in note!!.comments.indices) {
                note!!.comments[index].editable = false
            }
            notes.removeAt(indexOfNote)
        }
    }


    fun createComment(comment: Comment) {
        var noteToAddComment = findNote(comment.noteId)
        if (noteToAddComment != null) {
            var commentToAdd: Comment =
                if (noteToAddComment.comments.size < 1) comment.copy(id = 1) else comment.copy(id = noteToAddComment.comments.last().id + 1)
            noteToAddComment.comments += commentToAdd
        } else
            throw NoteNotFoundException()
    }


    fun editComment(comment: Comment, newMessage: String): Comment {
        var commentToReturn: Comment
        if (comment.editable) {
            commentToReturn = comment.copy(message = newMessage)
        } else commentToReturn = comment
        return commentToReturn
    }


    fun findComment(id: Int, noteId: Int): Comment? {
        var note: Note? = findNote(noteId)
        var commentToReturn: Comment? = null
        if (note != null) {
            for (index in note.comments.indices) {
                if (id == note.comments[index].id) {
                    commentToReturn = note.comments[index]
                }
            }
        }
        return commentToReturn
    }

    fun deleteComment(id: Int, note: Note) {
        var commentIndex = findCommentIndex(id, note)
        if (commentIndex != null) {
            note.comments[commentIndex].editable = false
            deletedComments += note.comments[commentIndex]
            note.comments.removeAt(commentIndex)
        }
    }


    fun restoreComment(id: Int, note: Note) {
        var commentToRestore: Comment? = null
        for (index in deletedComments.indices) {
            if (id == deletedComments[index].id && note.id == deletedComments[index].noteId)
                commentToRestore = deletedComments[index]
        }

        if (commentToRestore != null) {
            commentToRestore.editable = false
            note.comments.add(commentToRestore.id - 1, commentToRestore)
        }
    }

    fun getComments(note: Note): List<Comment> {
        var commentsOfNote = mutableListOf<Comment>()
        for (index in note.comments.indices) {
            commentsOfNote += note.comments[index]

        }
        return commentsOfNote
    }

    fun findNote(id: Int): Note? {
        var noteToReturn: Note? = null
        for (index in notes.indices) {
            if (id == notes[index].id)
                noteToReturn = notes[index]
        }
        return noteToReturn
    }

    fun findNoteIndex(id: Int): Int? {
        var indexOfNote: Int? = null
        for (index in notes.indices) {
            if (id == notes[index].id)
                indexOfNote = index
        }
        return indexOfNote
    }

    fun findCommentIndex(id: Int, note: Note): Int? {
        var indexOfComment: Int? = null
        for (index in note.comments.indices) {
            if (id == note.comments[index].id)
                indexOfComment = index
        }
        return indexOfComment
    }


    fun printNotes() {
        if (notes.size > 0) {
            for (index in notes.indices) {
                println(notes[index])
            }
        } else println("There are no notes created!")
    }


}