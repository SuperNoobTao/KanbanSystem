package cc.superliar.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by shentao on 2016/11/10.
 */
@Data
public class UserDto {

        @NotNull
        private String account;

        @NotNull
        private String password;


}
