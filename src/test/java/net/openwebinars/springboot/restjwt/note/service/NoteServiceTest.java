package net.openwebinars.springboot.restjwt.note.service;

import net.openwebinars.springboot.restjwt.note.model.Note;
import net.openwebinars.springboot.restjwt.note.repo.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    NoteRepository repository;

    @InjectMocks
    NoteService service;

    @Test
    void notesGroupedByTagsDtoList() {

        Note note = Note.builder()
                .title("Quincy")
                .content("parturient montes nascetur ridiculus mus etiam vel augue vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id massa")
                .author("Mélissandre")
                .important(false)
                .tags(List.of("tag1", "tag2", "tag3"))
                .build();

        Note note1 = Note.builder()
                .title("Apuntes")
                .content("parturient montes nascetur ridiculus mus etiam vel augue vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id massa")
                .author("Mélissandre")
                .important(true)
                .tags(List.of("tag1", "tag2", "tag3"))
                .build();

        when(repository.findByAuthor("Mélissandre")).thenReturn(List.of(note1, note));

        service.notesGroupedByTagsDtoList("Mélissandre");

        assertEquals(2, List.of(note1, note).size());

    }
}