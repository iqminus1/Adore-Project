package uz.pdp.adoreproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.adoreproject.entity.*;
import uz.pdp.adoreproject.entity.template.AbsIntEntity;
import uz.pdp.adoreproject.mapper.RestaurantMapper;
import uz.pdp.adoreproject.payload.ApiResultDTO;
import uz.pdp.adoreproject.payload.RestaurantCrudDTO;
import uz.pdp.adoreproject.payload.RestaurantDTO;
import uz.pdp.adoreproject.repository.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final AttachmentServiceImpl attachmentService;
    private final RegionRepository regionRepository;
    private final AttachmentRepository attachmentRepository;
    private final DistrictRepository districtRepository;
    private final NeighbourhoodRepository neighbourhoodRepository;

    @Override
    public ApiResultDTO<List<RestaurantDTO>> read() {
        List<RestaurantDTO> list = restaurantRepository.findAll().stream().map(this::toDTO).toList();
        return ApiResultDTO.success(list);
    }


    @Override
    public ApiResultDTO<RestaurantDTO> read(Integer id) {
        return ApiResultDTO.success(toDTO(restaurantRepository.findById(id).orElseThrow()));
    }

    @Override
    public ApiResultDTO<RestaurantDTO> create(RestaurantCrudDTO crudDTO) {
        List<Attachment> attachments = attachmentRepository.findAllById(crudDTO.getAttachmentIds());
        Neighbourhood neighbourhood = getNeighbourhood(crudDTO);
        Restaurant restaurant = restaurantMapper.toEntity(crudDTO);
        restaurant.setNeighbourhood(neighbourhood);
        restaurant.setAttachments(attachments);
        restaurantRepository.save(restaurant);
        return ApiResultDTO.success(toDTO(restaurant));
    }


    @Override
    public ApiResultDTO<RestaurantDTO> update(Integer id, RestaurantCrudDTO crudDTO) {
        List<Attachment> attachments = attachmentRepository.findAllById(crudDTO.getAttachmentIds());
//        List<Integer> attachmentIds = attachments.stream().map(AbsIntEntity::getId).toList();
//        crudDTO.getAttachmentIds().forEach(attId->{
//            if (attachmentIds.contains(attId)) {
//                return;
//            }
//            attachments.add(attachmentRepository.findById(attId).orElseThrow());
//        });
        Neighbourhood neighbourhood = getNeighbourhood(crudDTO);
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        restaurantMapper.updateEntity(crudDTO, restaurant);
        restaurant.setNeighbourhood(neighbourhood);
        restaurant.setAttachments(attachments);
        restaurantRepository.save(restaurant);
        return ApiResultDTO.success(toDTO(restaurant));
    }

    @Override
    public void delete(Integer id) {
        restaurantRepository.deleteById(id);
    }

    private RestaurantDTO toDTO(Restaurant restaurant) {
        RestaurantDTO dto = restaurantMapper.toDTO(restaurant);
        dto.setNeighbourhoodId(restaurant.getNeighbourhood().getId());
        dto.setAttachmentIds(restaurant.getAttachments().stream().map(AbsIntEntity::getId).toList());
        return dto;
    }

    private Neighbourhood getNeighbourhood(RestaurantCrudDTO crudDTO) {
        Region region = regionRepository.findByName(crudDTO.getName()).orElseGet(() -> {
            Region reg = new Region(crudDTO.getRegionName());
            regionRepository.save(reg);
            return reg;
        });
        District district = districtRepository.findByNameAndRegionId(crudDTO.getName(), region.getId()).orElseGet(() -> {
            District dis = new District(region, crudDTO.getDistrictName());
            districtRepository.save(dis);
            return dis;
        });
        return neighbourhoodRepository.findByNameAndDistrictIdAndHouseNumberAndStreet(
                crudDTO.getNeighbourhoodName()
                , district.getId(),
                crudDTO.getNeighbourhoodHouseNumber(),
                crudDTO.getNeighbourhoodStreet()).orElseGet(() -> {
            Neighbourhood nei = new Neighbourhood(district,
                    crudDTO.getNeighbourhoodName(),
                    crudDTO.getNeighbourhoodStreet(),
                    crudDTO.getNeighbourhoodHouseNumber());
            neighbourhoodRepository.save(nei);
            return nei;
        });
    }
}
