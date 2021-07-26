package com.ssafy.donas.domain;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString
public class SigninRequest {
    @ApiModelProperty(required = true)
    @NotNull
    String email;
    
    @ApiModelProperty(required = true)
    @NotNull
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$")
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
