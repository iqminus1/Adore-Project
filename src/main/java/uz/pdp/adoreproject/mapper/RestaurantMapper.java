package uz.pdp.adoreproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.adoreproject.entity.Comment;
import uz.pdp.adoreproject.entity.Restaurant;
import uz.pdp.adoreproject.payload.CommentDTO;
import uz.pdp.adoreproject.payload.RestaurantCrudDTO;
import uz.pdp.adoreproject.payload.RestaurantDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RestaurantMapper {
    RestaurantDTO toDTO(Restaurant restaurant);

    Restaurant toEntity(RestaurantCrudDTO restaurantCrudDTO);

    void updateEntity(RestaurantCrudDTO crudDTO, @MappingTarget Restaurant restaurant);

}