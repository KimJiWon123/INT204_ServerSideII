package sit.int204.practice.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import sit.int204.practice.DTO.PageDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ListMapper {
    public static final ListMapper listMapper = new ListMapper();
    private static ModelMapper modelMapper = new ModelMapper();
    public Object toPage;

    private ListMapper() {
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass, ModelMapper modelMapper) {
        return source.stream().map(entity -> modelMapper.map(entity, targetClass)).collect(Collectors.toList());
    }

    public static ListMapper getInstance() {
        return listMapper;
    }
    public <S, T> PageDTO<T> toPageDTO(Page<S> source, Class<T> targetClass, ModelMapper modelMapper) {
        PageDTO<T> page = modelMapper.map(source, PageDTO.class);
        page.setContent(mapList(source.getContent(), targetClass, modelMapper));
        return page;
    }

//    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
//        return mapList(source,targetClass, modelMapper);
//    }

//    public <S, T> PageDTO<T> toPageDTO(Page<S> source, Class<T> targetClass) {
//        return toPageDTO(source, targetClass, modelMapper);
//    }
}