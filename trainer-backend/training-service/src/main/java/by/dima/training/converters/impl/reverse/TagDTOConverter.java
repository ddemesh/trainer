package by.dima.training.converters.impl.reverse;

import by.dima.training.converters.Converter;
import by.dima.training.dto.TagDTO;
import by.dima.training.model.Tag;
import org.springframework.stereotype.Service;

@Service
public class TagDTOConverter implements Converter<Tag, TagDTO> {

    @Override
    public TagDTO convert(Tag source) {
        return new TagDTO(source.getId(), source.getName());
    }
}
