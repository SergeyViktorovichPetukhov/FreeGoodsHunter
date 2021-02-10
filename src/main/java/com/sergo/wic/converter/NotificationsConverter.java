package com.sergo.wic.converter;

import com.sergo.wic.dto.NotificationsDto;
import com.sergo.wic.entities.Notification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationsConverter {
    @Autowired
    private ModelMapper modelMapper;

    public List<NotificationsDto> convertAllNotifications(List<Notification> notifications){
        List<NotificationsDto> result = new ArrayList<>(notifications.size());
        for(int i = 0; i < notifications.size(); i ++){
            NotificationsDto notificationsDto = new NotificationsDto();
            result.add(notificationsDto);
            modelMapper.map(notifications.get(i), result.get(i));
        }
        return result;
    }
}
