package com.sergo.wic.converter;

import com.sergo.wic.dto.WinningsDto;
import com.sergo.wic.entities.Winning;
import com.sergo.wic.utils.DateUtils;
import org.modelmapper.Converters;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.TemporalUnit;
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
            winningsDto.setDate(DateUtils.convertDateToStringDate("yyyy-MM-dd", winnings.get(i).getDate()));
            result.add(winningsDto);
        }
        return result;
    }
}
