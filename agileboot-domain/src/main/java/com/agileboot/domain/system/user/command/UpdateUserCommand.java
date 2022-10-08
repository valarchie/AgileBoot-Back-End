package com.agileboot.domain.system.user.command;

import com.agileboot.domain.system.user.UserModel;
import lombok.Data;

@Data
public class UpdateUserCommand extends AddUserCommand {


    private Long userId;

    @Override
    public UserModel toModel() {
        UserModel model = super.toModel();
        model.setUserId(userId);
        return model;
    }

}
