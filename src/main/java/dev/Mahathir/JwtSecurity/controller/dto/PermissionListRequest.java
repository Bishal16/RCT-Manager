package dev.Mahathir.JwtSecurity.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class PermissionListRequest {
    List<Integer> permissionIdList;
}
