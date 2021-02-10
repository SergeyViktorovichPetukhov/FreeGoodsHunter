package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.NotificationsDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NotificationResponse extends ResponseContent {
    private List<NotificationsDto> notifications;
}
