package moussed.edu.tr.model;

import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseDto toDTO(Course course);

    Course toEntity(CourseDto dto);
}
