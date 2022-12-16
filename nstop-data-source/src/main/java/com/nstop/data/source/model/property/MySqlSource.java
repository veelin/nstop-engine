package com.nstop.data.source.model.property;

import com.nstop.common.util.Base64Util;
import com.nstop.data.source.enums.SourceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.Assert;

/**
 * @author: origindoris
 * @Title: MySqlSource
 * @Description:
 * @date: 2022/10/18 10:19
 */
@Data
@AllArgsConstructor
public class MySqlSource extends SourceProperty {

    public MySqlSource() {
        type = SourceTypeEnum.MYSQL.getType();
    }

    private String url;

    private String userName;

    private String password;

    private String driverClassName;

    private String projectName;


    @Override
    public void verifyParam() {
        super.verifyParam();
        Assert.notNull(url, "数据源链接不能为空！");
        Assert.notNull(userName, "数据源用户名不能为空！");
        Assert.notNull(password, "数据源密码不能为空！");
        Assert.notNull(driverClassName, "数据源驱动程序类名不能为空！");
        Assert.notNull(projectName, "数据源数据库名称不能为空！");
        password = Base64Util.encoder(password);

    }
}
