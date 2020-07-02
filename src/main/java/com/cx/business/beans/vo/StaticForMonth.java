package com.cx.business.beans.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
public class StaticForMonth implements Serializable {

    private Integer month;

    private Integer number;
}
