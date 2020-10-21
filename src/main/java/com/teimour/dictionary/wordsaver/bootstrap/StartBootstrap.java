package com.teimour.dictionary.wordsaver.bootstrap;

import com.teimour.dictionary.wordsaver.domain.*;
import com.teimour.dictionary.wordsaver.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author kebritam
 * @project word-saver
 * @date 16/10/2020
 */

@Component
public class StartBootstrap implements CommandLineRunner {

    private final DefinitionService definitionService;
    private final ExampleService exampleService;
    private final NoteService noteService;
    private final WordService wordService;
    private final CategoryService categoryService;

    public StartBootstrap(DefinitionService definitionService, ExampleService exampleService,
                          NoteService noteService, WordService wordService, CategoryService categoryService) {
        this.definitionService = definitionService;
        this.exampleService = exampleService;
        this.noteService = noteService;
        this.wordService = wordService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {

        Example example1= Example.builder()
                .exampleValue("hey kamran what are you doing.").build();
        Example example2=Example.builder()
                .exampleValue("hey mamad I hate you.").build();

        Definition definition1= Definition.builder()
                .definitionValue("when you meet someone say it.")
                .examples(Set.of(example1,example2))
                .wordClass(WordClasses.NOUN).build();

        Note note1= Note.builder()
                .notesValue("it's slang.").build();

        Word word1= Word.builder()
                .wordClasses(Set.of(WordClasses.NOUN))
                .wordValue("hey")
                .definitions(Set.of(definition1))
                .notes(note1)
                .phonetic("hei")
                .antonyms(new HashSet<>())
                .synonyms(new HashSet<>())
                .build();

        Category category1 = Category.builder()
                .categoryName("conversation")
                .words(Set.of(word1)).build();
        Category category2 = Category.builder()
                .categoryName("meeting")
                .words(Set.of(word1)).build();

        exampleService.save(example1);
        exampleService.save(example2);
        definitionService.save(definition1);
        noteService.save(note1);
        wordService.save(word1);
        categoryService.save(category1);
        categoryService.save(category2);



        Example example3= Example.builder()
                .exampleValue("hi there.").build();
        Example example4=Example.builder()
                .exampleValue("hi stupid.").build();

        Definition definition2= Definition.builder()
                .definitionValue("when you meet someone say it.")
                .examples(Set.of(example3,example4))
                .wordClass(WordClasses.NOUN).build();

        Note note2= Note.builder()
                .notesValue("it's middle polite.").build();

        Word word2= Word.builder()
                .wordClasses(Set.of(WordClasses.NOUN))
                .wordValue("hi")
                .definitions(Set.of(definition2))
                .notes(note2)
                .phonetic("hai")
                .antonyms(new HashSet<>())
                .synonyms(new HashSet<>())
                .build();

        Category category3 = Category.builder()
                .categoryName("bump into")
                .words(Set.of(word2)).build();
        Category category4 = Category.builder()
                .categoryName("shy")
                .words(Set.of(word2)).build();

        exampleService.save(example3);
        exampleService.save(example4);
        definitionService.save(definition2);
        noteService.save(note2);
        wordService.save(word2);
        categoryService.save(category3);
        categoryService.save(category4);




        Example example5= Example.builder()
                .exampleValue("hello sir.").build();
        Example example6=Example.builder()
                .exampleValue("hello doctor.").build();

        Definition definition3= Definition.builder()
                .definitionValue("when you meet someone say it.")
                .examples(Set.of(example5,example6))
                .wordClass(WordClasses.NOUN).build();

        Note note3= Note.builder()
                .notesValue("it's very polite.").build();

        Word word3= Word.builder()
                .wordClasses(Set.of(WordClasses.NOUN))
                .wordValue("hello")
                .definitions(Set.of(definition3))
                .notes(note3)
                .phonetic("hello")
                .antonyms(new HashSet<>())
                .synonyms(new HashSet<>())
                .build();

        Category category5 = Category.builder()
                .categoryName("respect")
                .words(Set.of(word1, word3)).build();

        exampleService.save(example5);
        exampleService.save(example6);
        definitionService.save(definition3);
        noteService.save(note3);
        wordService.save(word3);
        categoryService.save(category5);



        Example example7= Example.builder()
                .exampleValue("bye kamran.").build();
        Example example8=Example.builder()
                .exampleValue("bye mamad I hate you.").build();

        Definition definition4= Definition.builder()
                .definitionValue("when you want to end conversation you will say it.")
                .examples(Set.of(example7,example8))
                .wordClass(WordClasses.NOUN).build();


        Word word4= Word.builder()
                .wordClasses(Set.of(WordClasses.NOUN))
                .wordValue("bye")
                .definitions(Set.of(definition4))
                .notes(note1)
                .phonetic("bai")
                .antonyms(new HashSet<>())
                .synonyms(new HashSet<>())
                .build();

        Category category6 = Category.builder()
                .categoryName("end")
                .words(Set.of(word4)).build();
        Category category7 = Category.builder()
                .categoryName("bless")
                .words(Set.of(word1, word4)).build();

        exampleService.save(example7);
        exampleService.save(example8);
        definitionService.save(definition4);
        noteService.save(note1);
        wordService.save(word4);
        categoryService.save(category6);
        categoryService.save(category7);


        word1.getSynonyms().add(word2);
        word1.getSynonyms().add(word3);
        word1.getAntonyms().add(word4);

        wordService.save(word1);
    }
}
