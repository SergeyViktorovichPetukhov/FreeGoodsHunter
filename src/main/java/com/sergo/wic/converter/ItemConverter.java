package com.sergo.wic.converter;

import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.entities.*;
import com.sergo.wic.repository.ShareItemsRepository;
import com.sergo.wic.repository.UserItemsRepository;
import com.sergo.wic.service.ItemService;
import com.sergo.wic.service.ShareService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class ItemConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ShareService shareService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserItemsRepository userItemsRepository;

    @Autowired
    private ShareItemsRepository shareItemsRepository;

    @Transactional
    public Item convertToModel(final ItemDto source, User user , Share share) {
        final Item item = new Item();
                   item.setLatitude(source.getPoint().getLatitude());
                   item.setLongitude(source.getPoint().getLongitude());
              UserItems userItems = new UserItems(user);
                   userItems.setUser(user);
                   userItems.setItem(item);
                   userItemsRepository.save(userItems);
              ShareItems shareItems = new ShareItems(share);
                   shareItems.setShare(share);
                   shareItems.setItem(item);
                   shareItemsRepository.save(shareItems);
              item.setShareItems(shareItems);
              item.setUserItems(userItems);
          itemService.save(item);
            Optional<Share> share1 = shareService.findByShareId(source.getShareId());
            share1.ifPresent((sh) ->
                    item
                            .getShareItems()
                            .setShare(sh));

        return item;
    }

    public ItemDto convertToDto(final Item source) {
        final ItemDto itemDto = new ItemDto();
        modelMapper.map(source, itemDto);
        return itemDto;
    }
//    public Response convertToItemResponse(final Item source){
//
//    }
}
