package com.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelPostDTO {

    /**
     * Stable post id for incremental synchronization.
     */
    private Long id;

    /**
     * 景点id
     */
    private Long destinationId;


    /**
     * 用户姓名
     */
    private String name;

    /**
     * 标题
     */
    private String title;

    /**
     * 文字描述
     */
    private String content;

    /**
     * 点赞数量
     */
    private Integer liked;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
