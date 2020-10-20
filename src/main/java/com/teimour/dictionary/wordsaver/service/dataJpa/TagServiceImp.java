package com.teimour.dictionary.wordsaver.service.dataJpa;

import com.teimour.dictionary.wordsaver.domain.Tag;
import com.teimour.dictionary.wordsaver.repository.TagRepository;
import com.teimour.dictionary.wordsaver.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author kebritam
 * @project word-saver
 * @date 20/10/2020
 */

@Slf4j
@Service
public class TagServiceImp implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImp(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag findById(UUID uuid) {
        Optional<Tag> tagOptional=tagRepository.findById(uuid);
        if (tagOptional.isPresent()){
            return tagOptional.get();
        } else{
            throw new NullPointerException();// TODO: 10/20/2020 implement better exception
        }
    }

    @Override
    public Set<Tag> findAll() {
        return Set.copyOf(tagRepository.findAll());
    }

    @Override
    public Tag save(Tag object) {
        return tagRepository.save(object);
    }

    @Override
    public void delete(Tag object) {
        tagRepository.delete(object);
        log.info("tag deleted");
    }

    @Override
    public void deleteById(UUID uuid) {
        tagRepository.deleteById(uuid);
        log.info("tag deleted");
    }
}
