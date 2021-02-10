package com.sergo.wic.converter;

import com.sergo.wic.dto.WinningsDto;
import com.sergo.wic.entities.Winning;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WinningsConverter {
    @Autowired
    private ModelMapper modelMapper;

    public List<WinningsDto> convertAllWinnings(List<Winning> winnings){
        List<WinningsDto> result = new ArrayList<>(winnings.size());
        for(int i = 0; i < winnings.size(); i ++){
            WinningsDto winningsDto = new WinningsDto(true);
            result.add(winningsDto);
            modelMapper.map(winnings.get(i), result.get(i));
        }
        return result;
    }
}
