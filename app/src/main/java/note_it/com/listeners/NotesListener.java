package note_it.com.listeners;

import note_it.com.entities.Note;

public interface NotesListener {
    void onNoteClicked(Note note, int position);
}
