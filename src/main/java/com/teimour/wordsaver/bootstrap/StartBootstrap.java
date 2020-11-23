package com.teimour.wordsaver.bootstrap;

import com.teimour.wordsaver.domain.*;
import com.teimour.wordsaver.security.user.Authority;
import com.teimour.wordsaver.security.user.User;
import com.teimour.wordsaver.security.user.UserRepository;
import com.teimour.wordsaver.service.CategoryService;
import com.teimour.wordsaver.service.WordService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author kebritam
 * Project word-saver
 * Created on 16/10/2020
 */

@Component
public class StartBootstrap implements CommandLineRunner {

    private final WordService wordService;
    private final CategoryService categoryService;
    private final UserRepository userRepository;

    public StartBootstrap(WordService wordService, CategoryService categoryService, UserRepository userRepository) {
        this.wordService = wordService;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

        User user=new User();
        user.setId(1);
        user.setEmail("amirali@gmail.com");
        user.setUsername("kebritam");
        user.setPassword("$2y$12$ef/FiQiAyAdNnY5vJUQrw.VO1DA8AuAjbBp6UqnCVbCrMTMXw2uP2");
        user.setAuthorities(List.of(Authority.ROLE_ADMIN,Authority.ROLE_USER));
        userRepository.save(user);

        Example example1= Example.builder()
                .exampleValue("hey kamran what are you doing.").build();
        Example example2=Example.builder()
                .exampleValue("hey mamad I hate you.").build();

        Definition definition1= Definition.builder()
                .definitionValue("when you meet someone say it.")
                .examples(Set.of(example1,example2))
                .wordClass(WordClass.NOUN).build();

        Note note1= Note.builder()
                .notesValue("it's slang.").build();

        Word word1= Word.builder()
                .wordClasses(Set.of(WordClass.NOUN))
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
                .wordClass(WordClass.NOUN).build();

        Note note2= Note.builder()
                .notesValue("it's middle polite.").build();

        Word word2= Word.builder()
                .wordClasses(Set.of(WordClass.NOUN))
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
                .wordClass(WordClass.NOUN).build();

        Note note3= Note.builder()
                .notesValue("it's very polite.").build();

        Word word3= Word.builder()
                .wordClasses(Set.of(WordClass.NOUN))
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

        wordService.save(word3);
        categoryService.save(category5);



        Example example7= Example.builder()
                .exampleValue("bye kamran.").build();
        Example example8=Example.builder()
                .exampleValue("bye mamad I hate you.").build();

        Definition definition4= Definition.builder()
                .definitionValue("when you want to end conversation you will say it.")
                .examples(Set.of(example7,example8))
                .wordClass(WordClass.NOUN).build();


        Word word4= Word.builder()
                .wordClasses(Set.of(WordClass.NOUN))
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

        wordService.save(word4);
        categoryService.save(category6);
        categoryService.save(category7);


        wordService.addSynonym(word1,word2);
        wordService.addSynonym(word1,word3);
        wordService.addAntonym(word1,word4);

        wordService.save(word2);
        wordService.save(word3);
        wordService.save(word4);
        wordService.save(word1);
    }
}
