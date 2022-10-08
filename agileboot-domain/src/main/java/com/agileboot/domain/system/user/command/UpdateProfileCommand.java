package com.agileboot.domain.system.user.command;

import com.agileboot.domain.system.user.UserModel;
import lombok.Data;

@Data
public class UpdateProfileCommand {

    private Long userId;

    private Integer sex;
    private String nickName;
    private String phoneNumber;
    private String email;


    public void updateModel(UserModel model) {
        if (model != null) {
            model.setSex(sex);
            model.setNickName(nickName);
            model.setPhoneNumber(phoneNumber);
            model.setEmail(email);
        }
    }

}
