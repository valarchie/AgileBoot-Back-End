package com.agileboot.admin.request;

import com.agileboot.domain.system.user.RegisterUserModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户注册对象
 *
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterDTO extends LoginDTO {

    public RegisterUserModel toModel() {
        RegisterUserModel model = new RegisterUserModel();

        model.setCode(this.getCode());
        model.setUuid(this.getUuid());
        model.setUsername(this.getUsername());
        model.setPassword(this.getPassword());

        return model;
    }

}
