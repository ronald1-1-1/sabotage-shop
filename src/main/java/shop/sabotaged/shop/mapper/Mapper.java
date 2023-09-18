package shop.sabotaged.shop.mapper;

public interface Mapper<DTO, ENTITY> {

    DTO toDtoFromEntity(ENTITY entity);
}
