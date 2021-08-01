package com.sergo.wic.converter;

import com.sergo.wic.dto.WinningsDto;
import com.sergo.wic.entities.Winning;
import org.modelmapper.Converters;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WinningsConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public WinningsConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<WinningsDto> convertAllWinnings(List<Winning> winnings){
        List<WinningsDto> result = new ArrayList<>(winnings.size());
        for(int i = 0; i < winnings.size(); i ++){
            WinningsDto winningsDto = new WinningsDto();
            modelMapper.map(winnings.get(i), winningsDto);
            winningsDto.setCompanyId(winnings.get(i).getShare().getCompany().getLogin());
//            winningsDto.setShareId(winnings.get(i).getShare().getShareId());
            result.add(winningsDto);
        }
        return result;
    }
}
