package com.duyduong.jobhunter.mapper;

import com.duyduong.jobhunter.domain.User;
import com.duyduong.jobhunter.domain.dto.request.UserReqDTOCreate;
import com.duyduong.jobhunter.domain.dto.request.UserReqDTOUpdate;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMapperConfig {

    @Bean
    public ModelMapper userMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        PropertyMap<?, ?> convertUserReqDTOCreate2User = new PropertyMap<UserReqDTOCreate, User>() {
            @Override
            protected void configure() {
                skip(destination.getId()); // ID thường được sinh bởi database
                skip(destination.getRefreshToken()); // Không cần nhận refresh token từ request create
                skip(destination.getCreatedAt()); // Các trường thời gian nên được xử lý ở backend
                skip(destination.getUpdatedAt());
                skip(destination.getCreatedBy());
                skip(destination.getUpdatedBy());
            }
        };

        PropertyMap<?, ?> convertUserReqDTOUpdate2User = new PropertyMap<UserReqDTOUpdate, User>() {
            @Override
            protected void configure() {
                skip(destination.getId()); // ID thường được sinh bởi database
                skip(destination.getEmail());
                skip(destination.getPassword());
                skip(destination.getRefreshToken()); // Không cần nhận refresh token từ request create
                skip(destination.getCreatedAt()); // Các trường thời gian nên được xử lý ở backend
                skip(destination.getUpdatedAt());
                skip(destination.getCreatedBy());
                skip(destination.getUpdatedBy());
            }
        };
        mapper.addMappings(convertUserReqDTOCreate2User);
        mapper.addMappings(convertUserReqDTOUpdate2User);
        return mapper;
    }
}
