package com.sergo.wic.dto.Response;

import com.sergo.wic.dto.Response.ResponseContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class UnreadNotificationsResponse extends ResponseContent {
    boolean unread;
}
