package by.dima.training.converters.impl;

import by.dima.training.converters.Converter;
import by.dima.training.dto.TagDTO;
import by.dima.training.model.Tag;
import org.springframework.stereotype.Service;

@Service
public class TagConverter implements Converter<TagDTO, Tag> {
    @Override
    public Tag convert(TagDTO source) {
        return new Tag(source.getId(), source.getName());
    }
}
