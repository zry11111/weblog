package com.zry.weblog.search.constant;

import java.time.format.DateTimeFormatter;

public interface Constants {

    /**
     * 年-月-日 小时-分钟-秒
     */
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}