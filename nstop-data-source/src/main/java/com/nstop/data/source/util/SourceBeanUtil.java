package com.nstop.data.source.util;

import com.nstop.data.source.enums.SourceTypeEnum;
import com.nstop.data.source.model.Source;
import com.nstop.data.source.model.dto.SourceDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: origindoris
 * @Title: SourceBeanUtil
 * @Description:
 * @date: 2022/11/24 14:29
 */
public class SourceBeanUtil {

    public static SourceDTO buildSourceDTO(Source source) {
        SourceDTO sourceDTO = new SourceDTO();
        BeanUtils.copyProperties(source, sourceDTO);
        SourceTypeEnum sourceType = SourceTypeEnum.getSourceType(source.getSourceType());
        if (sourceType != null) {
            sourceDTO.setSourceTypeName(sourceType.getDesc());
        }
        return sourceDTO;
    }


    public static List<SourceDTO> buildSourceDTO(List<Source> sources) {
        List<SourceDTO> list = new ArrayList<>();
        for (Source source : sources) {
            SourceDTO sourceDTO = buildSourceDTO(source);
            list.add(sourceDTO);
        }
        return list;
    }
}
